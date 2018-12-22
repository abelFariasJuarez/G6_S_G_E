<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div class="container text-light" >
  <div class="row">
    <div class="col align-self-start"> 
    	<h1>Dispositivos Disponibles</h1>
		<tr>
			<th></th>
			<th scope="col">Dispositivo</th>
			<th scope="col">Equipo Concreto</th>
			<th scope="col">¿Es inteligente?</th>
			<th scope="col">¿Es Equipo de bajo consumo?</th>
			<th scope="col">Consumo(Kwh)</th>	
			</tr>
			<select name="dispo" size="2" width="1000">
				
				<c:forEach items="${disponibles}" var="disponible">
							<option>${disponible.nombre} + ${disponible.codigo} + ${disponible.nombre} + {disponible.isInteligente} + ${disponible.isBajoConsumo} + ${disponible.consumoPorHora}</option>
							</tr>
						</c:forEach>
			</select>


			
    </div>
    <div class="col align-self-center"> 
    </div>
    
   	<div class="col align-self-end"> 
   		<form:form action="/demo/login/Usuario/abmReg/nuevaReg?dispositivo=${dispositivo}" method="POST">
   		<button type="submit" name="NuevoDisp" class="btn btn-dark" > Seleccionar </button> 
   		<form:form action="/demo/login/Usuario/abmDisp" method="POST">
   		<button type="submit" name="cancel" class="btn btn-dark" > Cancelar </button> 
   	   	</div>
  </div>
</div>
</body>
</html>