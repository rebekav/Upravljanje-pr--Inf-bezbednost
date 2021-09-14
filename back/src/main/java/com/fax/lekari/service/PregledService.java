package com.fax.lekari.service;

import com.fax.lekari.dto.PregledDTOReq;
import com.fax.lekari.dto.PregledDtoRes;
import com.fax.lekari.dto.SimpleSelectDTORes;

import java.util.List;

public interface PregledService {
    List<PregledDtoRes> istorijaPregleda(String name) throws Exception;

    List<PregledDtoRes> dostupniTermini(String name);

    String zakaziPregled(int id, String name) throws Exception;

    String napraviTermin(PregledDTOReq pregledDTOReq, String name) throws Exception;


    List<SimpleSelectDTORes> usluge(String name) throws Exception;

    List<PregledDtoRes> radniKalendar(String name) throws Exception;

    List<PregledDtoRes> radniKalendarZaSestru(String name) throws Exception;
}
