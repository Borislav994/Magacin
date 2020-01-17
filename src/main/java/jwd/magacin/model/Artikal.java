package jwd.magacin.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tblArtikal")
public class Artikal {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	@Column(name = "naziv", nullable = false)
	private String naziv;
	@Column(name = "pakovanje", nullable = false)
	private int pakovanje;
	@Column(name = "jedMere")
	private String jedinicaMere;
	@Column(name = "kolicina")
	private int kolicina;
	@Column(name = "kalkCena")
	private double kalkulisanaCena;
	@ManyToOne(fetch = FetchType.EAGER)
	private Magacin magacin;
	@OneToMany(mappedBy = "artikal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<StavkaPrijema> stavkePrijema = new ArrayList<>();

	public Artikal() {
		super();
	}

	public List<StavkaPrijema> getStavkePrijema() {
		return stavkePrijema;
	}

	public void setStavkePrijema(List<StavkaPrijema> stavkePrijema) {
		this.stavkePrijema = stavkePrijema;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getPakovanje() {
		return pakovanje;
	}

	public void setPakovanje(int pakovanje) {
		this.pakovanje = pakovanje;
	}

	public String getJedinicaMere() {
		return jedinicaMere;
	}

	public void setJedinicaMere(String jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public double getKalkulisanaCena() {
		return kalkulisanaCena;
	}

	public void setKalkulisanaCena(double kalkulisanaCena) {
		this.kalkulisanaCena = kalkulisanaCena;
	}

	public Magacin getMagacin() {
		return magacin;
	}

	public void setMagacin(Magacin magacin) {
		this.magacin = magacin;
		if (!magacin.getArtikli().contains(this)) {
			magacin.getArtikli().add(this);
		}
	}

}
