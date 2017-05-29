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
<title>Change Permission of Directory Created by Another Manager</title>
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
<h1>Change Permission of Directory Created by Super Manager</h1>
</div>
<br/>


</div>
<br>
<div align="left">
<h4>
<%
String[] manager_id_array=(String[]) session.getAttribute("manager_id");
for(int i=0;i<manager_id_array.length-1;i++){%>
<a href="<%=request.getContextPath()%>/directoryController?flag=viewMedia&user_id=<%=manager_id_array[i] %>" ><%=manager_id_array[i] %></a> /<%}%> 
</h4>
</div>
<br/><br/>

<table align="center" border="1px" class="table table-bordered table-hover">
<tr><td><b>Directory Name</b></td><td><b>Directory Description</b></td><td><b>Permission Type</b></td><td><b>Created By</b></td><td><b>Change Permission Type</b></td></tr>
<c:forEach items="${requestScope.materiallistManager }" var="y">
<tr><td>${y.directory_name }</td><td>${y.directory_description }</td><td>${y.directory_access_permissions }</td><td>${y.user_id }</td><td><a href="<%=request.getContextPath() %>/directoryController?flag=changePermissionOfManager&directory_id=${y.directory_id }">Change</a></td></tr>
</c:forEach>


</table>





</body>
</html>