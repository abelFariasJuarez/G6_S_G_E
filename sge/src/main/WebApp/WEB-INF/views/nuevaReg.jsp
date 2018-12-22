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

<div class="container text-light">
  <div class="row">
    <div class="col align-self-start"> 
    <h2>Configure la condicion</h2><br>
    <h3>Valor esperado</h3><input type="text" name="username"><br>
    <h3>Tipo comparador</h3><br>
    <select id="oculto68" class="table table-dark " size=10>
		<c:forEach items="${usuarioLogueado}" var="dispositivo">
			<tr>
				<option>${dispositivos.getNombre()}   ${dispositivo.getConsumoPorHora()}</option>
			</tr>
		</c:forEach>
	</select>
	
    </div>
    
    <div class="col align-self-center"> 
    </div>
    
   	<div class="col align-self-end"> 
   		<form:form action="/demo/login/Usuario/abmDisp" method="POST">
   		<button type="submit" name="cancel" class="btn btn-dark" > Cancelar </button>
   		</form:form> 
   	   	</div>
  </div>
</div>
</body>
</html>