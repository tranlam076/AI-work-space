'use strict';
jQuery(document).ready(function($) {
	var SINGLETON = function() {
		// Private Properties
		var check1 =true;
		var check2 =true;
		var url = "http://localhost:8080/Task";

		// Public Properties

		// Private Method
		function showForm () {
			$('.add-cat').click(function(event) {
				if (check1) {
					$('.edit-cat-form').show();
					$('.add-cat').val('Add Position');
					$('.name-cat').val('');
					check1 = false;
				} else {
					$('.edit-cat-form').hide();
					check1 = true;
				}
			});

			$('.add-news').click(function(event) {
				if (check2) {
					$('.edit-news-form').show();
					$('.add-news').val('Add Person');
					$('.title-news').val('');
					$('.content-news').val('');
					check2 = false;
				} else {
					$('.edit-news-form').hide();
					check2 = true;
				}
			});

			$('.main-menu-1').click(function(event) {
				$('.content-cat').show();
				$('.content-news').hide();
			});

			$('.main-menu-2').click(function(event) {
				$('.content-news').show();
				$('.content-cat').hide();
			});

		}

		function showEdit () {
			$('.btn-edit').click(function(event) {
				var parent = $(this).parent();
				var id_news;
				var title;
				var content;
				var nameCat;
				var id_cat;
				if (parent.siblings().length < 3) {
					id_cat = $(parent.siblings()[0]).text();
					nameCat = $(parent.siblings()[1]).text();
					$('.edit-cat-form').show();
					$('.add-cat').val('Edit Position');
					$('.name-cat').val(nameCat);

				}

				if (parent.siblings().length >2) {
					id_news = $(parent.siblings()[0]).text();
					title = $(parent.siblings()[1]).text();
					content = $(parent.siblings()[2]).text();
					$('.edit-news-form').show();
					$('.add-news').val('Edit Person');
					$('.title-news').val(title);
					$('.content-news').val(content);
				}
			});
		}

		function excecuteDelete () {
			$('.btn-delete').click(function(event) {
				if (confirm("Are you sure?")) {
					var parent = $(this).parent();
					var id_news;
					var id_cat;
					if (parent.siblings().length < 3) {
						id_cat = $(parent.siblings()[0]).text();
						request(url +  '/admin/delete-cat', 'POST', {id_cat: id_cat}, function(error, data) {
							if (error) {
								console.log(error);
							} else {
								window.location.href = "http://localhost:8080/Task/admin/index";
							}
						});
					}

					if (parent.siblings().length >2) {
						id_news = $(parent.siblings()[0]).text();
						request(url +  '/admin/delete-news', 'POST', {id_news: id_news}, function(error, data) {
							if (error) {
								console.log(error);
							} else {
								window.location.href = "http://localhost:8080/Task/admin/index";
							}
						});

					}
				}
			});
		}

		function request(url, method, data, callback) {
			$.ajax({
				url: url,
				cache:false,
				data: data,
				contentType: 'application/x-www-form-urlencoded',
				error: function(error) {
					callback(error);
				},
				success: function(data) {
					callback(null, data);
				},
				type: method
			});
		}

		//Public Method
		return function ( ) {
			showForm ();
			showEdit();
			excecuteDelete ();
		}
	}	
	SINGLETON()();
});



