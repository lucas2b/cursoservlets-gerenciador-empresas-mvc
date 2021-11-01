package br.com.alura.gerenciador.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.modelo.Banco;

public class RemoverEmpresa implements Acao{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String empresaID = request.getParameter("id");
		
		Banco banco = new Banco();
		banco.removerEmpresa(empresaID);
		
		return "redirect:frontController?acao=ListarEmpresas";
	}

}
