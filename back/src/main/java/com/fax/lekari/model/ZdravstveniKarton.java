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
@Table(name="zdravstveni_karton")
@Getter
@Setter
public class ZdravstveniKarton implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private byte overen;

	private String terapija;

	@Lob
	@Column(name="beleska", length=512)
	private String beleska;

	@Temporal(TemporalType.DATE)
	private Date vreme;

	//one-to-one association to Pregled
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pregled_id", referencedColumnName = "id")
	private Pregled pregled;

	public ZdravstveniKarton() {
	}
}