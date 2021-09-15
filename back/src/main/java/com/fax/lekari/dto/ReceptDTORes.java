package com.fax.lekari.dto;

import com.fax.lekari.model.Recept;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceptDTORes {
    private String naziv;
    private String napomena;
    private int id;
    public ReceptDTORes(){

    }
    public ReceptDTORes(Recept r){
        this.setId(r.getId());
        this.setNaziv(r.getNaziv());
        this.setNapomena(r.getNapomena());

    }
}
