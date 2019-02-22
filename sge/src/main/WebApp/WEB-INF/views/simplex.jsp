<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Admin</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">


</head>
<body>

	<table id="oculto" class="table table-dark ">

		<thead>
			<tr>
	<th scope="col">Dispositivo:</th>
	<th scope="col">Maximo:</th>
	<th scope="col">ConsumoPeriodo</th>
	<th scope="col">esEficiente</th>
</tr>
</thead>
<tbody>

<c:forEach items="${dispositivos}" var="dispositivo">
	<tr>
		<td>${dispositivo.getNombre() }</td>
		<td>${recomendacion.horasMaximasPara(dispositivo)} Horas</td>
		<td>${usuarioLogueado.consumoEnPeriodoDe(dispositivo)}</td>

		<td>${recomendacion.esEficiente(dispositivo,usuarioLogueado.consumoEnPeriodoDe(dispositivo)) }</td>
	</tr>
</c:forEach>
</tbody>

</table>
horas Totales Mensuales Hogar eficiente:${recomendacion.horasTotalesMensuales}



<form:form  action="/Cliente/simplex" method="POST" >
<input type="submit" name="send"   />
</form:form>

				<form:form action="/Cliente" method="GET">
					<button type=submit>Volver</button>
				</form:form>

</body>
</html>

