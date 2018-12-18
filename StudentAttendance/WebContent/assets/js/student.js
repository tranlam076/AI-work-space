
// -------------------------------------BASE-API---------------------------------------------
var currentHref = window.location.href.substring(0, window.location.href.lastIndexOf('/'));
var url = currentHref + '/api/';
var currentCourseId = null;
var userLoginId = getCookie('userLoginId'); 
var currentImageId = null;
var currentImage = {
    x: 0,
    y: 0,
    width: 0,
    height: 0
};

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
function Student () {
}

Student.prototype.addClickEvent = function () {
    var self = this;
    $('.list-class-item').off('click').on('click', function(event) {
        $('.list-class-item').removeClass('selected')
        $(this).addClass('selected');
        currentCourseId = $(this).find('.courseId').text();
        self.getAllImagesOfCourse(currentCourseId);
    });

    $('.list-image-item').off('click').on('click', function(event) {
        $('.list-image-item').removeClass('selected')
        currentImageId = $(this).attr('value');
        $(this).addClass('selected');
        faceMark.drawImage(($(this).find('img').attr('src')));
        faceMark.markAllFace({});

        $.ajax({
            type: 'GET',
            url: `${url}images/${currentImageId}/data/${userLoginId}`,
            success: function(result) {
                // var object = result;
                // if (object.status == "success") {
                // var data =decodeURIComponent(object);
                //     console.log(data);
                //     if (!data) {
                //         return;
                //     }
                console.log(result);
                faceMark.markAllFace([result]);
                // }
            },
            error: function(error) {
                console.log(error);
            }
        });
    });
}

Student.prototype.getAllCourses = function (student_id) {
    var self = this;
    request(url + 'students/' + student_id + '/courses', 'GET', null, function(error, data) {
        if (error) {
            console.log(error);
        } else {
            console.log(data);
            if (data.length === 0) {
                console.log('data null');
            } else {
                var divClass;
                for (var i = 0; i < data.length; i++) {
                    $('#listCourse').append(`
                        <div class="list-class-item">
                        <p class="item-name">${data[i].name}</p>
                        <p class="item-id"><span class="courseId">${data[i].id}</span></p>
                        <div class="d-flex justify-content-end">
                        </div>
                        </div>
                        `);
                }
                self.addClickEvent();
            }
        }
    });
}

Student.prototype.getCourse = function (course_id) {
    request(url  + 'courses/' + course_id, 'GET', null, function(error, data) {
        if (error) {
            console.log(error);
        } else {
            console.log(data);
            if (data.length === 0) {
                console.log('data null');
            } else {
                console.log("doing somethings");
            }
        }
    });
}

Student.prototype.getAllImagesOfCourse = function (course_id) {
    var self = this;
    request(url  + 'courses/' + course_id + '/images', 'GET', null, function(error, data) {
        if (error) {
            console.log(error);
        } else {
            console.log(data);
            $('#listImage').html('');
            if (data.length === 0) {
                console.log('data null');
            } else {
                for (var i = 0; i < data.length; i++) {
                    $('#listImage').append(`
                        <div class="list-image-item" value="${data[i].id}">
                        <img src="/StudentAttendance/images/${data[i].name}">
                        </div>
                        `);
                }
                self.addClickEvent();
            }
        }
    });
}

Student.prototype.getStudentInfo = function (teacher_id) {
    request(url + 'students/' + teacher_id, 'GET', null, function(error, data) {
        if (error) {
            console.log(error);
        } else {
            console.log(data);
            if (data.length === 0) {
                console.log('data null');
            } else {
                $('#bd-profile').text(data.name);
            }
        }
    });
}
// --------------------------------------------FACE MARK FUNCTION-------------------------------------
var mouseDownPosition = null;
var mousePosition = null;
var mouseDowning = false;
var newMark = null;
var selectedMark = null;
var typeMoveMark = null;
var currentMark = null;
function FaceMark () {
}

