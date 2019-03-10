package br.com.ps.biblioteca.dao.impl;

import java.util.ArrayList;

import br.com.ps.biblioteca.dao.OperadorDaoInterface;
import br.com.ps.biblioteca.model.Operador;

public class OperadorDao implements OperadorDaoInterface {
	
	private static final String nomeArq = "textfiles/OperadoresArq.dat";
	private static TextFileDao<Operador> arq = new TextFileDao<Operador>();

	@Override
	public Boolean loginOperador(String cpf, String senha) {
		ArrayList<Operador> lista = new ArrayList<Operador>(arq.getLista(nomeArq));
		
		for(Operador operador : lista) {
			if(operador.getCpf() == cpf && operador.getSenha() == senha)
				return true;
		}
		
		return false;
	}

}
