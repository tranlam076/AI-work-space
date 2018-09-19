<%-- <%@page isELIgnored="true" %> --%>
<%@page import="tranlam.models.Image"%>
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
		pageContext.setAttribute("scope", "pageContext");
	%>
	Scope of the attribute:
	<%=pageContext.getAttribute("scope")%>
	<br /> Scope of the attribute by EL: ${pageScope.scope  }
	${requestScope.otherScope} ${sessionScope.otherScope }
	${applicationScope.otherScope }
	<br /> Scope of the attribute by EL:
	<!-- exception language -->
	${ 4 + 5 }
	<br /> Student name by EL:
	<!-- exception language: search EL IMPLICIT Object -->
	${requestScope.student.name }
	<%--<%=application.getInitParameter("account") %> --%>
	<br /> Account: ${initParam.account }
	<br /> Languages: ${requestScope.languages[2] }
	<br /> Other scope of the attribute:
	<%=pageContext.getAttribute("otherScope", PageContext.SESSION_SCOPE)%>
	<br /> Scope of the attribute:
	<%=pageContext.getAttribute("otherScope", PageContext.APPLICATION_SCOPE)%>
	<br /> Scope of the attribute:
	<%=pageContext.findAttribute("otherScope")%>
	<br /> Example of Standard Actions

	<br />
	<jsp:useBean id="student" type="tranlam.models.Student" class="tranlam.models.Student"
		scope="request"> <!-- use scope to get value for Bean, -->
		<jsp:setProperty property="name" name="student" value="Clover" />
		<!-- set value for bean if don't use scope -->
	</jsp:useBean>
	<%-- <jsp:setProperty property="name" name="student" value="Clover" /> --%>
		
	Get property name:
	<jsp:getProperty property="name" name="student" />




	<!-- finding all scpoe (page, request, applycation)  -->
	<br /> Image name:
	<%=((Image) request.getAttribute("imagename")).getName()%>
</body>
</html>