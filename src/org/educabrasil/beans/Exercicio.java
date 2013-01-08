package org.educabrasil.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="exercicio")
public class Exercicio {

	@Id @GeneratedValue
	private Long idExercicio;
	
	private Integer ano;
	
	private Double orcamento;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Despesa> despesas;
	
	public Exercicio() {
	}
	
	public Exercicio(Integer ano) {
		this.ano = ano;
	}

	public Long getIdExercicio() {
		return idExercicio;
	}

	public void setIdExercicio(Long idExercicio) {
		this.idExercicio = idExercicio;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Double getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Double orcamento) {
		this.orcamento = orcamento;
	}

	public List<Despesa> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<Despesa> despesas) {
		this.despesas = despesas;
	}

}
