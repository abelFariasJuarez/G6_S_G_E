<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div class="container text-light">
  <div class="row">
    <div class="col align-self-start"> 

			
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