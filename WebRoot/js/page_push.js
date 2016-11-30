function new_push() {

	$
			.post(
					'json/pushcheck.action',
					function(data) {
						if (data.newT > 0) {
							$('#new_tweet_bar').show();
							$('#new_tweet_bar div').text(
									'查看 ' + data.newT + ' 条新推文');
							$('#global-actions li').first().addClass('new');
							$('title').text('(' + data.newT + ')' + ' TWCN');
						}
						if (data.newR > 0) {
							$('.people.notifications .count').show();
							$('.people.notifications .count-inner').css(
									'background', '#0084B4');
							$('.people.notifications .count-inner').text(
									data.newR + data.newL);
							// notification page event
							$('.easyui-tabs li').eq(0).find('.tabs-title')
									.text('收到的评论(' + data.newR + '新)');
							if ($('.tabs-panels .panel').eq(0).find('.my_lrr').length == 0) {
								$('.tabs-panels .panel')
										.eq(0)
										.find('.replay_area_d0')
										.eq(data.newR - 1)
										.after(
												'<div class="my_lrr">上次看到这里</div>');
							}
						}
						if (data.newL > 0) {
							$('.people.notifications .count').show();
							$('.people.notifications .count-inner').css(
									'background', '#0084B4');
							$('.people.notifications .count-inner').text(
									data.newR + data.newL);
							// notification page event
							$('.easyui-tabs li').eq(2).find('.tabs-title')
									.text('收到的点赞(' + data.newL + '新)');
							if ($('.tabs-panels .panel').eq(2).find('.my_lrr').length == 0) {
								$('.tabs-panels .panel')
										.eq(2)
										.find('.replay_area_d0')
										.eq(data.newL - 1)
										.after(
												'<div class="my_lrr">上次看到这里</div>');
							}
						}
						if (data.newD > 0) {
							$('.dm-nav').addClass(
									'global-dm-nav new with-count');
							$('.dm-nav .count-inner').css('background',
									'#0084B4');
							$('.dm-nav .count-inner').text(data.newD);
							// dm page envent
							$.each(data.newD_uid, function(i, x) {
								$('.show-dm-area[data-suid=' + x[0] + ']')
										.parent().find('.my_dm_count').text(
												x[1]);
								$('.show-dm-area[data-suid=' + x[0] + ']')
										.parent().find('.my_dm_count').show();
							});
						}
					});

}

$(function() {
	$('.easyui-tabs li').eq(0).click(function() {
		if ($(this).find('.tabs-title').text().match('新')) {
			$.post('json/pushuprrp.action');
			$(this).find('.tabs-title').text('收到的评论');
			// update nav_bar
			$('.people.notifications .count').hide();
			new_push();
		}
	});
	$('.easyui-tabs li').eq(2).click(function() {
		if ($(this).find('.tabs-title').text().match('新')) {
			$.post('json/pushuplrl.action');
			$(this).find('.tabs-title').text('收到的点赞');
			// update nav_bar
			$('.people.notifications .count').hide();
			new_push();
		}
	});
	$('.easyui-tabs li:gt(0)').click(function() {
		$('.easyui-tabs li').eq(0).click();
	});
	$('.show-dm-area').click(
			function() {
				var suid = $(this).attr('data-suid');
				var obj = {
					suid : suid
				};
				$.post('json/pushupdm.action', obj);
				$('.show-dm-area[data-suid=' + suid + ']').parent().find(
						'.my_dm_count').hide();
				// update nav_bar
				var dm = $('.dm-nav .count-inner').text();
				dm -= $('.show-dm-area[data-suid=' + suid + ']').parent().find(
						'.my_dm_count').text();
				if (dm <= 0) {
					$('.dm-nav').removeClass('global-dm-nav new with-count');
					$('.dm-nav .count-inner').text('');
				} else {
					$('.dm-nav .count-inner').text(dm);
				}
			});

	// new push detected
	new_push();
	setInterval('new_push()', 60000);
});