<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Admin</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mihogar</title>
</head>
<body>





	<div class="container margen ">
		<div class="row">
			<div class="col align-self-start">
			
			<h1>Dispositivo:</h1>
				<select id="dispSeleccionado" onclick="function()" class="table table-dark ">

						<c:forEach items="${dispositivosInt}" var="dispositivo">
							<tr>
								<option>${dispositivo.getNombre()}</option>
							</tr>
						</c:forEach>
				</select>
			</div>
			<h2>Reglas del dispositivo
			<select id="reglas" class="table table-dark " size= 10>

			<c:forEach items="${reglas}" var="regla">
				<tr>
					<option>${regla.getName()}}</option>
				</tr>
			</c:forEach>
			</select>

			<script>
			function(){
			    $.ajax({
			        url : '/refresh?dispSeleccionado=${dispSeleccionado}',
			        method : 'GET',
			        async : false,
			        complete : function(data) {
			    	 $('#reglas').html(data);
			        }
			    });

			</script>
			<div class="col col-sm align-self-center "></div>
			<div class="col align-self-end">
			<div class="row">
	   		<form:form action="/Cliente/ABMReglas/SelectDisp?dispSeleccionado=${dispSeleccionado}" method="POST">
	   		<button type="submit" name="SelectDisp" class="btn btn-dark" > Seleccionar </button> 
	   		</form:form>
	   		</div>
	   		<div class="row">
	   		<form:form action="/Cliente/ABMReglas/nuevaReg" method="POST">
	   		<button type="submit" name="NuevaRegla" class="btn btn-dark" > Nueva Regla </button> 
	   		</form:form>
	   		</div>
	   		<div class="row">
	   		<form:form action="/Cliente/ABMReglas/modifReg" method="POST">
	   		<button type="submit" name="ModifRegla" class="btn btn-dark" > Modificar </button> 
	   		</form:form>
	   		</div>
	   		<div class="row">
	   		<form:form  method="POST">
	   		<button type="submit" name="EliminRegla"  class="btn btn-dark" > Eliminar </button>  
			</form:form>
			</div>
		</div>
	</div>
	
	
		<div class="container margen ">
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

</body>
</html>