package com.fax.lekari.service;

import com.fax.lekari.dto.*;

import java.util.List;

public interface PregledService {
    List<PregledDtoRes> istorijaPregleda(String name) throws Exception;

    List<PregledDtoRes> dostupniTermini(String name);

    String zakaziPregled(int id, String name) throws Exception;

    String napraviTermin(PregledDTOReq pregledDTOReq, String name) throws Exception;

    int pregledZapocni(PregledDTOReq pregledDTOReq, String name, int id) throws Exception;
    List<SimpleSelectDTORes> usluge(String name) throws Exception;

    List<PregledDtoRes> radniKalendar(String name) throws Exception;

    List<PregledDtoRes> radniKalendarZaSestru(String name) throws Exception;

    PregledLightDTO getPregled(String name, int id) throws Exception;

    String addRecept(String name, int id, ReceptDTOReq receptDTOReq) throws Exception;
}
