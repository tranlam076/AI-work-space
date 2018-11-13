var api_url = 'admin/api';
var editCondition = {};

function setEvent() {
    //
    $("#table").on('change', function () {
        resetTable();
        var tableName = $('#table').val();
        getListField(tableName);
    });
    $('#btn-search').click(function () {
        search();
    });
    //
    $('.pagination').on('click', '.paginate_button', function (event) {
        offset = parseInt($(event.target).attr('offset'));
        searchAction();
    });
    $(document).on('click', '.view', function (event) {
        var rowid = parseInt(($(event.target).closest('tr')).attr('rowid'));
    });
    $(document).on('click', '.edit', function (event) {
        var rowid = parseInt(($(event.target).closest('tr')).attr('rowid'));
    });
    $('#add-one').on('click', function () {
        buildFormSubmit($(tablesNameCbId).val());
        $('#btn-submit-edit').css('display', 'none');
        $('#btn-submit-add').css('display', 'block');
    });
    $('.close').on('click', function () {
        $('.modal').fadeOut();
    });
    $('#btn-submit-add').on('click', insertRow);
    //
    $(document).on('click', '.delete', function (event) {
        var condition = getCondition($(event.target).closest('tr'));
        swal({
            title: "Are you sure?",
            text: "Once deleted, you will not be able to recover this imaginary file!",
            icon: "warning",
            buttons: true,
            dangerMode: true
        }).then((willDelete) => {
            if (willDelete) {
                $('#processing-modal').css('display', 'block');
                deleteRow(condition);
                $('#processing-modal').css('display', 'none');
                search();
                swal("The row has been deleted!", {
                    icon: "success"
                });
            } else {
                swal("Data is safe!");
            }
        });
    });
    //
    $(document).on('click', '.edit', function (event) {
        editCondition = getCondition($(event.target).closest('tr'));
        buildFormSubmit($('#table').val());
        $('#btn-submit-edit').css('display', 'block');
        $('#btn-submit-add').css('display', 'none');
        for (var key in editCondition) {
            $('#' + key).val(editCondition[key]);
        }
    });
    //
    $('#btn-submit-edit').on('click', function () {
        editRow(editCondition, getObj());
    });
}

function getCondition(rowElement) {
    var condition = {};
    $(rowElement).children('td').each(function () {
        var key = $(this).attr('key');
        var value = $(this).attr('value');
        if (key)
            condition[key] = value;
    });
    return condition;
}

function getListTable() {
    $.ajax({
        type: 'POST',
        url: api_url,
        data: {
            type: "get_list_table"
        },
        dataType: "json",
        success: function (resp) {
            if (resp.state === "success") {
                $('#table').html('');
                for (var i = 0; i < resp.data.length; i++) {
                    $('#table').append('<option>' + resp.data[i] + '</option>');
                }
                getListField(resp.data[0]);
            } else {
                alert(resp.state);
            }
        }
    });
}

function getListField(tableName) {
    $.ajax({
        type: 'POST',
        url: api_url,
        data: {
            type: "get_list_field",
            table_name: tableName
        },
        dataType: "json",
        success: function (resp) {
            if (resp.state === "success") {
                $('#field').html('');
                for (var i = 0; i < resp.data.length; i++) {
                    $('#field').append('<option>' + resp.data[i] + '</option>');
                }
            } else {
                alert(resp.state);
            }
        }
    });
}

function setFieldName() {
    $(fieldNameCbId).html('');
    var table = getTableByName($(tablesNameCbId).val());
    for (var i = 0; i < table.field.length; i++) {
        $(fieldNameCbId).append('<option>' + table.field[i].name + '</option>');
    }
}

var search = function () {
    $('#processing-modal').css('display', 'block');
    resetTable();
    $.ajax({
        url: api_url,
        type: "post",
        data: {
            type: 'search',
            table_name: $('#table').val(),
            keyword: $('#keywords').val(),
            field: $('#field').val()
        },
        dataType: 'json',
        success: function (response) {
            showData(response.data);
            $('#processing-modal').css('display', 'none');
        }
    });
};

