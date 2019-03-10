package br.com.ps.biblioteca.dao;

import java.util.ArrayList;

import br.com.ps.biblioteca.model.Periodico;

public interface PeriodicoDaoInterface {
	
	public Boolean cadastrarPeriodico(Periodico periodico);
	
	public Boolean emprestarPeriodico(Periodico periodico);
	
	public Boolean devolverPeriodico(Periodico periodico);

	public Boolean removerPeriodico(Periodico periodico);

	public Boolean editarPeriodico(Periodico periodico);

	public ArrayList<Periodico> listarPeriodicos();

	public Periodico buscarPeriodico(int id);

	public ArrayList<Periodico> buscarPeriodico(String nome);
}
