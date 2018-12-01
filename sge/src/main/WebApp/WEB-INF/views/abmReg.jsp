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
    	<select name="dispo" size="2">
    			<c:forEach items="${dispositivos}" var="dispositivo">
				<option>${dispositivo.nombre}</option>
				</c:forEach>
		</select>
    </div>
    <div class="col align-self-center"> 
		<h1>Reglas</h1>
		<select name="reglas" size="2">
		<c:forEach items="${reglas}" var="regla">
		<option>${regla.nombre}</option>
		</c:forEach>
    </div>
   	<div class="col align-self-end"> 
   		<form:form method="POST">
   		<button type="submit" name="SelectDisp" class="btn btn-dark" > Seleccionar </button> 
   		</form:form>   
   		<form:form action="/demo/login/Usuario/abmDisp/nuevaReg" method="POST">
   		<button type="submit" name="NuevaRegla" class="btn btn-dark" > Nueva Regla </button> 
   		</form:form>   
   		<form:form action="/demo/login/Usuario/abmReg/modifReg" method="POST">
   		<button type="submit" name="ModifRegla" class="btn btn-dark" > Modificar </button> 
   		</form:form>   
   		<form:form  method="POST">
   		<button type="submit" name="EliminRegla"  class="btn btn-dark" > Eliminar </button>  
		</form:form>   
   	</div>
  </div>
</div>
</body>
</html>