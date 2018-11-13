var api_url = 'teacher/api';
var listClass = null;
var listClassField = null;
var curClassItem = null;
var listStudent = null;
var listStudentField = null;
var listImage = null;
var listImageElement = null;
var selectedImg = null;
var mouseDownPosition = null;
var mouseUpPosition = null;
var mousePosition = null;
var mouseDowning = false;
var newMark = null;
var selectedMark = null;
var typeMoveMark = null;
var currentMark = null;
var moving = false;
var selectedImageName = null;
// --------- SET EVENT TO BUTTON ---------------
$(document).ready(function () {
    // delete class
    $('#list-class-container').on('click', '.delete', function (e) {
        setSelectedClass(e);
        deleteAClass();
    });
    // show form edit class
    $('#list-class-container').on('click', '.edit', function (e) {
        setSelectedClass(e);
        showEditForm(e);
    });
    // list students
    $('#list-class-container').on('click', '.timeline-header', function (e) {
        setSelectedClass(e);
        listAllStudentsInClass();
        getAllImagesInClass(curClassItem.code);
    });
    // delete student
    $('#list-student-container').on('click', '.delete-student', function (e) {
        if (curClassItem !== null) {
            var order = $($(e.target).closest('li')).attr('order');
            deleteAStudentInClass({
                code: listStudent[order].code,
                class_code: curClassItem.code
            });
        }
    });
    // add class
    $('#btn-submit-add').on('click', createAClass);
    // edit class
    $('#btn-submit-edit').on('click', editAClass);
    // show form add class
    $('#btn-create-class').on('click', showCreateClassForm);
    // add student
    $('#btn-submit-add-student').on('click', addStudentsToClass);
    // show form add student
    $('#btn-add-students').on('click', showAddStudentsForm);
    $('#list-day-select').on('change', showImageSlider);
    $('#image-navigation').on('click', 'img', function (e) {
        showLargeImage(e);
    });
    $('#btn-submit-change-password').on('click', changePassword);
    $('#large-image-container').on('mousedown', function (e) {
        $('#list-student-container .timeline-body').removeClass('selected-student-li');
        if (selectedImageName === null)
            return;
        $('.mark-face-rect').removeClass('selected-mark');
        var parentOffset = $(this).parent().offset();
        mouseDownPosition = {
            x: Math.round(e.pageX - parentOffset.left),
            y: Math.round(e.pageY - parentOffset.top)
        };
        mousePosition = Object.assign(mouseDownPosition);
        if (typeMoveMark === "create") {
            newMark = ___('div');
            newMark.className = 'mark-face-rect';
            newMark.style.height = '1px';
            newMark.style.width = '1px';
            newMark.style.top = mousePosition.y + 'px';
            newMark.style.left = mousePosition.x + 'px';
            newMark.setAttribute('face_code', genRandomString());
            newMark.setAttribute('student_code', 'unknown');
            $(newMark).append('<div class="student-code">unkown</div>')
            _('large-image-container').appendChild(newMark);
            selectedMark = null;
        }
        if (typeMoveMark.startsWith('expand-') || typeMoveMark === 'move') {
            selectedMark = currentMark;
            $('.mark-face-rect').removeClass('selected-mark');
            $($('.mark-face-rect').children('.student-code')[0]).css('display','none');
            $(selectedMark).addClass('selected-mark');
            $($(selectedMark).children('.student-code')[0]).css('display','block');
            $('#student-code').val($(currentMark).attr('student_code'));
        }
        mouseDowning = true;
    });
    $('#large-image-container').on('mouseup', function (e) {
        var canvas = _("canvas-large-image");
        if (newMark !== null) {
            if (newMark.offsetHeight / canvas.offsetHeight < 0.03 || newMark.offsetWidth / canvas.offsetWidth < 0.03) {
                newMark.remove();
            }
        }
        mouseDowning = false;
        newMark = null;
    });
    $('#large-image-container').on('mousemove', function (e) {
        var parentOffset = $(this).parent().offset();
        var x = e.pageX - parentOffset.left;
        var y = e.pageY - parentOffset.top;
        if (mouseDowning && newMark !== null) {
            newMark.style.height = Math.round(Math.abs(y - mouseDownPosition.y)) + 'px';
            newMark.style.width = Math.round(Math.abs(x - mouseDownPosition.x)) + 'px';
            newMark.style.left = Math.round(Math.min(mouseDownPosition.x, x)) + 'px';
            newMark.style.top = Math.round(Math.min(mouseDownPosition.y, y)) + 'px';
        }
        if (mouseDowning && selectedMark !== null) {
            var canvas = _("canvas-large-image");
            if (typeMoveMark === 'move') {
                selectedMark.style.left = Math.min(canvas.offsetWidth - selectedMark.offsetWidth, Math.max(0, (selectedMark.offsetLeft + (x - mousePosition.x)))) + 'px';
                selectedMark.style.top = Math.min(canvas.offsetHeight - selectedMark.offsetHeight, Math.max(0, (selectedMark.offsetTop + (y - mousePosition.y)))) + 'px';
            }
            if (typeMoveMark === 'expand-left') {
                var oldLeft = selectedMark.offsetLeft;
                var right = selectedMark.offsetLeft + selectedMark.offsetWidth;
                var oldWidth = selectedMark.offsetWidth;
                var newLeft = Math.min(Math.max(0, parseInt(x)), oldLeft + oldWidth - canvas.offsetWidth * 0.03);
                selectedMark.style.left = newLeft + 'px';
                selectedMark.style.width = (right - newLeft) + 'px';
            }
            if (typeMoveMark === 'expand-right') {
                selectedMark.style.width = Math.min(canvas.offsetWidth - selectedMark.offsetLeft, x - selectedMark.offsetLeft) + 'px';
            }
            if (typeMoveMark === 'expand-bottom') {
                selectedMark.style.height = Math.min(canvas.offsetHeight - selectedMark.offsetTop, y - selectedMark.offsetTop) + 'px';
            }
            if (typeMoveMark === 'expand-top') {
                var oldTop = selectedMark.offsetTop;
                var bottom = selectedMark.offsetTop + selectedMark.offsetHeight;
                var oldHeight = selectedMark.offsetHeight;
                var newTop = Math.min(Math.max(0, parseInt(y)), oldTop + oldHeight - canvas.offsetHeight * 0.03);
                selectedMark.style.top = newTop + 'px';
                selectedMark.style.height = (bottom - newTop) + 'px';
            }
        }
        if (!mouseDowning && typeMoveMark !== null && typeMoveMark.startsWith('expand-') || typeMoveMark === 'move') {
            var list = $('#list-student-container li');
            for (var i = 0; i < list.length; i++) {
                if ($(list[i]).attr('code') === $(currentMark).attr('studentcode')) {
                    $($(list[i]).find('.timeline-body')).addClass('selected-student-li');
                    $($('#list-student-container').closest('.list-view')).scrollTop(list[i].offsetTop - 5);
                }
            }
        }
        mousePosition = {
            x: x,
            y: y
        };
        changeMouseType();
    });
    $(document).on('keydown', function (e) {
        // press delete
        if (e.keyCode === 46) {
            if (selectedMark !== null) {
                selectedMark.outerHTML = '';
            }
        }
    });
    $('#list-student-container').on('click', 'li', function (e) {
        var code = ($($(e.target).closest('li')).attr('code'));
        var listItem = ($(e.target).closest('li'));
        $('#list-student-container .timeline-body').removeClass('selected-student-li');
        $($(listItem).find('.timeline-body')).addClass('selected-student-li');
        if (selectedMark !== null) {
            $(selectedMark).attr('studentcode', $(listItem).attr('code'));
            $('#student-code').val($(listItem).attr('code'));
        }
    });
    $('#save-mark-btn').on('click', changeFacesMark);
    $('#confidence').on('change', function () {
        var mark = $('.selected-mark')[0];
        $(mark).attr('confidence', $('#confidence').val());
    });
    $('#student-code').on('input',function (){
        $($(selectedMark).children('.student-code')[0]).css('display','block');
        selectedMark.setAttribute('student_code',$(this).val());
        $($(selectedMark).children('.student-code')[0]).html($(this).val());
    });
});
// --------- END - SET EVENT TO BUTTON ---------------
// --------------- API ----------------
// call API
var editAClass = function () {
    obj = {};
    for (var i = 0; i < listClassField.length; i++) {
        obj[listClassField[i]] = $('#' + listClassField[i]).val();
    }
    $.ajax({
        url: api_url,
        type: "post",
        data: {
            type: 'edit_a_class',
            obj: JSON.stringify(obj),
            condition: JSON.stringify(curClassItem)
        },
        dataType: 'json',
        success: function (response) {
            alert(response.state);
            if (response.state === 'success') {
                $('#add-row-modal').fadeOut();
                getAllClasses();
            }
        }
    });
};
// call API
var deleteAClass = function () {
    swal({
        title: "Are you sure?",
        text: "Once deleted, you will not be able to recover!",
        icon: "warning",
        buttons: true,
        dangerMode: true
    }).then((willDelete) => {
        if (willDelete) {
            // show processing modal
            $('#processing-modal').css('display', 'block');
            $('#list-student-container').html('');
            // send request
            $.ajax({
                url: api_url,
                type: "post",
                data: {
                    type: 'delete_a_class',
                    condition: JSON.stringify(curClassItem)
                },
                dataType: 'json',
                success: function (response) {
                    // hide processing modal
                    alert(response.state);
                    $('#processing-modal').css('display', 'none');
                    getAllClasses();
                }
            });
            // show dialog
            swal("The row has been deleted!", {
                icon: "success"
            });
        } else {
            $('#processing-modal').css('display', 'none');
            swal("Data is safe!");
        }
    });
};
// call API
var createAClass = function () {
    obj = {};
    for (var i = 0; i < listClassField.length; i++) {
        obj[listClassField[i]] = $('#' + listClassField[i]).val();
    }
    $.ajax({
        url: api_url,
        type: "post",
        data: {
            type: 'create_a_class',
            obj: JSON.stringify(obj)
        },
        dataType: 'json',
        success: function (response) {
            alert(response.state);
            if (response.state === 'success') {
                $('#add-row-modal').fadeOut();
                getAllClasses();
            }
        }
    });
};
// call API
var getAllClasses = function () {
    $('#processing-modal').fadeIn(200);
    $.ajax({
        url: api_url,
        type: "post",
        data: {
            type: 'get_all_classes'
        },
        dataType: 'json',
        success: function (response) {
            $('#list-class-container').html('');
            $('#list-class-select').html('');
            listClass = response.data;
            for (var i = 0; i < listClass.length; i++) {
                var value = listClass[i];
                str = '<li code="' + decodeURIComponent(value.code) + '" order="' + i + '">'
                        + '<div class="timeline-item custom-margin-timeline">'
                        + '<span class="time custom-margin-time color-red delete"><span class="glyphicon glyphicon-remove"></span></span>'
                        + '<span class="time custom-margin-time color-blue edit"><span class="glyphicon glyphicon-edit"></span></span>'
                        + '<h3 class="timeline-header"><a href="#" id="name-label">' + decodeURIComponent(value.name) + '</a></h3>'
                        + '<span class="time"><i class="fa fa-clock-o"></i><span id="schedule-label">' + decodeURIComponent(value.schedule) + '</span></span>'
                        + '<div class="timeline-body" id="code-label">' + decodeURIComponent(value.code) + '</div>'
                        + '</div>'
                        + '</li>';
                $('#class-tab #list-class-container').append(str);
                $('#list-class-select').append('<option code="' + decodeURIComponent(value.code) + '">' 
                        + decodeURIComponent(value.code) + ' --- ' 
                        + decodeURIComponent(value.name) + '</option>');
            }
            getAllClassField();
        }
    });
};
// call API
var getAllClassField = function () {
    $.ajax({
        url: api_url,
        type: "post",
        data: {
            type: 'get_all_class_field'
        },
        dataType: 'json',
        success: function (response) {
            listClassField = response.data;
            getAllStudentField();
        }
    });
};
// call API
var getAllStudentField = function () {
    $.ajax({
        url: api_url,
        type: "post",
        data: {
            type: 'get_all_student_field'
        },
        dataType: 'json',
        success: function (response) {
            $('#processing-modal').fadeOut(200);
            listStudentField = response.data;
        }
    });
};
// call API
var addStudentsToClass = function () {
    $('#processing-modal').fadeIn();
    studentObj = {};
    for (var i = 0; i < listStudentField.length; i++) {
        studentObj[listStudentField[i]] = $('#' + listStudentField[i]).val();
    }
    $.ajax({
        url: api_url,
        type: "post",
        data: {
            type: 'add_student_to_class',
            classObj: JSON.stringify(curClassItem),
            studentObj: JSON.stringify(studentObj)
        },
        success: function (response) {
            alert(response.state);
            listAllStudentsInClass();
//            getAllImagesInClass(curClassItem.code);
        }
    });
};
// call API
var listAllStudentsInClass = function () {
    $('#processing-modal').fadeIn();
    $.ajax({
        url: api_url,
        type: "post",
        data: {
            type: 'list_all_students_in_class',
            obj: JSON.stringify(curClassItem)
        },
        success: function (response) {
            if (response.state === 'success') {
                listStudent = response.data;
                $('#list-student-container').html('');
                for (var i = 0; i < listStudent.length; i++) {
                    var student = listStudent[i];
                    var strHtml = '<li order="' + i + '" code="' + student.code + '">'
                            + '<div class="timeline-item custom-margin-timeline">'
                            + '<span class="time custom-margin-time color-red delete-student"><span class="glyphicon glyphicon-remove"></span></span>'
                            + '<span class="time"><span id="schedule-label">' + student.name + '</span></span>'
                            + '<div class="timeline-body">' + student.code + '</div>'
                            + '</div>'
                            + '</li>';
                    $('#list-student-container').append(strHtml);
                }
                $('#processing-modal').fadeOut();
            }
        }
    });
};
// call API
var deleteAStudentInClass = function (condition) {
    $('#processing-modal').fadeIn();
    swal({
        title: "Are you sure?",
        text: "Once deleted, you will not be able to recover!",
        icon: "warning",
        buttons: true,
        dangerMode: true
    }).then((willDelete) => {
        if (willDelete) {
            // show processing modal
            $('#processing-modal').css('display', 'block');
            $('#list-student-container').html('');
            // send request
            $.ajax({
                url: api_url,
                type: "post",
                data: {
                    type: 'delete_a_student_in_class',
                    condition: JSON.stringify(condition)
                },
                success: function (response) {
                    // hide processing modal
                    $('#processing-modal').css('display', 'none');
                    listAllStudentsInClass();
                    if (response === 'success') {
                        // show dialog
                        swal("The row has been deleted!", {
                            icon: "success"
                        });
                    } else {
                        swal("Cannot delete this student!", {
                            icon: "fail"
                        });
                    }
                }
            });
        } else {
            $('#processing-modal').css('display', 'none');
            swal("Data is safe!");
        }
    });
};
// call API
var getAllImagesInClass = function (classCode) {
    $('#processing-modal').fadeIn();
    $.ajax({
        url: api_url,
        type: "post",
        data: {
            type: 'get_all_images_in_class',
            class_code: classCode
        },
        dataType: 'json',
        success: function (response) {
            listImage = response.data;
            $('#image-navigation').html('');
            listImageElement = [];
            var count = 0;
            for (var i = 0; i < listImage.length; i++) {
                listImageElement.push(new Image());
                listImageElement[i].setAttribute("order", i);
                listImageElement[i].onload = function () {
                    count++;
                    if (count === listImage.length) {
                        $('#processing-modal').fadeOut();
                    }
                };
                listImageElement[i].src = listImage[i];
            }
            showImageSlider();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
};
// call API
var changePassword = function () {
    $.ajax({
        url: api_url,
        type: "post",
        data: {
            type: 'change_password',
            old_password: encodeURIComponent($('#old-password-input-text').val()),
            new_password: encodeURIComponent($('#new-password-input-text').val())
        },
        success: function (response) {
            alert(response);
            if (response === 'Successful') {
                setCookie('password', encodeURIComponent($('#new-password-input-text').val()), 1);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
};
// call API
var changeFacesMark = function () {
    $.ajax({
        url: api_url,
        type: "post",
        data: {
            type: 'change_faces_mark',
            result_file: selectedImg.split('/')[1].split('.')[0],
            json_str: JSON.stringify(getAllFacesMark())
        },
        success: function (response) {
            alert(response);
        }
    });
};
// --------------- END - API ----------------
// --------------- SHOW FORM ----------------
function buildForm(listClassField, tableName) {
    $('#add-row-modal').css('display', 'block');
    var containerId = '#add-row-modal .form .box-body';
    $(containerId).html('');
    $('#add-row-modal .box-title').html(tableName);
    for (var i = 0; i < listClassField.length; i++) {
        var htmlStr =
                '<div class="col-sm-6 form-group">\n\
                    <label>' + listClassField[i] + '</label>\n\
                    <input type="text" class="form-control" id="' + listClassField[i] + '" autocomplete="off">\n\
                </div>';
        $(containerId).append(htmlStr);
    }
    $('#teacher_email').val(decodeURIComponent(getCookie("email")));
    $('#teacher_email').prop('disabled', true);
}
// show form
var showEditForm = function (e) {
    $('#btn-submit-edit').css('display', 'block');
    $('#btn-submit-add').css('display', 'none');
    $('#btn-submit-add-student').css('display', 'none');
    //
    buildForm(listClassField, "class");
    $('#add-row-modal .box-title').html('Edit');
    //
    for (var key in curClassItem) {
        $('#' + key).val(decodeURIComponent(curClassItem[key]));
    }
    $('#code').prop('disabled', true);
};
// show form
var showCreateClassForm = function () {
    $('#btn-submit-edit').css('display', 'none');
    $('#btn-submit-add').css('display', 'block');
    $('#btn-submit-add-student').css('display', 'none');
    buildForm(listClassField, "class");
};
// show form
var showAddStudentsForm = function () {
    if (curClassItem === null) {
        alert("You must select class first.");
        return;
    }
    $('#btn-submit-edit').css('display', 'none');
    $('#btn-submit-add').css('display', 'none');
    $('#btn-submit-add-student').css('display', 'block');
    buildForm(listStudentField, "class");
    $('#class_code').val(curClassItem.code);
    $('#class_code').prop('disabled', true);
};
// --------------- END - SHOW FORM ----------------
//-------------- OTHER --------------
var setSelectedClass = function (e) {
    $('.timeline-item').removeClass('class-item-selected');
    $($(e.target).closest('.timeline-item')).addClass('class-item-selected');
    //
    var idx = $($(e.target).closest('li')).attr('order');
    curClassItem = listClass[idx];
};
var getTimeFromImageURL = function (s) {
    var arr = s.split('/');
    return parseInt(arr[arr.length - 1].substring(0, arr[arr.length - 1].length - 4));
};
var showImageSlider = function () {
    selectedImageName = null;
    $('#image-navigation').html('');
    for (var j = 0; j < listImageElement.length; j++) {
        var cloneImage = listImageElement[j].cloneNode();
        cloneImage.setAttribute('height', '50');
        $('#image-navigation').append(cloneImage);
    }
    $('#large-image-container').html('');
};
var showLargeImage = function (e) {
    var order = $(e.target).attr('order');
    $('#large-image-container').html('<canvas id="canvas-large-image"></canvas>');
    var canvas = _("canvas-large-image");
    canvas.height = _('large-image-container').offsetHeight;
    canvas.width = _('large-image-container').offsetWidth;
    var ctx = canvas.getContext("2d");
    var cloneImage = listImageElement[order].cloneNode();
    ctx.drawImage(cloneImage, 0, 0, canvas.width, canvas.height);
    var src = $(e.target).attr('src');
    var arr = src.split('/');
    selectedImageName = arr[arr.length - 1].split('.')[0];
    $('#processing-modal').fadeIn();
    selectedImg = listImage[order];
    $.ajax({
        url: api_url,
        type: "post",
        data: {
            type: 'get_all_rectangles_in_image',
            result_file: listImage[order].split('/')[1].split('.')[0]
        },
        success: function (response) {
            $('#processing-modal').fadeOut();
            alert(response.state);
            markAllFace(response.data);
        }
    });
};
var markAllFace = function (list) {
    $('.mark-face-rect').remove();
    var W = _("canvas-large-image").offsetWidth;
    var H = _("canvas-large-image").offsetHeight;
    for (var i = 0; i < list.length; i++) {
        var location = list[i].location;
        var htmlStr = '<div class="mark-face-rect" style="\n\
                            width: ' + (location.width) + '%; \n\
                            height: ' + (location.height) + '%; \n\
                            position: absolute; \n\
                            top: ' + location.top + '%; \n\
                            left: ' + location.left + '%;"\n\
                            face_code = "' + list[i].face_code + '"\n\
                            student_code = "' + list[i].student_code + '">\n\
                            <div class="student-code">' + list[i].student_code + '</div>\n\
                        </div>';
        $('#large-image-container').append(htmlStr);
    }
};
function _(str) {
    return document.getElementById(str);
}
function log(str) {
    console.log(str);
}
function ___(str) {
    return document.createElement(str);
}
function changeMouseType() {
    var list = $('.mark-face-rect');
    var range = 3;
    for (var i = list.length - 1; i >= 0; i--) {
        if (mousePosition.x < list[i].offsetLeft - range || mousePosition.x > list[i].offsetLeft + list[i].offsetWidth + range)
            continue;
        if (mousePosition.y < list[i].offsetTop - range || mousePosition.y > list[i].offsetTop + list[i].offsetHeight + range)
            continue;
        currentMark = list[i];
        if (Math.abs(mousePosition.x - list[i].offsetLeft) < range) {
            typeMoveMark = "expand-left";
            document.body.style.cursor = 'w-resize';
            return;
        }
        if (Math.abs(mousePosition.x - (list[i].offsetLeft + list[i].offsetWidth)) < range) {
            typeMoveMark = "expand-right";
            document.body.style.cursor = 'w-resize';
            return;
        }
        if (Math.abs(mousePosition.y - list[i].offsetTop) < range) {
            typeMoveMark = "expand-top";
            document.body.style.cursor = 'n-resize';
            return;
        }
        if (Math.abs(mousePosition.y - (list[i].offsetTop + list[i].offsetHeight)) < range) {
            typeMoveMark = "expand-bottom";
            document.body.style.cursor = 'n-resize';
            return;
        }
        typeMoveMark = "move";
        document.body.style.cursor = 'move';
        return;
    }
    typeMoveMark = "create";
    document.body.style.cursor = 'auto';
}
function getAllFacesMark() {
    var canvas = _('canvas-large-image');
    var W = canvas.offsetWidth;
    var H = canvas.offsetHeight;
    var list = document.getElementsByClassName('mark-face-rect');
    var obj = [];
    for (var i = 0; i < list.length; i++) {
        var top = (list[i].offsetTop / H) * 100.0;
        var left = (list[i].offsetLeft / W) * 100.0;
        var width = (list[i].offsetWidth / W) * 100.0;
        var height = (list[i].offsetHeight / H) * 100.0;
        var location = {
            top: top,
            left: left,
            width: width,
            height: height
        };
        obj.push({
            location : location,
            face_code : list[i].getAttribute('face_code'),
            student_code : list[i].getAttribute('student_code')
        });
    }
    return obj;
}
//-------------- END - OTHER --------------
function genRandomString() {
  var text = "";
  var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

  for (var i = 0; i < 10; i++)
    text += possible.charAt(Math.floor(Math.random() * possible.length));

  return text;
}