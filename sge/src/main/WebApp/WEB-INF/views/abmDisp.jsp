<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div class="container text-light" id="oculto3">
  <div class="row">
    <div class="col align-self-start"> 
    	<h1>Dispositivos</h1>
    	<table  class="table table-dark ">
						
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
						<c:forEach items="${dispositivos}" var="dispositivo">
							<tr id ="${dispositivo.codigo}" >
							<td><input  id="${dispositivo.nombre}" class="fila" type="checkbox" onClick=myFunction("${dispositivo.codigo}") name="chk" /> </td>
								<td>${dispositivo.codigo}</td>
								<td>${dispositivo.nombre}</td>
								<td>${dispositivo.isInteligente}</td>
								<td>${dispositivo.isBajoConsumo}</td>
								<td>${dispositivo.consumoPorHora}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
    </div>
    <div class="col align-self-center"> 
    </div>
    
   	<div class="col align-self-end"> 
   		<form:form action="/demo/login/Usuario/abmDisp/nuevoDisp" method="POST">
   		<button type="submit" name="NuevoDisp" class="btn btn-dark" > Agregar </button> 
   		</form:form>
   		<form:form action="/demo/login/Usuario/abmDisp/modifDisp" method="POST">
   		<button type="submit" name="ModifDisp" class="btn btn-dark" > Modificar </button> 
   		</form:form>
   		<form:form  method="POST">
   		<button type="submit" name="EliminDisp" class="btn btn-dark" > Eliminar </button>  
		</form:form>   	
   	</div>
  </div>
</div>
</body>
</html>