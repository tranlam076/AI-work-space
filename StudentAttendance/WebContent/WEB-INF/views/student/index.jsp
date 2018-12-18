<%@page import="util.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Sinh Viên</title>
    <meta charset="utf-8">
    <meta name="viewport"
    content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css"
    rel="stylesheet">
    <link
    href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600"
    rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/css/font-awesome.css"
    rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/css/style.css"
    rel="stylesheet">
    <link
    href="<%=request.getContextPath()%>/assets/css/pages/dashboard.css"
    rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/css/index.css"
    rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/css/pages/teacher.css"
    rel="stylesheet">
</head>
<body>
    <%@include file="/WEB-INF/views/teacher/_top.jsp"%>
    <!-- /subnavbar -->
    <div class="main">
        <div class="main-inner">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="widget">
                            <div class="widget-header">
                                <i class="icon-list-alt"></i>
                                <h3>DS Lớp học</h3>
                            </div>
                            <!-- /widget-header -->
                            <div class="widget-content height-full overflow-y-auto" id="listCourse">
                                <!-- <div class="list-class-item">
                                    <p class="item-name">Ten lop hoc</p>
                                    <p class="item-id"><span class="courseId">id lop hoc</span></p>
                                    <div class="d-flex justify-content-end">
                                    </div>
                                </div> -->
                                <!-- /widget-content -->
                            </div>
                        </div>
                        <!-- /widget -->
                    </div>
                    <div class="col-sm-3">
                        <div class="widget">
                            <div class="widget-header">
                                <i class="icon-bookmark"></i>
                                <h3>Hình ảnh</h3>
                            </div>
                            <!-- /widget-header -->
                            <div class="widget-content height-full overflow-y-auto" id="listImage">
                                
                            </div>
                            <!-- /widget-content -->
                        </div>
                        <!-- /widget -->
                    </div>
                    <!-- /span3 -->

                    <div class="col-sm-6 height-full">
                        <div class="image-container" id="large-image-container">
                            <canvas id="canvas-large-image">Your browser doesn't support canvas</canvas>
                        </div>

                        <div class="face-mark-action">
                            <input type="text" id="student-code" placeholder="Student Code"  style="display: none;">
                            <button type="button" class="btn btn-primary pull-right" id="btnSaveImageData">Lưu</button>
                        </div>
                    </div>
                    <!-- /span6 -->
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /main-inner -->
    </div>
    <!-- /main -->

    <!-- Le javascript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script
        src="<%=request.getContextPath()%>/assets/js/jquery-3.3.1.min.js"></script>
        <script src="<%=request.getContextPath()%>/assets/js/bootstrap.js"></script>
        <script src="<%=request.getContextPath()%>/assets/js/student.js"></script>
    </body>
    </html>
