var i = 0;
var h = null;

function heart_burst() {
	h.css('background-position-x', (i++/0.28)+'%');
	if(i < 20) {
		setTimeout('heart_burst()', 1000 / 20);
	} else {
		h.removeProp('style');
	}
}

$(function() {

	$('#tweet-box-home-timeline').click(function() {
		$('.t1-form').toggleClass('condensed');
	});

	$('.ProfileTweet-action').click(function() {
		$(this).children('.dropdown').toggleClass('open');
	});

	$('#user-dropdown').click(function() {
		$(this).toggleClass('open');
	});

	$('.back-to-top').click(function() {
		$(window).scrollTop(0);
	});

	$('.HeartAnimation').click(function() {
		i = 0;
		h = $(this);
		heart_burst();
	});

});

