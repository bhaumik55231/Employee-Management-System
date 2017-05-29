<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/employee/">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registered</title>
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
%>
<div class="form-group">
Successfully registered - <%=request.getAttribute("first_name") %> <%=request.getAttribute("last_name") %>.

<a href="signin.jsp"><input class="btn btn-primary" type="button" value="Sign In"/></a>
</div>
</body>
</html>