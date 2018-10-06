<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<%@include file="/templates/admin/inc/menu.jsp" %>  
			<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9 content-right">
				<form action="<%=request.getContextPath()%>/file-upload" method="post" enctype="multipart/form-data">
					<input type="text" name="description" /> <input type="file"
						name="file" /> <input type="submit" />
				</form>
				<%String a = (((String)request.getAttribute("message"))==null)? "" : ((String)request.getAttribute("message"));%>
				<p><%=a %></p>
				
			</div>
		</div>
<%@include file="/templates/public/inc/footer.jsp" %>  