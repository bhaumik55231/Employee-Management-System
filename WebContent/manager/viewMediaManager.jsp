<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="Model.retrieveFile" %>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/manager/">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload File</title>
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
<h1>View Documents</h1>
</div>
<br/>


</div>
<br>
<div align="left">
<h4>
<%
String[] manager_id_array=(String[]) session.getAttribute("manager_id");
for(int i=0;i<manager_id_array.length;i++){%>
<a href="<%=request.getContextPath()%>/mediaController?flag=viewMedia&manager_id=<%=manager_id_array[i] %>" ><%=manager_id_array[i] %></a> / 	
<%}%><a href="<%=request.getContextPath()%>/mediaController?flag=findMedia2&manager_id=<%=session.getAttribute("userId")%>">Documents uploaded by employee under this Manager</a> / 
<a href="<%=request.getContextPath()%>/mediaController?flag=allPublicManager">All public Directories</a>  <%if(session.getAttribute("ATE")!=null){ %>/
<a href="<%=request.getContextPath()%>/mediaController?flag=allAteFiles">View Directory Shared by ATE</a><%} %>
</h4>
</div>
<br/><br/>
<form action="<%=request.getContextPath()%>/mediaController?flag=uploadMediaManager" method="post" enctype="multipart/form-data">
<table align="center">
<tr><td>Select Directory Name*</td><td><select class="form-control" name="director_name"><option disabled="disabled">Select Directory</option><c:forEach items="${requestScope.materiallistManager }" var="y"><option value="${y.director_name }">${y.director_name }</option></c:forEach></select></td></tr>
<tr><td><br></td></tr>
<tr><td>Upload file*</td><td><input type="file" class="form-control" name="fileName"></td></tr>
<tr><td><br></td></tr>
<tr><td>Description*</td><td><textarea name="description" class="form-control" rows="5" cols="50" placeholder="Write some description about the file...."></textarea></td></tr>
<tr><td><br></td></tr>
<tr><td colspan="2"><input type="Submit" class="btn btn-primary" value="Upload File"></td></tr>
</table>

</form>

<br><br/>
<div align="center"><%if(session.getAttribute("Owner")!=null) {%>
<h5>Documents Uploaded by: <%=session.getAttribute("Owner") %></h5><%} %>
</div>
<%if(request.getAttribute("materiallistManager")!=null){ %>
<table border="1px" align="center"  class="table table-bordered table-hover">						
<tr><td><b>Directory Name</b></td><td><b>Permission Type</b></td><td><b>Files</b></td><td><b>File Description</b></td><td><b>Directory Created By</b></td><td><b>Uploaded By</b></td><td><b>View / Download Document</b></td></tr>

<c:forEach items="${requestScope.materiallistManager }" var="x">
				<tr><td>${x.director_name }</td><td>${x.access_permission }</td><td><a href="<%=request.getContextPath()%>/viewController?materialid=${x.srno}">${x.file_name }</a></td><td>${x.description }</td><td>${x.user_id }</td><td>${x.uploaded_by }</td><td><a href="<%=request.getContextPath()%>/downloadController?flag=download&materialid=${x.srno}">View / Download</a></td></tr>
</c:forEach>
</table><%} %>
</body>
</html>