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
	
		<div class="container tamanio bg-dark margen text-light"  >
			<h1>Restricciones de Horas por Familia de Dispositivo</h1>
			<div class="col align-self-end">
				<form:form action="/Administrador" method="GET">
					<button type=submit>Volver</button>
				</form:form>
			</div>
		  	<div class="row">
		    	<div class="col align-self-start"> 
	
		    	<table id="oculto4" class="table table-dark ">
			    	<thead>
						<tr>
							<th scope="col">Codigo</th>
							<th scope="col">Horas Maximas de uso</th>
							<th scope="col">Horas Minimas de uso</th>
						</tr>
					</thead>
					<tbody>						
						<c:forEach items="${restriccionesHorasList}" var="restriccion">
							<tr>		
								<td>${restriccion.codigo}</td>
								<td>${restriccion.maximo}</td>
								<td>${restriccion.minimo}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		    </div>		
		  </div>
		</div>
	</body>
</html>