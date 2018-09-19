<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>sample</title>
</head>
<body>

	<%
		out.print("Hello world </br>");
		out.print("this is sample");
		int i = 3;
	%>
		value of i: <%=i %>
	<br/>
	<% pageContext.setAttribute("scope", "pageContext"); %>
	Scpoe of the attribute: <% pageContext.getAttribute("scope"); %>
		Scope of the attribute: <%= pageContext.findAttribute("scope") %>
	
	
</body>
</html>