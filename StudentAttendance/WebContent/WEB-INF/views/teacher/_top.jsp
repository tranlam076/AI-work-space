<%@page import="util.Constants"%>
<div class="navbar navbar-expand navbar-dark flex-column flex-md-row bd-navbar" style="background: #00ba8b !important">
	<a class="navbar-brand mr-0 mr-md-2" href="<%=request.getContextPath()%>/login">Student Attendance </a>
	<ul class="navbar-nav flex-row ml-md-auto d-none d-md-flex">
		<li class="nav-item dropdown">
			<a class="nav-item nav-link dropdown-toggle mr-md-2" href="#" id="bd-profile" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				Tên giáo viên
			</a>
			<div class="dropdown-menu dropdown-menu-right" aria-labelledby="bd-profile">
				<a class="dropdown-item" href="#">Profile</a>
				<!-- <a class="dropdown-item active" href="/docs/4.0/">Logout</a> -->
				<a class="dropdown-item " onclick="deleteAllCookies()" href="<%=request.getContextPath()%>/logout">Logout</a>
			</div>
			<script type="text/javascript">
				function deleteAllCookies() {
					var cookies = document.cookie.split(";");
					for (var i = 0; i < cookies.length; i++) {
						var cookie = cookies[i];
						var eqPos = cookie.indexOf("=");
						var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
						document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
					}
				}
			</script>
		</li>
	</ul>
	<!-- /navbar-inner -->
</div>
<!-- /navbar -->
<div class="subnavbar">
	<!-- <div class="subnavbar-inner">
		<div class="container">
			<ul class="mainnav">
				<li class="active"><a href="<%=Constants.url.TEACHER%>"><i
						class="icon-dashboard"></i><span>Dashboard</span> </a></li>
				<li><a href="<%=Constants.url.TEACHER_FILE%>"><i class="icon-bar-chart"></i><span>File</span>
				</a></li>
				<li><a href="<%=Constants.url.TEACHER_CAMERA%>"><i class="icon-facetime-video"></i><span>Camera</span> </a></li>
				<li><a href="shortcodes.html"><i class="icon-code"></i><span>Shortcodes</span>
				</a></li>
				<li class="dropdown"><a href="javascript:;"
					class="dropdown-toggle" data-toggle="dropdown"> <i
						class="icon-long-arrow-down"></i><span>Drops</span> <b
						class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="icons.html">Icons</a></li>
						<li><a href="faq.html">FAQ</a></li>
						<li><a href="pricing.html">Pricing Plans</a></li>
						<li><a href="login.html">Login</a></li>
						<li><a href="signup.html">Signup</a></li>
						<li><a href="error.html">404</a></li>
					</ul></li>
			</ul>
		</div>
	</div>-->
	<!-- /subnavbar-inner -->
</div>