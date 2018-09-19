<%@page import="model.bean.News"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<!-- Form elements -->    
<div class="grid_12">

	<div class="module">
		 <h2><span>Sửa tin tức</span></h2>
			
		 <div class="module-body">
		 	<%
				if(request.getAttribute("objNews")!=null){
				News objNews = (News)request.getAttribute("objNews");
			%>
			<form method="POST" action="<%=request.getContextPath() %>/admin/editNews?nid=<%=objNews.getId_news()%>" enctype="multipart/form-data">
				<p>
					<label>Tên tin</label>
					<input type="text" name="tentin" value="<%=objNews.getName() %>" class="input-medium" />
				</p>
				<p>
					<label>Danh mục tin</label>
					<select  name="danhmuc" class="input-short">
					<%
					if(request.getAttribute("listCat")!=null){
					ArrayList<Category> listCat = (ArrayList<Category>)request.getAttribute("listCat");
					String selected = "";
					if(listCat.size()>0){
					for(Category objCat : listCat){
						if(objCat.getId_cat() == objNews.getId_cat()){
							selected = "selected = 'selected'";
						} else {
							selected = "";
						}
					%>
						<option <%=selected %> value="<%=objCat.getId_cat()%>"><%=objCat.getName() %></option>
					<%}}} %>
					</select>
				</p>
				<p>
					<label>Hình ảnh</label>
					<img style="width:150px; height:100px;" alt="" src="<%=request.getContextPath()%>/files/<%=objNews.getPicture() %>" /><br />
					<input type="file"  name="hinhanh" value="<%=objNews.getPicture() %>" />
				</p>
				<p>
					<label>Mô tả</label>
					<textarea name="mota" value="" rows="7" cols="90" class="input-medium"><%=objNews.getPreview_text() %></textarea>
				</p>
				<p>
					<label>Chi tiết</label>
					<textarea  name="chitiet" value="" rows="7" cols="90" class="input-long"><%=objNews.getDetail_text() %></textarea>
				</p>
				<fieldset>
					<input class="submit-green" name="sua" type="submit" value="Sửa" /> 
					<input class="submit-gray" name="reset" type="reset" value="Nhập lại" />
				</fieldset>
			</form>
			<%} %>
		 </div> <!-- End .module-body -->

	</div>  <!-- End .module -->
	<div style="clear:both;"></div>
</div> <!-- End .grid_12 -->
<%@include file="/templates/admin/inc/footer.jsp" %> 