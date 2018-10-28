<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %> <!--  para usar jsp con el prefijo c  ( para condicionales o loops)-->
 <%@taglib uri="http://www.springframework.org/tags" prefix="spring" %><!--  para usar tags spring -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix= "fmt" %> <!-- para darle formato ejemplo fecha -->
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>${Admin.nombre}</h1>
<!-- para traer un objeto , nombre objeto(configurado en el homecontroller) y atributo que quiero traer -->
<!--
<c:forEach items="${LISTA } var="objeto"> -- no esta creado este ejemplo, es para recorrer una lista usando jstl(prefijo c como se indica arriba) --
<li>${objeto}</li>


</c:forEach>
<spring:url value="/resources" var="urlPublic" /> !--  apunta a la ruta resources --
<img src="$(urlPublic)/imagenes/${objeto.imagen }" /> !--  agrego imagen teniendo la ruta previamente que es resources/imagenes/laimagen , no esta hecho --

<fmt:formatDate value="${objeto.fecha}" pattern="dd-MM-yyyy" /> !--  le doy formato dia mes año --
<c:choose>!--  condicional --
<c:when test="${objeto.atributo == 'condcion' }" >  !-- if --
algo
</c:when>
<c:otherwise>
algo
</c:otherwise>
</c:choose> -->
</body>
</html>