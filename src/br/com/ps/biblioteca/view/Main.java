package br.com.ps.biblioteca.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import br.com.ps.biblioteca.model.Emprestimo;
import br.com.ps.biblioteca.model.Livro;
import br.com.ps.biblioteca.model.Periodico;
import br.com.ps.biblioteca.model.Usuario;
import br.com.ps.biblioteca.view.controller.MainPanelController;

public class Main {
	private static MainPanelController controller = new MainPanelController();
	private static Scanner entrada = new Scanner(System.in);

	public static void mainMenu() {
		
		System.out.println("\tSistema de Biblioteca");
        System.out.println("0. Fim");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Cadastrar Livro");
        System.out.println("3. Cadastrar Periódico");
        System.out.println("4. Registrar Empréstimo");
        System.out.println("5. Registrar Devolução");
        System.out.println("6. Pesquisar Livro");
        System.out.println("7. Pesquisar Periódico");
        System.out.println("8. Pagar Multa");
        System.out.println("Opcao:");
	}
	
	public static void cadastrarUsuario() {
		Integer opcao;
		Usuario usuario = new Usuario();
				
		System.out.println("\tCadastro de Usuário");
		
		System.out.println("Digite o nome do usuário:");
		usuario.setNome(entrada.nextLine());
		
		System.out.println("Digite o telefone:");
		usuario.setContato(entrada.nextLine());
		
		System.out.println("Digite a data de nascimento (dd/mm/yyyy):");
		usuario.setDataNasc(entrada.nextLine());
		
		System.out.println("Digite o CPF:");
		usuario.setCpf(entrada.nextLine());
		
		System.out.println("Digite o e-mail:");
		usuario.seteMail(entrada.nextLine());
		
		System.out.println("Digite a senha:");
		usuario.setSenha(entrada.nextLine());
		
		do {
			System.out.println("Digite o tipo de usuário (comum, aluno, professor):");
			usuario.setTipoUsuario(entrada.nextLine());
			
			if((usuario.getTipoUsuario().compareTo("comum") != 0)
					&& (usuario.getTipoUsuario().compareTo("aluno") != 0)
					&& (usuario.getTipoUsuario().compareTo("professor") != 0)) {
				System.out.println("Tipo de usuário inválido!");
				
			}
	
		} while((usuario.getTipoUsuario().compareTo("comum") != 0)
			&& (usuario.getTipoUsuario().compareTo("aluno") != 0)
			&& (usuario.getTipoUsuario().compareTo("professor") != 0));
		
		System.out.println("Deseja salvar o novo usuário?");
		System.out.println("0. Não");
	    System.out.println("1. Sim");
	    
	    opcao = Integer.parseInt(entrada.nextLine());
	    
	    switch(opcao){
        case 0:
        	
            return;
            
        case 1:
            controller.cadastrarUsuario(usuario);
            break;
            
        default:
            System.out.println("Opção inválida.");
        }
	    
	    
	}
	
	public static void cadastrarLivro() {
		Integer opcao;
		Livro livro = new Livro();
				
		System.out.println("\tCadastro de Livro");
		
		System.out.println("Digite o nome:");
		livro.setNome(entrada.nextLine());
		
		System.out.println("Digite o autor:");
		livro.setAutor(entrada.nextLine());
		
		System.out.println("Digite a editora:");
		livro.setEditora(entrada.nextLine());
		
		System.out.println("Digite o ano (1999):");
		livro.setAno(Integer.parseInt(entrada.nextLine()));
		
		System.out.println("Digite a edição (1):");
		livro.setEdicao(Integer.parseInt(entrada.nextLine()));
		
		System.out.println("Deseja salvar o novo livro?");
		System.out.println("0. Não");
	    System.out.println("1. Sim");
	    
	    opcao = Integer.parseInt(entrada.nextLine());
	    
	    switch(opcao){
        case 0:
        	
            return;
            
        case 1:
        	livro.setDisponivel(true);
            controller.cadastrarLivro(livro);
            break;
            
        default:
            System.out.println("Opção inválida.");
        }
	    
	    
	}
	
