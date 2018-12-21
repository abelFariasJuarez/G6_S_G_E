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
			
			<h1>Dispositivos</h1>
				<select id="oculto68" class="table table-dark " size=10>

						<c:forEach items="${usuarioLogueado.dispositivos}" var="dispositivo">
							<tr>

								<option>${dispositivo.getNombre()}   ${dispositivo.getConsumoPorHora()}</option>
							</tr>
						</c:forEach>
				</select>
			</div>
			<div class="col col-sm align-self-center "></div>
			<div class="col align-self-end">
			<div class="row">
			<form:form action="/Cliente/ABMDispositivos/nuevodisp" method="POST">
   			<button type="submit" name="nuevodisp" class="btn btn-dark" > Agregar </button> 
   			</form:form>
   			</div>
   			<div class="row">
   			<form:form action="/Cliente/abmDisp/modifDisp" method="POST">
   			<button type="submit" name="ModifDisp" class="btn btn-dark" > Modificar </button> 
   			</form:form>
   			</div>
   			<div class="row">
   			<form:form action="/Cliente/ABMDispositivos?dispositivo=${dispositivo}" method="POST">
   			<button type="submit" name="EliminDisp" class="btn btn-dark" > Eliminar </button>  
			</form:form>   	
			</div>
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