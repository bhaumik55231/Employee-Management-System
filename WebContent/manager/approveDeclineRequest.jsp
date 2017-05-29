<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/manager/">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Approve Decline Leave requests</title>
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
<br/>
<div align="center">
<h1>Approve / Decline Leave Request</h1>
</div>
<br/><br/>


<table align="center" border="1px" class="table table-bordered table-hover"><br><br><br>
<tr><td><strong>First Name</strong></td><td><strong>Last Name</strong></td><td><strong>Leave Type</strong></td><td><strong>Leave Description / Reason</strong></td><td><strong>Date</strong></td><td><strong>No of Days</strong></td><td><strong>Manage Leave Request</strong></td></tr>
<c:forEach items="${sessionScope.leaveRequestList }" var="x">
<tr><td>${x.first_name }</td><td>${x.last_name }</td><td>${x.leave_type }</td><td>${x.leave_description }</td><td>${x.date }</td><td>${x.days }</td><td><a href="<%=request.getContextPath()%>/leaveRequestController?flag=approve&leave_id=${x.leave_id}&days=${x.days }&user_id=<%=session.getAttribute("userId")%>">Approve</a> / <a href="<%=request.getContextPath()%>/leaveRequestController?flag=decline&leave_id=${x.leave_id}&user_id=<%=session.getAttribute("userId")%>">Decline</a></td></tr>
</c:forEach>
</table>

</div>

</body>
</html>