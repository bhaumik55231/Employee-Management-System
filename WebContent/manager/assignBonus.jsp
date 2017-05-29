<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/manager/">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign Bonus</title>
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

<br/><br/>
<div align="center">
<h1>Assign Bonus</h1>
</div>
<br/><br/>
<div class="form-group">
<form action="<%=request.getContextPath() %>/employeeController?flag=assignBonusEmployee" method="post">
<input type="hidden" name="user_id" value="<%=session.getAttribute("userId") %>">
<table align="center">
<tr><td>Select Manager / Employee* </td><td><select class="form-control" name="manager"><c:forEach items="${requestScope.list_immediate_employees }" var="x"><option value="${x.user_id }">${x.first_name } ${x.last_name }</option></c:forEach></select></td></tr>
<tr><td><br></td></tr>
<tr><td>Bonus Amount* </td><td><input class="form-control" type="text" name="bonus" placeholder="Bonus per Month"></td></tr>
<tr><td><br></td></tr>

<tr><td colspan="2" align="center"><input type="submit" value="Assign Bonus" class="btn btn-primary">&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="Reset" class="btn btn-primary"></td></tr>
</table>
</form>
</div>
</div>
</body>
</html>