<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Admin</title>
    <meta charset="utf-8">
    <meta name="viewport"
    content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="<%=request.getContextPath()%>/assets/css/bootstrap.css"
    rel="stylesheet">
    <link
    href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600"
    rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/css/font-awesome.css"
    rel="stylesheet">
    <link
    href="<%=request.getContextPath()%>/assets/css/pages/dashboard.css"
    rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/css/index.css"
    rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/libs/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/libs/AdminLTE.min.css">
    <link href="<%=request.getContextPath()%>/assets/css/pages/admin.css" rel="stylesheet">
    <style>
    .tab-content .row {
        padding: 0px 10px;
    }
</style>
</head>
<body>
    <%@include file="/WEB-INF/views/teacher/_top.jsp"%>
    <div class="wrapper">
        <!-- Main content -->
        <section class="content">
            <div class="row" id='training'>
                <div class="col-sm-2 pull-right form-group" style="left: 83.33333333%">
                    <button type="button" id="btn-training" class="btn btn-info" style="width: 100%">Training</button>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12" id="content-container">

                    <div class="row">
                        <div class="col-sm-3 form-group">
                            <label>Table</label>
                            <select id="table" class="form-control select2 select2-hidden-accessible" style="width: 100%;" tabindex="-1" aria-hidden="true">
                            </select>
                        </div>
                        <div class="col-sm-3 form-group">
                            <label>Field</label>
                            <select id="field" class="form-control select2 select2-hidden-accessible" style="width: 100%;" tabindex="-1" aria-hidden="true">
                            </select>
                        </div>
                        <div class="col-sm-2 form-group" style="display: none;">
                            <label>list student ids</label>
                            <select id="listStudentId" class="form-control select2 select2-hidden-accessible" style="width: 100%;" tabindex="-1" aria-hidden="true">
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
                                <!-- <tfoot></tfoot> -->
                            </table>
                        </div>
                    </div>
                    <div class="row" id="add-row-form" style="display: none; margin: 5px 0px; width: 170px;">
                        <a id="add-one" class="btn btn-block btn-social btn-primary">
                            Add Row
                        </a>
                    </div>
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row --> 
        </section>
        <!-- /.content -->
    </div>
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

<!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script
    src="<%=request.getContextPath()%>/assets/js/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/js/libs/popper.js"></script>
    <script src="<%=request.getContextPath()%>/assets/js/bootstrap.js"></script>
    <script src="<%=request.getContextPath()%>/assets/js/libs/jquery.dataTables.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/js/admin.js"></script>
    <script type="text/javascript">
        getListTable();
        setEvent();
    </script>
</body>
</html>
