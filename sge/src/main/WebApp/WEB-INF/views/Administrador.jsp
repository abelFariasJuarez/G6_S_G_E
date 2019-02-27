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
<link rel="stylesheet"
	href="<c:url value="/resources/CSS/adminCSS.css"/>">

</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark text-light">
		<p class="navbar-brand">Panel Administrador</p>
		<div>
			<div class="navbar-nav">
				<form:form action="/Administrador/consumo" method="POST">
					<button type="submit" name="oculto" class="btn btn-info solid"
						onClick=myFunction("oculto") >Consumo hogares</button>
					<span class="sr-only">(current)</span>
				</form:form>
				<form:form action="/Administrador/cargadispo" method="POST">
					<button type="submit" name="oculto2" class="btn btn-info solid"
						onClick=myFunction("oculto2")>Cargar Dispositivo/s</button>
					<span class="sr-only">(current)</span>
				</form:form>
				<form:form action="/Administrador/restriccionesHorarias" method="POST">
					<button type="submit" name="oculto4" class="btn btn-info solid"
						onClick=myFunction("oculto4") >Restricciones Horarias</button>
					<span class="sr-only">(current)</span>
				</form:form>				
				<a class="btn  btn-success" href="/login">Cerrar Sesion</a>
			</div>
		</div>
	</nav>
	<div>
		<br>
	</div>
	<nav class="container tamanio bg-dark margen text-light">
		<div>
			<div class="navbar-nav">
				<form:form action="/Administrador/reportes" method="GET">
					<div class="col align-self-start">
						<label class="colorfont">Reporte:</label>
						<select id="codReporte" name="codReporte" style="width: 220px; " class="form-control">
			                <option SELECTED value="hogares">Consumo por hogar</option>
			                <option value="dispositivos">Consumo promedio por tipo de dispositivo</option>
			                <option value="transdormadores">Consumo por transformador</option>
			            </select>
	            	</div>			
					<div class="col align-self-start">
						<label class="colorfont">Desde:</label>
						<input type="datetime-local" id="datedesde" placeholder="Desde"
							name="datedesde" class="form-control" />
					</div>
					<div class="col align-self-center">
						<label class="colorfont">Hasta:</label> 
						<input type="datetime-local" id="datehasta" placeholder="Hasta"
							name="datehasta" class="form-control" />
					</div>
					<div class="col align-self-end">
						<br>
						<button type="submit" name="verreporte" class="btn btn-info solid">Ver reporte</button>
						<span class="sr-only">(current)</span>
					</div>
				</form:form>
			</div>
		</div>
	</nav>	

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>

</body>
</html>

