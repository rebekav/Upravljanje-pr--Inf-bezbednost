package com.fax.lekari.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ocena database table.
 * 
 */
@Entity
@Table(name="ocena")
@Getter
@Setter
public class Ocena implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "ocena_klinike")
	private int ocenaKlinike;

	@Column(name = "ocena_lekara")
	private int ocenaLekara;

	//one-to-one association to Pregled
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pregled_id", referencedColumnName = "id")
	private Pregled pregled;

	public Ocena() {
	}
}