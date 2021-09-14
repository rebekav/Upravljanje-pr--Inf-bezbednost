package com.fax.lekari.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KorisnikDtoReq {
    private String identifikator;

    private String pass;

    private String adresa;

    private String telefon;

    private String ime;

    private String prezime;

    private String email;

    private Integer idKlinika;

}
