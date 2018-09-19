<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>

<%
	if(request.getParameter("msg")!=null){
		int msg = Integer.parseInt(request.getParameter("msg"));
		if(msg == 1){
			out.print("<h5 style='color:red'>Đăng nhập thành công</h5>");
		} 
	}
%>            
<!-- Dashboard icons -->
<div class="grid_main_l">
	<a href="<%=request.getContextPath() %>/admin/show-addNews" class="dashboard-module">
		<img src="<%=request.getContextPath() %>/templates/admin/images/Crystal_Clear_write.gif" width="64" height="64" alt="edit" />
		<span>Thêm tin tức</span>
	</a>
	
	<a href="<%=request.getContextPath() %>/admin/show-addCat" class="dashboard-module">
		<img src="<%=request.getContextPath() %>/templates/admin/images/Crystal_Clear_files.gif" width="64" height="64" alt="edit" />
		<span>Thêm Danh mục</span>
	</a>
	<div style="clear: both"></div>
</div> <!-- End .grid_7 -->

<!-- Account overview -->
<div class="grid_main_r">
	<div class="module">
			<h2><span>Quản trị hệ thống</span></h2>
			
			<div class="module-body">
				<p class="p">
					<strong>Phần mềm</strong> được viết trên nền tảng PHP&MySQL <br />
					<strong>Học viên thực hiện: </strong>Trần Nguyễn Gia Huy<br />
					<strong>Email: </strong>huytng@vinatab.net<br /> 
					<strong>Phone: </strong>0909.123.456<br />
				</p>
			</div>
	</div>
	<div style="clear:both;"></div>
	<div class="padding-bottom"></div>
</div> <!-- End .grid_5 -->
<%@include file="/templates/admin/inc/footer.jsp" %>           