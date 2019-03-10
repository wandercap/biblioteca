package br.com.ps.biblioteca.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("serial")
public class Emprestimo implements Serializable {

	private Integer id;
	private Usuario usuario;
	private Date dataEmprestimo;
	private Date dataDevolucao;
	private ArrayList<Livro> livros = new ArrayList<Livro>();
	private ArrayList<Periodico> periodicos = new ArrayList<Periodico>();
	private Double multa;
	private Integer numObras;
	private Boolean disponivel;
	
	public Emprestimo() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public void addPeriodico(Periodico periodico) {
		this.periodicos.add(periodico);
	}
	
	public void addLivro(Livro livro) {
		this.livros.add(livro);
	}
	
	public ArrayList<Livro> getLivros() {
		return livros;
	}

	public void setLivros(ArrayList<Livro> livros) {
		this.livros = livros;
	}

	public ArrayList<Periodico> getPeriodicos() {
		return periodicos;
	}

	public void setPeriodicos(ArrayList<Periodico> periodicos) {
		this.periodicos = periodicos;
	}

	public Double getMulta() {
		return multa;
	}

	public void setMulta(Double multa) {
		this.multa = multa;
	}

	public Integer getNumObras() {
		return numObras;
	}

	public void setNumObras(Integer numObras) {
		this.numObras = numObras;
	}

	public Boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

}
