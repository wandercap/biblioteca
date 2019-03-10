package br.com.ps.biblioteca.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Periodico implements Serializable {

	private Integer id;
	private String 	nome;
	private Integer volume;
	private Integer	mes;
	private Integer ano;
	private Boolean disponivel;
	
	public Periodico() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Integer getVolume() {
		return volume;
	}
	
	public void setVolume(Integer volume) {
		this.volume = volume;
	}
	
	public Integer getMes() {
		return mes;
	}
	
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	
	public Integer getAno() {
		return ano;
	}
	
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	
	public Boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

}
