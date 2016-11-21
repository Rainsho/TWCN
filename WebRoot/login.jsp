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
<script type="text/javascript" src="js/page_login.js"></script>

</head>

<body
	class="three-col logged-out ms-windows asian zh-cn front-page-photo-set front-page">

	<div id="doc">
		<div class="topbar js-topbar">
			<div class="global-nav global-nav--newLoggedOut">
				<div class="global-nav-inner">
					<div class="container">
						<ul class="nav js-global-actions" role="navigation"
							id="global-actions">
							<li id="global-nav-home" class="home"><a
								class="js-nav js-tooltip js-dynamic-tooltip" href="/"> <span
									class="Icon Icon--bird Icon--large"></span> <span class="text">主页</span>
							</a></li>
							<li id="global-nav-about" class="about"><a
								class="js-tooltip js-dynamic-tooltip" href="javascript:void(0);"
								target="_blank"> <span class="text">关于</span>
							</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="page-outer">
			<div id="page-container" class="AppContent  wrapper-front">
				<div class="front-container front-container-full-signup"
					id="front-container">
					<div class="front-bg">
						<img class="front-image" src="img/bg1.jpg" alt=""
							style="display: block;" id="bg_img">
					</div>
					<div class="front-card">
						<div class="front-welcome">
							<div class="front-welcome-text">
								<h1>欢迎使用推特中国。</h1>
								<p>联系你的好友和更多精彩。获取你感兴趣的实时更新。并通过每个视角观看事件实时呈现。</p>
							</div>
						</div>
						<div class="front-signin js-front-signin">
							<form action="https://twitter.com/sessions"
								class="t1-form signin" method="post">
								<div class="username field">
									<input type="text" id="signin-email"
										class="text-input email-input" name="login_id"
										placeholder="邮件地址或用户名">
								</div>
								<table class="flex-table password-signin">
									<tbody>
										<tr>
											<td class="flex-table-primary">
												<div class="password flex-table-form">
													<input type="password" id="signin-password"
														class="text-input flex-table-input" name="login_psw"
														placeholder="密码">
												</div>
											</td>
											<td class="flex-table-secondary">
												<button type="submit"
													class="submit btn primary-btn flex-table-btn js-submit">登录</button>
											</td>
										</tr>
									</tbody>
								</table>
								<div class="remember-forgot">
									<label class="t1-label remember"><input type="checkbox"
										value="1" name="remember_me"><span>记住我</span></label>
									<!--<span class="separator">·</span>
											<a class="forgot" href="https://twitter.com/account/begin_password_reset">忘记密码?</a>-->
								</div>
							</form>
						</div>
						<div class="front-signup js-front-signup">
							<h2>
								<strong>新来 TWCN?</strong> 注册
							</h2>
							<form action="https://twitter.com/signup" class="t1-form signup"
								id="frontpage-signup-form" method="post">
								<div class="field">
									<input type="text" class="text-input" name="nickname"
										maxlength="20" placeholder="全名">
								</div>
								<div class="field">
									<input type="text" class="text-input email-input" name="email"
										placeholder="邮件地址">
								</div>
								<div class="field">
									<input type="password" class="text-input" name="password"
										placeholder="密码">
								</div>
								<button type="submit" class="btn signup-btn u-floatRight">注册
									TWCN</button>
							</form>
						</div>
					</div>
					<div class="footer inline-list">
						<ul>
							<li><a href="javascript:void(0);">关于</a><span
								class="dot divider"> ·</span></li>
							<li><a href="javascript:void(0);">帮助</a><span
								class="dot divider"> ·</span></li>
							<li><a href="javascript:void(0);">博客</a><span
								class="dot divider"> ·</span></li>
							<li><span class="copyright">© 2016 TWCN</span></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>


