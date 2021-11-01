package br.com.alura.gerenciador.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Banco {
	
	private static List<Empresa> listaEmpresas = new ArrayList<Empresa>();
	private static List<Usuario> listaUsuarios = new ArrayList<Usuario>();
	
	static {
		Empresa empresa = new Empresa();
		empresa.setId(1);
		empresa.setNome("Google");
		
		listaEmpresas.add(empresa);
		
		empresa = new Empresa();
		empresa.setId(2);
		empresa.setNome("Microsoft");
		
		listaEmpresas.add(empresa);
		
		Usuario u1 = new Usuario();
		u1.setLogin("lucas");
		u1.setSenha("123");
		
		Usuario u2 = new Usuario();
		u2.setLogin("maiara");
		u2.setSenha("123");
		
		listaUsuarios.add(u1);
		listaUsuarios.add(u2);
	}
	

	public void adicionar(Empresa empresa) {
		Integer novoID = listaEmpresas.get(listaEmpresas.size() - 1).getId() + 1;
		empresa.setId(novoID);
		listaEmpresas.add(empresa);
	}
	
	
	public List<Empresa> listaDeEmpresas() {
		return this.listaEmpresas;
	}


	public void removerEmpresa(String empresaID) {
		Integer id = Integer.parseInt(empresaID);
		listaEmpresas.removeIf(empresa -> empresa.getId() == id);
	}


	public Empresa buscaEmpresaPorId(Integer id) {
		Iterator<Empresa> iterator = listaEmpresas.iterator();
		Empresa empresaSelecionada = null;
		
		while(iterator.hasNext()) {
			Empresa empresaIterator = iterator.next();
			
			if(empresaIterator.getId().equals(id)) {
				empresaSelecionada = empresaIterator;
				break;
			}
		}
		
		return empresaSelecionada;
	}


	public void editarEmpresa(Integer id, String nomeEmpresa, Date dataFormatada) {
		Iterator<Empresa> iterator = listaEmpresas.iterator();
		
		while(iterator.hasNext()) {
			Empresa empresaIterator = iterator.next();
			
			if(empresaIterator.getId().equals(id)) {
				empresaIterator.setNome(nomeEmpresa);
				empresaIterator.setDataCriacao(dataFormatada);
				break;
				
			}
		}
	}


	public Usuario autenticaUsuario(String login, String senha) {
		
		for(Usuario u : listaUsuarios) {
			if(u.ehIgual(login, senha)) {
				return u;
			}
		}
		
		return null;
	}
	
	

}
