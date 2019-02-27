<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Mis Dispositivos</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mihogar</title>
        <style>
            table tr:not(:first-child){
                cursor: pointer;transition: all .25s ease-in-out;
            }
            table tr:not(:first-child):hover{background-color: #ddd;}
        </style>
</head>
<body>
	<div class="container margen ">
	<h3>Dispositivos</h3>	
		<div class="row">
			<div class="col align-self-start"></div>
			<div class="col col-sm align-self-center ">
			</div>
			<div class="col align-self-end">
			
				<form:form action="/Cliente" method="GET">
					<button type=submit>Volver</button>
				</form:form></div>
		</div>
	</div>
	
	<div class="container tamanio bg-dark margen text-light"  >
		
			  	<div class="row">
		    	<div class="col align-self-start">
		    	<table id="table" class="table table-dark ">
						<tr>
							<th scope="col">Oid</th>
							<th scope="col">Nombre</th>
							<th scope="col">Tipo</th>
							<th scope="col">Consumo Por Hora</th>
							<th scope="col">Bajo Consumo</th>
							<th scope="col">Familia</th>
						</tr>
						<c:forEach items="${usuarioLogueado.dispositivos}" var="dispositivo">
							<tr>		
								<td>${dispositivo.getOid()}</td>
								<td>${dispositivo.getNombre()}</td>
								<td>${dispositivo.getClass().getSimpleName()}</td>
								<td>${dispositivo.getConsumoPorHora()}</td>
								<td>${dispositivo.getBajoConsumo()}</td>
								<td>${dispositivo.getRestriccionHoras().getCodigo()}</td>								
							</tr>
						</c:forEach>

				</table>
		    </div>		
		  </div>		  
		</div>
		
	<div class="container margen ">
		<div class="row">
			<div class="col align-self-start">
					<form:form action="/Cliente/ABMDispositivos/nuevodisp" method="POST">
   						<button type="submit" name="nuevodisp" class="btn btn-dark" > Agregar </button> 
   					</form:form>
   					<form:form action="/Cliente/abmDisp/modifDisp" method="POST">
   						<button type="submit" name="ModifDisp" class="btn btn-dark" > Modificar </button> 
   					</form:form>
   					<form:form action="/Cliente/ABMDispositivos?dispositivo=${dispositivo}" method="POST">
   						<button type="submit" name="EliminDisp" class="btn btn-dark" > Eliminar </button>  
					</form:form>
			</div>
		</div>	
		
	</div>
Name:<input type="text" name="fname" id="fname"><br><br>
Oid:<input type="text" name="foid" id="foid"><br><br>
<script>
	var table = document.getElementById('table');
	for(var i = 1; i < table.rows.length; i++)
	{
		table.rows[i].onclick = function()
		{
            document.getElementById("foid").value = this.cells[0].innerHTML;
            document.getElementById("fname").value = this.cells[1].innerHTML;
		};
	}    
</script>
         
</body>
</html>