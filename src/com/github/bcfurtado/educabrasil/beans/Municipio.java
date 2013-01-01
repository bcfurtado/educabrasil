package com.github.bcfurtado.educabrasil.beans;

public class Municipio {

	private Long id;
	private String nome;
	private Long geoNameId;
	private Double latitude;
	private Double longitude;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

}
