package com.fax.lekari.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PregledLightDTO {
    private int id;
    private String usluga;
    private String pacijent;
    private int pacijent_id;
    private String lekar;
    private String sestra;
    private String podaciOPregledu;
    private List<ReceptDTORes> recepti;
}
