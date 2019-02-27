<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nuevo Dispositivo</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
        <style>
            table tr:not(:first-child){
                cursor: pointer;transition: all .25s ease-in-out;
            }
            table tr:not(:first-child):hover{background-color: #ddd;}
        </style>
      
</head>

<body>

<div class="container text-light" >
  <div class="row">
    <div class="col align-self-start">
		<div class="container tamanio bg-dark margen text-light"  >  	
			<h3>Dispositivos Disponibles</h3>
			<div class="col align-self-end"> 
   				<form:form action="/Cliente/ABMDispositivos" method="POST">
					Familia:<input type="text" name="fcodigo" id="fcodigo" required><br><br>
					Nombre:<input type="text" name="fname" id="fname" required><br><br>
   					<button type="submit" name="nuevoDisp" class="btn btn-dark" > Agregar </button> 
   				</form:form>
   				<form:form action="/Cliente/ABMDispositivos" method="POST">
   					<button type="submit" name="cancel" class="btn btn-dark" > Cancelar </button> 
   				</form:form>
			</div>
		    <table id="table" class="table table-dark ">
				<tr>
					<th scope="col">Familia</th>
					<th scope="col">Nombre</th>
					<th scope="col">Es Inteligente</th>
					<th scope="col">Bajo Consumo</th>
					<th scope="col">Consumo Por Hora</th>							
				</tr>
				<c:forEach items="${disponibles}" var="disponible">
					<tr>		
						<td>${disponible.getCodigo()}</td>
						<td>${disponible.getNombre()}</td>
						<td>${disponible.getIsInteligente()}</td>
						<td>${disponible.getIsBajoConsumo()}</td>
						<td>${disponible.getConsumoPorHora()}</td>								
					</tr>
				</c:forEach>
			</table>
		</div>		
    </div>
  </div>
</div>


<script>
	var table = document.getElementById('table');
	for(var i = 1; i < table.rows.length; i++)
	{
		table.rows[i].onclick = function()
		{
            document.getElementById("fcodigo").value = this.cells[0].innerHTML;
            document.getElementById("fname").value = this.cells[1].innerHTML;
		};
	}    
</script>
</body>
</html>