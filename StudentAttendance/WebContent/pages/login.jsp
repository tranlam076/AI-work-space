<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Login - SA</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.7 -->
        <link rel="stylesheet" href="assets/bower_components/bootstrap/dist/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="assets/bower_components/font-awesome/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="assets/bower_components/Ionicons/css/ionicons.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css">
        <!-- iCheck -->
        <link rel="stylesheet" href="assets/plugins/iCheck/square/blue.css">
        <link href="assets/dist/img/favicon.ico" rel="shortcut icon" type="image/x-icon" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="assetshttps://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
          <script src="assetshttps://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
          <![endif]-->

        <!-- Google Font -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
        <style>
            body {
                background: -webkit-linear-gradient(45deg, #085078 10%, #85d8ce 90%);
                background: -moz-linear-gradient(45deg, #085078 10%, #85d8ce 90%);
                background: -ms-linear-gradient(45deg, #085078 10%, #85d8ce 90%);
                background: -o-linear-gradient(45deg, #085078 10%, #85d8ce 90%);
                background: linear-gradient(45deg, #085078 10%, #85d8ce 90%);
                overflow-y: hidden;
            }
        </style>
    </head>
    <body class="hold-transition">
        <script src="assets/dist/js/script.js"></script>
        <script type="text/javascript">
            if (getQueryVariable("clearCookie") === "true") {
                setCookie("password", "", 1);
                setCookie("email", "", 1);
                setCookie("realname", "", 1);
                setCookie("role", "", 1);
            }
            var email = getCookie("email");
            var password = getCookie("password");
            if (email !== "" && password !== "") {
                document.location = getCookie("role");
            }
        </script>
        <div class="login-box" style="width: 360px;">
            <div class="login-logo">
                <a href="assets/index2.html"> <b>Students</b>Clustering
                </a>
            </div>
            <!-- /.login-logo -->
            <div class="login-box-body">
                <p class="login-box-msg">Sign in to start your session</p>
                <div class="form-group has-feedback">
                    <input id="username" type="text" class="form-control" placeholder="Username" autocomplete="off">
                    <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                </div>
                <div class="form-group has-feedback">
                    <input id="password" type="password" class="form-control" placeholder="Password" autocomplete="off">
                    <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <button id="btn-submit" type="submit" class="btn btn-primary btn-block btn-flat">Sign
                            In</button>
                    </div>
                    <!-- /.col -->
                </div>
                <br> <a href="assets/register.jsp" class="text-center">Register a
                    new account</a>
            </div>
            <!-- /.login-box-body -->
        </div>
        <!-- /.login-box -->

        <!-- jQuery 3 -->
        <script src="assets/bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="assets/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- iCheck -->
        <script src="assets/plugins/iCheck/icheck.min.js"></script>
        <script>
            $("input").focus(function (e) {
                $(e.currentTarget).css("border", "");
            });
            $("#btn-submit").click(function () {
                var email = $("#username").val().trim();
                var password = $("#password").val().trim();
                $.ajax({
                    type: 'POST',
                    url: "login/api",
                    data: {
                        email: email,
                        password: password
                    },
                    dataType: "json",
                    success: function (resp) {
                        alert(resp.state);
                        if (resp.state === "success") {
                            setCookie("email", encodeURIComponent(email), 10);
                            setCookie("password", encodeURIComponent(password), 10);
                            setCookie("realname", encodeURIComponent(resp.data.realname), 10);
                            setCookie("role", encodeURIComponent(resp.data.role), 10);
                            document.location = resp.data.role;
                        }
                    }
                });
            });
        </script>
    </body>

</html>
