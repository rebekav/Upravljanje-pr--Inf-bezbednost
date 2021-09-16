package com.fax.lekari.service;

import com.fax.lekari.dto.ZdravstveniKartonDtoReq;
import com.fax.lekari.dto.ZdravstveniKartonDtoRes;

import java.util.List;

public interface ZdravstveniKartonService {
    List<ZdravstveniKartonDtoRes> mojKarton(String name) throws Exception;

    String dodajBelesku(ZdravstveniKartonDtoReq zdravstveniKartonDtoReq, String name) throws Exception;

    List<ZdravstveniKartonDtoRes> kartonPacijenta(int id, String name) throws Exception;

}
