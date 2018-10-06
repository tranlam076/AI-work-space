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
<%@include file="templates/Library/Bootstrap/css/bootstrap.css"%>
<%@include file ="templates/CSS/style.css"%>
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
					src="http://www.dut.udn.vn/Files/admin/images/Tin_tuc/Daotao/2017/HCERES/image002.png" alt=""></a>
			</div>
		</div>

		<div class="row content">
			<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 content-left">
				<div id="menu">
					<ul>
						<li class="main-menu-1"><a href="index.html" target="_top">TOP</a></li>
						<li class="main-menu-2"><a href="" target="_top">Call for
								Papers</a></li>
						<li class="main-menu-3">Paper Submission
							<ul class="main-menu-3-sub">
								<li class="main-menu3-sub-1"><a href="" target="_top">Abstract
										Submission</a></li>
								<li class="main-menu3-sub-2"><a href="">Format Download</a></li>
								<li class="main-menu3-sub-3"><a href="">Presentation
										Guidelines</a></li>
							</ul>
						</li>
						<li class="main-menu-4"><a href="" target="_blank">Program</a></li>
						<li class="main-menu-5"><a href="" target="_top">Organization</a></li>
						<li class="main-menu-6">Accommodation
							<ul class="main-menu-6-sub">
								<li class="main-menu6-sub-1"><a href="Access.html">Access</a></li>
								<li class="main-menu6-sub-3"><a href="Hotel.html">Hotel
										Information</a></li>
							</ul>
						</li>
						<li class="main-menu-7"><a href="">Contact us</a></li>
						<li class="main-menu-8"><a href="">General Infomation </a></li>
						<li class="main-menu-9"><a href="<%=request.getContextPath()%>/show-file-upload">File Upload</a></li>
						<li class="main-menu-10"><a
							href="<%=request.getContextPath()%>/admin/show-login">Login</a></li>
					</ul>
					<div class="sub-menu"></div>

				</div>
			</div>

			<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9 content-right">
				<h2>Organization</h2>
				<div class="box">
					<div class="box_h1">Organizing Committee</div>
					<c:forEach items="${listCats}" var="cat">
						<h5>
							<c:out value="${cat.name}"></c:out>
						</h5>
						<c:forEach items="${listNews}" var="news">
							<c:if test="${news.id_cat == cat.id_cat}">
								<dl>
									<dt>
										<c:out value="${news.title}"></c:out>
									</dt>
									<dd>
										<c:out value="${news.content}"></c:out>
									</dd>
								</dl>
							</c:if>
						</c:forEach>
					</c:forEach>
				</div>

				<div class="box">
					<div class="box_h1">Correspondence</div>
					<p>
						If you have any inquiry, you can contact with the general
						secretary via e-mail.<br> Dr. Hiroki Nagai (Kogakuin
						University)<br> 2665-1 Nakano-machi, Hachioji, Tokyo,
						192-0015 Japan
					</p>
					E-mail: <a href="mailto:isat16@sc.kogakuin.ac.jp">isat16@sc.kogakuin.ac.jp</a>
					<div>
						<div></div>
						<div></div>
					</div>
					<p class="p">&nbsp;</p>
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
	<%@ include file="templates/Library/jquery-3.2.1.min.js"%>
	</script>
	<script>
	<%@ include file="templates/Library/Bootstrap/js/bootstrap.js"%></script>
	<script>
	<%@ include file="templates/JavaScript/script.js"%>
	</script>
</body>
</html>