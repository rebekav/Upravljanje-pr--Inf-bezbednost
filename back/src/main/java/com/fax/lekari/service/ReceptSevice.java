package com.fax.lekari.service;

import com.fax.lekari.dto.ReceprFullDTO;

import java.util.List;

public interface ReceptSevice {
    List<ReceprFullDTO> sestraRecepti(String name)  throws Exception;

    String overa(int id, String name) throws Exception ;
}
