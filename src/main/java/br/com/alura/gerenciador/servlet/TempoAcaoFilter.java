package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/frontController")
public class TempoAcaoFilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Long inicio = System.currentTimeMillis();
		
		chain.doFilter(request, response); //prossegue com chamada normal ao FrontController
		
		Long fim = System.currentTimeMillis();
		
		System.out.println("Acaoo "+request.getParameter("acao") + " executada em " + (fim-inicio) + " milissegundos.");
	}



}
