package com.fax.lekari.controller;

import com.fax.lekari.dto.*;
import com.fax.lekari.service.KlinikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/klinike")
public class KlinikaController {

    @Autowired
    KlinikaService klinikaService;

    @GetMapping
    @PreAuthorize("hasAuthority('SUPER_ADMIN') || hasAuthority('PACIJENT')")
    public ResponseEntity<List<KlinikaDtoRes>> findAll() {
        List<KlinikaDtoRes> klinike = klinikaService.findAll();
        return new ResponseEntity<>(klinike, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<?> createKlinika(@RequestBody KlinikaDtoReq klinikaDtoReq) {
        try {
            String poruka = klinikaService.createKlinika(klinikaDtoReq);
            return new ResponseEntity<>(new SimpleStringResponseDTO(poruka), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/sestre")
    @PreAuthorize("hasAuthority('KLINIKA_ADMIN') || hasAuthority('LEKAR')")
    public ResponseEntity<?> usluge(Principal principal) {
        try {
            List<SimpleSelectDTORes> usluge = klinikaService.getSestre(principal.getName());
            return new ResponseEntity<>(usluge, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
