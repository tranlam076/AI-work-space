<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@include file="/templates/admin/inc/header.jsp"%>
<%@include file="/templates/admin/inc/menu.jsp"%>

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
	<div class="submission-controller">
		<select name="sorting" id="sort-by">
			<option>Sort by time</option>
			<option>Sort by title</option>
			<option>Sort by field</option>
		</select>
	</div>
	<div class="detail-submission">
	</div>
	<%
	int currentPage = Integer.parseInt(request.getParameter("currentPage"));
	%>
	<%
	int totalPage = (int) request.getAttribute("totalPage");
	%>
	<input type="hidden" id="totalPage" value="<%=totalPage%>">
	<input type="hidden" id="currentPage" value="<%=currentPage%>">
	<input type="hidden" id="link" value="<%=request.getContextPath()%>/admin/submissions?requestPage=">
	<div class="pagination">
	</div>
</div>
<%@include file="/templates/public/inc/footer.jsp"%>
