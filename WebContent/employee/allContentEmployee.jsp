<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/employee/">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Dashboard</title>
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
<%@include file="employeeHeader.jsp" %>
<br/>
<div align="center">
<h1>Employee Dashboard</h1>
</div>
<br/>


<table align="center">
<%-- 
<tr><td><a href="<%=request.getContextPath()%>/mediaController?flag=fetchMediaEmployee&user_id=<%=session.getAttribute("userId") %>"><input class="btn btn-info form-control" type="submit" value="Upload Media"/></a></td></tr>
<tr><td><br></td></tr> --%>

<tr><td><a href="<%=request.getContextPath()%>/updateProfileController?flag=updateProfile&user_id=<%=session.getAttribute("userId")%>"><input class="btn btn-info form-control" type="submit" value="Update Profile"/></a></td></tr>
<%if(session.getAttribute("status")==null){ %>
<tr><td><br></td></tr>
<tr><td><a href="<%=request.getContextPath()%>/mediaController?flag=fetchMediaEmployee1&user_id=<%=session.getAttribute("userId") %>"><input class="btn btn-info form-control" type="submit" value="View Media"/></a></td></tr>

<tr><td><br></td></tr>
<tr><td><a href="<%=request.getContextPath()%>/leaveRequestController?flag=availability&user_id=<%=session.getAttribute("userId")%>"><input class="btn btn-info form-control" type="submit" value="Request for Leave"/></a></td></tr>
<tr><td><br></td></tr>
<tr><td><a href="<%=request.getContextPath()%>/leaveRequestController?flag=viewStatus&user_id=<%=session.getAttribute("userId")%>"><input class="btn btn-info form-control" type="submit" value="View Leave Status"/></a></td></tr>
<tr><td><br></td></tr>
<tr><td><a href="<%=request.getContextPath()%>/employeeController?flag=viewPayStatement&user_id=<%=session.getAttribute("userId")%>"><input class="btn btn-info form-control" type="submit" value="View Pay Statement"/></a></td></tr>
<%} %>
</table>
</div>
</body>
</html>