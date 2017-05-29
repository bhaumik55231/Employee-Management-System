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
<h1>Update Your Profile Details</h1>
</div>
<br/><br/>
<form action="<%=request.getContextPath()%>/updateProfileController?flag=updateUserProfileManager" method="post">
<table align="center">
<c:forEach items="${requestScope.upd_profile }" var="x">

<tr><td><input type="hidden" name="userId" value="${x.user_id }" ></td></tr>
<tr><td>First Name*</td><td><input class="form-control" type="text" name="firstName" placeholder="Enter First Name" value="${x.first_name }"></td></tr>
<tr><td><br></td></tr>
<tr><td>Last Name*</td><td><input class="form-control" type="text" name="lastName" placeholder="Enter Last Name" value="${x.last_name }"></td></tr>
<tr><td><br></td></tr>
<tr><td>Address*</td><td><textarea class="form-control" rows="2" cols="50" name="address">${x.address }</textarea></td></tr>
<tr><td><br></td></tr>
<tr><td>Phone no.*</td><td><input class="form-control" type="text" name="phone" value="${x.phone }"/></td></tr>
<tr><td><br></td></tr>
<tr><td>Email Id*</td><td><input class="form-control" type="email" name="emailId" placeholder="abc@gmail.com" value="${x.email_id }"></td></tr>
<tr><td><br></td></tr>
<tr><td>Password*</td><td><input class="form-control" type="password" name="password" placeholder="Enter Password" value="${x.password }"></td></tr>
<tr><td><br></td></tr>
<tr><td>Confirm - Password*</td><td><input class="form-control" type="password" name="confirmPassword" placeholder="Re - enter Password" value="${x.password }"></td></tr>
<tr><td><br></td></tr>
<tr><td colspan="2" align="center"><input class="btn btn-primary" type="submit" value="Update"></td></tr>
<tr><td><br></td></tr>
</c:forEach>

</table>
</form>
</div>
</body>
</html>