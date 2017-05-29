<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/admin/">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Salary</title>
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
<h1>Generate PayCheck</h1>
<br><br>
</div>
<br>

<form action="<%=request.getContextPath()%>/employeeController?flag=updateSalaryEmployee" method="post">
<table align="center">
<c:forEach items="${requestScope.fetch_all_employee }" var="x">
<input type="hidden" name="salary_id" value="${x.salary_id }">
<tr><td>First Name</td><td><input disabled="disabled" class="form-control" type="text" value="${x.first_name }"></td></tr>
<tr><td><br></td></tr>
<tr><td>Last Name</td><td><input disabled="disabled" class="form-control" type="text" value="${x.last_name }"></td></tr>
<tr><td><br></td></tr>
<tr><td>User Id</td><td><input disabled="disabled" class="form-control" type="text" value="${x.user_id }"></td></tr>
<tr><td><br></td></tr>
<tr><td>Division Name</td><td><input disabled="disabled" class="form-control" type="text" value="${x.division_name }"></td></tr>
<tr><td><br></td></tr>
<tr><td>Role</td><td><input disabled="disabled" class="form-control" type="text" value="${x.user_type }"></td></tr>
<tr><td><br></td></tr>
<tr><td>Salary ($ per month)*</td><td><input class="form-control" type="text" value="${x.salary_per_month }" name="salary" placeholder="$ per Month"></td></tr>
<tr><td><br></td></tr>
<tr><td colspan="2" align="center"><input class="btn btn-primary" type="submit" value="Assign Salary"></td></tr>
<tr><td><br></td></tr>
</c:forEach>

</table>
</form>

</div>
</body>
</html>