<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>

<head>
<base href="<%=basePath%>">

<title>TWCN —— 推特中国</title>
<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="css/twitter_identify.css" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="css/twitter_core.bundle.css">
<link rel="stylesheet" href="css/twitter_more_1.bundle.css">
<link rel="stylesheet" href="css/twitter_more_2.bundle.css">

<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/page_setting.js"></script>

</head>

<body>
	<div class="topbar js-topbar">
		<div class="global-nav">
			<div class="global-nav-inner">
				<div class="container">
					<div role="navigation" style="display: inline-block;">
						<ul class="nav" id="global-actions">
							<li id="global-nav-home" class="home"><a href="/"> <span
									class="Icon Icon--home Icon--large"></span> <span class="text">主页</span>
							</a></li>
							<li class="people notifications"><a href="/i/notifications">
									<span class="Icon Icon--notifications Icon--large"></span> <span
									class="text">通知</span> <span class="count"><span
										class="count-inner">0</span></span>
							</a></li>
							<li class="dm-nav"><a href="#"> <span
									class="Icon Icon--dm Icon--large"></span> <span class="text">私信</span>
									<span class="dm-new"><span class="count-inner"></span></span>
							</a></li>
						</ul>
					</div>
					<div class="pull-right" style="display: inline-block;">
						<div role="search">
							<form class="t1-form form-search js-search-form" action="/search"
								id="global-nav-search">
								<input class="search-input" type="text" id="search-query"
									placeholder="搜索 Twitter" name="q"> <span
									class="search-icon js-search-action">
									<button type="submit" class="Icon Icon--search nav-search"
										tabindex="-1"></button>
								</span>
							</form>
						</div>
						<ul class="nav right-actions">
							<li class="me dropdown session" id="user-dropdown"><a
								class="btn js-tooltip settings dropdown-toggle"
								id="user-dropdown-toggle"> <img class="avatar size32"
									src="img/twitter-icon.png" alt="Profile and settings"></a>
								<div class="dropdown-menu">
									<div class="dropdown-caret">
										<span class="caret-outer"></span> <span class="caret-inner"></span>
									</div>
									<ul>
										<li class="current-user" data-name="profile"><a
											href="/Rainsho"
											class="account-summary account-summary-small js-nav">
												<div class="content">
													<div class="account-group js-mini-current-user">
														<b class="fullname">Rainsho</b> <span
															class="screen-name hidden" dir="ltr">@Rainsho</span> <small
															class="metadata">查看个人资料</small>
													</div>
												</div>
										</a></li>
										<li class="dropdown-divider"></li>
										<li><a href="#" class="js-nav">设置</a></li>
										<li class="js-signout-button" id="signout-button"><a
											href="#" class="dropdown-link">登出</a></li>
									</ul>
								</div></li>
							<!--<li role="complementary" class="topbar-tweet-btn">
									<button id="global-new-tweet-button" type="button" class="js-global-new-tweet js-tooltip btn primary-btn tweet-btn js-dynamic-tooltip">
          							<span class="Icon Icon--tweet Icon--large"></span>
          							<span class="text">发推</span>
        						</button>
								</li>-->
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="page-outer">
		<div id="page-container" class="AppContent wrapper wrapper-settings">
			<div class="dashboard">
				<div
					class="DashboardProfileCard DashboardProfileCard--withoutStats module">
					<a class="DashboardProfileCard-bg u-bgUserColor u-block"
						href="/RainshoTwcn" tabindex="-1" aria-hidden="true"></a>
					<div class="DashboardProfileCard-content">
						<a class="DashboardProfileCard-avatarLink u-inlineBlock"
							href="/RainshoTwcn" title="twcn" tabindex="-1" aria-hidden="true">
							<img
							class="DashboardProfileCard-avatarImage js-action-profile-avatar"
							src="img/twitter-icon.png" alt="">
						</a>
						<div class="DashboardProfileCard-userFields">
							<div class="DashboardProfileCard-name u-textTruncate">
								<a class="u-textInheritColor" href="/RainshoTwcn">twcn</a>
							</div>
							<span class="DashboardProfileCard-screenname u-inlineBlock u-dir"
								dir="ltr"> <a
								class="DashboardProfileCard-screennameLink u-linkComplex u-linkClean"
								href="/RainshoTwcn">@<span class="u-linkComplex-target">RainshoTwcn</span></a>
							</span>
						</div>
					</div>
				</div>

				<div id="settings_nav" role="navigation" class="module">
					<ul class="js-nav-links">
						<li class="active"><a class="list-link js-nav"
							href="javascript:void(0);" data-nav="1">账号 <span
								class="Icon Icon--caretRight"></span></a></li>
						<li class=""><a class="list-link js-nav"
							href="javascript:void(0);" data-nav="2">密码<span
								class="Icon Icon--caretRight"></span></a></li>
						<li class=""><a class="list-link js-nav"
							href="javascript:void(0);" data-nav="3">安全与隐私 <span
								class="Icon Icon--caretRight"></span></a></li>
						<li class=""><a class="list-link js-nav"
							href="javascript:void(0);" data-nav="4">你屏蔽的账号 <span
								class="Icon Icon--caretRight"></span></a></li>
					</ul>
				</div>
				<div class="Footer module roaming-module">
					<div class="flex-module">
						<div class="flex-module-inner">
							<ul class="u-cf">
								<li class="Footer-item Footer-copyright copyright">© 2016
									TWCN</li>
								<li class="Footer-item"><a class="Footer-link"
									href="/about">关于</a></li>
								<li class="Footer-item"><a class="Footer-link"
									href="//support.twitter.com">帮助</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<div role="main" aria-labelledby="content-main-heading"
				class="content-main" id="div_username">
				<div class="content-header">
					<div class="header-inner">
						<h2 id="content-main-heading">账号</h2>
						<p class="subheader">修改你的基本账号和语言设置。</p>
					</div>
				</div>
				<div class="content-inner no-stream-end">
					<form id="account-form"
						class="t1-form form-horizontal requires-password"
						autocomplete="off" method="POST"
						action="https://twitter.com/settings/accounts/update">
						<div id="settings-alert-box" class="alert hidden">
							<span id="settings-alert-close" class="icon close"></span>
						</div>
						<div id="username_fieldset" class="control-group">
							<label for="user_screen_name" class="t1-label control-label">用户名</label>
							<div class="controls">
								<p id="username_notification" class="notification"></p>
								<input id="user_screen_name" maxlength="15"
									name="user[screen_name]" type="text" value="RainshoTwcn">
								<p class="notification">
									https://twitter.com/<span id="username_path">RainshoTwcn</span>
								</p>
							</div>
						</div>
						<div id="email_fieldset" class="control-group">
							<label for="user_email" class="t1-label control-label">邮件地址</label>
							<div class="controls">
								<p id="email_notification" class="notification"></p>
								<input id="user_email" class="email-input" name="user[email]"
									type="text" value="rainsho@126.com">
								<p>
									邮件地址不会公开显示。 <a
										href="https://support.twitter.com/articles/15356"
										target="_blank">了解更多</a>。
								</p>
								<p></p>
							</div>
						</div>
						<hr>
						<div class="form-actions">
							<button id="settings_save" class="btn primary-btn" type="submit"
								disabled="disabled">保存更改</button>
							<span class="spinner-small settings-save-spinner"></span>
						</div>
					</form>
				</div>
			</div>

			<div role="main" aria-labelledby="content-main-heading"
				class="content-main" id="div_password">
				<div class="content-header">
					<div class="header-inner">
						<h2 id="content-main-heading">密码</h2>
						<p class="subheader">更改你的密码或找回当前密码。</p>
					</div>
				</div>
				<div class="content-inner no-stream-end">
					<form class="t1-form form-horizontal" id="password-form"
						method="POST"
						action="https://twitter.com/settings/passwords/update">
						<div id="settings-alert-box" class="alert hidden">
							<span id="settings-alert-close" class="icon close"></span>
						</div>
						<div class="control-group">
							<label for="current_password" class="t1-label control-label">当前密码</label>
							<div class="controls">
								<input id="current_password" type="password"
									name="current_password">
								<p>
									<a href="/account/access_password_reset" id="forgot_password"
										class="js-static-forgot-password">忘记密码了?</a>
								</p>
							</div>
						</div>
						<div id="password-match-pair">
							<div class="control-group">
								<label for="user_password" class="t1-label control-label">新密码</label>
								<div id="password_strength" class="controls">
									<input id="user_password" type="password" name="user_password">
									<small id="password_strength_feedback"
										class="help-inline help-error" style="display: none;"></small>
								</div>
							</div>
							<div class="control-group">
								<label for="user_password_confirmation"
									class="t1-label control-label">确认密码</label>
								<div class="controls">
									<input id="user_password_confirmation" type="password"
										name="user_password_confirmation"> <small
										id="password_match" class="help-inline help-error"
										style="display: none;">密码不匹配</small>
								</div>
							</div>
						</div>
						<hr>
						<div class="form-actions">
							<button id="settings_save" class="btn primary-btn" type="submit"
								disabled="disabled">保存更改</button>
							<span class="spinner-small settings-save-spinner"></span>
						</div>

					</form>
				</div>
			</div>

		</div>
	</div>

</body>

</html>


