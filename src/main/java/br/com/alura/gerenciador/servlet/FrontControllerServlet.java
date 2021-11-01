package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.controller.Acao;
import br.com.alura.gerenciador.controller.EditarEmpresaNoBanco;
import br.com.alura.gerenciador.controller.ExibirCadastrarNovaEmpresa;
import br.com.alura.gerenciador.controller.ExibirEditarEmpresa;
import br.com.alura.gerenciador.controller.ListarEmpresas;
import br.com.alura.gerenciador.controller.GravarNovaEmpresaNoBanco;
import br.com.alura.gerenciador.controller.RemoverEmpresa;
import br.com.alura.gerenciador.modelo.Usuario;


@WebServlet("/frontController")
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acaoParam = request.getParameter("acao");
		String nomeJspComTipoRedirecionamento = null;
		
		//Padrão de projeto Command utilizando reflection + interface
		
		String nomeClasse = "br.com.alura.gerenciador.controller." + acaoParam;
		System.out.println(nomeClasse);
		
		//Login
		HttpSession sessao = request.getSession();
		boolean usuarioNaoLogado = sessao.getAttribute("usuarioLogado") == null;
		boolean paginaProtegida = !(acaoParam.equals("LoginForm") || acaoParam.equals("Login"));
		
		if(usuarioNaoLogado && paginaProtegida) {
			response.sendRedirect("frontController?acao=LoginForm");
			return;
		}
		
		try {
			
			Class classeCarregada = Class.forName(nomeClasse);
			Acao acao = (Acao)classeCarregada.newInstance(); //reflection
			nomeJspComTipoRedirecionamento = acao.execute(request, response);
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			
			throw new ServletException(e);
		}
		
		
		//Trecho substituído pelo padrão de projeto Command
//		if(acao.equals("ExibirCadastrarNovaEmpresa")) {
//			ExibirCadastrarNovaEmpresa exbCadNovEmp = new ExibirCadastrarNovaEmpresa();
//			nomeJspComRedirecionamento= exbCadNovEmp.execute(request, response);
//		
//		}else if(acao.equals("GravarNovaEmpresaNoBanco")) {
//			GravarNovaEmpresaNoBanco novaEmpresaController = new GravarNovaEmpresaNoBanco();
//			nomeJspComRedirecionamento= novaEmpresaController.execute(request, response);
//			
//		}else if(acao.equals("ListarEmpresas")) {
//			
//			ListarEmpresas listaEmpresasController = new ListarEmpresas();
//			nomeJspComRedirecionamento = listaEmpresasController.executa(request, response);
//			
//		}else if(acao.equals("ExibirEditarEmpresa")) {
//			ExibirEditarEmpresa exibirEditarController = new ExibirEditarEmpresa();
//			nomeJspComRedirecionamento = exibirEditarController.execute(request, response);
//			
//		}else if(acao.equals("EditarEmpresaNoBanco")) {
//			EditarEmpresaNoBanco editarEmpresaController = new EditarEmpresaNoBanco();
//			nomeJspComRedirecionamento = editarEmpresaController.execute(request, response);
//			
//		}else if(acao.equals("RemoverEmpresa")) {
//			RemoverEmpresa removerEmpresaController = new RemoverEmpresa();
//			nomeJspComRedirecionamento = removerEmpresaController.execute(request, response);
//			
//		}
		
		
		//------ View Resolver
		
		String[] jspETipoRedirect = nomeJspComTipoRedirecionamento.split(":");
		String tipoRedirect = jspETipoRedirect[0]; //define se é um forward (RequestDispatcher) ou um redirect (redirecionamento por navegador)
		String jspResolvido = jspETipoRedirect[1]; //nome final do JSP
		
		if(tipoRedirect.equals("forward")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + jspResolvido); //único caminho que acessa os JSP
			rd.forward(request, response);
		}else if (tipoRedirect.equals("redirect")) {
			response.sendRedirect(jspResolvido); //não retorna um JSP, retorna a requisição novamente para o servlet apontando a ação que busca o JSP
		}
		
		
	}

}
