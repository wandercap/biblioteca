package br.com.ps.biblioteca.dao.impl;

import java.util.ArrayList;

import br.com.ps.biblioteca.dao.PeriodicoDaoInterface;
import br.com.ps.biblioteca.model.Periodico;

public class PeriodicoDao implements PeriodicoDaoInterface {
	
	private static final String nomeArq = "textfiles/PeriodicosArq.dat";
	private static TextFileDao<Periodico> arq = new TextFileDao<Periodico>();

	@Override
	public Boolean cadastrarPeriodico(Periodico periodico) {
		ArrayList<Periodico> lista = new ArrayList<Periodico>(arq.getLista(nomeArq));
		
		periodico.setId(lista.size());
		
		lista.add(periodico);
		
		if(arq.setLista(lista, nomeArq))
			return true;
		else
			return false;
	}
	
	@Override
	public Boolean removerPeriodico(Periodico periodico) {
		ArrayList<Periodico> lista = new ArrayList<Periodico>(arq.getLista(nomeArq));
		Periodico p = new Periodico();
		p.setId(periodico.getId());
		
		try {
			lista.set(periodico.getId(), p);
		} catch (IndexOutOfBoundsException f) {
			return false;
		}
		
		if(arq.setLista(lista, nomeArq))
			return true;
		else
			return false;
	}

	@Override
	public Boolean editarPeriodico(Periodico periodico) {
		ArrayList<Periodico> lista = new ArrayList<Periodico>(arq.getLista(nomeArq));
		
		lista.set(periodico.getId(), periodico);
		
		if(arq.setLista(lista, nomeArq))
			return true;
		else
			return false;
	}
	
	@Override
	public ArrayList<Periodico> listarPeriodicos() {
		return new ArrayList<Periodico>(arq.getLista(nomeArq));
	}

	@Override
	public Periodico buscarPeriodico(int id) {
		ArrayList<Periodico> lista = new ArrayList<Periodico>(arq.getLista(nomeArq));
		
		if(lista.size() > id)
			return lista.get(id);
		else
			return null;
	}
	
	@Override
	public ArrayList<Periodico> buscarPeriodico(String nome) {
		ArrayList<Periodico> lista = new ArrayList<Periodico>(arq.getLista(nomeArq));
		ArrayList<Periodico> periodicos = new ArrayList<Periodico>();
		
		for(Periodico periodico : lista) {
			if(periodico.getNome().compareTo(nome) == 0)
				periodicos.add(periodico);
		}
		
		if(periodicos.isEmpty()) {
			return null;
		} else {
			return periodicos;
		}
	}

	@Override
	public Boolean emprestarPeriodico(Periodico periodico) {
		ArrayList<Periodico> lista = new ArrayList<Periodico>(arq.getLista(nomeArq));
		
		periodico.setDisponivel(false);
		
		lista.set(periodico.getId(), periodico);
		
		if(arq.setLista(lista, nomeArq))
			return true;
		else
			return false;
	}

	@Override
	public Boolean devolverPeriodico(Periodico periodico) {
		ArrayList<Periodico> lista = new ArrayList<Periodico>(arq.getLista(nomeArq));
		
		periodico.setDisponivel(true);
		
		lista.set(periodico.getId(), periodico);
		
		if(arq.setLista(lista, nomeArq))
			return true;
		else
			return false;

	}

}
