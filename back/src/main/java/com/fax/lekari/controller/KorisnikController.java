package com.fax.lekari.controller;

import com.fax.lekari.dto.*;
import com.fax.lekari.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/korisnik")
public class KorisnikController {

    @Autowired
    KorisnikService korisnikService;


    @GetMapping
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<List<KorisnikDtoRes>> findAll() {
        List<KorisnikDtoRes> korisnici = korisnikService.findAllUsers();
        return new ResponseEntity<>(korisnici, HttpStatus.OK);
    }

    @GetMapping("/roles")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<List<SimpleSelectDTORes>> roles() {
        List<SimpleSelectDTORes> roles = korisnikService.roles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PostMapping("/addRole")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<?> addRole(@RequestBody AddRoleDtoReq addRoleDtoReq) {
        try {
            String poruka = korisnikService.addRole(addRoleDtoReq);
            return new ResponseEntity<>(new SimpleStringResponseDTO(poruka), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/select")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<List<SimpleSelectDTORes>> users() {
        List<SimpleSelectDTORes> users = korisnikService.users();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @PutMapping("/ban/{id}")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<?> ban(@PathVariable("id") int id) {
        try {
            String poruka = korisnikService.ban(id);
            return new ResponseEntity<>(new SimpleStringResponseDTO(poruka), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/unban/{id}")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<?> unban(@PathVariable("id") int id) {
        try {
            String poruka = korisnikService.unban(id);
            return new ResponseEntity<>(new SimpleStringResponseDTO(poruka), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit")
    @PreAuthorize("hasAuthority('PACIJENT') || hasAuthority('LEKAR') || hasAuthority('MEDICINSKA_SESTRA')")
    public ResponseEntity<?> editProfile(@RequestBody KorisnikEditDtoReq korisnikDto, Principal principal) {
        try {
            String poruka = korisnikService.editProfile(korisnikDto, principal.getName());
            return new ResponseEntity<>(new SimpleStringResponseDTO(poruka), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
