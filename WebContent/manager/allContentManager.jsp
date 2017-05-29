<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/manager/">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manager Dashboard</title>
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

<table align="center">
<tr><td><br></td></tr>
<tr><td><a href="<%=request.getContextPath()%>/mediaController?flag=fetchMediaManager&user_id=<%=session.getAttribute("userId") %>"><input class="btn btn-info form-control" type="submit" value="Upload Media"/></a></td></tr>
<tr><td><br></td></tr>
<tr><td><a href="<%=request.getContextPath()%>/mediaController?flag=fetchMediaManager1&user_id=<%=session.getAttribute("userId") %>"><input class="btn btn-info form-control" type="submit" value="View Media"/></a></td></tr>
<tr><td><br></td></tr>
<tr><td><a href="<%=request.getContextPath()%>/employeeController?flag=fetchProtectedDirectory&user_id=<%=session.getAttribute("userId") %>"><input class="btn btn-info form-control" type="submit" value="Give Access to ATE"/></a></td></tr>
<tr><td><br></td></tr>
<tr><td><a href="createDirectory.jsp"><input class="btn btn-info form-control" type="submit" value="Create Directory"/></a></td></tr>
<tr><td><br></td></tr>
<tr><td><a href="<%=request.getContextPath()%>/directoryController?flag=getPublicDirectories"><input class="btn btn-info form-control" type="submit" value="Change Directory access permission"/></a></td></tr>
<tr><td><br></td></tr>
<tr><td><a href="<%=request.getContextPath()%>/directoryController?flag=anotherManagerDirectory&user_id=<%=session.getAttribute("userId") %>"><input class="btn btn-info form-control" type="submit" value="Change Permission Access of Directory Created by Super Manager"/></a></td></tr>
<tr><td><br></td></tr>
<tr><td><a href="<%=request.getContextPath()%>/updateProfileController?flag=updateProfileManager&user_id=<%=session.getAttribute("userId")%>"><input class="btn btn-info form-control" type="submit" value="Update Profile"/></a></td></tr>
<tr><td><br></td></tr>
<tr><td><a href="<%=request.getContextPath()%>/leaveRequestController?flag=availabilityManager&user_id=<%=session.getAttribute("userId")%>"><input class="btn btn-info form-control" type="submit" value="Request for Leave"/></a></td></tr>
<tr><td><br></td></tr>
<tr><td><a href="<%=request.getContextPath()%>/leaveRequestController?flag=fetchLeaveRequest&user_id=<%=session.getAttribute("userId")%>"><input class="btn btn-info form-control" type="submit" value="Accept / Decline Leave Request"/></a></td></tr>
<tr><td><br></td></tr>
<tr><td><a href="<%=request.getContextPath()%>/leaveRequestController?flag=viewStatusManager&user_id=<%=session.getAttribute("userId")%>"><input class="btn btn-info form-control" type="submit" value="View Leave Status"/></a></td></tr>
<tr><td><br></td></tr>
<tr><td><a href="<%=request.getContextPath()%>/employeeController?flag=assignBonus&user_id=<%=session.getAttribute("userId")%>"><input class="btn btn-info form-control" type="submit" value="Assign Bonus"/></a></td></tr>
<tr><td><br></td></tr>
<tr><td><a href="<%=request.getContextPath()%>/employeeController?flag=viewPayStatementManager&user_id=<%=session.getAttribute("userId")%>"><input class="btn btn-info form-control" type="submit" value="View Pay Statement"/></a></td></tr>
</table>
</div>


</body>
</html>