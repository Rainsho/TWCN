var bg = ["img/bg2.jpg", "img/bg3.jpg", "img/bg1.jpg"];

function cg_bg_img() {
	var t = bg.shift();
	$('#bg_img').fadeOut(2000, function() {
		$('#bg_img').attr('src', t);
		$('#bg_img').fadeIn(2000, function() {
			bg.push(t);
			cg_bg_img();
		});
	});
}

$(function() {
	cg_bg_img();
	$('input[name="remember_me"]').change(function(){
		if($('input[name="remember_me"]').prop('checked')){
			$('input[name="remember_me"]').val(1);
		}else{
			$('input[name="remember_me"]').val(0);
		}
	});
});