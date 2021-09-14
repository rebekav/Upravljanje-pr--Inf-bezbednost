package com.fax.lekari.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class KorisnikDtoRes implements Comparable<KorisnikDtoRes>{
    private int id;

    private String adresa;

    private String email;

    private String identifikator;

    private String ime;

    private String pass;

    private String prezime;

    private String telefon;

    private byte validiran;

    private boolean changePass;

    private KlinikaDtoRes klinikaDtoRes;

    private List<String> roles;

    @Override
    public int compareTo(KorisnikDtoRes korisnikDtoRes) {
        return this.getIme().compareTo((korisnikDtoRes).getIme());
    }
}
