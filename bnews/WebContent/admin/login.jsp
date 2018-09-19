<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<!-- Form elements -->    
<div class="grid_12">
<%
	if(request.getParameter("msg")!=null){
		int msg = Integer.parseInt(request.getParameter("msg"));
		if(msg == 1){
			out.print("<h5 style='color:red'>Sai tên đăng nhập hoặc mật khẩu</h5>");
		} 
	}
%>
	<div class="module">
		 <h2><span>Thêm danh mục</span></h2>
		 <div class="module-body">
		 
			<form method="POST" action="<%=request.getContextPath() %>/admin/login">
				
				<p>
					<label>Tên đăng nhập</label>
					<input type="text" name="username" value="" class="input-medium" />
				</p>
				<p>
					<label>Mật khẩu</label>
					<input type="password" name="password" value="" class="input-medium" />
				</p>
				<fieldset>
					<input class="submit-green" type="submit" name="login" value="Đăng nhập" /> 
				</fieldset>
				
			</form>
		 </div> <!-- End .module-body -->

	</div>  <!-- End .module -->
	<div style="clear:both;"></div>
</div> <!-- End .grid_12 -->
<%@include file="/templates/admin/inc/footer.jsp" %> 