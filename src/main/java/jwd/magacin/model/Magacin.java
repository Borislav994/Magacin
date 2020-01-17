package jwd.magacin.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="tblMagacin")
public class Magacin {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	@Column(name = "naziv" ,nullable=false, unique=true)
	private String naziv;
	@Column(name = "adresa", nullable=false)
	private String adresa;
	@OneToMany(mappedBy="magacin", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<Artikal> artikli = new ArrayList<>();
	
	public Magacin() {
		super();
	}

	public List<Artikal> getArtikli() {
		return artikli;
	}

	public void setArtikli(List<Artikal> artikli) {
		this.artikli = artikli;
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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	
	public void addArtikal(Artikal artikal){
		this.artikli.add(artikal);
		if(artikal.getMagacin()!=null && !artikal.getMagacin().equals(this)){
			artikal.setMagacin(this);
		}
	}

}
