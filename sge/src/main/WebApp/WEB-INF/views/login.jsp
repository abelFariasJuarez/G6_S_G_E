<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href=loginCSS.css>
</head>
<body class="text-center">
<div >
  <div class="container">
  <div class="row">
      <div class="col col-sm align-self-start"></div>
  	<div class="col col-sm align-self-center">
    	 
   <h1 class ="h1">Sistema de Gestion Electrica</h1>
<img src="logo.jpg">
    </div>
    <div class="col col-sm align-self-end"></div>
	</div>
</div>

  <div class="container">
  <div class="row">
    <div class="col col-sm align-self-start"></div>
  	<div class="col col-sm align-self-center">
    	 
    <h2 class ="col">Login</h2>
    </div>
    <div class="col col-sm align-self-end"></div>
	</div>
</div>

<div class="container">
  <div class="row">
    <div class="col col-sm align-self-start"></div>
  	<div class="col col-sm align-self-center">
    	 
    	
<div class="form">  
 <form:form   method="post">  
  <form:form  action="login" method="post">  
      
<input type="text" id="username" placeholder="Usuario" name="username" class= "form-control" />
  
<input type="password" id="password" placeholder="Password" name="password" class= "form-control" />

 <button id="LoginButton" class="btn btn-dark" > Sign in </button> 
</form:form> 
</form:form> 
</div>

   
    </div>
    <br></br>
     <span>${message}</span>
    <div class="col col-sm align-self-end"></div>
  </div>
</div>


<div class="container">

    <div class="col col-sm">
<footer>
 <a href=mapa class="btn btn-dark" >Mapa de consumo</a>
</footer>
  </div>
</div>
</div>

  ${loginError}

</body>
</html>