	public static void cadastrarPeriodico() {
		Integer opcao;
		Periodico periodico = new Periodico();
				
		System.out.println("\tCadastro de Periódico");
		
		System.out.println("Digite o nome:");
		periodico.setNome(entrada.nextLine());
		
		System.out.println("Digite o Volume:");
		periodico.setVolume(Integer.parseInt(entrada.nextLine()));
		
		System.out.println("Digite o mes (01):");
		periodico.setMes(Integer.parseInt(entrada.nextLine()));
		
		System.out.println("Digite o ano (1999):");
		periodico.setAno(Integer.parseInt(entrada.nextLine()));
		
		System.out.println("Deseja salvar o novo periódico?");
		System.out.println("0. Não");
	    System.out.println("1. Sim");
	    
	    opcao = Integer.parseInt(entrada.nextLine());
	    
	    switch(opcao){
        case 0:
        	
           return;
            
        case 1:
        	periodico.setDisponivel(true);
            controller.cadastrarPeriodico(periodico);
            break;
            
        default:
            System.out.println("Opção inválida.");
        }
	    
	    
	}
	
	public static void imprimeEmprestimos(ArrayList<Emprestimo> emprestimos) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
    	System.out.println("Id | Data de Emprestimo | Data de Devolução | Numero de Obras");

