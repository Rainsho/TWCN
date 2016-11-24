var i = 0;
var h = null;

function heart_burst() {
	h.css('background-position-x', (i++ / 0.28) + '%');
	if (i < 20) {
		setTimeout('heart_burst()', 1000 / 20);
	}
}

$(function() {

	$('#tweet-box-home-timeline').click(function() {
		$('.t1-form').removeClass('condensed');
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

	$('#new_tweet_bar div').click(function() {
		window.location.href = "i";
	});

	// 发推相关事件
	$('#tweet-box-home-timeline div').bind("DOMSubtreeModified", function() {
		ck_t_length();
	});
	$('#tweet-box-home-timeline div').click(function() {
		if ($(this).text() == '有什么新鲜事?') {
			$(this).html('<br>');
		}
	});
	// 文件上传
	$('.t1-label input').change(function() {
		if ($('.t1-label input').val() == '') {
			return;
		}
		if (ck_t_file()) {
			var files = $('.t1-label input').prop('files');
			var data = new FormData();
			for ( var i = 0; i < files.length; i++) {
				data.append('file', files[i]);
			}
			// upload
			$('.tweet-counter + button').prop('disabled', true);
			$.ajax({
				url : 'json/tuploadmedia.action',
				type : 'POST',
				data : data,
				cache : false,
				processData : false,
				contentType : false,
				success : function() {
					// 上传完成事件
					$('#select_files').text('上传完成');
					$('#upload_bar div').css('width', '100%');
					ck_t_length();
				}
			});
		}
		;
	});
	$('.tweet-counter + button').click(function() {
		var txt = $('#tweet-box-home-timeline div').text();
		$.post('json/ttweet.action', {
			'tweet.tcontent' : txt
		}, function(data) {
			// 发推完成事件
			$('#upload_div').hide();
			$('#upload_bar div').css('width', '33%');
			$('#tweet-box-home-timeline div').html('<br>');
			$('.t1-form').addClass('condensed');
			$('#new_tweet_bar').show();
			$('#new_tweet_bar div').text('发送成功，请刷新查看新推文');
		});
	});

	// 删除相关事件
	$('.js-actionDelete button').click(function() {
		var that = $(this);
		if (confirm('确认删除？')) {
			var tid = that.attr('data-tid');
			$.post('json/tdelete.action', {
				'tweet.tid' : tid
			}, function() {
				that.parents('.stream-item').remove();
			});
		}
	});

	// 取消关注相关事件
	$('.js-unfollowed button').click(
			function() {
				var that = $(this);
				if (confirm('确认取关？')) {
					var uid = that.attr('data-uid');
					$.post('rsunfollow.action', {
						'uid' : uid
					}, function(data) {
						$('.js-unfollowed button[data-uid=' + uid + ']')
								.parents('.stream-item').hide(1000);
					});
				}
			});

	// 关注相关事件
	$('.follow-button').click(function() {
		var that = $(this);
		var uid = that.attr('data-uid');
		$.post('rsfollow.action', {
			'uid' : uid
		}, function(data) {
			that.children().children().after('已');
			that.prop('disabled', true);
		});
	});

	// like 相关事件
	$('.js-like-btn[data-like="1"] .HeartAnimation').css(
			'background-position-x', '100%');
	$('.js-like-btn[data-like="1"] span').css('color', '#e81c4f');
	$('.js-like-btn[data-like="1"] .HeartAnimation').unbind();
	$('.js-like-btn').click(
			function() {
				var that = $(this);
				if (that.attr('data-like') == 1) {
					// 取消喜欢
					var tid = that.attr('data-tid');
					$.post('tmunlike.action', {
						'tid' : tid
					}, function() {
						that.find('.HeartAnimation').css(
								'background-position-x', '0%');
						// 重新绑定点击动画
						that.find('.HeartAnimation').click(function() {
							i = 0;
							h = $(this);
							heart_burst();
						});
						// 更新数量
						var c = that.find('span').text();
						that.find('span').text(--c);
						that.find('span').css('color', '#aab8c2');
						that.removeAttr('data-like');
					});
				} else {
					// 喜欢
					var tid = that.attr('data-tid');
					$.post('tmlike.action', {
						'tid' : tid
					}, function() {
						that.find('.HeartAnimation').css(
								'background-position-x', '100%');
						// 解除动画绑定
						that.find('.HeartAnimation').unbind();
						// 更新数量
						var c = that.find('span').text();
						that.find('span').text(++c);
						that.find('span').css('color', '#e81c4f');
						that.attr('data-like', '1');
					});
				}
			});

	// 回复相关事件
	$('.js-replay-btn').click(function() {
		$(this).parents('.stream-item').find('.replay_area').toggle(500);
	});
	$('.replay-btn').click(
			function() {
				var that = $(this);
				var rcontent = that.prev().val();
				var tid = that.attr('data-tid');
				var suid = that.attr('data-suid');
				if (rcontent.length == 0) {
					alert('没有评论内容');
					return;
				}
				var obj = {
					tid : tid,
					suid : suid,
					rcontent : rcontent
				};
				$.post('tmaddreplay.action', obj,
						function(data) {
							var c = that.parents('.tweet').find(
									'.js-replay-btn .IconTextContainer span')
									.text();
							that.parents('.tweet').find(
									'.js-replay-btn .IconTextContainer span')
									.text(++c);
							that.prev().val('');
							that.removeAttr('data-suid');
							that.text('评论');
							// 异步加载其他信息
							var div = $.parseHTML(data);
							that.parents('.replay_area_d0').before(
									$(div).children().parent());
							// 新元素绑定事件！！！
						});
			});

	// 删除回复
	$('.replay-del-btn').click(
			function() {
				var that = $(this);
				var rid = that.attr('data-rid');
				$.post('tmdelreplay.action', {
					'rid' : rid
				},
						function() {
							var c = that.parents('.tweet').find(
									'.js-replay-btn .IconTextContainer span')
									.text();
							that.parents('.tweet').find(
									'.js-replay-btn .IconTextContainer span')
									.text(--c);
							that.parents('.replay_area_d0').hide(1000);
						});
			});

	// 回复回复
	$('.replay-replay-btn').click(
			function() {
				var that = $(this);
				var suid = that.attr('data-suid');
				var username = that.attr('data-username');
				that.parents('.replay_area').children('.replay_area_d0').find(
						'input').val('回复@' + username + '：');
				that.parents('.replay_area').children('.replay_area_d0').find(
						'.replay-btn').attr('data-suid', suid);
				that.parents('.replay_area').children('.replay_area_d0').find(
						'.replay-btn').text('回复');
			});
	$('.replay-btn').prev('input').change(function() {
		if ($(this).val() == '') {
			$(this).next().text('评论');
			$(this).next().removeAttr('data-suid');
		}
	});

});

function ck_t_file() {
	var files = $('.t1-label input').prop('files');
	if (files.lengh == 0) {
		return false;
	}
	var c_mp4 = 0;
	var t_size = 0;
	var f_name = '';
	$.each(files, function(i, f) {
		if (f.type == 'video/mp4') {
			c_mp4++;
		}
		t_size += f.size;
		f_name += f.name + '  ';
	});
	if (c_mp4 > 1) {
		alert('只能上传一个视频文件！');
		$('.t1-label input').val('');
		return false;
	}
	if (t_size > 30000000) {
		alert('所选超过30M限制');
		$('.t1-label input').val('');
		return false;
	}
	if (files.length > 4 + c_mp4) {
		alert('仅显示前4张图片');
	}
	$('#upload_div').show();
	$('#select_files').text('已选文件：' + f_name.substr(0, 30) + ' ...');
	return true;
}

function ck_t_length() {
	var char_left = 140 - $('#tweet-box-home-timeline div').text().length;
	$('.tweet-counter').text(char_left);
	if (char_left >= 0 && char_left < 140) {
		$('.tweet-counter + button').prop('disabled', false);
	} else {
		$('.tweet-counter + button').prop('disabled', true);
	}
}
