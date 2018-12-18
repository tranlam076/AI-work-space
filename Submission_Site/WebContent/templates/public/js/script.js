'use strict';
jQuery(document).ready(function($) {
	var SINGLETON = function() {
		// Private Properties

		// Public Properties

		// Private Method

		
		//Public Method
		return function ( ) {
			$('.main-menu-3').hover(function() {
				$('.main-menu-3-sub').show()
			}, function() {
				$('.main-menu-3-sub').hide();
			});

			$('.main-menu-5').hover(function() {
				$('.main-menu-5-sub').show()
			}, function() {
				$('.main-menu-5-sub').hide();
			});

		}
	}	
	SINGLETON()();
});



