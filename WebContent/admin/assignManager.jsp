<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/admin/">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign Manager</title>
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
%><div class="form-group">
<%@include file="adminHeader.jsp" %>
<div align="center">
<h1>Assign Employee under any Manager</h1>
<br><br>
</div>
<br>
<form action="<%=request.getContextPath()%>/assignManagerController?flag=manager_assigned" method="post">
<table align="center">

<tr><td>Manager Name</td>
<td><select class="form-control" name="manager">
<c:forEach items="${requestScope.fetch_manager }" var="i">
<option class="form-control" value="${i.user_id }">${i.first_name } ${i.last_name }  </option>
</c:forEach>
</select></td></tr>
<tr><td><br></td></tr>
<tr><td>Employee Name</td><td><select class="form-control" name="employee">
<c:forEach items="${requestScope.fetch_employee }" var="x">
<option class="form-control" value="${x.user_id }">${x.first_name } ${x.last_name }  </option>
</c:forEach>
</select></td></tr>
<tr><td><br></td></tr>
<tr><td colspan="2"><input class="btn btn-primary" type="submit" value="Assign Manager"></td></tr>
</table>
</form>
</div>
</body>
</html>