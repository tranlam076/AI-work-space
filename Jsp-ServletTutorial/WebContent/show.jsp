<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	The name image is: <%= request.getAttribute("imageName") %>
	
	<br/>
	<br/>
	<br/>
	
	<img alt="This is a show" src="images/img.jpg">
</body>
</html>