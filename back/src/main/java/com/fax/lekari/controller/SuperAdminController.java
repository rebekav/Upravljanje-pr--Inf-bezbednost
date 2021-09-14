package com.fax.lekari.controller;

import com.fax.lekari.dto.KorisnikDtoReq;
import com.fax.lekari.dto.SimpleStringResponseDTO;
import com.fax.lekari.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/superadmini")
public class SuperAdminController {
    @Autowired
    KorisnikService korisnikService;


    @PostMapping
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<?> registerSuperAdmin(@RequestBody KorisnikDtoReq korisnikDto) {
        try {
            String poruka = korisnikService.registerSuperAdmin(korisnikDto);
            return new ResponseEntity<>(new SimpleStringResponseDTO(poruka), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
