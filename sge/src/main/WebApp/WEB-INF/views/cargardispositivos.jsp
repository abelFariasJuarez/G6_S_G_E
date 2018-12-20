<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>
<body>

<div class="container text-light" >
  <div class="row">
    <div class="col align-self-start"> 
    <h1>Dispositivos disponibles</h1>
    	<table id="oculto" class="table table-dark ">
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
							<tr>

								<td>${disponible.nombre}</td>
								<td>${disponible.codigo}</td>
								<td>${disponible.isInteligente }</td>
								<td>${disponible.isBajoConsumo}</td>
								<td>${disponible.consumoPorHora }</td>
								<td>${disponible.codigoRestriccionHoras }<td>
							</tr>
						</c:forEach>
					</tbody>
				</table>	
			
    </div>
    <div class="col align-self-center"> 
    <select name="dispo" size="2">
				
				<c:forEach items="${disponibles}" var="disponible">
							<option>${disponible.nombre}</option>
						
						</c:forEach>
			</select>
						
    </div>
 
   	<div class="col align-self-end"> 
   		<form:form action="/demo/login/Usuario/abmReg/nuevaReg?dispositivo=${dispositivo}" method="POST">
   		<button type="submit" name="NuevoDisp" class="btn btn-dark" > Seleccionar </button> 
   		</form:form>
   		<form:form action="/Cliente" method="POST">
   		<button type="submit" name="cancel" class="btn btn-dark" > Cancelar </button> 
   			</form:form>
   	   	</div>

  </div>
</div>
</body>
</html>