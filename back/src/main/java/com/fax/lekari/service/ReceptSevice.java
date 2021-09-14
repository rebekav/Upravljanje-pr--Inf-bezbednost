package com.fax.lekari.service;

import com.fax.lekari.dto.ReceprResDTO;

import java.util.List;

public interface ReceptSevice {
    List<ReceprResDTO> sestraRecepti(String name)  throws Exception;

    String overa(int id, String name) throws Exception ;
}
