<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/admin/">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Organizational Profile</title>
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
<h1>Update Organizational Profile</h1>
<br><br>
</div>
<br>

<form action="<%=request.getContextPath()%>/updateProfileController?flag=updateOrgProfile" method="post">
<table align="center">
<c:forEach items="${requestScope.upd_org_profile }" var="x">

<tr><td><input type="hidden" name="userId" value="${x.user_id }" ></td></tr>
<tr><td>First Name*</td><td><input disabled="disabled" class="form-control" type="text" name="firstName" placeholder="Enter First Name" value="${x.first_name }"></td></tr>
<tr><td><br></td></tr>
<tr><td>Last Name*</td><td><input disabled="disabled" class="form-control" type="text" name="lastName" placeholder="Enter Last Name" value="${x.last_name }"></td></tr>
<tr><td><br></td></tr>
<tr><td>Division Name*</td><td><select name="division" class="form-control"><option disabled="disabled">${x.division_name }</option><option>Information Technology</option><option>Accounting</option><option>Financing</option><option>Marketing</option><option>Production</option></select></td></tr>
<tr><td><br></td></tr>
<tr><td>Role*</td><td><select name="role" class="form-control"><option disabled="disabled">${x.user_type }</option><option>manager</option><option>employee</option></select></td></tr>
<tr><td><br></td></tr>
<tr><td>Under The Supervision*</td><td><select name="supervisor_id" class="form-control"><option value=<%=session.getAttribute("immediate_manager_id") %>><%=session.getAttribute("manager_name") %></option><c:forEach items="${requestScope.manager_list }" var="y"><option value="${y.user_id }">${y.first_name } ${y.last_name }</option></c:forEach></select></td></tr>
<tr><td><br></td></tr>

<tr><td colspan="2" align="center"><input class="btn btn-primary" type="submit" value="Update"></td></tr>
<tr><td><br></td></tr>
</c:forEach>

</table>
</form>

</div>
</body>
</html>