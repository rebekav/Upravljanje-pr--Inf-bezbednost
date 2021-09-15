package com.fax.lekari.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceprFullDTO {
    private int id;

    private boolean overen;

    private String naziv;
    private String napomena;

    private PregledDtoRes pregled;
}
