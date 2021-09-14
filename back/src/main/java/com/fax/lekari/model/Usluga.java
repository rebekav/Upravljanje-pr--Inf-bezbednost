package com.fax.lekari.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usluga database table.
 * 
 */
@Entity
@Table(name="usluga")
@Getter
@Setter
public class Usluga implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int cena;

	private String naziv;

	//bi-directional many-to-one association to Pregled
	@OneToMany(mappedBy="usluga")
	private List<Pregled> pregleds;

	//bi-directional many-to-one association to Klinika
	@ManyToOne
	private Klinika klinika;

	public Usluga() {
	}

	public Pregled addPregled(Pregled pregled) {
		getPregleds().add(pregled);
		pregled.setUsluga(this);

		return pregled;
	}

	public Pregled removePregled(Pregled pregled) {
		getPregleds().remove(pregled);
		pregled.setUsluga(null);

		return pregled;
	}

}