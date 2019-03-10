package br.com.ps.biblioteca.dao.impl;

import java.util.ArrayList;

import br.com.ps.biblioteca.dao.UsuarioDaoInterface;
import br.com.ps.biblioteca.model.Usuario;

public class UsuarioDao implements UsuarioDaoInterface {
	
	private static final String nomeArq = "textfiles/UsuariosArq.dat";
	private static TextFileDao<Usuario> arq = new TextFileDao<Usuario>();

	@Override
	public Boolean cadastrarUsuario(Usuario Usuario) {
		ArrayList<Usuario> lista = new ArrayList<Usuario>(arq.getLista(nomeArq));
		
		Usuario.setId(lista.size());
		
		lista.add(Usuario);
		
		if(arq.setLista(lista, nomeArq))
			return true;
		else
			return false;
	}
	
	@Override
	public Boolean removerUsuario(Usuario usuario) {
		ArrayList<Usuario> lista = new ArrayList<Usuario>(arq.getLista(nomeArq));
		Usuario u = new Usuario();
		u.setId(usuario.getId());
		
		try {
			lista.set(usuario.getId(), u);
		} catch (IndexOutOfBoundsException f) {
			return false;
		}
		
		if(arq.setLista(lista, nomeArq))
			return true;
		else
			return false;
	}

	@Override
	public Boolean editarUsuario(Usuario usuario) {
		ArrayList<Usuario> lista = new ArrayList<Usuario>(arq.getLista(nomeArq));
		
		lista.set(usuario.getId(), usuario);
		
		if(arq.setLista(lista, nomeArq))
			return true;
		else
			return false;
	}
	
	@Override
	public ArrayList<Usuario> listarUsuarios() {
		return new ArrayList<Usuario>(arq.getLista(nomeArq));
	}

	@Override
	public Usuario buscarUsuario(int id) {
		ArrayList<Usuario> lista = new ArrayList<Usuario>(arq.getLista(nomeArq));
		
		if(lista.get(id) != null)
			return lista.get(id);
		else
			return null;
	}
	
	@Override
	public Usuario buscarUsuario(String cpf) {
		ArrayList<Usuario> lista = new ArrayList<Usuario>(arq.getLista(nomeArq));
		
		for(Usuario usuario : lista) {
			if(usuario.getCpf().compareTo(cpf) == 0) {
				return usuario;
			}
		}
		return null;
	}

}
