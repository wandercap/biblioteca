package br.com.ps.biblioteca.dao;

import java.util.ArrayList;

import br.com.ps.biblioteca.model.Usuario;

public interface UsuarioDaoInterface {
	
	public Boolean cadastrarUsuario(Usuario usuario);

	public Boolean removerUsuario(Usuario usuario);

	public Boolean editarUsuario(Usuario usuario);

	public ArrayList<Usuario> listarUsuarios();

	public Usuario buscarUsuario(int id);

	public Usuario buscarUsuario(String cpf);

}
