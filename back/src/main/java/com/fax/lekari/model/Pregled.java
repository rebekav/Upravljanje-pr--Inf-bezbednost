package com.fax.lekari.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the pregled database table.
 * 
 */
@Entity
@Table(name="pregled")
@Getter
@Setter
public class Pregled implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="podaci_o_pregledu")
	private String podaciOPregledu;

	private int popust;

	private int trajanje;

	@Temporal(TemporalType.TIMESTAMP)
	private Date vreme;


	//one-to-one association to Ocena
	@OneToOne(mappedBy = "pregled")
	private Ocena ocena;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="lekar")
	private User lekar;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="meidicnska_sestra")
	private User medicinskaSestra;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="pacijent")
	private User pacijent;

	//bi-directional many-to-one association to Usluga
	@ManyToOne
	private Usluga usluga;

	//one-to-one association to ZdravstveniKarton
	@OneToOne(mappedBy = "pregled")
	private ZdravstveniKarton zdravstveniKartons;


	@OneToMany(mappedBy = "pregled")
	private List<Recept> recepti;

	public Pregled() {
	}

}