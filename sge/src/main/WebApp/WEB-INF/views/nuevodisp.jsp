<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
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
			<select name="dispo" size="10" width="1000">
				
				<c:forEach items="${disponibles}" var="disponible">
				<tr>
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
   		</form:form>
   		<form:form action="/demo/login/Usuario/abmDisp" method="POST">
   		<button type="submit" name="cancel" class="btn btn-dark" > Cancelar </button> 
   		</form:form>
   	   	</div>
  </div>
</div>
</body>
</html>