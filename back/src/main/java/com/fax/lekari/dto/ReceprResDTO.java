package com.fax.lekari.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceprResDTO {
    private int id;

    private boolean overen;

    private String naziv;

    private PregledDtoRes pregled;
}
