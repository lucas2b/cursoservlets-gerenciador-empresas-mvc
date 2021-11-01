<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value="/frontController" var="frontController" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
		<form action="${frontController}" method="post">
			Login: <input type="text" name="login"> <br>
			Senha: <input type="password" name="senha">
			<input type="hidden" name="acao" value="Login">
			<input type="submit" value="Enviar">
		</form>
</body>
</html>