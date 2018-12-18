function Service() {
	if (typeof Service.instance === 'object') {
        return Service.instance;
    }

	Service.instance = this;
    return this;
}

new Service();

Service.prototype.upload = function (dataObj, callback) {
	var formData = new FormData();
	formData.append("type", dataObj.type);
	formData.append("id", dataObj.id);
	formData.append("file", dataObj.file);
	$.ajax({
		type : "POST",
		url : dataObj.url,
		cache : false,
		data : formData,
		processData: false,
		contentType : false,
		beforeSend: function() {
			if (callback.prepare != null)
                callback.prepare();
		},
		xhr: function () {
            myXhr = $.ajaxSettings.xhr();
            if (myXhr.upload) {
                myXhr.upload.addEventListener('progress', function (e) {
                    if (e.lengthComputable) {
                        var percentValue = Math.round((e.loaded / e.total) * 100);
                        if (callback.progress != null)
                            callback.progress(percentValue);
                    }
                }, false);
            }
            return myXhr;
        },
		success : function(result) {
			if (callback.success != null)
                callback.success(result);
		},
		error : function(result) {
			if (callback.error != null)
                callback.error(result);
		},
		complete : function () {
			if (callback.complete != null)
                callback.complete(result);
		}
	});
}