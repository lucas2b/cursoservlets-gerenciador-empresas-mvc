package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/frontController")
public class AutorizacaoFilter implements Filter {

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
			
		HttpSession sessao = request.getSession();
	
		String acaoParam = request.getParameter("acao");
		boolean usuarioNaoLogado = sessao.getAttribute("usuarioLogado") == null;
		boolean paginaProtegida = !(acaoParam.equals("LoginForm") || acaoParam.equals("Login"));
		
		if(usuarioNaoLogado && paginaProtegida) {
			response.sendRedirect("frontController?acao=LoginForm"); //Interrompe e redireciona, que cai no Filter novamente
			return;
		}else {			
			chain.doFilter(request, response); //prossegue para FrontController
		}
		
	}


}
