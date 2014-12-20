package org.educabrasil.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="municipio")
public class Municipio {

	@Id @GeneratedValue
	private Long idMunicipio;
	
	private String id;
	private String nome;
	private Long geoNameId;
	private Double latitude;
	private Double longitude;

	@OneToMany(cascade=CascadeType.ALL)
	private List<Orcamento> orcamentos = new ArrayList<Orcamento>();

	@OneToMany(cascade=CascadeType.ALL)
	private List<Despesa> despesas = new ArrayList<Despesa>();
	
	public Municipio() {		
	}

	public Long getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Long idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getGeoNameId() {
		return geoNameId;
	}

	public void setGeoNameId(Long geoNameId) {
		this.geoNameId = geoNameId;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public List<Orcamento> getOrcamentos() {
		return orcamentos;
	}

	public void setOrcamentos(List<Orcamento> orcamentos) {
		this.orcamentos = orcamentos;
	}

	public List<Despesa> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<Despesa> despesas) {
		this.despesas = despesas;
	}

	
	
}
