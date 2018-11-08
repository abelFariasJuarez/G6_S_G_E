<!DOCTYPE html>
<html>
<head>
	<title>Ejemplo Leaflet</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<link rel="stylesheet" href="https://unpkg.com/leaflet@1.3.4/dist/leaflet.css">
    <script src="https://unpkg.com/leaflet@1.3.4/dist/leaflet.js"></script>
</head>
<body style="padding-left:20px;padding-right: 20px; ">	
	
		<h2 class="title" style="color: #7a7f87; text-align: center;">Ejemplo Mapa con leaflet y open street map</h2>
		<hr>

	</div>
	 <div id="mapa" style="height: 500px">
	 	
	 </div>
	 <hr>
	 <div>
	 <h3>Instructivo</h3>
	 <ul>
		 <li><strong>Crear Mapa:</strong> 
		 	<ol>
		 		<li>Incluimos Jquery, leaflet.css, leaflet.js; en ese orden (es recomendable incluir los archivos en la carpeta del proyecto y no usar CDN.</li>
		 		<pre>
		 			<xmp>
		 				<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
						<link rel="stylesheet" href="https://unpkg.com/leaflet@1.3.4/dist/leaflet.css">
					    <script src="https://unpkg.com/leaflet@1.3.4/dist/leaflet.js"></script>
		 			</xmp>
		 		</pre>
		 		<li>Crear Div para el mapa asignando un alto</li>
			 	<pre>
			 		<xmp>
						<div id="mapa" style="height: 500px">
						</div> 
					</xmp>
			 	</pre>
			 	<li>Tomamos el div como elemento para crear en él un mapa y agregamos el mapa de OSM</li>
			 	<pre>
			 		<xmp>
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
						}
						</script>
			 		</xmp>
			 		* 'mapa' es el id del div que va a ser contenedor.
			 	</pre>
			 	<li>Estamos listos para crear marcadores, poligonos o cualquier otra figura en él.</li>
			 	<p>A los elementos creados se les puede agregar eventos; por ejemplo: click, hover, etc.</p>
			 	<p>Es muy importante agregar cada elemento creado al mapa: ' .addTo(mapa); ' </p>
			 	<pre>
			 		<xmp>
			 			<script type="text/javascript">
			 					// Un punto
								var marker2 = L.marker([-34.659438,-58.4704135]).addTo(mapa).on('click', onClick);
								//Un poligono
								var polygon = L.polygon([
									[-34.697878, -58.468897],
								    [-34.686919, -58.486813],
								    [-34.651905, -58.530758],
								    [-34.65924, -58.418313]
								],{
									 color: 'red',
									 fillColor: 'blue',
								    fillOpacity: 0.1
								}).addTo(mapa);
								//Un circulo
								var circle = L.circle([-34.697878, -58.468897], {
										    color: 'blue',
										    fillColor: '#f03',
										    fillOpacity: 0.5,
										    radius: 500
										}).addTo(mapa);
			 			</script>
			 		</xmp>
			 	</pre>
		 	</ol>
		 </li>
	 </ul>
	<h3>Documentación</h3>
	<ul>
		<li><a href="https://leafletjs.com/">Leaflet:</a> es una biblioteca <strong>JavaScript</strong> open source para crear mapas interactivos, agregar controles, marcadores y Poligonos.</li>
		<li><a href="https://www.openstreetmap.org/#map=4/-40.44/-63.59">OpenStreetMap:</a> es un proveedor de mapas. Ademas de permitir crear y editar mapas ya existentes, posee APIs que permiten a Leaflet cargar de a porciones el mapa en su contenedor.</li>
	</ul> 	
	<h3>Ver támbien:</h3>
	<ul>
		<li><a href="https://github.com/Leaflet/Leaflet.Icon.Glyph">Leaflet Icon Glyph:</a> Permite cambiar el icono por defecto de los marcadores.</li>
		<li><a href="https://github.com/Leaflet/Leaflet.markercluster">Marker Cluster:</a> Permite agrupar marcadores. A medida que nos acercamos, los marcadores se desagrupan.</li>
		<li><a href="https://github.com/Norkart/Leaflet-MiniMap">Mini Map:</a> Permite agregar un minimapa dentro del contenedor de Leaflet. El mismo permite el desplazamiento del mapa padre.</li>
	</ul>
	 </div>
</body>
<hr>
<footer style="text-align: center;background-color:grey;color:white">
	Catedra Diseño de Sistemas - UTN FRBA 2018
</footer>
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
		var marker = L.marker([-34.598494, -58.420186]).addTo(mapa).bindPopup("<button class='btn btn-info' onclick='alert(\"Soy medrano\")'>No me toques(o Si)</button>");
		
		var marker2 = L.marker([-34.659438,-58.4704135]).addTo(mapa).on('click', onClick);
		
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