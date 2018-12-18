<%@page import="util.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Trang chủ</title>
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
	<%@include file="_top.jsp"%>
	<!-- /subnavbar -->
	<div class="main">
		<div class="main-inner">
			<div class="container-fluid">
				<div class="row">
					<div class="col-sm-2">
						<div class="widget">
							<div class="widget-header">
								<i class="icon-list-alt"></i>
								<h3>DS Lớp học</h3>
								<i class="icon-plus-sign-alt pull-right" id="btnAddClass"></i>
							</div>
							<!-- /widget-header -->
							<div class="widget-content height-full " id="listCourse">
								<!-- <div class="list-class-item">
			                        <p>Tên lớp học</p>
			                        <p>Thời gian học</p>
			                        <i class="icon-edit"></i> <i class="icon-trash"></i>
			                    </div> 
			                    <div class="list-class-item selected"></div>
			                -->

			            </div>
			        </div>
			        <!-- /widget -->
			    </div>
			    <!-- /span3 -->
			    <div class="col-sm-2">
			    	<div class="widget">
			    		<div class="widget-header">
			    			<i class="icon-bookmark"></i>
			    			<h3>DS Sinh viên</h3>
			    			<i class="icon-plus-sign-alt pull-right" id="btnAddStudent"></i>
			    		</div>
			    		<!-- /widget-header -->
			    		<div class="widget-content height-full " id="listStudent">
								<!-- <div class="list-student-item">
									<p class="item-name">Nguyễn Viết Hoàng</p>
									<p class="item-id">Mã số 101</p>
								</div>
								<div class="list-student-item selected"></div> -->
							</div>
							<!-- /widget-content -->
						</div>
						<!-- /widget -->
					</div>
					<!-- /span3 -->
					<div class="col-sm-2">
						<div class="widget">
							<div class="widget-header">
								<i class="icon-bookmark"></i>
								<h3>Hình ảnh</h3>
								<i class="icon-plus-sign-alt pull-right" id="btnAddImage"></i>
							</div>
							<!-- /widget-header -->
							<div class="widget-content height-full" id="listImage">
								<!-- <div class="list-image-item">
									<img src="<%=request.getContextPath()%>/assets/img/testimg.jpg" alt="url of image">
									<p>image here</p>
								</div> -->
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
							<input type="text" id="student-code" placeholder="Student Code">
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

	<div class="modal" id="modalAddClass">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title" id="addClassTitle">Thêm lớp học</h4>
					<h4 class="modal-title" id="editClassTitle" style="display: none;">Sửa lớp học</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				
				<!-- Modal body -->
				<div class="modal-body">
					<form onsubmit="return false;">	
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">Tên lớp học</span>
							</div>
							<input type="text" class="form-control" id="txtNameClass">
						</div>
					</form>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal" id="btnAddNewClass">Thêm</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal" id="btnEditClass" style="display: none;">Sửa</button>
				</div>

			</div>
		</div>
	</div>


	<div class="modal" id="modalAddStudent">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Thêm học sinh</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				
				<!-- Modal body -->
				<div class="modal-body">
					<form onsubmit="return false;">	
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">Tên học sinh</span>
							</div>
							<input type="text" class="form-control" id="txtNameStudent">
						</div>
					</form>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal" id="btnAddNewStudent">Thêm</button>
				</div>

			</div>
		</div>
	</div>
	
	<div class="modal modal-add-image" id="modalAddImage">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Thêm hình ảnh</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				
				<!-- Modal body -->
				<div class="modal-body row">
					<div class="col-sm-6 d-flex flex-column" style="text-align: center">
						<input type="file" id="inSelectFile" hidden>
						<div class="file" id="btnSelectFile">
							<span class="noselect">Chọn tệp từ máy tính</span>
						</div>
						<p>HOẶC</p>
						<div class="flex-grow-1 camera">
							<div class="choose-camera" id="btnOpenCamera">
								<span>Mở Camera</span>
							</div>
							<video id="videoCamera"></video>
						</div>
					</div>
					
					<div class="col-sm-6">
						<div class="preview">
							<img id="imgPreview">
						</div>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal" id="btnUploadImg">Thêm</button>
				</div>

			</div>
		</div>
	</div>
	<!-- Le javascript
		================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script
		src="<%=request.getContextPath()%>/assets/js/jquery-3.3.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/bootstrap.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/camera.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/service.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/teacher.js"></script>

		<script>		
			$("#btnAddClass").on("click", function() {
				$("#modalAddClass").modal();
			});

			$("#btnAddStudent").on("click", function() {
				$("#modalAddStudent").modal();
			});

			$("#btnAddImage").on("click", function() {
				$("#modalAddImage").modal();
			});

			$("#btnSelectFile").on("click", function() {
				console.log("click")
				$("#inSelectFile").trigger("click");
			});

			var currentImgFile = null;
			$("#inSelectFile").on("input", function() {
				var file = $(this)[0].files[0];
				$("#imgPreview").attr("src", URL.createObjectURL(file));
				currentImgFile = file;
			});

			var camera = new Camera(document.querySelector('#videoCamera'));
			$("#btnOpenCamera").on("click", function() {
				$("#btnOpenCamera").hide();
				camera.turnOn();
			});

			$("#videoCamera").on("click", function() {
				var file = camera.takePhoto();
				$("#imgPreview").attr("src", URL.createObjectURL(file));
				currentImgFile = file;
			});

		</script>
	</body>
	</html>
