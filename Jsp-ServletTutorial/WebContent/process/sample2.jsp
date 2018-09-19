<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	This is a sample. <br/>
	This is Blog <%= config.getInitParameter("blog") %>
	<%-- <% config. request. response. application.getInitParameter (contextParam) //su dung giong ben servlet %> --%>
	
	<%out.print("Viet Nam"); %>
	<%-- <% pageContext.get... Exception. Session. %> --%>
	
</body>
</html>