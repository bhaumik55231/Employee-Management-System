<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign In</title>
<base href="${pageContext.request.contextPath}/employee/">
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
<%
String validateSession=(String)session.getAttribute("type");
if(validateSession != null){
	response.sendRedirect("loggedIn.jsp");
}
String error=(String)request.getAttribute("error");
if(error != null){
	out.print("<p>"+error +"</p>");
}
%>
<br><br>
<div class="form-group">
<div align="center"><h1>Login</h1></div>

<form action="<%=request.getContextPath()%>/login?flag=login" method="post">

<table align="center">
<tr><td><br></td></tr>
<tr><td>User Id</td>  <td><input class="form-control" type="text" name="userId" placeholder="Enter User Id"/></td></tr>
<tr><td><br></td></tr>
<tr><td>Password</td> <td><input class="form-control" type="password" name="password" placeholder="Enter password"/></td></tr>
<tr><td><br></td></tr>
<tr><td><input type="submit"  class="btn btn-primary" value="Sign - In"/></td><td>&nbsp;&nbsp;<a href="register.jsp"><input  class="btn btn-primary" type="button" value="Create Account"></a></td></tr>
</table>

</form>
</div>
</body>
</html>