		for(Emprestimo emprestimo: emprestimos) {
			System.out.println(emprestimo.getId() + " " + dateFormat.format(emprestimo.getDataEmprestimo()) + " " + dateFormat.format(emprestimo.getDataDevolucao()) + " " + emprestimo.getNumObras());
		}
	}
	
	public static void registrarDevolucao() {
		Integer opcao;
		Integer id;
		
		Emprestimo emprestimo = new Emprestimo();
		Usuario usuario = new Usuario();
				
		System.out.println("Devolução de obras");
		
		System.out.println("\tDigite o CPF do usuário:");
		if((usuario = controller.pesquisarUsuario(entrada.nextLine())) != null) {
			ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
			
			emprestimos = controller.pesquisarEmprestimosUsuario(usuario);
			
			if(emprestimos != null) {
				imprimeEmprestimos(emprestimos);
			} else {
				System.out.println("Não há empréstimos para este usuário!");
				return;
			}
		} else {
			System.out.println("Usuário não encontrado!");
			
			return;
		}
		
		System.out.println("Digite o id do emprestimo:");
		id = Integer.parseInt(entrada.nextLine());
		
		emprestimo = controller.pesquisarEmprestimoId(id);
		
		System.out.println("Devolução das obras:");
		for(Livro livro: emprestimo.getLivros()) {
			System.out.println(livro.getNome());
		}
		for(Periodico periodico: emprestimo.getPeriodicos()) {
			System.out.println(periodico.getNome());
		}
		
		System.out.println("Deseja confirmar a devolução?");
		System.out.println("0. Não");
	    System.out.println("1. Sim");
	    
	    opcao = Integer.parseInt(entrada.nextLine());
	    
	    switch(opcao){
        case 0:
        	
           return;
            
        case 1:
            controller.registrarDevolucao(emprestimo);
            break;
            
        default:
            System.out.println("Opção inválida.");
        }
		
	    
	}
	
	public static void registrarEmprestimo() {
		Integer opcao;
		Integer id;
		Integer countObras;
		
		Emprestimo emprestimo = new Emprestimo();
		Livro livro = new Livro();
		Periodico periodico = new Periodico();
		Usuario usuario = new Usuario();
			
		System.out.println("\tEmpréstimo de obras");
		
		System.out.println("\tDigite o CPF do usuário:");
		if((usuario = controller.pesquisarUsuario(entrada.nextLine())) != null) {
			ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
			
			emprestimos = controller.pesquisarEmprestimosUsuario(usuario);
			
			if(emprestimos != null) {
				countObras = 0;
				
				for(Emprestimo emp: emprestimos) {
					if(emp.getDisponivel() == false) {
						System.out.println("Este usuário tem uma multa pendente!");
						
						return;
					} else {
						countObras += emp.getLivros().size() + emp.getPeriodicos().size();
					}
				}
				
				if((usuario.getTipoUsuario().compareTo("comum") == 0 && countObras > 0)
					|| (usuario.getTipoUsuario().compareTo("aluno") == 0 && countObras > 1)
					|| (usuario.getTipoUsuario().compareTo("professor") == 0 && countObras > 2)) {
					
					System.out.println("Este usuário não pode emprestar mais obras!");
					
					return;
				}
			}
		} else {
			System.out.println("Usuário não encontrado!");
			
			return;
		}
		
		countObras = 0;
		do{
			System.out.println("Deseja emprestar um livro ou periódico?");
			System.out.println("0. Finalizar");
		    System.out.println("1. Livro");
		    System.out.println("2. Periódico");
		    
		    opcao = Integer.parseInt(entrada.nextLine());
            
            switch(opcao){
            case 0:
            	break;
            	
            case 1:
            	System.out.println("Digite o id do livro:");
        		id = Integer.parseInt(entrada.nextLine());
        		
        		livro = controller.pesquisarLivroId(id);
        		
        		if(livro != null) {
        			if(livro.isDisponivel()) {
        				emprestimo.getLivros().add(livro);
        				countObras++;
        				System.out.println(livro.getNome() + " Adicionado!");
        			} else {
        				System.out.println(livro.getNome() + " Não está disponível!");
        			}
        			
        		} else {
        			System.out.println("Livro não encontrado!");
        			
        		}
        		
                break;
                
            case 2:
            	System.out.println("Digite o id do periódico:");
        		id = Integer.parseInt(entrada.nextLine());
        		
        		periodico = controller.pesquisarPeriodicoId(id);
        		
        		if(periodico != null) {
        			if(periodico.isDisponivel()) {
        				emprestimo.getPeriodicos().add(periodico);
        				countObras++;
        				System.out.println(periodico.getNome() + " Adicionado!");
        			} else {
        				System.out.println(periodico.getNome() + " Não está disponível!");
        			}
        			
        		} else {
        			System.out.println("Periódico Não encontrado!");
        		}
                break;
            
            default:
                System.out.println("Opção inválida.");
                
            }
            
        } while(opcao != 0 && !(usuario.getTipoUsuario().compareTo("comum") == 0 && countObras == 1) 
        		&& !(usuario.getTipoUsuario().compareTo("aluno") == 0 && countObras == 2) 
        		&& !(usuario.getTipoUsuario().compareTo("professor") == 0 && countObras == 3));
		
		if(emprestimo.getLivros().size() == 0 && emprestimo.getPeriodicos().size() == 0) {
			System.out.println("Nenhuma obra foi selecionada!");
			return;
		}
		
		System.out.println("Deseja finalizar o emprestimo?");
		System.out.println("0. Cancelar");
	    System.out.println("1. Sim");
	    
	    opcao = Integer.parseInt(entrada.nextLine());
	    
	    switch(opcao){
        case 0:
        	
           return;
            
        case 1:
        	emprestimo.setNumObras(countObras);
        	emprestimo.setUsuario(usuario);

            controller.registrarEmprestimo(emprestimo);
            break;
            
        default:
            System.out.println("Opção inválida.");
            
        }
	    
	    
	}

	public static void pesquisarLivro() {
		Integer opcao;
		String busca;
		
		ArrayList<Livro> livros = new ArrayList<Livro>();
				
		System.out.println("\tPesquisar Livros");
		
		System.out.println("Deseja pesquisar o livros por titulo ou autor?");
		System.out.println("0. Cancelar");
	    System.out.println("1. Titulo");
	    System.out.println("2. Autor");
	    
	    opcao = Integer.parseInt(entrada.nextLine());
	    
	    switch(opcao){
        case 0:
        	
           return;
            
        case 1:
        	System.out.println("Digite o titulo do livro:");
        	busca = entrada.nextLine();
        	
            livros = controller.pesquisarLivro(busca, opcao);
            break;
            
        case 2:
        	System.out.println("Digite o nome do autor:");
        	busca = entrada.nextLine();
        	
            livros = controller.pesquisarLivro(busca, opcao);
            break;
            
        default:
            System.out.println("Opção inválida.");
            
        }
	    
	    if(livros != null) {
	    	System.out.println("Id | Nome | Autor | Editora | Ano | Edição");
	    	for(Livro livro: livros) {
	    		System.out.println(livro.getId() + " " + livro.getNome() + " " + livro.getAutor() + " " + livro.getEditora() + " " + livro.getAno() + " " + livro.getEdicao());
	    	}
	    } else {
	    	System.out.println("Livro não encontrado!");
	    }
	    
	    
	}
	
	public static void pesquisarPeriodico() {
		String busca;
		
		ArrayList<Periodico> periodicos = new ArrayList<Periodico>();
				
		System.out.println("\tPesquisar Periodicos");
		
		System.out.println("Digite o nome do periódico:");
    	busca = entrada.nextLine();
    	
        periodicos = controller.pesquisarPeriodico(busca);
	    
	    if(periodicos != null) {
	    	System.out.println("Id | Nome | Volume | Mês | Ano");
	    	for(Periodico periodico: periodicos) {
	    		System.out.println(periodico.getId() + " " + periodico.getNome() + " " + periodico.getVolume() + " " + periodico.getMes() + " " + periodico.getAno());
	    	}
	    } else {
	    	System.out.println("Periódico não encontrado!");
	    }
	    
	    
	}
	
	public static void pagarMulta() {
		Integer opcao;
		Emprestimo emprestimo = new Emprestimo();
		Usuario usuario = new Usuario();
				
		System.out.println("\tPagar Multa");
		
		System.out.println("Digite o CPF do usuário:");
		if((usuario = controller.pesquisarUsuario(entrada.nextLine())) != null) {
			ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
			
			emprestimos = controller.pesquisarEmprestimosUsuario(usuario);
			
			if(emprestimos != null) {
				for(Emprestimo emp: emprestimos) {
					if(emp.getDisponivel() == false) {
						emprestimo = emp;
					}
				}
			} else {
				System.out.println("Este usuário não tem empréstimos!");
				
				return;
			}
		} else {
			System.out.println("Usuário não encontrado!");
			
			return;
	    }
		
		if(emprestimo.getId() != null) {
			System.out.println("Deseja pagar a multa no valor de " + controller.calculaMulta(emprestimo) + "?");
			
			System.out.println("0. Não");
		    System.out.println("1. Sim");
		    
		    opcao = Integer.parseInt(entrada.nextLine());
		    
		    switch(opcao){
	        case 0:
	        	
	           return;
	            
	        case 1:
	        	controller.pagarMulta(emprestimo);
	            break;
	     
	        default:
	            System.out.println("Opção inválida.");
	            
	        }
		} else {
			System.out.println("Não há multas a serem pagas!");
		}
		
		
	}
	
	public static void main(String[] args) {
		Integer opcao;
        
        do {
            mainMenu();
            opcao = Integer.parseInt(entrada.nextLine());
            
            switch(opcao){
            case 0:
            	
            	return;
            case 1:
                cadastrarUsuario();
                
                break;
                
            case 2:
                cadastrarLivro();
                
                break;
                
            case 3:
                cadastrarPeriodico();
                
                break;
                
            case 4:
                registrarEmprestimo();
                
                break;
                
            case 5:
                registrarDevolucao();
                
                break;
                
            case 6:
                pesquisarLivro();
                
                break;
                
            case 7:
                pesquisarPeriodico();
                
                break;
                
            case 8:
                pagarMulta();
                
                break;
            
            default:
                System.out.println("Opção inválida.");
                
            }
        } while(opcao != 0);
        
        entrada.close();
	}
}
