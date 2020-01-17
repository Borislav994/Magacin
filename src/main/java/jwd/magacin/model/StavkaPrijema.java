package jwd.magacin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tblStavkaPrijema")
public class StavkaPrijema {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	@Column(name = "kolicina")
	private int kolicina;
	@Column(name = "jedCena")
	private int jedinicnaCena;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	private Artikal artikal;

	public StavkaPrijema() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public double getJedinicnaCena() {
		return jedinicnaCena;
	}

	public void setJedinicnaCena(int jedinicnaCena) {
		this.jedinicnaCena = jedinicnaCena;
	}

	public Artikal getArtikal() {
		return artikal;
	}

	public void setArtikal(Artikal artikal) {
		this.artikal = artikal;
	}

}
