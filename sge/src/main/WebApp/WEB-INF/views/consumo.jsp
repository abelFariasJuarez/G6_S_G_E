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
<title>Consumo</title>
</head>
<body>

	<div class="container margen ">
		<div class="row">
			<div class="col align-self-start"></div>
			<div class="col col-sm align-self-center ">
				<form:form action="	/Administrador/consumo" method="POST">
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

				<table id="oculto" class="table table-dark ">

					<thead>
						<tr>
							<th scope="col">Cliente</th>
							<th scope="col">Consumo</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${clientes}" var="cliente">
							<tr>

								<td>${cliente.username}</td>
								<td>${cliente.consumo()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
			<div class="col align-self-end"></div>
		</div>
	</div>


	<div class="container margen ">
		<div class="row">
			<div class="col align-self-start"></div>
			<div class="col col-sm align-self-center "></div>
			<div class="col align-self-end">

				<form:form action="/Administrador" method="GET">
					<button type=submit>Volver</button>
				</form:form>
			</div>
		</div>
	</div>

</body>
</html>