<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Student name:
	<%=request.getParameter("studentname")%>
	<br /> Student name by EL: ${param.studentname }
	<br /> Region by EL: ${param.region }
	<br /> Region by EL otherWay: ${param["region"]}
	<br />
	<jsp:useBean id="student"  class="tranlam.models.Student">
		<jsp:setProperty property="name" name="student" param="studentname" />
		<!-- set value for bean if don't use scope -->
	</jsp:useBean>
	<%-- <jsp:setProperty property="name" name="student" value="Clover" /> --%>
	Get property name:
	<jsp:getProperty property="name" name="student" />
</body>
</html>