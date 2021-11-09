package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;


@WebServlet("/empresas")
public class EmpresasService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Banco banco = new Banco();
		
		List<Empresa> listaEmpresas = banco.listaDeEmpresas();
		
		XStream xstream = new XStream();
		xstream.alias("empresa", Empresa.class);
		
		String xml = xstream.toXML(listaEmpresas);
		
		response.setContentType("application/xml");
		response.getWriter().print(xml);
		
//		Gson gson = new Gson();
//		String listaEmpresasJson = gson.toJson(listaEmpresas);
//		
//		response.setContentType("application/json");
//		PrintWriter writer = response.getWriter();
//		writer.print(listaEmpresasJson);
	}

}
