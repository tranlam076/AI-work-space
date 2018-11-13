<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>User Profile</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta
            content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
            name="viewport">
        <!-- Bootstrap 3.3.7 -->
        <link rel="stylesheet" href="../assets/bower_components/bootstrap/dist/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="../assets/bower_components/font-awesome/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="../assets/bower_components/Ionicons/css/ionicons.min.css">
        <!-- DataTables -->
        <link rel="stylesheet" href="../assets/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="../assets/dist/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="../assets/dist/css/skins/_all-skins.min.css">
        <!-- Crop Images -->
        <link  href="../assets/dist/css/cropper.min.css" rel="stylesheet">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Google Font -->
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    </head>
    <body class="skin-blue sidebar-mini sidebar-collapse">
        <div class="wrapper">
            <header class="main-header"> <!-- Logo --> 
                <a href="index2.html" class="logo"> 
                    <!-- mini logo for sidebar mini 50x50 pixels -->
                    <span class="logo-mini"><b>S</b>C</span> <!-- logo for regular state and mobile devices -->
                    <span class="logo-lg"><b>Student</b>Clustering</span>
                </a> <!-- Header Navbar: style can be found in header.less --> 
                <nav class="navbar navbar-static-top"> 
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <!-- User Account: style can be found in dropdown.less -->
                            <li class="dropdown user user-menu"><a href="#"
                                                                   class="dropdown-toggle" data-toggle="dropdown"> <img
                                        src="../assets/dist/img/user2-160x160.jpg" class="user-image"
                                        alt="User Image"> <span class="hidden-xs">Alexander
                                        Pierce</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- User image -->
                                    <li class="user-header"><img src="../assets/dist/img/user2-160x160.jpg"
                                                                 class="img-circle" alt="User Image">

                                        <p>
                                            Alexander Pierce - Web Developer <small>Member since
                                                Nov. 2012</small>
                                        </p></li>
                                    <!-- Menu Body -->
                                    <li class="user-body">
                                        <div class="row">
                                            <div class="col-xs-4 text-center">
                                                <a href="#">Followers</a>
                                            </div>
                                            <div class="col-xs-4 text-center">
                                                <a href="#">Sales</a>
                                            </div>
                                            <div class="col-xs-4 text-center">
                                                <a href="#">Friends</a>
                                            </div>
                                        </div> <!-- /.row -->
                                    </li>
                                    <!-- Menu Footer-->
                                    <li class="user-footer">
                                        <div class="pull-left">
                                            <a href="#" class="btn btn-default btn-flat">Profile</a>
                                        </div>
                                        <div class="pull-right">
                                            <a href="login.html?clearCookie=true" class="btn btn-default btn-flat">Sign out</a>
                                        </div>
                                    </li>
                                </ul></li>
                        </ul>
                    </div>
                </nav>
            </header>
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="main-sidebar"> <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar"> <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="../assets/dist/img/user2-160x160.jpg" class="img-circle"
                                 alt="User Image">
                        </div>
                        <div class="pull-left info">
                            <p>Alexander Pierce</p>
                            <a href="#"><i class="fa fa-circle text-success"></i> Student</a>
                        </div>
                    </div>
                    <!-- /.search form --> <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu" data-widget="tree">
                        <li class="header">MAIN NAVIGATION</li>
                        <li class="treeview"><a href="#"> <i class="fa fa-dashboard"></i>
                                <span>Dashboard</span> <span class="pull-right-container"> <i
                                        class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="index.html"><i class="fa fa-circle-o"></i>
                                        Dashboard v1</a></li>
                                <li><a href="index2.html"><i class="fa fa-circle-o"></i>
                                        Dashboard v2</a></li>
                            </ul></li>
                        <li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
                                <span>Layout Options</span> <span class="pull-right-container">
                                    <span class="label label-primary pull-right">4</span>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="layout/top-nav.html"><i
                                            class="fa fa-circle-o"></i> Top Navigation</a></li>
                                <li><a href="layout/boxed.html"><i class="fa fa-circle-o"></i>
                                        Boxed</a></li>
                                <li><a href="layout/fixed.html"><i class="fa fa-circle-o"></i>
                                        Fixed</a></li>
                                <li><a href="layout/collapsed-sidebar.html"><i
                                            class="fa fa-circle-o"></i> Collapsed Sidebar</a></li>
                            </ul></li>
                    </ul>
                </section> <!-- /.sidebar --> </aside>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>Crop Images</h1>
                </section>
                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-9">
                            <div style="height: 600px;widows: calc(100% - 15px);margin: 0 auto;">
                                <img id="image" src="../assets/dist/img/photo1.png" style="height: 600px;">
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="box box-primary">
                                <div class="box-body box-profile" style="font-size: 13pt;">

                                    <p class="text-muted text-center">Information</p>

                                    <ul class="list-group list-group-unbordered">
                                        <li class="list-group-item">
                                            <b>Class</b> <a class="pull-right">1,322</a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Lesson</b> <a class="pull-right">543</a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Start time</b> <a class="pull-right">13,287</a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>End time</b> <a class="pull-right">13,287</a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>(X,Y)</b> <a class="pull-right" id="xy">13,287</a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>(W,H)</b> <a class="pull-right" id="wh">13,287</a>
                                        </li>
                                    </ul>

                                    <a href="#" class="btn btn-primary btn-block"><b>Submit</b></a>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- /.row --> </section>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->
            <footer class="main-footer">
                <div class="pull-right hidden-xs">
                    <b>Version</b> 1.0
                </div>
                <strong>Da Nang University of Science and Technology.</strong> </footer>
        </div>
        <!-- ./wrapper -->

        <!-- jQuery 3 -->
        <script src="../assets/bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="../assets/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- DataTables -->
        <script src="../assets/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
        <script src="../assets/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
        <!-- SlimScroll -->
        <script src="../assets/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <!-- FastClick -->
        <script src="../assets/bower_components/fastclick/lib/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="../assets/dist/js/adminlte.min.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="../assets/dist/js/demo.js"></script>
        <!-- Crop Images -->
        <script src="../assets/dist/js/cropper.min.js"></script>
        <!-- script on page -->
        <script type="text/javascript">
            var data = {
                class: "",
                lesson: "",
                startTime: "",
                endTime: "",
                x: "",
                y: "",
                w: "",
                h: ""
            };
            const image = document.getElementById('image');
            const cropper = new Cropper(image, {
                aspectRatio: 1 / 1,
                crop(event) {
                    data.x = parseInt(event.detail.x);
                    data.y = parseInt(event.detail.y);
                    data.w = parseInt(event.detail.width);
                    data.h = parseInt(event.detail.height);
                    $("#xy").html("(" + data.x + "," + data.y + ")");
                    $("#wh").html("(" + data.w + "," + data.h + ")");
                },
                viewMode: 3,
                dragMode: 'move',
            });
        </script>
    </body>
</html>
