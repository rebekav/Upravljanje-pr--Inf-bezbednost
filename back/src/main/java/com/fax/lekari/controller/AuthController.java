package com.fax.lekari.controller;

import com.fax.lekari.dto.*;
import com.fax.lekari.model.User;
import com.fax.lekari.security.TokenUtils;
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
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
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
        if (userDb.getExpire()!=null && userDb.getExpire().before(new Date())){
            return new ResponseEntity<>(new SimpleStringResponseDTO("Vreme za promenu passworda isteklo"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(tokenRes, HttpStatus.OK);
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

