<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Teacher</title>
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
        <!-- daterange picker -->
        <link rel="stylesheet" href="assets/bower_components/bootstrap-daterangepicker/daterangepicker.css">
        <!-- bootstrap datepicker -->
        <link rel="stylesheet" href="assets/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
        <!-- iCheck for checkboxes and radio inputs -->
        <link rel="stylesheet" href="assets/plugins/iCheck/all.css">
        <!-- Bootstrap Color Picker -->
        <link rel="stylesheet" href="assets/bower_components/bootstrap-colorpicker/dist/css/bootstrap-colorpicker.min.css">
        <!-- Bootstrap time Picker -->
        <link rel="stylesheet" href="assets/plugins/timepicker/bootstrap-timepicker.min.css">
        <!-- Select2 -->
        <link rel="stylesheet" href="assets/bower_components/select2/dist/css/select2.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css">
        <!--style-->
        <link rel="stylesheet" href="assets/dist/css/style.css">
        <link href="assets/dist/img/favicon.ico" rel="shortcut icon" type="image/x-icon" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="assets/https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="assets/https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Google Font -->
        <!--<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">-->
        <style>
            .tab-content .row {
                padding: 0px 10px;
            }
        </style>
    </head>
    <body class="skin-blue sidebar-mini sidebar-collapse">
        <div class="wrapper">
            <!--header-->
            <header class="main-header">  
                <a href="#" class="logo"> 
                    <span class="logo-mini"><b>S</b>A</span> 
                    <span class="logo-lg"><b>Student</b>Attendance</span>
                </a> 
                <nav class="navbar navbar-static-top"> 
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
                                    <span class="hidden-xs" id="user-name-header"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li class="user-header" style="height: 100px;">
                                        <p>
                                        </p>
                                    </li>
                                    <li class="user-footer">
                                        <div class="pull-left">
                                            <a href="assets/#" class="btn btn-default btn-flat">Profile</a>
                                        </div>
                                        <div class="pull-right">
                                            <a href="login?clearCookie=true" class="btn btn-default btn-flat">Sign out</a>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </nav> 
            </header>
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-12" id="content-container">
                            <div class="nav-tabs-custom">
                                <ul class="nav nav-tabs">
                                    <li class="active"><a href="assets/#class-tab" data-toggle="tab">Class</a></li>
                                    <li><a href="assets/#upload-tab" data-toggle="tab">Upload</a></li>
                                    <li><a href="assets/#settings" data-toggle="tab">Settings</a></li>
                                </ul>
                                <div class="tab-content">
                                    <div class="active tab-pane" id="class-tab">
                                        <div class="row">
                                            <div class="col-lg-2">
                                                <button type="button" class="btn btn-block btn-primary" id="btn-create-class"><i class="fa fa-fw fa-plus"></i> class</button>
                                                <div class="col-sm-12 list-view">
                                                    <ul class="timeline" id="list-class-container">
                                                    </ul>
                                                </div>
                                            </div>
                                            <div class="col-lg-2">
                                                <button type="button" class="btn btn-block btn-primary" id="btn-add-students"><i class="fa fa-fw fa-plus"></i> students</button>
                                                <div class="col-sm-12 list-view">
                                                    <ul class="timeline" id="list-student-container">

                                                    </ul>
                                                </div>
                                            </div>
                                            <div class="col-lg-8">
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <div class="image-container" id="large-image-container">
                                                            <canvas></canvas>
                                                        </div>
                                                        <div class="image-navigation" id="image-navigation">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-lg-4" style="padding-top: 15px;">
                                                        <input type="text" class="form-control" id="student-code" placeholder="Student Code">
                                                    </div>
                                                    <div class="col-lg-2" style="padding-top: 15px;">
                                                        <button id="save-mark-btn" type="button" class="btn btn-block btn-primary btn-lg">Save</button>
                                                    </div>
                                                </div>
                                                <!-- /.box -->
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="upload-tab">
                                        <div class="row">
                                            <div class="col-sm-9">
                                                <div class="file-upload">
                                                    <button class="file-upload-btn" type="button" onclick="$('.file-upload-input').trigger('click')">Add Image</button>
                                                    <div class="image-upload-wrap">
                                                        <form enctype="multipart/form-data" method="post">
                                                            <input id="file-upload-input" class="file-upload-input" type='file' onchange="readURL(this);"/>
                                                        </form>
                                                        <div class="drag-text">
                                                            <h3>Drag and drop a file or select add Image</h3>
                                                        </div>
                                                    </div>
                                                    <div class="file-upload-content">
                                                        <img class="file-upload-image" src="#" alt="your image" />
                                                    </div>
                                                    <div class="image-title-wrap">
                                                        <button type="button" onclick="removeUpload()" class="remove-image">
                                                            Remove 
                                                            <span class="image-title">Uploaded Image</span>
                                                        </button>
                                                    </div>
                                                </div>                                                
                                            </div>
                                            <div class="col-sm-3">
                                                <div class="form-group">
                                                    <label>Class</label>
                                                    <select class="form-control select2" style="width: 100%;" id="list-class-select">
                                                        <option selected="selected">Alabama</option>
                                                    </select>
                                                </div>
                                                <button type="button" class="btn btn-block btn-primary" id="btn-upload">Upload</button>
                                            </div>
                                        </div>                                       
                                    </div>
                                    <div class="tab-pane" id="settings">
                                        <div class="row" style="font-size: 11pt;">
                                            <div class="col-md-3">
                                                <h3> Change password </h3>
                                                <div class="form-group">
                                                    <label for="exampleInputEmail1">Old password</label>
                                                    <input type="password" class="form-control" id="old-password-input-text">
                                                </div>
                                                <div class="form-group">
                                                    <label for="exampleInputEmail1">New password</label>
                                                    <input type="password" class="form-control" id="new-password-input-text">
                                                </div>
                                                <button type="submit" class="btn btn-primary" id="btn-submit-change-password">Submit</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
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
        <!--=================-->
        <!--MODAL-->
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
        <!--add row modal-->
        <div id="add-row-modal" class="modal">
            <div class="modal-content">
                <div class="box box-primary" style="margin-bottom: 0;">
                    <div class="box-header with-border">
                        <h3 class="box-title pull-left">Add a row</h3>
                        <span class="close pull-right" onclick="hideAddRowModal()">&times;</span>
                    </div>
                    <div role="form" class="form">
                        <div class="box-body">
                        </div>
                        <div class="box-footer">
                            <div class="col-md-16" style="padding: 0px 15px;">
                                <button id="btn-submit-add" class="btn btn-primary">Submit</button>
                                <button id="btn-submit-edit" class="btn btn-primary">Edit</button>
                                <button id="btn-submit-add-student" class="btn btn-primary">Add Student</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div><!--processing modal-->
        <!--add student modal-->
        <div id="add-students-modal" class="modal">
            <div class="modal-content" style="min-width: 300px; width: 30%;border-radius: 4px;">
                <div class="box-header with-border">
                    <h3 class="box-title pull-left">Add students</h3>
                    <span class="close pull-right" onclick="document.getElementById('add-students-modal').style.display = 'none';">&times;</span>
                </div>
                <div style="padding: 10px;">
                    <textarea id="student-data" style="width: 100%; height: 300px;padding: 5px;border: 1px solid gray;border-radius: 4px;" wrap="off"></textarea>
                </div>
                <div  class="box-footer">
                    <button id="btn-submit-add-student" class="btn btn-primary">Submit</button>
                </div>
            </div>
        </div><!--add student modal-->
        <!--result add student modal-->
        <div id="result-add-students-modal" class="modal">
            <div class="modal-content" style="min-width: 300px; width: 30%;border-radius: 4px;height: 500px;">
                <div class="box-header with-border">
                    <h3 class="box-title pull-left">Results</h3>
                    <span class="close pull-right" onclick="document.getElementById('result-add-students-modal').style.display = 'none';">&times;</span>
                </div>
                <div style="padding: 10px;overflow: scroll; height: 450px;" id="result-add-student-container">
                </div>
            </div>
        </div><!--result add student modal-->
        <!--MODAL-->
        <!--======================-->
        <!-- jQuery 3 -->
        <script src="assets/bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="assets/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- Select2 -->
        <script src="assets/bower_components/select2/dist/js/select2.full.min.js"></script>
        <!-- InputMask -->
        <script src="assets/plugins/input-mask/jquery.inputmask.js"></script>
        <script src="assets/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
        <script src="assets/plugins/input-mask/jquery.inputmask.extensions.js"></script>
        <!-- date-range-picker -->
        <script src="assets/bower_components/moment/min/moment.min.js"></script>
        <script src="assets/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
        <!-- bootstrap datepicker -->
        <script src="assets/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
        <!-- bootstrap color picker -->
        <script src="assets/bower_components/bootstrap-colorpicker/dist/js/bootstrap-colorpicker.min.js"></script>
        <!-- bootstrap time picker -->
        <script src="assets/plugins/timepicker/bootstrap-timepicker.min.js"></script>
        <!-- SlimScroll -->
        <script src="assets/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <!-- iCheck 1.0.1 -->
        <script src="assets/plugins/iCheck/icheck.min.js"></script>
        <!-- FastClick -->
        <script src="assets/bower_components/fastclick/lib/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="assets/dist/js/adminlte.min.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="assets/dist/js/demo.js"></script>
        <!--show data-->
        <script src="assets/dist/js/showDataTable.js"></script>
        <script src="assets/dist/js/sweetalert.min.js"></script>
        <script src="assets/dist/js/upload-file.js"></script>
        <script src="assets/dist/js/showDataTable.js"></script>
        <script src="assets/dist/js/teacher.js"></script>
        <script src="assets/dist/js/script.js"></script>
        <!-- script on page -->
        <script type="text/javascript">
//                        intitTableFields('');
//                        getAllClasses();
                        $('.user-header p').html(decodeURIComponent(getCookie('realname')) + ' - Teacher');
                        $('#user-name-header').html(decodeURIComponent(getCookie('realname')));
                        getAllClasses();
        </script>
    </body>
</html>
