<%@page import="br.com.alura.gerenciador.modelo.Empresa"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de Empresas</title>
</head>
<body>
	<%
		HttpSession sessao = request.getSession();
		String nomeEmpresa = sessao.getAttribute("nomeEmpresa") == null ? null : sessao.getAttribute("nomeEmpresa").toString();
	%>

	<c:if test="${not empty nomeEmpresa}">
			Empresa <strong>${nomeEmpresa}</strong> cadastrada com sucesso! <br>
			<% request.getSession().invalidate(); %>
	</c:if>

	<br>
	Lista de empresas cadastradas: <br>
	<ul>
		<c:forEach items="${listaEmpresas}" var="empresa">
			<li>${empresa.nome} -  <fmt:formatDate value="${empresa.dataCriacao}" pattern="dd/MM/yyyy"/>
				
				<c:url value="frontController?acao=RemoverEmpresa" var="removeEmpresa">
	    			<c:param name="id" value="${empresa.id}"/>
				</c:url>
				<a href="${removeEmpresa}">remover</a>
				
				<c:url value="frontController?acao=ExibirEditarEmpresa" var="editaEmpresa">
	    			<c:param name="id" value="${empresa.id}"/>
				</c:url>
				<a href="${editaEmpresa}">editar</a>
			
			</li>
		</c:forEach>
	</ul>

</body>
</html>