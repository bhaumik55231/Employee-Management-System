<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Directory Access Permission</title>
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
<h1>Change Directory Access Permission</h1>
</div>


<br><br>
<table border="1px" align="center"  class="table table-bordered table-hover">						
<tr><td><b>Directory Name</b></td><td><b>Directory Description</b></td><td><b>Access Permission Type</b></td><td><b>User Id</b></td><td><b>Manage Access Permissions</b></td></tr>
<c:forEach items="${requestScope.all_public_directories }" var="x">
<tr><td>${x.directory_name }</td><td>${x.directory_description }</td><td>${x.directory_access_permissions }</td><td>${x.user_id }</td><td><a href="<%=request.getContextPath()%>/directoryController?flag=fetchDirectoryData&directory_id=${x.directory_id}&directory_name=${x.directory_name}">Update Access Permission</a></td></tr>
</c:forEach>
</table>
</div>
</body>
</html>