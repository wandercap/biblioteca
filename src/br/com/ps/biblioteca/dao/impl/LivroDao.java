package br.com.ps.biblioteca.dao.impl;

import java.util.ArrayList;

import br.com.ps.biblioteca.dao.LivroDaoInterface;
import br.com.ps.biblioteca.model.Livro;

public class LivroDao implements LivroDaoInterface {
	
	private static final String nomeArq = "textfiles/livrosArq.dat";
	private static TextFileDao<Livro> arq = new TextFileDao<Livro>();

	@Override
	public Boolean cadastrarLivro(Livro livro) {
		ArrayList<Livro> lista = new ArrayList<Livro>(arq.getLista(nomeArq));
		
		livro.setId(lista.size());
		
		lista.add(livro);
		
		if(arq.setLista(lista, nomeArq))
			return true;
		else
			return false;
	}
	
	@Override
	public Boolean removerLivro(Livro livro) {
		ArrayList<Livro> lista = new ArrayList<Livro>(arq.getLista(nomeArq));
		Livro l = new Livro();
		l.setId(livro.getId());
		
		try {
			lista.set(livro.getId(), l);
		} catch (IndexOutOfBoundsException f) {
			return false;
		}
		
		if(arq.setLista(lista, nomeArq))
			return true;
		else
			return false;
	}

	@Override
	public Boolean editarLivro(Livro livro) {
		ArrayList<Livro> lista = new ArrayList<Livro>(arq.getLista(nomeArq));
		
		lista.set(livro.getId(), livro);
		
		if(arq.setLista(lista, nomeArq))
			return true;
		else
			return false;
	}
	
	@Override
	public ArrayList<Livro> listarLivros() {
		return new ArrayList<Livro>(arq.getLista(nomeArq));
	}

	@Override
	public Livro buscarLivro(int id) {
		ArrayList<Livro> lista = new ArrayList<Livro>(arq.getLista(nomeArq));
		
		if(lista.size() > id)
			return lista.get(id);
		else
			return null;
	}
	
	@Override
	public ArrayList<Livro> buscarLivroNome(String nome) {
		ArrayList<Livro> lista = new ArrayList<Livro>(arq.getLista(nomeArq));
		ArrayList<Livro> livros = new ArrayList<Livro>();
		
		for(Livro livro : lista) {
			if(livro.getNome().compareTo(nome) == 0)
				livros.add(livro);
		}
		
		if(livros.isEmpty()) {
			return null;
		} else {
			return livros;
		}
	}
	
	@Override
	public ArrayList<Livro> buscarLivroAutor(String autor) {
		ArrayList<Livro> lista = new ArrayList<Livro>(arq.getLista(nomeArq));
		ArrayList<Livro> livros = new ArrayList<Livro>();
		
		for(Livro livro : lista) {
			if(livro.getAutor().compareTo(autor) == 0)
				livros.add(livro);
		}
		
		if(livros.isEmpty()) {
			return null;
		} else {
			return livros;
		}
	}
	

	@Override
	public Boolean emprestarLivro(Livro livro) {
		ArrayList<Livro> lista = new ArrayList<Livro>(arq.getLista(nomeArq));
		
		livro.setDisponivel(false);
		
		lista.set(livro.getId(), livro);
		
		if(arq.setLista(lista, nomeArq))
			return true;
		else
			return false;
	}

	@Override
	public Boolean devolverLivro(Livro livro) {
		ArrayList<Livro> lista = new ArrayList<Livro>(arq.getLista(nomeArq));
		
		livro.setDisponivel(true);
		
		lista.set(livro.getId(), livro);
		
		if(arq.setLista(lista, nomeArq))
			return true;
		else
			return false;

	}

}
