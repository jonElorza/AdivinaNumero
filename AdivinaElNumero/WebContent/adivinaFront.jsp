<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Adivina el numero</h1>
	<h3>Se a creado un numero aleatorio del 0 al 10. Tienes 3 intentos para adivinarlo. Suerte!! </h3>
	
	<form action="adivNumero" method="post">
		<input type="text" placeholder="introduce un numero" name="number">
		<button type="submit">Enviar</button>
	</form>
	
	<p>${adivinado}</p>
	
</body>
</html>