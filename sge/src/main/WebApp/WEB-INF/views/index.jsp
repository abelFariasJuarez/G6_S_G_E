<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajax in Spring MVC</title>
<script
	src="${pageContext.request.contextPath }/resources/js/jquery-1.6.2.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$('#buttonDemo1').click(function() {
			$.ajax({
				type : 'GET',
				url : '/api/ajaxrest/demo1',
				success : function(result) {
					$('#result1').text(result);
				}
			});
		});

		$('#buttonDemo2').click(function() {
			var fullName = $('#fullName').val();
			$.ajax({
				type : 'GET',
				url : '/api/ajaxrest/demo2/' + fullName,
				success : function(result) {
					$('#result2').text(result);
				}
			});
		});

		$('#buttonDemo3').click(function() {
			$.ajax({
				type : 'GET',
				url : '/api/ajaxrest/demo3',
				dataType : 'json',
				contentType : 'application/json',
				success : function(result) {
					var s = 'Id: ' + result.id;
					s += '<br/>Name: ' + result.name;
					s += '<br/>Price: ' + result.price;
					$('#result3').html(s);
				}
			});
		});

		$('#buttonDemo4').click(function() {
			$.ajax({
				type : 'GET',
				url : '/api/ajaxrest/demo4',
				dataType : 'json',
				contentType : 'application/json',
				success : function(result) {
					var s = '';
					for (var i = 0; i < result.length; i++) {
						s += '<br/>Id: ' + result[i].id;
						s += '<br/>Name: ' + result[i].name;
						s += '<br/>Price: ' + result[i].price;
						s += '<br/>======================';
					}
					$('#result4').html(s);
				}
			});
		});

	});
</script>
</head>
<body>

	<fieldset>
		<legend>Demo 1</legend>
		<input type="button" value="Demo 1" id="buttonDemo1" />
		<br/>
		<span id="result1"></span>
	</fieldset>

	<fieldset>
		<legend>Demo 2</legend>
		Full Name <input type="text" id="fullName" />
		<br/>
		<input type="button" value="Demo 2" id="buttonDemo2" />
		<br/>
		<span id="result2"></span>
	</fieldset>

	<fieldset>
		<legend>Demo 3</legend>
		<input type="button" value="Demo 3" id="buttonDemo3" />
		<br/>
		<span id="result3"></span>
	</fieldset>

	<fieldset>
		<legend>Demo 4</legend>
		<input type="button" value="Demo 4" id="buttonDemo4" />
		<br/>
		<div id="result4"></div>
	</fieldset>

</body>
</html>