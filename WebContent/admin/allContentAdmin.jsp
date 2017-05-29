<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/admin/">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Dashboard</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
<%



String error=(String)request.getAttribute("error");
if(error != null){
	out.print("<p>"+error +"</p>");
}
%>
<div class="form-group">
<%@include file="adminHeader.jsp" %>

<table align="center">
<tr><td><br></td></tr>
<tr><td><a href="<%=request.getContextPath()%>/employeeController?flag=inactive"><input type="submit" class="btn btn-info form-control" value="View Newly Registered & Inactive Employee"/></a></td></tr>
<tr><td><br></td></tr>
<tr><td><a href="<%=request.getContextPath()%>/employeeController?flag=active"><input type="submit" class="btn btn-info form-control" value="View Active Employees and Managers"/></a></td></tr>
<tr><td><br></td></tr>
<%-- <tr><td><a href="<%=request.getContextPath()%>/mediaController?flag=fetchMediaForAdmin&user_id=<%=session.getAttribute("userId") %>"><input class="btn btn-info form-control" type="submit" value="View Media"/></a></td></tr>
<tr><td><br></td></tr> --%>
<tr><td><a href="<%=request.getContextPath()%>/assignManagerController?flag=fetch_employees"><input class="btn btn-info form-control" type="submit" value="Assign Manager"/></a></td></tr>
<tr><td><br></td></tr>
<%-- <tr><td><a href="<%=request.getContextPath()%>/employeeController?flag=promoteEmployee"><input class="btn btn-info form-control" type="submit" value="Promote an Employee"/></a></td></tr>
<tr><td><br></td></tr> --%>
<tr><td><a href="<%=request.getContextPath()%>/employeeController?flag=organizationalEmployee"><input class="btn btn-info form-control" type="submit" value="Update Organizational Profile of an Employee"/></a></td></tr>
<tr><td><br></td></tr>
<tr><td><a href="<%=request.getContextPath()%>/employeeController?flag=generatePaycheck"><input class="btn btn-info form-control" type="submit" value="Generate PayChecks"/></a></td></tr>
<tr><td><br></td></tr>
<tr><td><a href="<%=request.getContextPath()%>/employeeController?flag=updateSalary"><input class="btn btn-info form-control" type="submit" value="Update Salary"/></a></td></tr>
<tr><td><br></td></tr>
<tr><td><a href="<%=request.getContextPath()%>/carryForwardController?flag=carryForward"><input class="btn btn-warning form-control" type="submit" value="Carry Forward Leaves and Generate salary on 1st of Each Month"/></a></td></tr>

</table>

</div>

</body>
</html>