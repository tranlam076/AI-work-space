<%@page import="util.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<link rel="icon" type="image/png" href="assets/img/icon-mini.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Login Page</title>
<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
<meta name="viewport" content="width=device-width" />
<!-- Bootstrap core CSS     -->
<link href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css" rel="stylesheet" />
<!-- Animation library for notifications   -->
<!-- <link href="<%=request.getContextPath()%>/assets/css/animate.min.css" rel="stylesheet" /> -->
<!--  Light Bootstrap Table core CSS    -->
<!-- <link href="<%=request.getContextPath()%>/assets/css/light-bootstrap-dashboard.css?v=1.4.0"
	rel="stylesheet" /> -->
<!--  CSS for Demo Purpose, don't include it in your project     -->
<!--  <link href="<%=request.getContextPath()%>/assets/css/demo.css" rel="stylesheet" /> -->
<!--     Fonts and icons     -->
<link
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300'
	rel='stylesheet' type='text/css'>

</head>
<body>

	<div class="wrapper">
		<div class="content">
			<div class="container-fluid">
				<div class="row" style="display: flex; justify-content: center;">
					<div class="col-sm-9 col-md-7 col-lg-5" style="margin-top: 100px;">
						<div class="card" style="border: none;">
							<div class="header">
								<h4 class="title">SIGN IN</h4>
							</div>
							<div class="content">
								<form action="<%=Constants.url.LOGIN%>" method="POST">
									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label>Username</label> <input id="txtUser" name="username" type="text"
													class="form-control" placeholder="Username" value="">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label>Password</label> <input id="txtPass" name="password" type="password"
													class="form-control" placeholder="Password" value="">
											</div>
										</div>
									</div>
									<p id="pMessage" style="color: red" hidden>Sai tên đăng
										nhập hoặc mật khẩu</p>
									<button type="submit" id="btnLogin"
										class="btn btn-info btn-fill pull-right">Login</button>
									<div class="clearfix"></div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

<script
	src="<%=request.getContextPath()%>/assets/js/jquery-3.3.1.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/bootstrap.js"
	type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/js/login.js"
	type="text/javascript"></script>

</html>
