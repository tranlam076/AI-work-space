<%@include file="/templates/admin/inc/header.jsp"%>
<%@include file="/templates/public/inc/menu.jsp"%>
<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9 content-right">
	<div class="login-form">
		<%String msg = request.getParameter("msg");
			String message = request.getParameter("message");
		if (msg != null && msg.equals("error")) {%>
			<script type="text/javascript">
				alert("error: <%= message%>")
			</script>
			
		<% }%>
		
	
		<form action="<%=request.getContextPath() %>/login-jwt"
			method="POST">
			<span>Login</span> <input type="email" placeholder="Your e-mail"
				required name="username" /> <input type="password"
				placeholder="Your password" required name="password" /> <input
				type="submit" value="Login" />
		</form>
		<!-- <a href="#">Forgot password</a> <a href="#" style="float: right;">Sign
			up here</a> -->
	</div>
</div>
<%@include file="/templates/public/inc/footer.jsp" %>  