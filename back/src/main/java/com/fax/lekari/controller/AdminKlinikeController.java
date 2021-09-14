package com.fax.lekari.controller;

import com.fax.lekari.dto.KorisnikDtoReq;
import com.fax.lekari.dto.SimpleSelectDTORes;
import com.fax.lekari.dto.SimpleStringResponseDTO;
import com.fax.lekari.service.KlinikaService;
import com.fax.lekari.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/adminklinike")
public class AdminKlinikeController {
    @Autowired
    KorisnikService korisnikService;
    @Autowired
    KlinikaService klinikaService;

    @PostMapping
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<?> registerKlinikaAdmin(@RequestBody KorisnikDtoReq korisnikDto) {
        try {
            String poruka = korisnikService.registerKlinikaAdmin(korisnikDto);
            return new ResponseEntity<>(new SimpleStringResponseDTO(poruka), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/lekari")
    @PreAuthorize("hasAuthority('KLINIKA_ADMIN')")
    public ResponseEntity<?> lekari(Principal principal) {
        try {
            List<SimpleSelectDTORes> lekari = klinikaService.lekari(principal.getName());
            return new ResponseEntity<>(lekari, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/sestre")
    @PreAuthorize("hasAuthority('KLINIKA_ADMIN')")
    public ResponseEntity<?> medicinskeSestre(Principal principal) {
        try {
            List<SimpleSelectDTORes> medicinskeSestre = klinikaService.medicinskeSestre(principal.getName());
            return new ResponseEntity<>(medicinskeSestre, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
