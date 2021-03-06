<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ConsumoPeriodo</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>
<body>

	<c:choose>
		<c:when test="${hasta==null && desde==null}">
        ${usuarioLogueado.getNombre()} por favor, ingrese un per�odo.
        <br />
		</c:when>
		<c:otherwise>
        Su consumo durante el per�odo ${desde }  -  ${hasta } fue de ${usuarioLogueado.consumoEnPeriodo(desde,hasta)}.
        <br />
		</c:otherwise>
	</c:choose>
 
	<div class="container text-light" id="oculto2">
		<div class="row">
			<form:form action="/Cliente/ConsumoPeriodo/Consumo" method="POST">
				<div class="col align-self-start">
					<label class="colorfont">Desde:</label> <input
						type="datetime-local" id="datedesde" placeholder="Desde"
						name="datedesde" class="form-control" />
				</div>
				<div class="col align-self-center">
					<label class="colorfont">Hasta:</label> <input
						type="datetime-local" id="datehasta" placeholder="Hasta"
						name="datehasta" class="form-control" />
				</div>
				<div class="col align-self-end">
					<button type="submit" name="reporte" class="btn btn-info solid">Calcular consumo</button>
					<span class="sr-only">(current)</span>
				</div>
			</form:form>
			<tr>
				<td>Reporte</td>
				<td>${reporte}</td>
			</tr>

			<div class="col align-self-end">

				<form:form action="/Cliente" method="GET">
					<button type=submit>Volver</button>
				</form:form>



			</div>

		</div>
	</div>
</body>
</html>