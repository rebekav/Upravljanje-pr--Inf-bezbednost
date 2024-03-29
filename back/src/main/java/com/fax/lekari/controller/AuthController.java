package com.fax.lekari.controller;

import com.fax.lekari.dto.*;
import com.fax.lekari.model.User;
import com.fax.lekari.security.TokenUtils;
import com.fax.lekari.service.EmailService;
import com.fax.lekari.service.KorisnikService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;

@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    KorisnikService korisnikService;

    @Autowired
    TokenUtils tokenUtils;


    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginDTO user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(),
                user.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails details = userDetailsService.loadUserByUsername(user.getUsername());
        User userDb = korisnikService.findByEmail(user.getUsername());
        TokenDTO tokenRes = new TokenDTO(tokenUtils.generateToken(details));
        if (userDb.getValidiran() != 1) {
            return new ResponseEntity<>(new SimpleStringResponseDTO("Nalog nije aktivan"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(tokenRes, HttpStatus.OK);
    }

    @RequestMapping(value = "/passwordless", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> passwordless(@RequestBody LoginDTO user) {
        try{
            korisnikService.sendPasswordless(user.getUsername());
        }
        catch(Exception e){
            return  new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new SimpleStringResponseDTO("Success"), HttpStatus.OK);
    }

    @RequestMapping(value = "/passwordless/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> passwordlessCheck(@RequestBody TokenDTO body) {

        try{
            User user = korisnikService.checkPasswordless(body.getToken());
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),
                    user.getPass());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails details = userDetailsService.loadUserByUsername(user.getEmail());
            User userDb = korisnikService.findByEmail(user.getEmail());
            TokenDTO tokenRes = new TokenDTO(tokenUtils.generateToken(details));
            if (userDb.getValidiran() != 1) {
                return new ResponseEntity<>(new SimpleStringResponseDTO("Nalog nije aktivan"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(tokenRes, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/me")
    public ResponseEntity<?> getMyProfile(Principal principal) {
        try {
            KorisnikDtoRes korisnik = korisnikService.getMyProfile(principal.getName());
            return new ResponseEntity<>(korisnik, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/change-password")
    public ResponseEntity<SimpleStringResponseDTO> changePassword(@RequestBody ChangePasswordDto changePasswordDto, Principal principal) {
        try {
            korisnikService.changePassword(changePasswordDto, principal);
            return new ResponseEntity<>(new SimpleStringResponseDTO("Uspesno!"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}

