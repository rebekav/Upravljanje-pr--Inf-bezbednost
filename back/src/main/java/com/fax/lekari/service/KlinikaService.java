package com.fax.lekari.service;

import com.fax.lekari.dto.KlinikaDtoReq;
import com.fax.lekari.dto.KlinikaDtoRes;
import com.fax.lekari.dto.SimpleSelectDTORes;

import java.util.List;

public interface KlinikaService {
    List<KlinikaDtoRes> findAll();

    String createKlinika(KlinikaDtoReq klinikaDtoReq);
    List<SimpleSelectDTORes> lekari(String name) throws Exception;

    List<SimpleSelectDTORes> medicinskeSestre(String name) throws Exception;
}
