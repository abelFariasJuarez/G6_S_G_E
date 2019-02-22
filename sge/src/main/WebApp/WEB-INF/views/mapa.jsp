<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<title>Mapa Consumo</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">	<link rel="stylesheet" href="https://unpkg.com/leaflet@1.3.4/dist/leaflet.css">
    <script src="https://unpkg.com/leaflet@1.3.4/dist/leaflet.js"></script>
</head>
<body style="padding-left:20px;padding-right: 20px; ">	
	
		<h2 class="title" style="color: #7a7f87; text-align: center;">Mapa Consumo</h2>
		<hr>

	 <div id="mapa" style="height: 500px">
	 	
	 	</div>
	 	
	 	
	
	<div class="container text-light">
  <div class="row">
    <div class="col align-self-start"> 
    <form:form method="POST" action="/mapa">
  <button type="submit" class="btn btn-info solid"  > Recargar transformadores</button> 
    <br>
     </form:form>
    </div>
    <div class="col align-self-center"> 

<c:forEach items="${transs}" var="trans">
${trans.ubicacion.latitud}
 </c:forEach>
    </div>
   <div class="col align-self-end"> 
   <form:form method="POST" action="/login">
     <button type="submit" class="btn btn-info solid" name="vlvmp"  > volver</button> 
      </form:form>
   </div>
</div>
</div>
	 
	
</html>
<script type="text/javascript">
	$( document ).ready(function() {
		mapa = L.map('mapa', {
	    center: [-34.598313, -58.463745],
	    zoom: 10,  
	    minZoom: 4,
	    maxZoom:17,
	    zoomControl:true 
		});
		L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
	    attribution: ''}).addTo(mapa);
	
		
		 <c:forEach items="${transs}" var="trans">
		//var marker = L.marker([${trans.ubicacion.latitud}, ${trans.ubicacion.longitud}]).addTo(mapa).bindPopup("<button class='btn btn-info' onclick='alert(" + ${trans.energiaSuministrada()} + ")'>Transformador</button>");
		var marker = L.marker([${trans.ubicacion.latitud}, ${trans.ubicacion.longitud}]).addTo(mapa).bindPopup("${trans.energiaSuministrada()}");
		  
		</c:forEach>
		
		
	  <c:forEach items="${zonas}" var="zona">
	    
	  var circle = L.circle([${zona.centro.latitud},${zona.centro.longitud}], {
		    color: 'blue',
		    fillColor: '#f03',
		    fillOpacity: 0.5,
		    radius:${zona.radio}
		}).addTo(mapa);
    
  </c:forEach>
		
		var polygon = L.polygon([
				[-34.697878, -58.468897],
			    [-34.686919, -58.486813],
			    [-34.651905, -58.530758],
			    [-34.614052, -58.529385],
			    [-34.552432, -58.499172],
			    [-34.533202, -58.46484],
			    [-34.582965, -58.381756],
			    [-34.634075, -58.35159],
			    [-34.65924, -58.418313]
			],{
				 color: 'red',
				 fillColor: 'blue',
			    fillOpacity: 0.1,
			    radius: 500
			}).addTo(mapa).on('click', onMapClick);
		function onMapClick(e) {
		    alert("Latitud y Longitud: " + e.latlng);
		}
	});
	function onClick(e){
		var r = confirm("Ir a medrano?");
		if (r == true) {
			mapa.setView(new L.LatLng((-34.598494), (-58.420186)), parseInt(17));
		} else {
		    
		}
	}
</script>