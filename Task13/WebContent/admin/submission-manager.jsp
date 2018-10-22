<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<%@include file="/templates/admin/inc/menu.jsp" %>  

<script type="text/javascript">
	var listSubmissions = [];
	<c:forEach items="${listSubs}" var="sub">
	listSubmissions.push({
		fieldName: "${sub.fieldName}",
		title: "${sub.title}",
		time: "${sub.createdAt}",
		detail: "${pageContext.request.contextPath}/admin/submissions?idSubmission=${sub.idSubmission}",
	});				
	</c:forEach>
</script>
<div class="content-right">
	<table class="submission">
		<tr>
			<td width="20%">
				<input type="checkbox" value="all">
				<select name="sorting" id="sort-by">
					<option>Sort by time</option>
					<option>Sort by title</option>
					<option>Sort by field</option>
				</select>
			</td>
			<td width="20%">title</td>
			<td width="20%">field</td>
			<td width="20%">date submit</td>
			<td width="20%">click for details</td>
		</tr>
	</table>
	<table class="detail-submission">
	</table>
	<div class="pagination">
	<%int currentPages = Integer.parseInt(request.getParameter("currentPage")); %>
	<%int totalPages = (int)request.getAttribute("totalPages");%>
	<a href="<%=request.getContextPath()%>/admin/submissions?requestPage=<%=currentPages-1%>">&laquo;</a>
	
		<a href="<%=request.getContextPath()%>/admin/submissions" class="active">1</a>
		
		<a href="<%=request.getContextPath()%>/admin/submissions">2</a>
		<a href="<%=request.getContextPath()%>/admin/submissions">3</a>
		<a href="<%=request.getContextPath()%>/admin/submissions">4</a>
		<a href="<%=request.getContextPath()%>/admin/submissions">5</a>
		<a href="<%=request.getContextPath()%>/admin/submissions">6</a>		
	<a href="<%=request.getContextPath()%>/admin/submissions?requestPage=<%=currentPages+1%>">&raquo;</a>
	</div>
</div>
<%@include file="/templates/public/inc/footer.jsp" %>  