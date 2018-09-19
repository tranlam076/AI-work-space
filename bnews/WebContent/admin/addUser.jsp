<%@page import="model.bean.User"%>
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
		if(msg == 0){
			out.print("<h5 style='color:red'>Tên đăng nhập đã tồn tại, vui lòng chọn tên khác!</h5>");
		}
	}
%>
	<div class="module">
		 <h2><span>Thêm người dùng</span></h2>
		 <div class="module-body">
		 
			<form method="POST" action="<%=request.getContextPath() %>/admin/addUser">
				
				<p>
					<label>Tên người dùng</label>
					<input type="text" name="username" value="" class="input-medium" />
					<label>Mật khẩu</label>
					<input type="password" name="password" value="" class="input-medium" />
					<label>Họ và tên</label>
					<input type="text" name="fullname" value="" class="input-medium" />
				</p>
				<fieldset>
					<input class="submit-green" type="submit" value="Thêm" /> 
					<input class="submit-gray" name="reset" type="reset" value="Nhập lại" />
				</fieldset>
				
			</form>
		 </div> <!-- End .module-body -->

	</div>  <!-- End .module -->
	<div style="clear:both;"></div>
</div> <!-- End .grid_12 -->
<%@include file="/templates/admin/inc/footer.jsp" %> 