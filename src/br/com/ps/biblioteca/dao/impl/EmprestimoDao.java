package br.com.ps.biblioteca.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import br.com.ps.biblioteca.dao.EmprestimoDaoInterface;
import br.com.ps.biblioteca.model.Emprestimo;
import br.com.ps.biblioteca.model.Usuario;

public class EmprestimoDao implements EmprestimoDaoInterface{
	
	private static final String nomeArq = "textfiles/EmprestimosArq.dat";
	private static TextFileDao<Emprestimo> arq = new TextFileDao<Emprestimo>();

	@Override
	public Boolean cadastrarEmprestimo(Emprestimo emprestimo) {
		ArrayList<Emprestimo> lista = new ArrayList<Emprestimo>(arq.getLista(nomeArq));
		
		emprestimo.setId(lista.size());
		
		lista.add(emprestimo);
		
		if(arq.setLista(lista, nomeArq))
			return true;
		else
			return false;
	}
	
	@Override
	public Boolean removerEmprestimo(Emprestimo emprestimo) {
		ArrayList<Emprestimo> lista = new ArrayList<Emprestimo>(arq.getLista(nomeArq));
		Emprestimo e = new Emprestimo();
		e.setId(emprestimo.getId());
		
		try {
			lista.set(emprestimo.getId(), e);
		} catch (IndexOutOfBoundsException f) {
			return false;
		}
		
		if(arq.setLista(lista, nomeArq))
			return true;
		else
			return false;
	}

	@Override
	public Boolean editarEmprestimo(Emprestimo emprestimo) {
		ArrayList<Emprestimo> lista = new ArrayList<Emprestimo>(arq.getLista(nomeArq));
		
		lista.set(emprestimo.getId(), emprestimo);
		
		if(arq.setLista(lista, nomeArq))
			return true;
		else
			return false;
	}
	
	@Override
	public Emprestimo buscarEmprestimo(Integer id) {
		ArrayList<Emprestimo> lista = new ArrayList<Emprestimo>(arq.getLista(nomeArq));
		
		for(Emprestimo emprestimo : lista) {
			if(emprestimo.getId().equals(id)) {
				return emprestimo;
			}
		}
		return null;
	}
	
	@Override
	public Emprestimo buscarEmprestimo(Usuario usuario) {
		ArrayList<Emprestimo> lista = new ArrayList<Emprestimo>(arq.getLista(nomeArq));
		
		for(Emprestimo emprestimo : lista) {
			if(emprestimo.getUsuario().getId().equals(usuario.getId())) {
				return emprestimo;
			}
		}
		return null;
	}
	
	@Override
	public ArrayList<Emprestimo> buscarEmprestimoList(Usuario usuario) {
		ArrayList<Emprestimo> lista = new ArrayList<Emprestimo>(arq.getLista(nomeArq));
		ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
		
		for(Emprestimo emprestimo : lista) {
			if(emprestimo.getUsuario() != null) {
				if(emprestimo.getUsuario().getId().equals(usuario.getId())) {
					emprestimos.add(emprestimo);
				}
			}
		}
		
		if(emprestimos.isEmpty()) {
			return null;
		} else {
			return emprestimos;
		}
	}
	
	@Override
	public Double calculaMulta(Emprestimo emprestimo) {
		Long diff;
				
		Date data = new Date();
		
		diff = data.getTime() - emprestimo.getDataDevolucao().getTime();
		
		emprestimo.setMulta(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) * 5.0);
		
		return emprestimo.getMulta();
	}
	
	@Override
	public Boolean pagarMulta(Emprestimo emprestimo) {
		return removerEmprestimo(emprestimo);
	}

}
