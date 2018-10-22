<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<%@include file="/templates/admin/inc/menu.jsp" %>  
		
			<div class="content-right">			
				<div class="content-cat">
					<h2>
						<span>Position</span>
					</h2>
					<table>
						<tr>
							<th>id</th>
							<th>position</th>
							<th>selection</th>
						</tr>
						<c:forEach items="${listCats}" var="cat">
							<tr>
								<td width="30%">${cat.id_cat}</td>
								<td width="50%">${cat.name}</td>
								<td width="80px">
								<button  class="btn-edit">Edit</button>
								<button class="btn-delete">Delete</button>					
								</td>
							</tr>
						</c:forEach>
					</table>
					<button class="add-cat">Add Position</button>
					<div class="edit-cat-form">
						<form action="<%=request.getContextPath()%>/admin/add-cat"
							method="POST">
							<input class="name-cat" type="text"
								placeholder="Enter name" required name="name" /> 
								<input class ="btn-add-cat" type="submit" value="Add Position" />
						</form>
					</div>
				</div>
			
				<div class="content-news">
					<h2>
						<span>Individual</span>
					</h2>
					<table>
						<tr>
							<th>id</th>
							<th>name</th>
							<th>detail</th>
							<th>position</th>
							<th>selection</th>
						</tr>
						<c:forEach items="${listNews}" var="news">
							<tr>
								<td width="20%">${news.id_news}</td>
								<td width="15%">${news.title}</td>
								<td width="30%">${news.content}</td>
								<td width="13%">${news.nameCat}</td>
								<td width="22%">
								<button class="btn-edit">Edit</button>
								<button class="btn-delete">Delete</button>			
								</td>
							</tr>
						</c:forEach>
					</table>
					
					<button class="add-news">Add Person</button>
					
					<div class="edit-news-form">
						<form action="<%=request.getContextPath()%>/admin/add-news"
							method="POST">
							<input class="title-news" type="text"
								placeholder="enter name" required name="title" /> <input class="content-news"
								type="text" placeholder="enter detail" required name="content" />
								<div class="select-position">
									<span>Choose Position:</span> 
									<select name = "idCat">
										<c:forEach items="${listCats}" var="cat">
											<option value="${cat.id_cat}">${cat.name}</option>
										</c:forEach>
									</select>
								</div>
							 <input class="btn-add-news" type="submit" value="Add Person" />
						</form>
					</div>
				</div>		
			</div>

<%@include file="/templates/public/inc/footer.jsp" %>  