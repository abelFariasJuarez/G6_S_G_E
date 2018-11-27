<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Admin</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet" href="<c:url value="/resources/CSS/adminCSS.css"/>">




<script>
function myFunction(boton) {
    var boton1 = document.getElementById(boton);
   /* var botones = document.getElementsByClassName("btn");
    var i; */
      if (boton1.style.visibility === "hidden") {
        boton1.style.visibility = "visible";
    } else {
        boton1.style.visibility = "hidden";
    }
 /*   for(i=0;i<botones.length;i++){
  if(botones[i].name != boton1.id && boton1.style.visibility ==="visible"){

    botones[i].disabled = true;
  }
  else{
    botones[i].disabled= false;
  }

   

    }*/


  
   
}
</script>


</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<p class="navbar-brand">Panel Administrador</p>

		</button>
		<div>
			<div class="navbar-nav">

				<button type="button" name="oculto" class="btn btn-info solid"
					onClick=myFunction("oculto") >Consumo hogares</button>
				<span class="sr-only">(current)</span>

				<button type="button" name="oculto2" class="btn btn-info solid"
					onClick=myFunction("oculto2")>Crear nuevo dispositivo</button>
				<span class="sr-only">(current)</span>


				<button type="button" name="oculto3" class="btn btn-info solid"
					onClick=myFunction("oculto3") >Ver reportes</button>
				<span class="sr-only">(current)</span> <a class="btn  btn-success"
					href="login.html">Cerrar Sesion</a>
			</div>
		</div>
	</nav>




	<div class="container tamanio bg-dark margen text-light" id="oculto2"
		style="visibility: hidden">
		Creacion Dispositivo
		<div class="row">
			<div class="col col-sm align-self-start  "></div>
			<div class="col col-sm align-self-center  ">

				<input type="text" id="Dispositivo" placeholder="FamiliaDispositivo"
					name="Dispositivo" class="form-control" /> <input type="text"
					id="Dispositivo" placeholder="Consumo" name="Dispositivo"
					class="form-control" /> <input type="checkbox" id="BajoConsumo"
					placeholder="BajoConsumo" name="BajoConsumo" /> Bajo consumo <br>
				<input type="checkbox" id="Inteligente" placeholder="Inteligente"
					name="Inteligente" /> Es Inteligente? <br>
				<button class="btn btn-success bg-sucess "
					data-toggle="confirmation" id="botondispositivo"
					placeholder="Crear" name="botondispositivo" />
				Crear Dispositivo
			</div>
			<div class="col col-sm align-self-end "></div>
		</div>
	</div>


<ol>
					<c:forEach items="${clientes}" var="cliente">
						<li>${cliente}</li>
					</c:forEach>
				</ol>

	<div class="container margen ">
		<div class="row">
			<div class="col align-self-start"></div>
			<div class="col col-sm align-self-center ">
				
				<table id="oculto" class="table table-dark "
					style="visibility: hidden">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Cliente</th>
							<th scope="col">Consumo</th>
							<th scope="col">Ubicacion</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${clientes}" var="cliente">
							<tr>
							<td>#</td>
								<td>${cliente.nombre}</td>
								<td>Consumo</td>
								<td>${cliente.ubicacion.latitud}
									${cliente.ubicacion.latitud}</td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
			</div>
			<div class="col align-self-end"></div>
		</div>
	</div>





	<div class="container" id="oculto3" style="visibility: hidden">
		<div class="row">
			<div class="col align-self-start">
				<label class="colorfont text-light">Desde:</label> <input
					type="date" />
			</div>
			<div class="col align-self-center">
				<label class="colorfont text-light">Hasta:</label> <input
					type="date" />
			</div>
			<div class="col align-self-end">
				<button class="btn  btn-success" type="button"
					data-toogle="confirmation" data-title="Estas seguro?">Ver
					reporte</button>
			</div>
		</div>
	</div>



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

