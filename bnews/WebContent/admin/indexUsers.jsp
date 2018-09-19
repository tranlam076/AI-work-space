<%@page import="model.bean.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<div class="bottom-spacing">
	  <!-- Button -->
	  <div class="float-left">
		  <a href="<%=request.getContextPath() %>/admin/show-addUser" class="button">
			<span>Thêm người dùng <img src="<%=request.getContextPath() %>/templates/admin/images/plus-small.gif" alt="Thêm tin"></span>
		  </a>
	  </div>
	  <div class="clear"></div>
</div>
<%
	if(request.getParameter("msg")!=null){
		int msg = Integer.parseInt(request.getParameter("msg"));
		if(msg == 1){
			out.print("<h5 style='color:red'>Thêm thành công</h5>");
		} else
		if(msg == 2){
			out.print("<h5 style='color:red'>Sửa thành công</h5>");
			} else
		if(msg == 3){
			out.print("<h5 style='color:red'>Xóa thành công</h5>");
			} else
		if(msg == 4){
			out.print("<h5 style='color:red'>Bạn không có quyền xóa admin!</h5>");
		}
			else {
			out.print("<h5 style='color:red'>Thất bại</h5>");
		}
		
	}
%>
<div class="grid_12">
	<!-- Example table -->
	<div class="module">
		<h2><span>Danh sách người dùng</span></h2>
		
		<div class="module-table-body">
			<form action="">
			
			<table id="myTable" class="tablesorter">
				<thead>
					<tr>
						<th style="width:4%; text-align: center;">ID</th>
						<th style="width:20%">Tên người dùng</th>
						<th style="width:20%">Họ và tên</th>
						<th style="width:11%; text-align: center;">Chức năng</th>
					</tr>
				</thead>
				<tbody>
				<%
				ArrayList<User> listUsers = (ArrayList<User>) request.getAttribute("listUsers");
				if(listUsers.size()>0){
				for(User objUser : listUsers){
				%>
					<tr>
						<td class="align-center"><%=objUser.getId() %></td>
						<td><a href=""><%=objUser.getUsername() %></a></td>
						<td><%=objUser.getFullname() %></td>
						<td align="center">
							<a href="<%=request.getContextPath()%>/admin/show-editUser?uid=<%=objUser.getId()%>">Sửa <img src="<%=request.getContextPath() %>/templates/admin/images/pencil.gif" alt="edit" /></a>
							<a href="<%=request.getContextPath()%>/admin/delUser?uid=<%=objUser.getId()%>">Xóa <img src="<%=request.getContextPath() %>/templates/admin/images/bin.gif" width="16" height="16" alt="delete" /></a>
						</td>
					</tr>
					<%}} %>
				</tbody>
			</table>
			</form>
		 </div> <!-- End .module-table-body -->
	</div> <!-- End .module -->
		 <div class="pagination">           
			<div class="numbers">
				<span>Trang:</span> 
				<a href="">1</a> 
				<span>|</span> 
				<a href="">2</a> 
				<span>|</span> 
				<span class="current">3</span> 
				<span>|</span> 
				<a href="">4</a> 
				<span>|</span> 
				<a href="">5</a> 
				<span>|</span> 
				<a href="">6</a> 
				<span>|</span> 
				<a href="">7</a>
				<span>|</span> 
				<a href="">8</a> 
				<span>|</span> 
				<a href="">9</a>
				<span>|</span> 
				<a href="">10</a>   
			</div> 
			<div style="clear: both;"></div> 
		 </div>
	
</div> <!-- End .grid_12 -->
<%@include file="/templates/admin/inc/footer.jsp" %> 