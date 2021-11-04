package br.com.alura.gerenciador.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout implements Acao {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession sessao = request.getSession();
		
		//sessao.removeAttribute("usuarioLogado");
		sessao.invalidate(); //destroi o cookie
		
		return "redirect:frontController?acao=LoginForm";
	}

}
