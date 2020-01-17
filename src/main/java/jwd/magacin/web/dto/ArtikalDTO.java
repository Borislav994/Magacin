package jwd.magacin.web.dto;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

public class ArtikalDTO {

	private Long id;
	private String naziv;
	@Min(0)
	private int pakovanje;
	@NotEmpty
	private String jedinicaMere;
	private int kolicina;
	private double kalkulisanaCena;
	private Long magacinId;
	private String magacinName;
	
	public ArtikalDTO() {
		super();
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

	public Long getMagacinId() {
		return magacinId;
	}

	public void setMagacinId(Long magacinId) {
		this.magacinId = magacinId;
	}

	public String getMagacinName() {
		return magacinName;
	}

	public void setMagacinName(String magacinName) {
		this.magacinName = magacinName;
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
}
