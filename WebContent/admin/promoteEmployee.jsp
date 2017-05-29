<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/admin/">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>promote Employees</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</head>
<body>
<%

if(session.getAttribute("type")==null){
	response.sendRedirect("../employee/signin.jsp");
}
else if(session.getAttribute("type")!= null && session.getAttribute("type").equals("employee")){
	response.sendRedirect("../employee/allContentAdmin.jsp");
}
else if(session.getAttribute("type")!= null && session.getAttribute("type").equals("manager")){
	response.sendRedirect("../manager/allContentManager.jsp");
}

String error=(String)request.getAttribute("error");
if(error != null){
	out.print("<p>"+error +"</p>");
}
%>
<div class="form-group">
<%@include file="adminHeader.jsp" %>
<div align="center">
<h1>Promote Employees</h1>
<br><br>
</div>
<br>
<table align="center" border="1px" class="table table-bordered table-hover">

<tr><td align="center"><b>First Name</b></td><td align="center"><b>Last Name</b></td><td align="center"><b>Email Id</b></td><td align="center"><b>User Id</b></td><td align="center"><b>Password</b></td><td align="center"><b>Promote to Manager level</b></td></tr>
<c:forEach items="${requestScope.fetch_active_employee }" var="x">
<tr><td>${x.first_name}</td><td>${x.last_name}</td><td>${x.email_id }</td><td>${x.user_id }</td><td>${x.password }</td><td><a href="<%=request.getContextPath()%>/assignManagerController?flag=makeManager&user_id=${x.user_id}&first_name=${x.first_name}">Make him / her Manager</a></td></tr>
</c:forEach>
</table>
</div>
</body>
</html>