FaceMark.prototype.drawImage = function (src) {
    var canvas = document.getElementById('canvas-large-image');
    canvas.height = document.getElementById('canvas-large-image').offsetHeight;
    canvas.width = document.getElementById('canvas-large-image').offsetWidth;
    var ctx = canvas.getContext('2d');
    if (!src) {
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        return;
    }
    var x,y,width,height;
    var img = new Image();
    img.src = src;
    var scaleImg = img.width/img.height;
    var scaleCanvas = canvas.width/canvas.height

    if (scaleImg > scaleCanvas) {
        var scale = img.width/canvas.width;
        x = 0;
        width = canvas.width;
        height = width/scaleImg;
        y =  (canvas.height - height)/2
    } else {
        var scale = img.height/canvas.height;
        y = 0;
        height = canvas.height;
        width = height*scaleImg;
        x =  (canvas.width - width)/2
    }
    currentImage.x = x + 14;
    currentImage.y = y;
    currentImage.width = width;
    currentImage.height = height;
    img.onload = function () {
        ctx.drawImage(img, x, y, width, height);
    }
}

FaceMark.prototype.markAllFace = function (list) {
    $('.mark-face-rect').remove();
    if (list == null) {
        return;
    }
    for (var i = 0; i < list.length; i++) {
        var location = list[i].location;
        var mark = document.createElement('div');
        mark.className = 'mark-face-rect';
        mark.style.height = location.height/100*currentImage.height + 'px';
        mark.style.width = location.width/100*currentImage.width + 'px';
        mark.style.top = (location.top/100*currentImage.height + currentImage.y) + 'px';
        mark.style.left = (location.left/100*currentImage.width + currentImage.x) + 'px';
        mark.setAttribute('face_code', list[i].face_code);
        mark.setAttribute('student_code', list[i].student_code);
        $(mark).append('<div class="student-code">' + list[i].student_code + '</div>');
        $('#large-image-container').append(mark);
    }
};

FaceMark.prototype.getAllFacesMark = function () {
    var list = document.getElementsByClassName('mark-face-rect');
    var obj = [];
    for (var i = 0; i < list.length; i++) {
        var top = list[i].offsetTop;
        var left = list[i].offsetLeft;
        var width = list[i].offsetWidth;
        var height = list[i].offsetHeight ;

        var location = {
            top: (top - currentImage.y)*100/currentImage.height,
            left: (left - currentImage.x)*100/currentImage.width,
            width: width*100/currentImage.width,
            height: height*100/currentImage.height
        };
        obj.push({
            location : location,
            face_code : list[i].getAttribute('face_code'),
            student_code : list[i].getAttribute('student_code')
        });
    }
    return obj;
}

FaceMark.prototype.genRandomString =  function () {
  var text = "";
  var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  for (var i = 0; i < 10; i++)
    text += possible.charAt(Math.floor(Math.random() * possible.length));
return text;
}

