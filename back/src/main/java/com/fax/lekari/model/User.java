package com.fax.lekari.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@Getter
@Setter
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String adresa;

	private String email;

	private String identifikator;

	private String ime;

	private String pass;

	private String prezime;

	private String telefon;

	private byte validiran;

	@Temporal(TemporalType.DATE)
	private Date expire;

	//bi-directional many-to-one association to Pregled
	@OneToMany(mappedBy="lekar")
	private List<Pregled> preglediZaLekara;

	//bi-directional many-to-one association to Pregled
	@OneToMany(mappedBy="medicinskaSestra")
	private List<Pregled> preglediZaMedicinskuSestru;

	//bi-directional many-to-one association to Pregled
	@OneToMany(mappedBy="pacijent")
	private List<Pregled> preglediZaPacijenta;

	//bi-directional many-to-many association to Role
	@ManyToMany
	@JoinTable(
		name="role_has_user"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="role_id")
			}
		)
	private List<Role> roles;

	//bi-directional many-to-one association to Klinika
	@ManyToOne
	private Klinika klinika;

	public User() {
	}


	public Pregled addPreglediZaLekara(Pregled preglediZaLekara) {
		getPreglediZaLekara().add(preglediZaLekara);
		preglediZaLekara.setLekar(this);

		return preglediZaLekara;
	}

	public Pregled removePreglediZaLekara(Pregled preglediZaLekara) {
		getPreglediZaLekara().remove(preglediZaLekara);
		preglediZaLekara.setLekar(null);

		return preglediZaLekara;
	}


	public Pregled addPreglediZaMedicinskuSestru(Pregled preglediZaMedicinskuSestru) {
		getPreglediZaMedicinskuSestru().add(preglediZaMedicinskuSestru);
		preglediZaMedicinskuSestru.setMedicinskaSestra(this);

		return preglediZaMedicinskuSestru;
	}

	public Pregled removePreglediZaMedicinskuSestru(Pregled preglediZaMedicinskuSestru) {
		getPreglediZaMedicinskuSestru().remove(preglediZaMedicinskuSestru);
		preglediZaMedicinskuSestru.setMedicinskaSestra(null);

		return preglediZaMedicinskuSestru;
	}


	public Pregled addPreglediZaPacijenta(Pregled preglediZaPacijenta) {
		getPreglediZaPacijenta().add(preglediZaPacijenta);
		preglediZaPacijenta.setPacijent(this);

		return preglediZaPacijenta;
	}

	public Pregled removePreglediZaPacijenta(Pregled preglediZaPacijenta) {
		getPreglediZaPacijenta().remove(preglediZaPacijenta);
		preglediZaPacijenta.setPacijent(null);

		return preglediZaPacijenta;
	}
}