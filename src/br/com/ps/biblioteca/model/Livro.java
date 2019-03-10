package br.com.ps.biblioteca.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Livro implements Serializable {

	private Integer id;
	private String  nome;
	private String 	autor;
	private Integer ano;
	private Integer edicao;
	private String 	editora;
	private Boolean disponivel;

	public Livro() {
	}

	public String getNome() {
		return nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getEdicao() {
		return edicao;
	}

	public void setEdicao(Integer edicao) {
		this.edicao = edicao;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}
	
	public Boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

}
