<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="Model.retrieveFile" %>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/admin/">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload File</title>
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
<h1>View & Upload Documents</h1>
<br><br>
</div>
<form action="<%=request.getContextPath()%>/upload?flag=uploadMediaAdmin" method="post" enctype="multipart/form-data">
<table align="center">
<tr><td>Upload file*</td><td><input class="form-control" type="file" name="fileName"></td></tr>
<tr><td><br></td></tr>
<tr><td>Description*</td><td><textarea class="form-control" name="description" rows="5" cols="50" placeholder="Write some description about the file...."></textarea></td></tr>
<tr><td><br></td></tr>
<tr><td colspan="2" align="center"><input class="btn btn-primary" type="Submit" value="Upload File"></td></tr>
</table>

</form>

<br><br><br><br>
<hr>
<br>
<table border="1px" align="center" class="table table-bordered table-hover">						
<tr><td><strong>Files</strong></td><td><strong>Description</strong></td></tr>
<c:forEach items="${requestScope.materiallistAdmin }" var="x">
				<tr><td><a href="<%=request.getContextPath()%>/viewController?materialid=${x.srno}">${x.file_name }</a></td><td>${x.description }</td></tr>
</c:forEach>
		</table>
</div>		
</body>
</html>