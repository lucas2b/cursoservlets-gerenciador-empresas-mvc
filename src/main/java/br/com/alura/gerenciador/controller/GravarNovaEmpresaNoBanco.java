package br.com.alura.gerenciador.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

public class GravarNovaEmpresaNoBanco implements Acao {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("Empresa cadastrada!");

		String nomeEmpresa = request.getParameter("nome");
		String dataCriacao = request.getParameter("data");
		
		if(!nomeEmpresa.isEmpty() && !dataCriacao.isEmpty()) { //se as entradas forem null, apenas exibe a lista
		
			Date dataCriacaoFormatada = null;
			
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				dataCriacaoFormatada = sdf.parse(dataCriacao);
			} catch (ParseException e) {
				throw new ServletException(e); //Padrão catch and re-throw
			}
			
			Empresa empresa = new Empresa();
			empresa.setNome(nomeEmpresa);
			empresa.setDataCriacao(dataCriacaoFormatada);
			
			Banco banco = new Banco();
			banco.adicionar(empresa);
			
			//adicionando dado na sessão para mostrar na listagem
			HttpSession session = request.getSession();
			session.setAttribute("nomeEmpresa", empresa.getNome());
		}
		
		
		return "redirect:frontController?acao=ListarEmpresas";
		
	}

}