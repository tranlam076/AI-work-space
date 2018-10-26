'use strict';
jQuery(document).ready(function($) {
	var SINGLETON = function() {
		// Private Properties
		var check1 =true;
		var check2 =true;
		var url = "http://localhost:8080/Task13";

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
								window.location.href = "http://localhost:8080/Task13/admin/index";
							}
						});
					}

					if (parent.siblings().length >2) {
						id_news = $(parent.siblings()[0]).text();
						request(url +  '/admin/delete-news', 'POST', {id_news: id_news}, function(error, data) {
							if (error) {
								console.log(error);
							} else {
								window.location.href = "http://localhost:8080/Task13/admin/index";
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

		// ------------------------- submission manangerment -----------------

		function sorting() {
			showDetail("Sort by time");
			$("#sort-by").change(function(event) {
				showDetail($("#sort-by").val());
			});
		}

		function showDetail (kind) {	
			console.log(kind);
			if (typeof listSubmissions !== 'undefined' && listSubmissions !== null) {
				switch (kind) {
					case "Sort by time":
					listSubmissions.sort((a,b) => {
						return (a.time < b.time) ? 1: (a.time > b.time) ? -1 : 0;
					});
					break;
					case "Sort by title":
					listSubmissions.sort((a,b) => {
						return (a.title > b.title) ? 1: (a.title < b.title) ? -1 : 0;
					});
					break;
					case "Sort by field":
					listSubmissions.sort((a,b) => {
						return (a.fieldName > b.fieldName) ? 1: (a.fieldName < b.fieldName) ? -1 : 0;
					});
					break;
				}

				if (listSubmissions.length == 0) return;
				$(".detail-submission").html("");
				for (var submission of listSubmissions) {
					$(".detail-submission").append(`
						<ul class="row-submission">
						<li class="submission-title">
						<a href="${submission.detail}">${submission.title}</a>
						</li>
						<li class="submission-field">
						<p>${submission.fieldName}<span>${submission.time.substring(0, submission.time.length - 2)}</span></p>
						</li>
						</ul>
					`);
					// $(".detail-submission").append(`
					// 	<tr>
					// 	<td width="20%">
					// 	<input type="checkbox" value="${submission.idSubmission}">
					// 	</td>
					// 	<td width="20%">${submission.title}</td>
					// 	<td width="20%">${submission.fieldName}</td>
					// 	<td width="20%">${submission.time}</td>
					// 	<td width="20%"><a href="${submission.detail}">detail</a></td>
					// 	</tr>
					// `);
				}
			}	
		}
		function showPagination () {
			var totalPage = parseInt($("#totalPage").val());
			var currentPage = parseInt($("#currentPage").val());
			var link = $("#link").val();
			
			if (totalPage < 5) {
				if (currentPage > 1) {
					$('.pagination').html(`<a href="${link}1">&laquo;</a>`);
				}
				for (var i = 1; i<= totalPage; i++) {
					if (i == currentPage) {
						$('.pagination').append(`<a href="${link}${i}"class="active">${i}</a>`);
					} else {
						$('.pagination').append(`<a href="${link}${i}">${i}</a>`);
					}
				}
				if (totalPage > currentPage) {
					$('.pagination').append(`<a href="${link}${currentPage+1}">&raquo;</a>`);
				}
			} else {
				if (currentPage > 1) {
					$('.pagination').html(`<a href="${link}${currentPage-1}">&laquo;</a>`);
				}

				if (currentPage > 5 ) {
					$('.pagination').append(`<a href="${link}${currentPage-5}">...</a>`);
				}

				var indexP = parseInt(currentPage/6);
				for (var i = indexP*5 + 1; i < indexP + 6; i++) {
					if (i == currentPage) {
						$('.pagination').append(`<a href="${link}${i}"class="active">${i}</a>`);
					} else if (i <= totalPage) {
						$('.pagination').append(`<a href="${link}${i}">${i}</a>`);
					}
				}
				
				if (totalPage > indexP*5 + 1) {
					$('.pagination').append(`<a href="${link}${indexP*5 + 6}">...</a>`);
				}
				if (currentPage < totalPage)  {
					$('.pagination').append(`<a href="${link}${currentPage + 1}">&raquo;</a>`);
				}
			}
		}
		//Public Method
		return function ( ) {
			showForm ();
			showEdit();
			excecuteDelete ();
			sorting();
			showPagination ();
		}
	}	
	SINGLETON()();
});

