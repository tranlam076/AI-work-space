function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('.mark-face-rect').remove();
            $('.image-upload-wrap').hide();
            $('.file-upload-image').attr('src', e.target.result);
            $('.file-upload-content').show();
            $('.image-title').html(input.files[0].name);
        };
        reader.readAsDataURL(input.files[0]);
    } else {
        removeUpload();
    }
}
function removeUpload() {
//    $('.file-upload-input').replaceWith($('.file-upload-input').clone());
    $('.file-upload-input').val('');
    $('.file-upload-content').hide();
    $('.image-upload-wrap').removeClass('image-dropping');
    $('.image-upload-wrap').show();
}
$('.image-upload-wrap').bind('dragover', function () {
    $('.image-upload-wrap').addClass('image-dropping');
});
$('.image-upload-wrap').bind('dragleave', function () {
    $('.image-upload-wrap').removeClass('image-dropping');
});
function _(el) {
    return document.getElementById(el);
}
$('#btn-upload').click(function (e) {
    var inputFile = document.getElementById('file-upload-input');
    if (inputFile.files.length === 0) {
        return;
    }
    var data = new FormData();
    data.append($('#list-class-select').find(":selected").attr('code'), inputFile.files[0]);
    var ajax = new XMLHttpRequest();
    ajax.upload.addEventListener("progress", progressHandler, false);
    ajax.addEventListener("load", completeHandler, false);
    ajax.addEventListener("error", errorHandler, false);
    ajax.addEventListener("abort", abortHandler, false);
    $('#btn-upload').html("Sending...");
    ajax.open("POST", "uploadImage");
    ajax.send(data);
});
function progressHandler(event) {
    $('#processing-modal').fadeIn(200);
    console.log("Uploaded " + event.loaded + " bytes of " + event.total);
    var percent = (event.loaded / event.total) * 100;
    console.log(Math.round(percent) + "% uploaded... please wait");
}
function completeHandler(event) {
    $('#processing-modal').fadeOut(200);
    var resp = JSON.parse(event.target.responseText.replace(/\'/g, '"'));
    if (resp.state !== 'success')
        return;
    var list = resp.data;
    $('.mark-face-rect').remove();
    for (var i = 0; i < list.length; i++) {
        var location = list[i].location;
        var htmlStr = '<div class="mark-face-rect" style="\n\
                            width: ' + (location.width) + '%; \n\
                            height: ' + (location.height) + '%; \n\
                            position: absolute; \n\
                            top: ' + location.top + '%; \n\
                            left: ' + location.left + '%;">\n\
                            <div class="student-code">' + list[i].student_code + '</div>\n\
                        </div>';
        $('.file-upload-content').append(htmlStr);
    }
    $('#btn-upload').html("Upload");
}
function errorHandler(event) {
    console.log("Upload Failed");
}
function abortHandler(event) {
    console.log("Upload Aborted");
}
$(document).on("mouseenter", ".mark-face-rect", function() {
    studentCode = $(this).children('.student-code')[0];
    $(studentCode).css('display','block');
});
$(document).on("mouseleave", ".mark-face-rect", function() {
    studentCode = $(this).children('.student-code')[0];
    $(studentCode).css('display','none');
});

