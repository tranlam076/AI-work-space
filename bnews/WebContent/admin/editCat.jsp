<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<!-- Form elements -->    
<div class="grid_12">

	<div class="module">
		 <h2><span>Sửa</span></h2>
		 <div class="module-body">
		 <%
			if(request.getAttribute("objCat")!= null){
		 	Category objCat = (Category)request.getAttribute("objCat");
		 %>
			<form method="POST" action="<%=request.getContextPath() %>/admin/editCat?cid=<%=objCat.getId_cat() %>">
				
				<p>
					<label>Tên danh mục</label>
					<input type="text" id="name" name="name" value="<%=objCat.getName() %>" class="input-medium" />
				</p>
				<fieldset>
					<input class="submit-green" type="submit" value="Sửa" /> 
					<input class="submit-gray" name="reset" type="reset" value="Nhập lại" />
				</fieldset>
				
			</form>
			<%} %>
		 </div> <!-- End .module-body -->

	</div>  <!-- End .module -->
	<div style="clear:both;"></div>
</div> <!-- End .grid_12 -->
<%@include file="/templates/admin/inc/footer.jsp" %> 