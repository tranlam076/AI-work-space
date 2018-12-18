<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@include file="/templates/admin/inc/header.jsp"%>
<%@include file="/templates/admin/inc/menu.jsp"%>
<div class="content-right">
	<!-- start details submission -->
	<div class="detail-submission">
		<div class="row">
			<div class="left">
				<section>		
					<h2 class="title">${submission.title}</h2>
					<h3 class="field">${submission.fieldName}</h3>
					<p class="description"> <strong>Discription: </strong> <span>${submission.description}</span></p>
				</section>
			</div>
			<div class="right">
				<section>
					<h3> Date submit:</h3>
					<p>${submission.createdAt}</p>
					<br/>
					<h3> Download paper:</h3>
					<p><a href="<%=request.getContextPath()%>/download?filename=${submission.fileNameUpload}">${submission.fileNameUpload}</a></p>
				</section>
			</div>
		</div>
		<div class="row">
			<hr>
		</div>
		<div class="row">
			<h2>Author</h2>
			<c:forEach items="${listAuthors}" var="author">
				<div class="author">	
					<div class="text">
						<h3>${author.name}</h3>
						<p>Name: <span>${author.name}</span></p>
						<p>Email: <span>${author.email}</span></p>
						<p>Country: <span>${author.country}</span></p>
						<p>Organization: <span>${author.organization}</span></p>
						<p>Web page: <span>${author.webPage}</span></p>
						<c:if test="${author.corresponding == true}">
						<!-- <p>Corresponding: <span>✔</span></p> -->
						<p>Corresponding: <span>yes</span>
						</c:if>
						<c:if test="${author.corresponding == false}">
						<!-- <p>Corresponding: <span>✘</span></p> -->
						<p>Corresponding: <span>no</span>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</div>
		<hr>
		<section>
			<h3>Tag: <span> <strong>${submission.keywords}</strong></span></h3>
		</section>
	</div>
</div>
<!-- end detail submission -->
<%@include file="/templates/public/inc/footer.jsp"%>
