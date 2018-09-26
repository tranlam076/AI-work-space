<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>isat</title>
<style>
.content-left {
	width: 40%;
	margin: 20px;
	float: left;
}

.content-right {
	width: 40%;
	margin: 20px;
	float: right;
}

.edit-form-cat form {
	width: 80%;
	margin-left: 20px;
}

.edit-form-cat form input[type="text"], .edit-form-cat form input[type="submit"] {
	display: block;
	font-size: 14px;
	margin: 20px;
	height: 44px;
	border-radius: 40px;
	line-height: 30px;
	padding: 0px 25px;
	margin-left: -30px;
	border: 1px solid #376EC0;
	color: #376EC0;
	width: 100%;
}

.edit-form-cat form input[type="submit"] {
	border: none;
	line-height: 46px;
	height: 46px;
	background: #376EC0;
	color: #fff;
	cursor: pointer;
	witdth: 100%;
}
</style>
</head>
<body>
	<div class="content-left">
		<h2>
			<span>category</span>
		</h2>
		<table>
			<tr>
				<th>id</th>
				<th>name</th>
				<th>info</th>
			</tr>
			<c:forEach items="${listCats}" var="cat">

				<tr>
					<td>${cat.id_cat}</td>
					<td>${cat.name}</td>
					<td><button>Edit</button>
						<form
							action="<%=request.getContextPath()%>/admin/delete-cat?${cat.id_cat}"
							method="POST">
							<input type="submit" value="delete" />
						</form></td>
				</tr>
			</c:forEach>
		</table>
		<div class="edit-form-cat">
			<form action="<%=request.getContextPath()%>/admin/add-cat"
				method="POST">
				<span>Add new Category</span> <input type="text"
					placeholder="new category name" required name="name" /> <input
					type="submit" value="Add category" />
			</form>
		</div>
	</div>

	<div class="content-right">
		<h2>
			<span>news</span>
		</h2>
		<table>
			<tr>
				<th>id</th>
				<th>title</th>
				<th>content</th>
				<th>id_cat</th>
				<th>info</th>
			</tr>
			<c:forEach items="${listNews}" var="news">
				<tr>
					<td>${news.id_news}</td>
					<td>${news.title}</td>
					<td>${news.content}</td>
					<td>${news.id_cat}</td>
					<td><button>Edit</button>
						<form
							action="<%=request.getContextPath()%>/admin/delete-cat?${cat.id_cat}"
							method="POST">
							<input type="submit" value="delete" />
						</form></td>
				</tr>
			</c:forEach>
		</table>
		<div class="edit-form-cat">
			<form action="<%=request.getContextPath()%>/admin/add-news"
				method="POST">
				<span>Add new News</span> <input type="text"
					placeholder="new news title" required name="title" /> <input
					type="text" placeholder="new news content" required name="content" />
				<input type="text" placeholder="new news id_Cat" required
					name="id_cat" /> <input type="submit" value="Add news" />
			</form>
		</div>
	</div>
</body>
</html>