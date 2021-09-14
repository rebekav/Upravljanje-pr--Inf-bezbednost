package com.fax.lekari.controller;

import com.fax.lekari.dto.KorisnikDtoReq;
import com.fax.lekari.dto.SimpleStringResponseDTO;
import com.fax.lekari.dto.ZdravstveniKartonDtoRes;
import com.fax.lekari.service.KorisnikService;
import com.fax.lekari.service.ZdravstveniKartonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "pacijent")
public class PacijentController {
    @Autowired
    KorisnikService korisnikService;
    @Autowired
    ZdravstveniKartonService zdravstveniKartonService;

    @PostMapping
    public ResponseEntity<?> registerPacijent(@RequestBody KorisnikDtoReq korisnikDto) {
        try {
            String poruka = korisnikService.registerPacijent(korisnikDto);
            return new ResponseEntity<>(new SimpleStringResponseDTO(poruka), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/karton")
    @PreAuthorize("hasAuthority('LEKAR') || hasAuthority('MEDICINSKA_SESTRA')")
    public ResponseEntity<?> kartonPacijenta(@PathVariable("id") int id, Principal principal) {
        try {
            List<ZdravstveniKartonDtoRes> logovi = zdravstveniKartonService.kartonPacijenta(id, principal.getName());
            return new ResponseEntity<>(logovi, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
