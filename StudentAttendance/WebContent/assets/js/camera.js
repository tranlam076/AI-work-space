function Camera(video) {
	this.video = video;
}

Camera.prototype.isSupported = function() {
	navigator.getMedia = (navigator.getUserMedia
			|| navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia);
	return navigator.getMedia;
}

Camera.prototype.turnOn = function() {
	var self = this;
	if (this.isSupported()) {
		navigator.getMedia({
			video : true
		}, function(stream) {
			self.video.src = window.URL.createObjectURL(stream);
		}, function(err) {
			console.error("There was an error with accessing the camera stream:", err.name);
		});
	}
	this.video.play();
}

Camera.prototype.takePhoto = function() {
	function takeSnapshot(video) {
		var canvas = document.createElement("canvas");
		var context = canvas.getContext('2d');
		var width = video.videoWidth;
		var height = video.videoHeight;
		if (width && height) {
			canvas.width = width;
			canvas.height = height;
			context.drawImage(video, 0, 0, width, height);
			return canvas.toDataURL('image/png');
		}
	}
	function dataURLtoBlob(dataurl) {
		var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1], bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(
				n);
		while (n--) {
			u8arr[n] = bstr.charCodeAt(n);
		}
		return new Blob([ u8arr ], {
			type : mime
		});
	}

	var snap = takeSnapshot(this.video);
	var blob = dataURLtoBlob(snap);
	var file = new File([blob], "tmp.png", {type: 'image/png', lastModified: Date.now()});
	return file;
}