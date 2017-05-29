<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/employee/">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Leave Request</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<link href="css/bootstrap.min.css" rel="stylesheet">
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
<h1>Leave Request</h1>
</div>
<form action="<%=request.getContextPath()%>/leaveRequestController?flag=add" method="post">
<input type="hidden" name="user_id" value="<%=session.getAttribute("userId") %>"/>
<input type="hidden" name="first_name" value="<%=session.getAttribute("firstName") %>"/>
<input type="hidden" name="last_name" value="<%=session.getAttribute("lastName") %>"/>
<table align="center">
<tr><td>Start Date*</td><td><input class="form-control" type='text' id='datepicker' name="date" required="required" class="form-control" placeholder='Select date (mm/dd/yyyy)' /><script>$('#datepicker').datepicker({ minDate: 1 });</script></td></tr>
<tr><td><br/></td></tr>
<tr><td>No of Days*</td><td><select class="form-control" name="days"><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option></select></td></tr>
<tr><td><br/></td></tr>
<tr><td>Leave Type*</td><td><input class="form-control" type="text" name="type" placeholder="Enter Leave Type"/></td></tr>
<tr><td><br/></td></tr>
<tr><td>Leave Description*</td><td><textarea class="form-control" rows="5" cols="50" name="description" placeholder="leave Description"></textarea></td></tr>
<tr><td><br/></td></tr>
<tr><td><input type="Submit" class="btn btn-primary" value="Submit Leave Request"/></td></tr>
</table>
</form>

</div>
</body>
</html>