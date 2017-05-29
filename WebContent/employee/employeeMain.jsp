<%@page import="Model.retrieveFile"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/employee/">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload File</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
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
<h1>Upload & view Documents</h1>
</div>

<br/>
<form action="<%=request.getContextPath()%>/upload?flag=uploadMediaEmployee" method="post" enctype="multipart/form-data">
<table align="center">
<tr><td>Upload file*</td><td><input class="form-control" type="file" name="fileName"></td></tr>
<tr><td><br></td></tr>
<tr><td>Description*</td><td><textarea class="form-control" name="description" rows="5" cols="50" placeholder="Write some description about the file...."></textarea></td></tr>
<tr><td><br></td></tr>
<tr><td colspan="2" align="center"><input class="btn btn-primary" type="Submit" value="Upload File"></td></tr>
</table>

</form>
<hr>
<br>

<br>

<br>
<table border="1px" align="center"  class="table table-bordered table-hover">						
<tr><td>Files</td><td>Description</td><td>View / Download Document</td></tr>

<c:forEach items="${requestScope.materiallistEmployee }" var="x">
				<tr><td><a href="<%=request.getContextPath()%>/viewController?materialid=${x.srno}">${x.file_name }</a></td><td>${x.description }</td><td><a href="<%=request.getContextPath()%>/downloadController?flag=download&materialid=${x.srno}">View / Download</a></td></tr>
</c:forEach>
		</table>
</div>				
</body>
</html>