'use strict';
jQuery(document).ready(function($) {
	var SINGLETON = function() {
		// Private Properties
		var check1 =true;

		// Public Properties

		// Private Method
		
		function addAuthor () {
			$('.add-author-information').click(function(event) {
				var authorLabel = "Author " + ($('.author1').siblings().length + 2);
				var author = "author" + ($('.author1').siblings().length + 2);
				var firstname = "firstname" + ($('.author1').siblings().length + 2);
				var lastname = "lastname" + ($('.author1').siblings().length + 2);
				var email = "email" + ($('.author1').siblings().length + 2);
				var country = "country" + ($('.author1').siblings().length + 2);
				var organization = "organization" + ($('.author1').siblings().length + 2);
				var webpage = "webpage" + ($('.author1').siblings().length + 2);
				var corresponding = "corresponding" + ($('.author1').siblings().length + 2);

				var newAuthorForm = `
					<div class="author">
						<strong>${authorLabel}</strong>
						<div class="author-container">
							First name(+):
							<input type="text" name="${firstname}">
						</div>
						<div class="author-container">
							Last name (*):
							<input type="text" name="${lastname}" required="required">
						</div>
						<div class="author-container">
							Email (*):
							<input type="text" name="${email}" required="required">
						</div>
						<div class="author-container">
							Country (*):
							<input type="text" name="${country}" required="required">
						</div>
						<div class="author-container">
							Organization (*):
							<input type="text" name="${organization}" required="required">
						</div>
						<div class="author-container">
							Web page:
							<input type="text" name="${webpage}">
						</div>
						<div class="author-container">
							<input type="checkbox" name="${corresponding}" value="on">
							<span>corresponding author</span>
						</div>
					</div>
				`;
				$('.author-information').append(newAuthorForm);
			});
		}

		function showStep () {
			$('.btn-select-track').click(function(event) {
				$('.select-track').hide('slow/400/fast', function() {
					
				});
				var track = $('.select-track input[type="radio"]:checked').siblings().text();
				$('.track-selected').text(track);
				$('.mark-information').show('slow/400/fast', function() {
					
				});
			});
		} 

		//Public Method
		return function ( ) {
			addAuthor();
			showStep ()
		}
	}	
	SINGLETON()();
});