FaceMark.prototype.changeMouseType = function () {
    if (mouseDowning) return;
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

FaceMark.prototype.selectMark = function(studentId) {
    currentMark = $(`.mark-face-rect[student_code='${studentId}']`)[0];
    selectedMark = currentMark;
    $('.mark-face-rect').removeClass('selected-mark');
    $(selectedMark).addClass('selected-mark');
    $($(selectedMark).children('.student-code')[0]).css('display','block');
    $('#student-code').val($(currentMark).attr('student_code'));
}
// ---------------------------------------------document ready --------------------------------------------
var faceMark = new FaceMark();
var student = new Student();
$(document).ready(function () {
    //-----------------------------------------get all information-----------------------------------------
    if (!userLoginId) {
        window.location.href = currentHref + '/login';
    }
    student.getStudentInfo(userLoginId);
    student.getAllCourses(userLoginId);
    // -------------------------------------------add new image data-------------------------------------------
    $('#btnSaveImageData').on('click', function(event) {
        if (!currentImageId) return;
        var studentFaceData = faceMark.getAllFacesMark();
        $.ajax({
            type: 'GET',
            url: `${url}images/${currentImageId}/data`,
            success: function(result) {
                var object = result;
                if (object.status == "success") {
                    var data =decodeURIComponent(object.data);
                    if (!data) {
                        return;
                    }
                    var allFaceMark = JSON.parse(data);
                    for (var i = 0; i< allFaceMark.length; i ++) {
                        if (allFaceMark[i].student_code == studentFaceData[0].student_code) {
                            allFaceMark[i] = studentFaceData[0];
                            break;
                        }
                    }

                    $.ajax({
                        type: 'POST',
                        url: `${url}images/${currentImageId}/data`,
                        data: {
                            data: JSON.stringify(allFaceMark)
                        },
                        error: function(error) {
                            console.log(error);
                        },
                        success: function(data) {
                            console.log(data);
                            alert('Lưu thành công!');
                        }
                    });
                }
            },
            error: function(error) {
                console.log(error);
            }
        });
    });

    //-------------------------------------------edit face mark------------------------------------------------ 
    $('#large-image-container').on('mousedown', function (e) {
        $('.mark-face-rect').removeClass('selected-mark');
        var parentOffset = $(this).parent().offset();
        mouseDownPosition = {
            x: Math.round(e.pageX - parentOffset.left),
            y: Math.round(e.pageY - parentOffset.top)
        };
        mousePosition = Object.assign(mouseDownPosition);
        if (typeMoveMark === "create") {
            $('.mark-face-rect').remove();
            newMark = document.createElement('div');
            newMark.className = 'mark-face-rect';
            newMark.style.height = '1px';
            newMark.style.width = '1px';
            newMark.style.top = mousePosition.y + 'px';
            newMark.style.left = mousePosition.x + 'px';
            newMark.setAttribute('face_code', faceMark.genRandomString());
            newMark.setAttribute('student_code', userLoginId);
            $(newMark).append('<div class="student-code">' + userLoginId + '</div>')
            $('#large-image-container').append(newMark);
            selectedMark = newMark;
        }
        if (typeMoveMark.startsWith('expand-') || typeMoveMark === 'move') {
            selectedMark = currentMark;
            $('.mark-face-rect').removeClass('selected-mark');
            $(selectedMark).addClass('selected-mark');
            $($(selectedMark).children('.student-code')[0]).css('display','block');
            $('#student-code').val($(currentMark).attr('student_code'));
        }
        mouseDowning = true;
    });
    //------------------------------------------controll mouse event------------------------------------------------
    $('#large-image-container').on('mouseup', function (e) {
        var canvas = document.getElementById("canvas-large-image");
        if (newMark !== null) {
            if (newMark.offsetHeight / canvas.offsetHeight < 0.03 || newMark.offsetWidth / canvas.offsetWidth < 0.03) {
                newMark.remove();
            }
        }
        try {
            $(selectedMark).addClass('selected-mark');
            currentSelectedMark = selectedMark;
        } catch(e) {
            console.log(e);
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
            var canvas = document.getElementById("canvas-large-image");
            if (typeMoveMark === 'move') {
                selectedMark.style.left = Math.min(canvas.offsetWidth - selectedMark.offsetWidth + 14, Math.max(14, (selectedMark.offsetLeft + (x - mousePosition.x)))) + 'px';
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
        mousePosition = {
            x: x,
            y: y
        };
        faceMark.changeMouseType();
    });
    //---------------------press delete to delete a selected face mark ------------------------
    $(document).on('keydown', function (e) {
        if (e.keyCode === 46) {
            if (selectedMark !== null) {
                selectedMark.outerHTML = '';
            }
        }
    });
    // ------------------------------action for change, update student code----------------------------
    $('#student-code').on('input', function (){
        $($(selectedMark).children('.student-code')[0]).css('display','block');
        selectedMark.setAttribute('student_code',$(this).val());
        $($(selectedMark).children('.student-code')[0]).html($(this).val());
    });
});
