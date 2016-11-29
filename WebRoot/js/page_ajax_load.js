var index_page = 2;
var user_page = 2;

$(function() {

	$(window).scroll(
			function() {
				if ($(window).scrollTop() == $(document).height()
						- $(window).height()) {
					$('.stream-loading').show();
					$('.stream-end-inner').first().hide();
					var url = window.location.pathname;
					if (url.match(/\/TWCN\/u\/\w+/)) {
						var u = url.replace(/\/TWCN\/u\//, '');
						var obj = {
							u : u,
							page : user_page
						};
						$.post('userpage.action', obj, function(data) {
							setTimeout(function() {
								var _html = $.parseHTML(data);
								var list = $(_html).find('#stream-items-id')
										.children();
								$('#stream-items-id').append(list);
								$('.stream-loading').hide();
								$('.stream-end-inner').first().show();
								$('*').unbind();
								onload_events();
								if (list.length < 20) {
									$(window).unbind('scroll');
									$('.back-to-top').show();
								}
								$('.index_cc').css('height',
										$('#page-outer').css('height'));
							}, 2000);
						});
						user_page++;
					} else {
						var obj = {
							page : index_page
						};
						$.post('userindex.action', obj, function(data) {
							setTimeout(function() {
								var _html = $.parseHTML(data);
								var list = $(_html).find('#stream-items-id')
										.children();
								$('#stream-items-id').append(list);
								$('.stream-loading').hide();
								$('.stream-end-inner').first().show();
								$('*').unbind();
								onload_events();
								if (list.length < 20) {
									$(window).unbind('scroll');
									$('.back-to-top').show();
								}
								$('.index_cc').css('height',
										$('#page-outer').css('height'));
							}, 2000);
						});
						index_page++;
					}
				}
			});

});