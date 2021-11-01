package br.com.alura.gerenciador.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

public class ExibirEditarEmpresa implements Acao {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		Integer id = Integer.parseInt(idString);
					
		Banco banco = new Banco();
		Empresa empresaSelecionada = banco.buscaEmpresaPorId(id);
		
		request.setAttribute("id", empresaSelecionada.getId());
		request.setAttribute("nome", empresaSelecionada.getNome());
		request.setAttribute("data", empresaSelecionada.getDataCriacao());
		
		return "forward:cadastraNovaEmpresa.jsp";
	}

}
