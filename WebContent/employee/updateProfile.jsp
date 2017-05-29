<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/employee/">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Profile</title>
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
<h1>Update Your Profile</h1>
</div>
<br/><br/><br/><br/>
<form action="<%=request.getContextPath()%>/updateProfileController?flag=updateUserProfile" method="post">
<table align="center">
<c:forEach items="${requestScope.upd_profile }" var="x">

<tr><td><input type="hidden" name="userId" value="${x.user_id }" ></td></tr>
<tr><td>First Name*</td><td><input class="form-control" type="text" name="firstName" placeholder="Enter First Name" value="${x.first_name }"></td></tr>
<tr><td><br></td></tr>
<tr><td>Last Name*</td><td><input class="form-control" type="text" name="lastName" placeholder="Enter Last Name" value="${x.last_name }"></td></tr>
<tr><td><br></td></tr>
<tr><td>Address*</td><td><textarea rows="2" cols="50" class="form-control" name="address">${x.address }</textarea></td></tr>
<tr><td><br></td></tr>
<tr><td>Phone no.*</td><td><input type="text" class="form-control" name="phone" value="${x.phone }"/></td></tr>
<tr><td><br></td></tr>
<tr><td>Email Id*</td><td><input type="email" name="emailId" class="form-control" placeholder="abc@gmail.com" value="${x.email_id }"></td></tr>
<tr><td><br></td></tr>
<tr><td>Password*</td><td><input type="password" class="form-control" name="password" placeholder="Enter Password" value="${x.password }"></td></tr>
<tr><td><br></td></tr>
<tr><td>Confirm - Password*</td><td><input type="password" class="form-control" name="confirmPassword" placeholder="Re - enter Password" value="${x.password }"></td></tr>
<tr><td><br></td></tr>
<tr><td colspan="2" align="center"><input type="submit" class="btn btn-primary" value="Update"></td></tr>

</c:forEach>

</table>
</form>
</div>
</body>
</html>