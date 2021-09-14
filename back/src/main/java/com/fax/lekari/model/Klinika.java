package com.fax.lekari.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the klinika database table.
 * 
 */
@Entity
@Table(name="klinika")
@Getter
@Setter
public class Klinika implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String adresa;

	private String naziv;

	private String opis;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="klinika")
	private List<User> users;

	//bi-directional many-to-one association to Usluga
	@OneToMany(mappedBy="klinika")
	private List<Usluga> uslugas;

	public Klinika() {
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setKlinika(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setKlinika(null);

		return user;
	}

	public Usluga addUsluga(Usluga usluga) {
		getUslugas().add(usluga);
		usluga.setKlinika(this);

		return usluga;
	}

	public Usluga removeUsluga(Usluga usluga) {
		getUslugas().remove(usluga);
		usluga.setKlinika(null);

		return usluga;
	}

}