<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="Model.retrieveFile" %>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/manager/">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Give Access to ATE</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
<%

if(session.getAttribute("type")==null){
	response.sendRedirect("../employee/signin.jsp");
}
else if(session.getAttribute("type")!= null && session.getAttribute("type").equals("admin")){
	response.sendRedirect("../admin/allContentAdmin.jsp");
}
else if(session.getAttribute("type")!= null && session.getAttribute("type").equals("employee")){
	response.sendRedirect("../employee/allContentEmployee.jsp");
}

String error=(String)request.getAttribute("error");
if(error != null){
	out.print("<p>"+error +"</p>");
}
%>
<div class="form-group">
<%@include file="managerHeader.jsp" %>
<br/>
<div align="center">
<h1>Give Access to Another Team Employee</h1>
</div>
<br/><br/>

<form action="<%=request.getContextPath()%>/employeeController?flag=giveAccess" method="post">
<table align="center">
<tr><td>Select Directory Name*</td><td><select class="form-control" name="get_directory_id"><option disabled="disabled">Select Directory</option><c:forEach items="${requestScope.get_all_protected_directory }" var="y"><option value="${y.directory_id }" >${y.directory_name }</option></c:forEach></select></td></tr>
<tr><td><br></td></tr>
<tr><td>Select Employee*</td><td><select class="form-control" name="ate_id"><option disabled="disabled">Select Employee</option><c:forEach items="${requestScope.get_all_employee }" var="x"><option value="${x.user_id }">${x.first_name } ${x.last_name }</option></c:forEach></select></td></tr>
<tr><td><br></td></tr>
<tr><td colspan="2"><input type="Submit" class="btn btn-primary" value="Give Access"></td></tr>
</table>

</form>
</div>
</body>
</html>