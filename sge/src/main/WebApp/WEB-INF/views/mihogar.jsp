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
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mihogar</title>
</head>
<body>

	<div class="container margen ">
		<div class="row">
			<div class="col align-self-start"></div>
			<div class="col col-sm align-self-center ">
				<form:form action="/Cliente/MiHogar" method="POST">
					<button type=submit>Reload</button>
				</form:form>
			</div>
			<div class="col align-self-end"></div>
		</div>
	</div>


	<div class="container margen ">
		<div class="row">
			<div class="col align-self-start"></div>
			<div class="col col-sm align-self-center ">

				<table id="oculto68" class="table table-dark ">

					<thead>
						<tr>
							<th scope="col">Dispositivo</th>
							<th scope="col">Estado</th>
							<th scope="col">Reglas activas</th>
							<th scope="col">Últimas mediciones</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${usuarioLogueado.getDispositivos()}"
							var="dispositivo">
							<tr>

								<td>${dispositivo.getNombre()}</td>
								<td>${dispositivo.getNombreEstado()}</td>
								<td><c:choose>
										<c:when test="${dispositivo.getReglas().size()==0}">
        Ninguno
        <br />
										</c:when>
										<c:otherwise>
        ${dispositivo.getReglas()}
        <br />
										</c:otherwise>
									</c:choose></td>
									<td>x</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				Su consumo en el último período fue de
				${usuarioLogueado.consumoEnPeriodo(fechaPrincipioDePeriodo,fechaActual)}

			</div>
			<div class="col align-self-end"></div>
		</div>
	</div>


	<div class="container margen ">
		<div class="row">
			<div class="col align-self-start"></div>
			<div class="col col-sm align-self-center "></div>
			<div class="col align-self-end">

				<form:form action="/Cliente" method="GET">
					<button type=submit>Volver</button>
				</form:form>
			</div>
		</div>
	</div>

</body>
</html>