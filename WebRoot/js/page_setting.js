var div = {
	1: 'div_username',
	2: 'div_password'
};

$(function() {

	$('.content-main').hide();
	$('#div_username').show();

	$('#user-dropdown').click(function() {
		$(this).toggleClass('open');
	});

	$('#settings_nav ul li a').click(function() {
		$('#settings_nav ul li').removeClass('active');
		$(this).parent().addClass('active');
		$('.content-main').hide();
		var div_id = '#' + div[$(this).attr('data-nav')];
		$(div_id).show();
	});

});