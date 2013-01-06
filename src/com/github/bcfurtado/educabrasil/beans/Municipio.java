package com.github.bcfurtado.educabrasil.beans;

import java.util.HashMap;
import java.util.Map;

public class Municipio {

	private String id;
	private String nome;
	private Long geoNameId;
	private Double latitude;
	private Double longitude;

	private Map<Integer, Exercicio> exercicios;

	public Municipio() {
		exercicios = new HashMap<Integer, Exercicio>();
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

	public Map<Integer, Exercicio> getExercicios() {
		return exercicios;
	}

	public void setExercicios(Map<Integer, Exercicio> exercicios) {
		this.exercicios = exercicios;
	}

}
