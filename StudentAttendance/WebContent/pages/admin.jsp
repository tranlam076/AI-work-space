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
        <link rel="stylesheet" href="assets/bower_components/bootstrap/dist/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="assets/bower_components/font-awesome/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="assets/bower_components/Ionicons/css/ionicons.min.css">
        <!-- DataTables -->
        <link rel="stylesheet" href="assets/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
        <!-- bootstrap datepicker -->
        <link rel="stylesheet" href="assets/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css">
        <link rel="stylesheet" href="assets/dist/css/style.css">
        <link href="assets/dist/img/favicon.ico" rel="shortcut icon" type="image/x-icon" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="assets/https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="assets/https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Google Font -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
        <style>
            .tab-content .row {
                padding: 0px 10px;
            }
        </style>
    </head>
    <body class="skin-blue sidebar-mini sidebar-collapse">
        <script src="assets/dist/js/script.js"></script>
        <script type="text/javascript">
            var email = getCookie("email");
            var password = getCookie("password");
            var role = getCookie("role");
            if (email === "" || password === "" || role !== "admin") {
                document.location = "login";
            }
        </script>
        <div class="wrapper">
            <header class="main-header">  
                <a href="assets/index2.html" class="logo"> 
                    <span class="logo-mini"><b>S</b>A</span> 
                    <span class="logo-lg"><b>Student</b>Attendance</span>
                </a> 
                <nav class="navbar navbar-static-top"> 
                    <a href="assets/#" class="sidebar-toggle" data-toggle="push-menu" role="button"> 
                        <span class="sr-only">Toggle navigation</span> 
                        <span class="icon-bar"></span> 
                        <span class="icon-bar"></span> 
                        <span class="icon-bar"></span>
                    </a>
                    <ul class="nav navbar-nav pull-left">
                        <li class="dropdown user user-menu">
                            <a href="assets/#" class="dropdown-toggle" data-toggle="dropdown">
                                <span style="font-size: 14pt;">Dashboard</span>
                            </a>
                        </li>
                    </ul>
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <li class="dropdown user user-menu">
                                <a href="assets/#" class="dropdown-toggle" data-toggle="dropdown"> 
                                    <img src="assets/dist/img/user2-160x160.jpg" class="user-image" alt="User Image"> 
                                    <span class="hidden-xs">Alexander Pierce</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li class="user-header">
                                        <img src="assets/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                                        <p>
                                            Alexander Pierce - Web Developer <small>Member since Nov. 2012</small>
                                        </p>
                                    </li>
                                    <li class="user-body">
                                        <div class="row">
                                            <div class="col-xs-4 text-center">
                                                <a href="assets/#">Followers</a>
                                            </div>
                                            <div class="col-xs-4 text-center">
                                                <a href="assets/#">Sales</a>
                                            </div>
                                            <div class="col-xs-4 text-center">
                                                <a href="assets/#">Friends</a>
                                            </div>
                                        </div> 
                                    </li>
                                    <li class="user-footer">
                                        <div class="pull-left">
                                            <a href="assets/#" class="btn btn-default btn-flat">Profile</a>
                                        </div>
                                        <div class="pull-right">
                                            <a href="login?clearCookie=true" class="btn btn-default btn-flat">Sign out</a>
                                        </div>
                                    </li>
                                </ul></li>
                        </ul>
                    </div>
                </nav> </header>
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="main-sidebar"> <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar"> <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="assets/dist/img/user2-160x160.jpg" class="img-circle"
                                 alt="User Image">
                        </div>
                        <div class="pull-left info">
                            <p>Alexander Pierce</p>
                            <a href="assets/#"><i class="fa fa-circle text-success"></i> Student</a>
                        </div>
                    </div>
                    <!-- /.search form --> <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu" data-widget="tree">
                        <li class="header">MAIN NAVIGATION</li>
                        <li class="treeview"><a href="assets/#"> <i class="fa fa-dashboard"></i>
                                <span>Dashboard</span> <span class="pull-right-container"> <i
                                        class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="assets/index.html"><i class="fa fa-circle-o"></i>
                                        Dashboard v1</a></li>
                                <li><a href="assets/index2.html"><i class="fa fa-circle-o"></i>
                                        Dashboard v2</a></li>
                            </ul></li>
                        <li class="treeview"><a href="assets/#"> <i class="fa fa-files-o"></i>
                                <span>Layout Options</span> <span class="pull-right-container">
                                    <span class="label label-primary pull-right">4</span>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="assets/layout/top-nav.html"><i
                                            class="fa fa-circle-o"></i> Top Navigation</a></li>
                                <li><a href="assets/layout/boxed.html"><i class="fa fa-circle-o"></i>
                                        Boxed</a></li>
                                <li><a href="assets/layout/fixed.html"><i class="fa fa-circle-o"></i>
                                        Fixed</a></li>
                                <li><a href="assets/layout/collapsed-sidebar.html"><i
                                            class="fa fa-circle-o"></i> Collapsed Sidebar</a></li>
                            </ul></li>
                    </ul>
                </section> <!-- /.sidebar --> </aside>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-12" id="content-container">
                            <div class="nav-tabs-custom">
                                <ul class="nav nav-tabs">
                                    <li class="active"><a href="assets/#activity" data-toggle="tab">Tables</a></li>
                                    <li><a href="assets/#timeline" data-toggle="tab">Timeline</a></li>
                                    <li><a href="assets/#settings" data-toggle="tab">Settings</a></li>
                                </ul>
                                <div class="tab-content">
                                    <div class="active tab-pane" id="activity">
                                        <div class="row">
                                            <div class="col-sm-3 form-group">
                                                <label>Table</label>
                                                <select id="table" class="form-control select2 select2-hidden-accessible" style="width: 100%;" tabindex="-1" aria-hidden="true">
                                                    <option selected="selected">Alabama</option>
                                                </select>
                                            </div>
                                            <div class="col-sm-3 form-group">
                                                <label>Field</label>
                                                <select id="field" class="form-control select2 select2-hidden-accessible" style="width: 100%;" tabindex="-1" aria-hidden="true">
                                                </select>
                                            </div>
                                            <div class="col-sm-4 form-group">
                                                <label>Keyword</label>
                                                <input id="keywords" type="text" class="form-control" id="inputEmail3">
                                            </div>
                                            <div class="col-sm-2 form-group">
                                                <label>&nbsp;</label>
                                                <button id="btn-search" type="button" class="btn btn-block btn-primary">Search</button>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="box-body" id="data-table-container">
                                                <table id="data-table" class="table table-bordered table-striped">
                                                    <thead></thead>
                                                    <tbody></tbody>
                                                    <tfoot></tfoot>
                                                </table>
                                            </div>
                                        </div>
                                        <div class="row" id="infor-table-container">
                                            <div class="col-sm-5">
                                                <div class="dataTables_info" id="example2_info" role="status" aria-live="polite">
                                                </div>
                                            </div>
                                            <div class="col-sm-7">
                                                <div class="dataTables_paginate paging_simple_numbers" id="example2_paginate">
                                                    <ul id="pagination" class="pagination pull-right" style="margin: 0;">
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row" id="add-row-form" style="display: none; margin: 5px 0px; width: 170px;">
                                            <a id="add-one" class="btn btn-block btn-social btn-twitter">
                                                Add Row
                                            </a>
                                        </div>
                                        <!-- /.box-body -->
                                    </div>
                                    <!-- /.tab-pane -->
                                    <div class="tab-pane" id="timeline">
                                    </div>
                                    <!-- /.tab-pane -->
                                    <div class="tab-pane" id="settings">
                                    </div>
                                    <!-- /.tab-pane -->
                                </div>
                                <!-- /.tab-content -->
                            </div>
                            <!-- /.nav-tabs-custom -->
                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- /.row --> 
                </section>
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

        <!--modal-->
        <div id="add-row-modal" class="modal">
            <div class="modal-content" style="min-width: 300px; width: 30%;border-radius: 4px;">
                <div class="box box-primary" style="margin-bottom: 0;">
                    <div class="box-header with-border">
                        <h3 class="box-title pull-left">Add a row</h3>
                        <span class="close pull-right">&times;</span>
                    </div>
                    <div role="form" class="form">
                        <div class="box-body">
                            <div class="form-group">
                                <label for="exampleInputEmail1">Email address</label>
                                <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Enter email">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Password</label>
                                <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Password">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputFile">File input</label>
                                <input type="file" id="exampleInputFile">
                            </div>
                        </div>
                        <div class="box-footer">
                            <button id="btn-submit-add" class="btn btn-primary">Submit</button>
                            <button id="btn-submit-edit" class="btn btn-primary">Edit</button>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div><!--modal-->
        <!--processing modal-->
        <div id="processing-modal" class="modal">
            <div class="modal-content" style="min-width: 300px; width: 30%;border-radius: 4px; height: 200px;">
                <div class="processing-container">
                    <h3>Processing, please wait.</h3>
                    <div class="progress-bar">
                        <div class="shadow"></div>
                    </div>
                </div>
            </div>
        </div><!--processing modal-->

        <!-- jQuery 3 -->
        <script src="assets/bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="assets/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- DataTables -->
        <script src="assets/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
        <script src="assets/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
        <!-- bootstrap datepicker -->
        <script src="assets/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
        <!-- SlimScroll -->
        <script src="assets/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <!-- FastClick -->
        <script src="assets/bower_components/fastclick/lib/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="assets/dist/js/adminlte.min.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="assets/dist/js/demo.js"></script>
        <script src="assets/dist/js/showDataTable.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <!-- script on page -->
        <script type="text/javascript">
            getListTable();
            setEvent();
        </script>
    </body>
</html>
