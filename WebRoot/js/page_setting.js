var div = {
	1 : 'div_username',
	2 : 'div_password'
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

	// 修改账号相关事件
	$('#user_screen_name,#user_email').focus(function() {
		$('#settings_save_username').prop("disabled", true);
	});
	$('#user_screen_name').keyup(function() {
		$('#username_notification').text('正在检查...');
		$('#username_path').text($('#user_screen_name').val());
	});
	$('#user_screen_name').blur(function() {
		$.getJSON("json/jsonisUsernameExist.action", {
			"tocheck" : $('#user_screen_name').val()
		}, function(json) {
			var t = json.checkresult;
			var origininfo = json.origininfo;
			var str = '邮件地址已被使用。一个邮件地址只能用于申请一个账号。';
			if (t && $('#user_screen_name').val() != origininfo) {
				$('#username_notification').text('用户名已被占用！');
			} else {
				$('#username_notification').text('用户名可以使用。');
				if ($('#email_notification').text() != str) {
					$('#settings_save_username').prop("disabled", false);
				}
			}
		});
	});
	$('#user_email').keyup(function() {
		var re = /(\w+)(@)([\w-_\.]+)(\.\w{2,})/;
		if (re.test($('#user_email').val())) {
			$('#email_notification').text('正在检查...');
		} else {
			$('#email_notification').text('邮件地址无效！');
		}
	});
	$('#user_email').blur(function() {
		$.getJSON("json/jsonisEmailExist.action", {
			"tocheck" : $('#user_email').val()
		}, function(json) {
			var t = json.checkresult;
			var origininfo = json.origininfo;
			var re = /(\w+)(@)([\w-_\.]+)(\.\w{2,})/;
			if (t && $('#user_email').val() != origininfo) {
				$('#email_notification').text('邮件地址已被使用。一个邮件地址只能用于申请一个账号。');
			} else if (re.test($('#user_email').val())) {
				$('#email_notification').text('邮箱可以使用。');
				if ($('#username_notification').text() != '用户名已被占用！') {
					$('#settings_save_username').prop("disabled", false);
				}
			} else {
				$('#email_notification').text('邮件地址无效！');
			}
		});
	});

	// 修改密码相关事件
	$('#current_password').blur(function() {
		if ($('#current_password').val() == '') {
			return;
		}
		$.getJSON("json/jsoncheckPswd.action", {
			"tocheck" : $('#current_password').val()
		}, function(json) {
			var t = json.checkresult;
			if (!t) {
				$('#current_password').attr('data-pswdck', 0);
				$('#password_strength_feedback').show();
			} else {
				$('#current_password').attr('data-pswdck', 1);
				$('#password_strength_feedback').hide();
			}
			ckpswd();
		});
	});
	$('#user_password,#user_password_confirmation').keyup(function() {
		var pswd1 = $('#user_password').val();
		var pswd2 = $('#user_password_confirmation').val();
		if (pswd1 != '' && pswd2 != '' && pswd1 != pswd2) {
			$('#password_match').show();
			$('#settings_save_password').prop("disabled", true);
		} else {
			$('#password_match').hide();
			if ($('#current_password').attr('data-pswdck') == 1) {
				$('#settings_save_password').prop("disabled", false);
			}
		}
	});
	$('#user_password,#user_password_confirmation').blur(function() {
		ckpswd();
	});

});

function updateusername() {
	var obj = {
		"user.username" : $('#user_screen_name').val(),
		"user.email" : $('#user_email').val(),
		"type" : 1
	};
	$.getJSON("json/jsonupdateUser.action", obj, function(json) {
		if (json.updateresult) {
			alert('更新成功！');
			window.location.href = "userupdate.action";
		}
	});
	return false;
}

function updatepassword() {
	var obj = {
		"user.password" : $('#user_password').val(),
		"type" : 2
	};
	$.getJSON("json/jsonupdateUser.action", obj, function(json) {
		if (json.updateresult) {
			alert('更新成功，请使用新密码登录！');
			window.location.href = "userlogout.action";
		}
	});
	return false;
}

function ckpswd() {
	var pswd1 = $('#user_password').val();
	var pswd2 = $('#user_password_confirmation').val();
	var pswdck = $('#current_password').attr('data-pswdck');
	if (pswd1 != '' && pswd2 != '' && pswd1 == pswd2 && pswdck == 1) {
		$('#settings_save_password').prop("disabled", false);
	} else {
		$('#settings_save_password').prop("disabled", true);
	}
}