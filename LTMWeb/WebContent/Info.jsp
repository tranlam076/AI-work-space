<%@ page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		User user = (User) request.getAttribute("user");
		if (user != null) {
	%>
	<p>
		id:
		<%=user.getId()%></p>
	<p>
		info:
		<%=user.getInfo()%></p>
	<%
		} else {
	%>
	response.sendRedirect("LoginServlet");
	<%
		}
	%>
</body>
</html>