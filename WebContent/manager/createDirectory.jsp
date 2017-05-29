<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/manager/">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Directory</title>
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
<br/><br/><br/>
<div align="center">
<h1>Create Directory</h1>
</div>
<div class="form-group">
<form action="<%=request.getContextPath() %>/directoryController?flag=create" method="post">
<input type="hidden" name="user_id" value="<%=session.getAttribute("userId") %>">
<table align="center">
<tr><td>Directory Access Permissions* </td><td><select class="form-control" name="access"><option value="public">Public</option><option value="private">Private</option><option value="default">Default</option><option value="protected">Protected</option></select></td></tr>
<tr><td><br></td></tr>
<tr><td>Directory Name* </td><td><input class="form-control" type="text" name="name" placeholder="Enter Directory Name"></td></tr>
<tr><td><br></td></tr>
<tr><td>Directory Description* </td><td><textarea class="form-control" rows="5" cols="50" name="description" placeholder="Enter Directory Description"></textarea></td></tr>
<tr><td><br></td></tr>
<tr><td colspan="2" align="center"><input type="submit" value="Create Directory" class="btn btn-primary">&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="Reset" class="btn btn-primary"></td></tr>
</table>
</form>
</div>
</div>
</body>
</html>