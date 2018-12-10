<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p>Welcome Admin</p>	
	<form action="${pageContext.request.contextPath}/FileUploadController"
		method="post" enctype="multipart/form-data">
		<input name="file" type="file" required="required"> 
		<input type="submit" value="Submit" />
	</form>
	
	<form action="${pageContext.request.contextPath}/LogoutController"
		method="post">
		<input type="submit" value="Logout" />
	</form>
</body>
</html>