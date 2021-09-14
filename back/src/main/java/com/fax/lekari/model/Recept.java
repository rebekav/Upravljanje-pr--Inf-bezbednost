package com.fax.lekari.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the zdravstveni_karton database table.
 *
 */
@Entity
@Table(name="recept")
@Getter
@Setter
public class Recept implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private boolean overen;

    private String naziv;

    //one-to-one association to Pregled
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pregled_id", referencedColumnName = "id")
    private Pregled pregled;
}