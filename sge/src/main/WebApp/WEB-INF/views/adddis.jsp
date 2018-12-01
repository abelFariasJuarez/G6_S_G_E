<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>User</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href=userCSS.css>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<script>

function myFunction(id){

	var fila = document.getElementById(id);
	console.log(fila.id);

}

</script>

</head>

<body>

<div class="container margen ">
		<div class="row">
			<div class="col align-self-start"></div>
			<div class="col col-sm align-self-center ">

				<table  class="table table-dark "
					>
					
					<thead>
						<tr>
						<th></th>
							<th scope="col">Dispositivo</th>
							<th scope="col">Equipo Concreto</th>
							<th scope="col">¿Es inteligente?</th>
							<th scope="col">¿Es Equipo de bajo consumo?</th>
							<th scope="col">Consumo(Kwh)</th>	
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${disponibles}" var="disponible">
							<tr id ="${disponible.codigo}" >
							<td><input  id="${disponible.nombre}" class="fila" type="checkbox" onClick=myFunction("${disponible.codigo}") name="chk" /> </td>
								<td>${disponible.codigo}</td>
								<td>${disponible.nombre}</td>
								<td>${disponible.isInteligente}</td>
								<td>${disponible.isBajoConsumo}</td>
								<td>${disponible.consumoPorHora}</td>
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
