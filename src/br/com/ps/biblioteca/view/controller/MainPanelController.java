package br.com.ps.biblioteca.view.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import br.com.ps.biblioteca.dao.impl.EmprestimoDao;
import br.com.ps.biblioteca.dao.impl.LivroDao;
import br.com.ps.biblioteca.dao.impl.PeriodicoDao;
import br.com.ps.biblioteca.dao.impl.UsuarioDao;
import br.com.ps.biblioteca.model.Emprestimo;
import br.com.ps.biblioteca.model.Livro;
import br.com.ps.biblioteca.model.Periodico;
import br.com.ps.biblioteca.model.Usuario;

public class MainPanelController {

	private UsuarioDao usuarioDao = new UsuarioDao();
	private LivroDao livroDao = new LivroDao();
	private PeriodicoDao periodicoDao = new PeriodicoDao();
	private EmprestimoDao emprestimoDao = new EmprestimoDao();
	
	public void cadastrarUsuario(Usuario usuario) {
		if(this.usuarioDao.cadastrarUsuario(usuario)) {
			System.out.println("Usuário cadastrado!");
		} else {
			System.out.println("Erro ao cadastrar usuário!");
		}
	}
	
	public void cadastrarLivro(Livro livro) {
		if(this.livroDao.cadastrarLivro(livro)) {
			System.out.println("Livro cadastrado!");
		} else {
			System.out.println("Erro ao cadastrar livro!");
		}
	}
	
	public void cadastrarPeriodico(Periodico periodico) {
		if(this.periodicoDao.cadastrarPeriodico(periodico)) {
			System.out.println("Periódico cadastrado!");
		} else {
			System.out.println("Erro ao cadastrar periódico!");
		}
	}
	
	public void registrarEmprestimo(Emprestimo emprestimo) {
		Date dataEmprestimo = new Date();
		Date dataDevolucao = new Date();
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(dataDevolucao);
		if(emprestimo.getUsuario().getTipoUsuario().compareTo("comum") == 0) {
			c.add(Calendar.DATE, 7);
		}
		if(emprestimo.getUsuario().getTipoUsuario().compareTo("aluno") == 0) {
			c.add(Calendar.DATE, 15);
		}
		if(emprestimo.getUsuario().getTipoUsuario().compareTo("professor") == 0) {
			c.add(Calendar.DATE, 30);
		}
		dataDevolucao = c.getTime();
		
		emprestimo.setDataEmprestimo(dataEmprestimo);
		emprestimo.setDataDevolucao(dataDevolucao);
		emprestimo.setDisponivel(true);
		
		for(Livro livro: emprestimo.getLivros()) {
			livro.setDisponivel(false);
			livroDao.editarLivro(livro);
		}
		
		for(Periodico periodico: emprestimo.getPeriodicos()) {
			periodico.setDisponivel(false);
			periodicoDao.editarPeriodico(periodico);
		}
		
		if(this.emprestimoDao.cadastrarEmprestimo(emprestimo)) {
			System.out.println("Emprestimo cadastrado!");
		} else {
			System.out.println("Erro ao cadastrar emprestimo!");
		}
	}
	
	public void registrarDevolucao(Emprestimo emprestimo) {
		Date dataAtual = new Date();
		
		for(Livro livro: emprestimo.getLivros()) {
			livro.setDisponivel(true);
			livroDao.editarLivro(livro);
		}
		
		for(Periodico periodico: emprestimo.getPeriodicos()) {
			periodico.setDisponivel(true);
			periodicoDao.editarPeriodico(periodico);
		}
		
		if(dataAtual.after(emprestimo.getDataDevolucao())) {
			System.out.println("Multa por atraso de " + this.emprestimoDao.calculaMulta(emprestimo) + "registrada!");
		} else {
			System.out.println("Devolução Efetuada com sucesso!");
			this.emprestimoDao.removerEmprestimo(emprestimo);
		}
	}
	
	public Emprestimo pesquisarEmprestimoId(Integer id) {
		return this.emprestimoDao.buscarEmprestimo(id);
	}
	
	public Emprestimo pesquisarEmprestimo(Usuario usuario) {
		return this.emprestimoDao.buscarEmprestimo(usuario);
	}
	
	public ArrayList<Emprestimo> pesquisarEmprestimosUsuario(Usuario usuario) {
		return this.emprestimoDao.buscarEmprestimoList(usuario);
	}
	
	public Usuario pesquisarUsuario(String cpf) {
		return this.usuarioDao.buscarUsuario(cpf);
	}
	
	public ArrayList<Livro> pesquisarLivro(String busca, Integer opcao) {
		if(opcao == 1) {
			return this.livroDao.buscarLivroNome(busca);
		} else {
			return this.livroDao.buscarLivroAutor(busca);
		}
	}
	
	public Livro pesquisarLivroId(Integer id) {
		return this.livroDao.buscarLivro(id);
	}
	
	public ArrayList<Periodico> pesquisarPeriodico(String nome) {
		return this.periodicoDao.buscarPeriodico(nome);
	}
	
	public Periodico pesquisarPeriodicoId(Integer id) {
		return this.periodicoDao.buscarPeriodico(id);
	}
	
	public Double calculaMulta(Emprestimo emprestimo) {
		return this.emprestimoDao.calculaMulta(emprestimo);
	}
	
	public void pagarMulta(Emprestimo emprestimo) {
		if(this.emprestimoDao.pagarMulta(emprestimo)) {
			System.out.println("Multa paga com sucesso!");
		} else {
			System.out.println("Erro ao pagar multa!");
		}
	}
}
