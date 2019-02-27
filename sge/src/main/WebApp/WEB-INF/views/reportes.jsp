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
			<h1>${tituloReporte}</h1>
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
						<c:forEach items="${columnasReporte}" var="column">
							<th scope="col">${column}</th>
						</c:forEach>													
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${rows}" var="row">
							<tr>
								<c:choose>
									<c:when test="${codReporte=='hogares'}">
									<td>${row.oid}</td>
									<td>${row.username}</td>
									<td>${row.consumo}</td>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${codReporte=='dispositivos'}">
									<td>${row.deviceType}</td>
									<td>${row.promedio}</td>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${codReporte=='transdormadores'}">
									<td>${row.oid}</td>
									<td>${row.consumo}</td>
									</c:when>
								</c:choose>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		    </div>		
		  </div>
		</div>
	</body>
</html>