function showData(data) {
    //
    var htmlStr = "<tr>";
    $("#field option").each(function () {
        htmlStr += '<th>' + $(this).val() + '</th>';
    });
    htmlStr += '<th>Option</th>';
    htmlStr += '</tr>';
    $('#data-table' + ' thead').html(htmlStr);
    $('#data-table' + ' tfoot').html(htmlStr);
    //
    htmlStr = "";
    for (var i = 0; i < data.length; i++) {
        htmlStr += '<tr>';
        for (var key in data[i]) {
            var showValue = data[i][key].trim();
            if (key === 'password')
                showValue = '********';
            htmlStr += '<td key="' + key + '" value="' + data[i][key].trim() + '">' + showValue + '</td>';
        }
        htmlStr += '<td><div class="input-group-btn"><button style="padding: 3px;" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-fw fa-gear"></i><span class="fa fa-caret-down"></span></button><ul class="dropdown-menu"><li><a href="#" style="color:#00acd6" class="view"><b>View</b></a></li><li><a href="#" style="color:#e08e0b" class="edit"><b>Edit</b></a></li><li><a href="#" style="color:#d73925" class="delete"><b>Delete</b></a></li></ul></div></td>';
        htmlStr += '</tr>';
    }
    $('#data-table' + ' tbody').html(htmlStr);
    //
    $('#data-table' + '').DataTable({
        'paging': false,
        'lengthChange': false,
        'searching': false,
        'ordering': true,
        'info': false,
        'autoWidth': true
    });
    //
    $('#add-row-form').css('display', 'block');
    $('#infor-table-container').css('display', 'block');
}

function resetTable() {
    $('#add-row-form').css('display', 'none');
    $('#add-row-form').css('display', 'none');
    $('#data-table-container').html('<table id="data-table" class="table table-bordered table-hover"><thead></thead><tbody></tbody><tfoot></tfoot></table>');
}

var buildFormSubmit = function (tableName) {
    $('#add-row-modal').css('display', 'block');
    var containerId = '#add-row-modal .form .box-body';
    $(containerId).html('');
    $('#add-row-modal .box-title').html(tableName);
    $('#field').children('option').each(function () {
        var htmlStr =
                '<div class="col-sm-6 form-group">\n\
                    <label>' + $(this).html() + '</label>\n\
                    <input type="text" class="form-control" id="' + $(this).html() + '" autocomplete="off">\n\
                </div>';
        $(containerId).append(htmlStr);
    });
};

function getObj() {
    var obj = {};
    $('#field').children('option').each(function () {
        var id = $(this).html();
        obj[id] = $('#' + id).val();
    });
    return obj;
}

var insertRow = function () {
    $('#processing-modal').css('display', 'block');
    var obj = getObj();
    $.ajax({
        url: api_url,
        type: "post",
        data: {
            type: 'insert',
            table_name: $('#table').val(),
            obj: JSON.stringify(obj)
        },
        dataType: 'json',
        success: function (response) {
            $('#processing-modal').css('display', 'none');
            alert(response.state);
        }
    });
};

function editRow(condition, obj) {
    $.ajax({
        url: api_url,
        type: "post",
        data: {
            type: 'edit',
            table_name: $('#table').val(),
            condition: JSON.stringify(condition),
            obj: JSON.stringify(obj)
        },
        dataType: 'json',
        success: function (response) {
            alert(response.state);
        }
    });
}

function deleteRow(condition) {
    $.ajax({
        url: api_url,
        type: "post",
        data: {
            type: 'delete',
            table_name: $('#table').val(),
            condition: JSON.stringify(condition)
        },
        dataType: 'json',
        success: function (response) {
            alert(response.state);
        }
    });
}

function hideAddRowModal() {
    $('#add-row-modal').fadeOut();
}