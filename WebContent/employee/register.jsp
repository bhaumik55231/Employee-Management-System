<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/employee/">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>

<%
String validateSession=(String)session.getAttribute("userId");
if(validateSession != null){
	response.sendRedirect("loggedIn.jsp");
}
String error=(String)request.getAttribute("error");
if(error != null){
	out.print("<p>"+error +"</p>");
}
%>
<br><br>
<div align="center"><h1>Registration</h1></div>
<br><br>
<form action="<%=request.getContextPath()%>/login?flag=register" method="post">
<div class="form-group">
<table align="center">
<tr><td>First Name*</td><td><input class="form-control" type="text" name="firstName" placeholder="Enter First Name"></td></tr>
<tr><td><br></td></tr>
<tr><td>Last Name*</td><td><input class="form-control" type="text" name="lastName" placeholder="Enter Last Name"></td></tr>
<tr><td><br></td></tr>
<tr><td>Address*</td><td><textarea class="form-control" rows="2" cols="40" name="address" placeholder="Enter Your Address"></textarea></td></tr>
<tr><td><br></td></tr>
<tr><td>Phone No.*</td><td><input class="form-control" type="text" name="phone" placeholder="Enter Phone number"></td></tr>
<tr><td><br></td></tr>
<tr><td>Email Id*</td><td><input class="form-control" type="email" name="emailId" placeholder="abc@gmail.com"></td></tr>
<tr><td><br></td></tr>
<tr><td>User Id*</td><td><input class="form-control" type="text" name="userId" placeholder="Enter User Id"></td></tr>
<tr><td><br></td></tr>
<tr><td>Password*</td><td><input class="form-control" type="password" name="password" placeholder="Enter Password"></td></tr>
<tr><td><br></td></tr>
<tr><td>Confirm - Password*</td><td><input class="form-control" type="password" name="confirmPassword" placeholder="Re - enter Password"></td></tr>
<tr><td><br></td></tr>
<tr><td colspan="2" align="center"><input class="btn btn-primary" type="submit" value="Register"></td></tr>

</table>
</div>
</form>
</body>
</html>