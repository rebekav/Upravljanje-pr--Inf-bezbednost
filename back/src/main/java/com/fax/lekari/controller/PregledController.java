package com.fax.lekari.controller;

import com.fax.lekari.dto.*;
import com.fax.lekari.service.KlinikaService;
import com.fax.lekari.service.PregledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/pregled")
public class PregledController {

    @Autowired
    PregledService pregledService;
    @Autowired
    KlinikaService klinikaService;

    @GetMapping("/istorija")
    @PreAuthorize("hasAuthority('PACIJENT')")
    public ResponseEntity<?> istorijaPregleda(Principal principal) {
        try {
            List<PregledDtoRes> pregledi = pregledService.istorijaPregleda(principal.getName());
            return new ResponseEntity<>(pregledi, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("/radniKalendarZaSestru")
    @PreAuthorize("hasAuthority('MEDICINSKA_SESTRA')")
    public ResponseEntity<?> radniKalendarZaSestru(Principal principal) {
        try {
            List<PregledDtoRes> pregledi = pregledService.radniKalendarZaSestru(principal.getName());
            return new ResponseEntity<>(pregledi, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/termini")
    @PreAuthorize("hasAuthority('PACIJENT')")
    public ResponseEntity<?> dostupniTermini(Principal principal) {
        try {
            List<PregledDtoRes> pregledi = pregledService.dostupniTermini(principal.getName());
            return new ResponseEntity<>(pregledi, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/usluge")
    @PreAuthorize("hasAuthority('KLINIKA_ADMIN') || hasAuthority('LEKAR')")
    public ResponseEntity<?> usluge(Principal principal) {
        try {
            List<SimpleSelectDTORes> usluge = pregledService.usluge(principal.getName());
            return new ResponseEntity<>(usluge, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/zakazi/{id}")
    @PreAuthorize("hasAuthority('PACIJENT')")
    public ResponseEntity<?> zakaziPregled(@PathVariable("id") int id, Principal principal) {
        try {
            String poruka = pregledService.zakaziPregled(id, principal.getName());
            return new ResponseEntity<>(new SimpleStringResponseDTO(poruka), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/zapocni/{id}")
    @PreAuthorize("hasAuthority('LEKAR')")
    public ResponseEntity<?> zapocniPregled(@RequestBody PregledDTOReq pregledDTOReq, @PathVariable("id") int id, Principal principal) {
        try {
            int _id = pregledService.pregledZapocni(pregledDTOReq,principal.getName(), id);
            return new ResponseEntity<>(_id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('LEKAR')")
    public ResponseEntity<?> getPregled(@PathVariable("id") int id, Principal principal) {
        try {
            PregledLightDTO res = pregledService.getPregled(principal.getName(), id);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/recepti")
    @PreAuthorize("hasAuthority('LEKAR')")
    public ResponseEntity<?> createRecept(@RequestBody ReceptDTOReq receptDTOReq,@PathVariable("id") int id, Principal principal) {
        try {
            String res = pregledService.addRecept(principal.getName(), id, receptDTOReq);
            return new ResponseEntity<>(new SimpleStringResponseDTO(res), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/termini")
    @PreAuthorize("hasAuthority('KLINIKA_ADMIN')")
    public ResponseEntity<?> napraviTermin(@RequestBody PregledDTOReq pregledDTOReq, Principal principal){
        try {
            String poruka = pregledService.napraviTermin(pregledDTOReq, principal.getName());
            return new ResponseEntity<>(new SimpleStringResponseDTO(poruka), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
