<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
   <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js" ></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<body>

</head>


<div class="login-wrap">   <h2>Login</h2>      
<form action="login" method="post">
<div class="form">  
<label>User Name:</label>  
 <input type="text" id="username" placeholder="Username" name="username" class= "form-control" />
 <label>Password:</label>   
<input type="password" id="password" placeholder="Password" name="password" class= "form-control" />    
 <button id="LoginButton"> Sign in </button> </div>
  </form>
 
  </div>

</body>
</html>