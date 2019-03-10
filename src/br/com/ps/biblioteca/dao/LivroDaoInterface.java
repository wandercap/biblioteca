package br.com.ps.biblioteca.dao;
import java.util.ArrayList;

import br.com.ps.biblioteca.model.Livro;

public interface LivroDaoInterface {

	public Boolean cadastrarLivro(Livro livro);
	
	public Boolean emprestarLivro(Livro livro);
	
	public Boolean devolverLivro(Livro livro);

	public Boolean removerLivro(Livro livro);

	public Boolean editarLivro(Livro livro);

	public ArrayList<Livro> listarLivros();

	public Livro buscarLivro(int id);

	public ArrayList<Livro> buscarLivroNome(String nome);

	public ArrayList<Livro> buscarLivroAutor(String autor);
}
