<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pay Statement</title>
<base href="${pageContext.request.contextPath}/employee/">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<link href="css/bootstrap.min.css" rel="stylesheet">

<script src="js/bootstrap.min.js"></script>

</head>
<body>

<%

if(session.getAttribute("type")==null){
	response.sendRedirect("signin.jsp");
}
else if(session.getAttribute("type")!= null && session.getAttribute("type").equals("admin")){
	response.sendRedirect("../admin/allContentAdmin.jsp");
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
<%@include file="employeeHeader.jsp" %>
<br/>
<div align="center">
<h1>View Pay Statement</h1>
</div>


<br><br>
<table border="1px" align="center"  class="table table-bordered table-hover">						
<tr><td><b>Date</b></td><td><b>First Name</b></td><td><b>Last name</b></td><td><b>Division Name</b></td><td><b>Salary per Month</b></td><td><b>Bonus for this month</b></td><td><b>Final Payment</b></td></tr>
<c:forEach items="${requestScope.salary_statement }" var="x">
<tr><td>${x.date }</td><td>${x.first_name }</td><td>${x.last_name }</td><td>${x.division_name }</td><td>${x.salary_per_month }</td><td>${x.bonus }</td><td>${x.total }</td></tr>
</c:forEach>
</table>
</div>
</body>
</html>