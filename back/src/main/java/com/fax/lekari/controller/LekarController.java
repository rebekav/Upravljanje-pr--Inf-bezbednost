package com.fax.lekari.controller;


import com.fax.lekari.dto.KorisnikDtoReq;
import com.fax.lekari.dto.KorisnikDtoRes;
import com.fax.lekari.dto.PregledDtoRes;
import com.fax.lekari.dto.SimpleStringResponseDTO;
import com.fax.lekari.service.KorisnikService;
import com.fax.lekari.service.PregledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/lekari")
public class LekarController {
    @Autowired
    KorisnikService korisnikService;
    @Autowired
    PregledService pregledService;
    @PostMapping
    @PreAuthorize("hasAuthority('KLINIKA_ADMIN')")
    public ResponseEntity<?> registerLekar(@RequestBody KorisnikDtoReq korisnikDto, Principal principal) {
        try {
            String poruka = korisnikService.registerLekar(korisnikDto, principal.getName());
            return new ResponseEntity<>(new SimpleStringResponseDTO(poruka), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/pacijenti")
    @PreAuthorize("hasAuthority('LEKAR')")
    public ResponseEntity<Collection<KorisnikDtoRes>> pacijentiKlinikeZaLekara(@RequestParam(value="filter", required = false) String filter, Principal principal) throws Exception {
        Collection<KorisnikDtoRes> korisnici = korisnikService.pacijentiKlinikeZaLekara(principal.getName(), filter);
        return new ResponseEntity<>(korisnici, HttpStatus.OK);
    }
    @GetMapping("/pregledi")
    @PreAuthorize("hasAuthority('LEKAR')")
    public ResponseEntity<?> radniKalendar(Principal principal) {
        try {
            List<PregledDtoRes> pregledi = pregledService.radniKalendar(principal.getName());
            return new ResponseEntity<>(pregledi, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
