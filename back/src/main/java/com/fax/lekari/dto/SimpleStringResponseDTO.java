package com.fax.lekari.dto;

public class SimpleStringResponseDTO {
    private String poruka;

    public SimpleStringResponseDTO(String poruka) {
        this.poruka = poruka;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }
}
