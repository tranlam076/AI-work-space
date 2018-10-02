<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ISAT</title>
<style>
<%@include file="/templates/Library/Bootstrap/css/bootstrap.css"%>
<%@include file ="/templates/CSS/style.css"%>
<%@include file ="/templates/admin/CSS/style.css"%>
</style>
</head>
<body>
	<div class="container-max">
		<div id="header">
			<div class="header-title">
				<h4>The 16th International Symposium on Advanced Technology
					(ISAT-16)</h4>
				<p>Advanced Technologies to Open a New Era</p>
				<p>November 1st-2nd, 2017</p>
				<p>Hachioji, Tokyo, Japan</p>
			</div>
			<div class="header-image">
				<a href="#"><img
					src="https://www.kogakuin.ac.jp/isat/img/header.jpg" alt=""></a>
			</div>
		</div>

		<div class="row content">
			<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 content-left">
				<div id="menu">
					<ul>
						<li class="main-menu-4"><a
							href="<%=request.getContextPath()%>/">HOME</a></li>
						<li class="main-menu-1">Position</li>
						<li class="main-menu-2">Person</li>
						<li class="main-menu-5">File Manager</li>
						<li class="main-menu-3"><a
							href="<%=request.getContextPath()%>/admin/show-login">Logout</a></li>
					</ul>
					<div class="sub-menu"></div>

				</div>
			</div>

			<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9 content-right">			
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
		</div>

		<div id="footer">
			<a href="" target="_top">Call for Papers</a> | <a href=""
				target="_top">Abstract Submission</a> | <a href="" target="_top">Organization</a>
			| <a href="" target="_top">Accommodation</a> | <a href=""
				target="_Top">Contact us</a><br> Copyright © Kogakuin
			University (Japan). All Rights Reserved.
		</div>
	</div>

	<script>
	<%@ include file="/templates/Library/jquery-3.2.1.min.js"%>
	</script>
	<script>
	<%@ include file="/templates/Library/Bootstrap/js/bootstrap.js"%></script>
	<script>
	<%@ include file="/templates/JavaScript/script.js"%>
	</script>
	<script>
	<%@ include file="/templates/admin/JavaScript/script.js"%>
</script>
</body>
</html>