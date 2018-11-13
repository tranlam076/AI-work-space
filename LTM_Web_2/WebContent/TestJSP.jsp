<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		for (int i = 1; i <= 100; i++) {
	%>
	<p><%=i%><br />
	</p>
	<%
		}
	%>


</body>
</html>