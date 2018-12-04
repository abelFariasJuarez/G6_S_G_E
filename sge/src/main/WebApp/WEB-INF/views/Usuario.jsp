<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>User</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href=userCSS.css>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</head>
<script>
function myFunction(boton) {
    var boton1 = document.getElementById(boton);
   
      if (boton1.style.visibility === "hidden") {
        boton1.style.visibility = "visible";
    } else {
        boton1.style.visibility = "hidden";
    }
  }
  </script>


  <script>
function myFunctionx() {
    var selec1 = document.getElementById("fstCombo").value;
    var selec2 =document.getElementById("SndCombo").value;
    console.log(selec1);
    if (selec1 ==="Regla"){

      switch (selec2){
        case "Modificar":

         case "Eliminar":

          case "Crear":
      }

    }
    else{
       switch (selec2){
        case "Modificar":

         case "Eliminar":

          case "Crear":

    }

   }
  
  </script>

<body>


<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <p class="navbar-brand" >Panel Usuario</p>

  
  <div >
    <div class="navbar-nav">
    
    
      <form:form action="/demo/login/Usuario/mihogar" method="POST">
      <button type="submit" name="oculto"  class="btn btn-info solid" >Mi Hogar</button><span class="sr-only">(current)</span>
      </form:form>
      
      <form:form action="/demo/login/Usuario/consumoperiodo" method="POST">
      <button type="submit" name="oculto2"  class="btn btn-info solid" >Consumo Periodo</button><span class="sr-only">(current)</span>
      </form:form>
      
      <form:form action="/demo/login/Usuario/adddis" method="POST">
      <button type="submit"  class="btn btn-info solid"  >Carga Archivo Dispositivos</button><span class="sr-only">(current)</span>
      </form:form>
      
      <form:form action="/demo/login/Usuario/simplex" method="POST">
      <button type="submit" name="oculto4" class="btn btn-info solid"  >SimpleX</button><span class="sr-only">(current)</span>
      </form:form>
      
      <form:form action="/demo/login/Usuario/abm" method="POST">
      <button type="submit" name="oculto5" class="btn btn-info solid"  >ABM</button><span class="sr-only">(current)</span>
      </form:form>
      
      <a class="btn  btn-success" href="login" >Cerrar Sesion</a>
    </div>
  </div>
</nav>
 <c:forEach items="${clientes}" var="usuario">
 ${usuario}
 	 </c:forEach>
 <div>Web Application. Passed parameter : th:text="${message}"</div>
  <p>Usuario: ${user}</p>
    <p>Contraseña: ${password}</p>
 <c:forEach items="${clientes}" var="usuario">
 ${usuario.nombre} hola
 	 </c:forEach>
<div class="container text-light" >
  <div class="row" style="visibility:hidden" id="oculto">
     <div class="col align-self-start"   id="oculto" >
     
    <span class=" border border-danger">Consumo Ultimo Periodo : x </span>
    <br>Ultimas Mediciones:
     <table     class=" table table-dark "  >
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Valor</th>
     <tbody>
    <tr>
      <th scope="row">1</th>
      <td>14</td>
      
    </tr>
    </tr>
  </thead>
   </tbody>
</table>
     </div>
    <div class="col align-self-center"  >
          <br>Estado Dispositivos:

     <table     class=" table table-dark "  >
  <thead>
    <tr>
      <th scope="col">Dispositivo</th>
      <th scope="col">Estado</th>
     <tbody>
    <tr>
      <th scope="row">Aire Acondicionado</th>
      <td>Prendido</td>
      
    </tr>
    </tr>
  </thead>
   </tbody>
</table>
</div>

 <div class="col align-self-end">
     <br>Estado Reglas:
     <table     class=" table table-dark "  >
  <thead>
    <tr>
      <th scope="col">Regla</th>
      <th scope="col">Estado</th>
     <tbody>
    <tr>
      <th scope="row">PrenderDispositivo</th>
      <td>Encendido</td>
      
    </tr>
    </tr>
  </thead>
   </tbody>
</table>
  </div>
  </div>
</div>
</div>
  
  
 


<div class="container text-light" id="oculto2" style="visibility:hidden">
  <div class="row">
    <div class="col align-self-start">
        <label class="colorfont" >Desde:</label>
<input type="date" />
  </div>
    <div class="col align-self-center">
     <label class="colorfont" >Hasta:</label>
    <input type="date" />
    </div>
    <div class="col align-self-end">
  <button type="button">Ver reporte</button> 
    </div>
  </div>
</div>


<div class="container text-light" id="oculto3" style="visibility:hidden">
  <div class="row">
    <div class="col align-self-start"> 
    <input name="uploadedfile" type="select" />
    <br>
      <input name="uploadedfile" type="file" />
    </div>
    <div class="col align-self-center"> 

    <input type="submit" value="Enviar archivo" />
    </div>
   <div class="col align-self-end"> 
    
   </div>
</div>
</div>


<div class="container text-light" id="oculto4" style="visibility:hidden">
  <div class="row">
    <div class="col align-self-start">
     
  </div>
    <div class="col align-self-center">
   Simplex
    </div>
    <div class="col align-self-end">
 
    </div>
  </div>
</div>





<div class="container text-light" id="oculto5" style="visibility:hidden">
  <div class="row">
    <div class="col align-self-start">
  <select id="fstCombo">
  <option value="Regla" >Regla</option>
  <option value="Dispositivo">Dispositivo</option>
  </select>

   <select id="SndCombo">
  <option value="Crear">Crear</option>
  <option value="Modificar">Modificar</option>
  <option value="Borrar">Borrar</option>
</select>

<button type="button" onClick ="myFunctionx()" >Ejecutar</buton>

  </div>
    <div class="col align-self-center">
 

  </div>
    <div class="col align-self-end">
 
    </div>
    </div>
</div>





</body>
</html>
