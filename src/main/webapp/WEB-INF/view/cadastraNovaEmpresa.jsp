<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value="/frontController" var="frontController" />
<%@taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Empresa</title>
</head>
<body>
	<c:if test="${empty id}">
		<form action="${frontController}" method="post">
			Nome: <input type="text" name="nome" value="aaaa"> <br>
			Data Criacao: <input type="text" name="data">
			<input type="hidden" name="acao" value="GravarNovaEmpresaNoBanco">
			<input type="submit" value="Enviar">
		</form>
	</c:if>
	
	<c:if test="${not empty id}">
		<form action="${frontController}" method="post">
			Nome: <input type="text" name="nome" value="${nome}"> <br>
			Data Criacao: <input type="text" name="data" value="<fmt:formatDate value="${data}" pattern="dd/MM/yyyy"/>">
			<input type="hidden" name="id" value="${id}">
			<input type="hidden" name="acao" value="EditarEmpresaNoBanco">
			<input type="submit" value="Enviar">
		</form>
	</c:if>
	<c:import url="logout-parcial.jsp"/>
</body>
</html>