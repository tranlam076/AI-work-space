<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/CheckLogin" method="post">
		id = <input type="text" name="id"> <br>
		pass = <input type="password" name="pass"> <br>
		<input type="submit" value="Login">
	</form>
</body>
</html>