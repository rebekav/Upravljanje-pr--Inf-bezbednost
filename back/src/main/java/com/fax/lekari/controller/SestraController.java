package com.fax.lekari.controller;

import com.fax.lekari.dto.KorisnikDtoReq;
import com.fax.lekari.dto.KorisnikDtoRes;
import com.fax.lekari.dto.ReceprResDTO;
import com.fax.lekari.dto.SimpleStringResponseDTO;
import com.fax.lekari.service.KorisnikService;
import com.fax.lekari.service.ReceptSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/sestre")
public class SestraController {

    @Autowired
    KorisnikService korisnikService;


    @Autowired
    ReceptSevice receptSevice;
    @PostMapping
    @PreAuthorize("hasAuthority('KLINIKA_ADMIN')")
    public ResponseEntity<?> registerSestra(@RequestBody KorisnikDtoReq korisnikDto, Principal principal) {
        try {
            String poruka = korisnikService.registerSestra(korisnikDto, principal.getName());
            return new ResponseEntity<>(new SimpleStringResponseDTO(poruka), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/pacijenti")
    @PreAuthorize("hasAuthority('MEDICINSKA_SESTRA')")
    public ResponseEntity<Collection<KorisnikDtoRes>> pacijentiKlinikeZaSestru(@RequestParam(defaultValue = "") String orderBy,
                                                                               @RequestParam(defaultValue = "") String direction, Principal principal) throws Exception {
        Collection<KorisnikDtoRes> korisnici = korisnikService.pacijentiKlinikeZaSestru(principal.getName(),orderBy, direction);
        return new ResponseEntity<>(korisnici, HttpStatus.OK);
    }


    @GetMapping("/recepti")
    @PreAuthorize("hasAuthority('MEDICINSKA_SESTRA')")
    public ResponseEntity<List<ReceprResDTO>> recepti(Principal principal) throws Exception {
        List<ReceprResDTO> korisnici = receptSevice.sestraRecepti(principal.getName());
        return new ResponseEntity<>(korisnici, HttpStatus.OK);
    }

    @PutMapping("/recepti/overa/{id}")
    @PreAuthorize("hasAuthority('MEDICINSKA_SESTRA')")
    public ResponseEntity<?> overa(@PathVariable("id") int id, Principal principal) {
        try {
            String poruka = receptSevice.overa(id, principal.getName());
            return new ResponseEntity<>(new SimpleStringResponseDTO(poruka), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleStringResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
