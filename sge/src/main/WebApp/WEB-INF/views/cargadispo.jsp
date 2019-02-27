<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>

    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="ISO-8859-1">

<title>CargaDispo</title>

<link rel="stylesheet"

	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"

	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"

	crossorigin="anonymous">

</head>

<body>



	<div class="container tamanio bg-dark margen text-light " id="oculto2">

		<form:form  action="/Administrador/cargadispo" method="POST" enctype="multipart/form-data">

		Carga Dispositivo/s

		<div class="row">

			<div class="col col-sm align-self-start  "></div>

			<div class="col col-sm align-self-center  ">

				<!--  <input name="uploadedfilea" type="select" /> <br>  -->  

				<input name="file" type="file"  size="50"/>

			</div>

			<div class="col align-self-center">

				<input type="submit" name="snd"  value="Enviar archivo" />

			</div>

			<div class="col align-self-end"></div>

		</div>

		<div class="col col-sm align-self-end "></div>

		</form:form>

	</div>

	

	<div class="container margen ">

		<div class="row">

			<div class="col align-self-start"></div>

			<div class="col col-sm align-self-center ">

			

			</div>

			<div class="col align-self-end">
				<form:form action="/Administrador" method="GET">
					<button type=submit>Volver</button>
				</form:form>
			</div>
		</div>

	</div>

		<div class="container margen ">

			<div class="row">

				<div class="col align-self-start"></div>

					<div class="col col-sm align-self-center ">



    	<table id="oculto" class="table table-dark ">

    	<thead>

			<tr>

			<th scope="col">Dispositivo</th>
			<th scope="col">Equipo Concreto</th>
			<th scope="col">¿Es inteligente?</th>
			<th scope="col">¿Es Equipo de bajo consumo?</th>
			<th scope="col">Consumo(Kwh)</th>	
			<th scope="col">Familia</th>

			</tr>

			</thead>

				<tbody>

				

				<c:forEach items="${dispositivosDisponibles}" var="dispositivoDisponible">

							<tr>



								<td>${dispositivoDisponible.nombre}</td>

								<td>${dispositivoDisponible.codigo}</td>

								<td>${dispositivoDisponible.isInteligente }</td>

								<td>${dispositivoDisponible.isBajoConsumo}</td>

								<td>${dispositivoDisponible.consumoPorHora }</td>

								<td>${dispositivoDisponible.codigoRestriccionHoras }<td>

							</tr>

						</c:forEach>

					</tbody>

				</table>

					</div>

				<div class="col align-self-end"></div>

			</div>

		</div>	

</body>

</html>