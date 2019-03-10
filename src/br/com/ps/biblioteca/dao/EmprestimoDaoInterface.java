package br.com.ps.biblioteca.dao;

import java.util.ArrayList;

import br.com.ps.biblioteca.model.Emprestimo;
import br.com.ps.biblioteca.model.Usuario;

public interface EmprestimoDaoInterface {
	
	public Boolean cadastrarEmprestimo(Emprestimo emprestimo);
	
	public Boolean removerEmprestimo(Emprestimo emprestimo);
	
	public Boolean editarEmprestimo(Emprestimo emprestimo);

	public Emprestimo buscarEmprestimo(Usuario usuario);

	public ArrayList<Emprestimo> buscarEmprestimoList(Usuario usuario);
	
	public Double calculaMulta(Emprestimo emprestimo);

	public Boolean pagarMulta(Emprestimo emprestimo);

	public Emprestimo buscarEmprestimo(Integer id);
}
