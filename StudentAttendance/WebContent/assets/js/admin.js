// -------------------------------------BASE-API---------------------------------------------
var currentHref = window.location.href.substring(0, window.location.href.lastIndexOf('/'));
var url = currentHref + '/api/';

function request(url, method, data, callback) {
    $.ajax({
        url: url,
        data: data,
        error: function(error) {
            callback(error);
        },
        success: function(data) {
            callback(null, data);
        },
        type: method
    });
}

function getCookie(name) {
  var value = "; " + document.cookie;
  var parts = value.split("; " + name + "=");
  if (parts.length == 2) return parts.pop().split(";").shift();
}

// --------------------------------------------TEACHER FUNTION-------------------------------------------

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
        buildFormSubmit($('#table').val());
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
        if (confirm("do you want to delete this column?")) {
            deleteRow(condition);
        }
    });
    //
    $(document).on('click', '.edit', function (event) {
        editCondition = getCondition($(event.target).closest('tr'));
        console.log(editCondition);
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
        if (key) {
            if (key.includes('Id')) {
                key = key.substring(0, key.indexOf("Id")) + "_id";
            }
            condition[key] = value;
        }
    });
    return condition;
}

function getListTable() {
    request(url + 'admin/tables', 'GET', null, function(error, result) {
        if (error) {
            console.log(error);
        } else {
            console.log(result);
            $('#table').html('');
            console.log(result[0]);
            for (var i = 0; i < result.length; i++) {
                $('#table').append('<option>' + result[i] + '</option>');
            }
            getListField(result[0]);
            updateUI();
        }
        
    });
}

function getListField(tableName) {
    request(url + 'admin/tables/' + tableName + '/columns', 'GET', null, function(error, result) {
        if (error) {
            console.log(error);
        } else {
            console.log(result);
            $('#field').html('');
            for (var i = 0; i < result.length; i++) {
                $('#field').append('<option>' + result[i] + '</option>');
            }
            updateUI();
        }
        
    });
}

var search = function () {
    resetTable();
    var data = {
        tableName: $('#table').val(),
        keyWord: $('#keywords').val() + "",
        field: $('#field').val()
    }
    request(url + 'admin/tables/search', 'POST', data, function(error, result) {
        if (error) {
            console.log(error);
        } else {
            console.log(result);
            showData(result);
        }
        updateUI();
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
    //
    htmlStr = "";
    for (var i = 0; i < data.length; i++) {
        htmlStr += '<tr>';
        for (var key in data[i]) {
            var showValue = (data[i][key] + "").trim();
            if (key === 'password')
                showValue = '********';
            htmlStr += '<td key="' + key + '" value="' + (data[i][key] + "").trim() + '">' + showValue + '</td>';
        }
        htmlStr += '<td><div class="btn-group"><button style="padding: 3px;" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-fw fa-gear"></i><span class="icon-gear"></span></button><div class="dropdown-menu"><a href="#" style="color:#e08e0b" class="dropdown-item edit"><b>Edit</b></a><a href="#" style="color:#d73925" class="dropdown-item delete"><b>Delete</b></a></div></div></td>';
        htmlStr += '</tr>';
    }
    $('#data-table' + ' tbody').html(htmlStr);
    // ---load image here!!
    var table = $('#data-table' + '').DataTable({
        'paging': true,
        'pageLength': 10,
        'lengthChange': false,
        'searching': false,
        'ordering': true,
        'info': false,
        'autoWidth': true,
        'scrollX': '100%',
        // 'columnDefs': [
        //     {   
        //         targets: 2,
        //         render: function(data) {
        //             if (data.includes('.jpg') || data.includes('.png')) {
        //                 return '<img src="'+data+'">';
        //             }  else {
        //                 return data;
        //             }
        //         }
        //     }       
        // ]
    });
    //
    $('#add-row-form').css('display', 'block');
    $('#data-table').css("min-height","100px");
}

function resetTable() {
    $('#add-row-form').css('display', 'none');
    $('#data-table-container').html('<table id="data-table" class="table table-bordered" style="width:100%"><thead></thead><tbody>');
}

var buildFormSubmit = function (tableName) {
    $('#add-row-modal').css('display', 'block');
    var containerId = '#add-row-modal .form .box-body';
    $(containerId).html('');
    $('#add-row-modal .box-title').html(tableName);
    $('#field').children('option').each(function () {
        var htmlStr =
        '<div class="col-sm-12 form-group max-width">\n\
        <label>' + $(this).html() + '</label>\n\
        <input type="text" class="form-control" id="' + $(this).html() + '" autocomplete="off">\n\
        </div>';
        $(containerId).append(htmlStr);
    });
};

var insertRow = function () {
    var obj = getObj();
    var data = {
        tableName: $('#table').val(),
        data: objToString(obj).toString()
    }
    console.log(data);
    request(url + 'admin/tables/add', 'POST', data, function(error, result) {
        if (error) {
            console.log(error);
        } else {
            console.log(result);
            refreshPage ();
        }
    });
};

function editRow(condition, obj) {
    var data = {
        tableName: $('#table').val(),
        condition: objToString(condition).toString(),
        data: objToString(obj).toString()
    }
    request(url + 'admin/tables/update', 'POST', data, function(error, result) {
        if (error) {
            console.log(error);
        } else {
            console.log(result);
            refreshPage ();
        }
    });
}

function deleteRow(condition) {
    var data = {
        tableName: $('#table').val(),
        condition: objToString(condition).toString(),
    }
    console.log(data);
    request(url + 'admin/tables/delete', 'POST', data, function(error, result) {
        if (error) {
            console.log(error);
        } else {
            console.log(result);
            refreshPage ();
        }  
    });
}

function hideAddRowModal() {
    $('#add-row-modal').fadeOut();
}

function getObj() {
    var obj = {};
    $('#field').children('option').each(function () {
        var id = $(this).html();
        obj[id] = $('#' + id).val();
    });
    return obj;
}

function objToString (obj) {
    var result = '';
    for (var key in obj) {
        result += key + ':::' + obj[key] + ',';
    }
    result = result.substring(0, result.length - 1);
    return result;
}

function refreshPage () {
    $('#add-row-modal').hide('slow/400/fast', function() {
    });
    search ();
}

function updateUI () {
    var tableName =  $('#table').val();
    var field = $('#field').val();
    if (tableName == 'student_image' && field == 'student_id') {
        $('#listStudentId').parent().show();
        $('#keywords').parent().removeClass('col-sm-4');
        $('#keywords').parent().addClass('col-sm-2');
        request(url + 'admin/tables/list-student-id', 'GET', null, function(error, result) {
            if (error) {
                console.log(error);
            } else {
                console.log(result);
                $('#listStudentId').html('');
                for (var i = 0; i < result.length; i++) {
                    $('#listStudentId').append('<option>' + result[i] + '</option>');
                }
                $('#listStudentId').change(function(event) {
                    $('#keywords').val($('#listStudentId').val());
                });
            }  
        });
    } else {
        $('#listStudentId').parent().hide();
        $('#keywords').parent().removeClass('col-sm-2');
        $('#keywords').parent().addClass('col-sm-4');
    }

}

jQuery(document).ready(function($) {
    $('#bd-profile').text('Admin');
    updateUI();

    $('#table').change(function(event) {
        updateUI();
    });
    $('#field').change(function(event) {
        updateUI();
    });

    $('#btn-training').on('click', function(event) {
        request(url + 'admin/train', 'GET', null, function(error, result) {
            if (error) {
                console.log(error);
            } else {
                console.log(result);
                alert("Training done!")
            }
        });
    });
});

