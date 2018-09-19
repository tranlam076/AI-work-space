<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="detail.jsp" method="get">
		student Name: <input type="text" name="studentname"> <br />
		Region: <select name="region">
			<option value="asia">Asia</option>
			<option value="america">America</option>
			<option value="europe">Europe</option>
			<option value="africa">Africa</option>
		</select> <br /> <input type="submit" value="Ok">
	</form>

</body>
</html>