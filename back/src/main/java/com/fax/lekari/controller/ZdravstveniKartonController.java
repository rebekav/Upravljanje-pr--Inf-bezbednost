package com.fax.lekari.controller;


import com.fax.lekari.dto.*;
import com.fax.lekari.service.ZdravstveniKartonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/karton")
public class ZdravstveniKartonController {

    @Autowired
    ZdravstveniKartonService zdravstveniKartonService;

    @GetMapping
    public ResponseEntity<?> mojKarton(Principal principal) {
        try {
            List<ZdravstveniKartonDtoRes> logovi = zdravstveniKartonService.mojKarton(principal.getName());
            return new ResponseEntity<>(logovi, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/dodajBelesku")
    @PreAuthorize("hasAuthority('LEKAR')")
    public ResponseEntity<?> dodajBelesku(@RequestBody ZdravstveniKartonDtoReq zdravstveniKartonDtoReq, Principal principal) {
        try {
            String poruka = zdravstveniKartonService.dodajBelesku(zdravstveniKartonDtoReq, principal.getName());
            return new ResponseEntity<>(new SimpleStringResponseDTO(poruka), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/overa/{id}")
    @PreAuthorize("hasAuthority('MEDICINSKA_SESTRA')")
    public ResponseEntity<?> overa(@PathVariable("id") int id, Principal principal) {
        try {
            String poruka = zdravstveniKartonService.overa(id, principal.getName());
            return new ResponseEntity<>(new SimpleStringResponseDTO(poruka), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
