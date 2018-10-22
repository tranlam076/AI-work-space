<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<%@include file="/templates/admin/inc/menu.jsp" %>  
			<div class="content-right">
				<table class="submission">
							<tr>
								<td>${submission.idSubmission}</td>
								<td>${submission.idField}</td>
								<td>${submission.title}</td>
								<td>${submission.description}</td>
								<td>${submission.keywords}</td>
								<td> <a href="<%=request.getContextPath()%>/download?filename=${submission.fileNameUpload}">${submission.fileNameUpload}</a></td>
								
							</tr>
					</table>
			</div>
<%@include file="/templates/public/inc/footer.jsp" %>  