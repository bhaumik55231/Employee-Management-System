<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/manager/">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Profile</title>
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
<table><tr><td><br></td></tr><tr><td align="left">&nbsp;&nbsp;&nbsp;<%=session.getAttribute("firstName") %></td><td width="1300px" align="right"><a href="<%=request.getContextPath()%>/logoutController"><input class="btn btn-danger" type="submit" value="Logout"></a>&nbsp;&nbsp;&nbsp;</td></tr></table>
<br/>
<div align="center">
<h1>Update Directory Permission</h1>
</div>
<br/><br/>
<form action="<%=request.getContextPath()%>/directoryController?flag=updateDirectoryPermission" method="post">
<table align="center">
<c:forEach items="${requestScope.particular_directory }" var="x">

<tr><td><input type="hidden" name="directory_id" value="${x.directory_id }" ></td></tr>
<tr><td><input type="hidden" name="directory_name" value="${x.directory_name }" ></td></tr>

<tr><td>Directory Name*</td><td><input class="form-control" type="text" disabled="disabled" value="${x.directory_name }"></td></tr>
<tr><td><br></td></tr>
<tr><td>Directory Description*</td><td><input class="form-control" type="text" disabled="disabled" value="${x.directory_description }"></td></tr>
<tr><td><br></td></tr>
<tr><td>User Id*</td><td><input class="form-control" type="text" disabled="disabled" value="${x.user_id }"></td></tr>
<tr><td><br></td></tr>
<tr><td>Select Access Permission*</td><td><select class="form-control" name="permission"><%if(request.getAttribute("permission").equals("public")) {%><option value="default">Default</option><option value="protected">Protected</option><option value="private">Private</option><%} 
else if(request.getAttribute("permission").equals("default")) {%><option value="protected">Protected</option><option value="private">Private</option><%} 
else {%><option value="private">Private</option><%} 
%></select></td></tr>
<tr><td><br></td></tr>
<tr><td colspan="2" align="center"><input class="btn btn-primary" type="submit" value="Update"></td></tr>
<tr><td><br></td></tr>
</c:forEach>

</table>
</form>
</div>
</body>
</html>