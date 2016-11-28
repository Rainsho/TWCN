<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>TWCN —— ${user.nickname }</title>
<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="css/twitter_identify.css" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="css/twitter_core.bundle.css">
<link rel="stylesheet" href="css/twitter_more_1.bundle.css">
<link rel="stylesheet" href="css/twitter_more_2.bundle.css">
<link rel="stylesheet" href="css/my.css">

<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/page_user.js"></script>

</head>

<body>
	<div class="topbar js-topbar">
		<div class="global-nav">
			<div class="global-nav-inner">
				<div class="container">
					<div role="navigation" style="display: inline-block;">
						<ul class="nav" id="global-actions">
							<li id="global-nav-home" class="home"><a href="i"> <span
									class="Icon Icon--home Icon--large"></span> <span class="text">主页</span>
							</a></li>
							<li class="people notifications"><a href="i/notifications">
									<span class="Icon Icon--notifications Icon--large"></span> <span
									class="text">通知</span> <span class="count"><span
										class="count-inner">0</span></span>
							</a></li>
							<li class="dm-nav"><a href="i/directmsg"> <span
									class="Icon Icon--dm Icon--large"></span> <span class="text">私信</span>
									<span class="dm-new"><span class="count-inner"></span></span>
							</a></li>
						</ul>
					</div>
					<div class="pull-right" style="display: inline-block;">
						<div role="search">
							<form class="t1-form form-search js-search-form"
								action="usersearch.action" method="get" id="global-nav-search">
								<input class="search-input" type="text" id="search-query"
									placeholder="搜索 Twitter" name="keyword"> <span
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
									src="${LOGIN_USER.avatar }" alt="Profile and settings"></a>
								<div class="dropdown-menu">
									<div class="dropdown-caret">
										<span class="caret-outer"></span> <span class="caret-inner"></span>
									</div>
									<ul>
										<li class="current-user" data-name="profile"><a
											href="u/${LOGIN_USER.username }"
											class="account-summary account-summary-small js-nav">
												<div class="content">
													<div class="account-group js-mini-current-user">
														<b class="fullname">${LOGIN_USER.nickname }</b> <span
															class="screen-name hidden" dir="ltr">@${LOGIN_USER.username
															}</span> <small class="metadata">查看个人资料</small>
													</div>
												</div>
										</a></li>
										<li class="dropdown-divider"></li>
										<li><a href="userupdate.action" class="js-nav">设置</a></li>
										<li class="js-signout-button" id="signout-button"><a
											href="userlogout.action" class="dropdown-link">登出</a></li>
									</ul>
								</div></li>
							<li role="complementary" class="topbar-tweet-btn">
								<button id="global-new-tweet-button" type="button"
									class="js-global-new-tweet js-tooltip btn primary-btn tweet-btn js-dynamic-tooltip">
									<span class="Icon Icon--tweet Icon--large"></span> <span
										class="text">发推</span>
								</button>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="page-outer">
		<div id="page-container" class="AppContent wrapper wrapper-settings">
			<div class="dashboard">
				<div class="DashboardProfileCard  module">
					<a class="DashboardProfileCard-bg u-bgUserColor u-block"
						href="u/${user.username }" tabindex="-1" aria-hidden="true"></a>
					<div class="DashboardProfileCard-content">
						<a class="DashboardProfileCard-avatarLink u-inlineBlock"
							href="u/${user.username }" title="${user.username }"
							tabindex="-1" aria-hidden="true"> <img
							class="DashboardProfileCard-avatarImage" src="${user.avatar }"
							alt="">
						</a>
						<div class="DashboardProfileCard-userFields">
							<div class="DashboardProfileCard-name u-textTruncate">
								<a class="u-textInheritColor" href="u/${user.username }">${user.nickname
									}</a>
							</div>
							<span class="DashboardProfileCard-screenname u-inlineBlock u-dir"
								dir="ltr"> <a
								class="DashboardProfileCard-screennameLink u-linkComplex u-linkClean"
								href="u/${user.username }">@<span
									class="u-linkComplex-target">${user.username }</span></a>
							</span>
						</div>
						<c:if test="${! rs_set_suid.contains(user.uid) }">
							<div class="not-following"
								style="position: absolute;left: 90px;top: 60px;">
								<button type="button"
									class="small-follow-btn follow-btn btn small follow-button"
									data-uid="${user.uid }">
									<div class="follow-text action-text">
										<span class="Icon Icon--follow"></span>关注
									</div>
								</button>
							</div>
						</c:if>
						<div class="ProfileCardStats">
							<ul
								class="ProfileCardStats-statList Arrange Arrange--bottom Arrange--equal">
								<li class="ProfileCardStats-stat Arrange-sizeFit"><a
									class="ProfileCardStats-statLink u-textUserColor u-linkClean u-block"
									href="u/${user.username }"> <span
										class="ProfileCardStats-statLabel u-block">推文</span> <span
										class="ProfileCardStats-statValue">${user.tweetses.size()
											}</span>
								</a></li>
								<li class="ProfileCardStats-stat Arrange-sizeFit"><a
									class="ProfileCardStats-statLink u-textUserColor u-linkClean u-block"
									href="u/${user.username }/following"> <span
										class="ProfileCardStats-statLabel u-block">正在关注</span> <span
										class="ProfileCardStats-statValue">${user.relationshipsesForHuid.size()
											}</span>
								</a></li>
							</ul>
						</div>
						<div id="dashboard-profile-prompt"></div>
					</div>
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
			<!-- user -->
			<div role="main" aria-labelledby="content-main-heading"
				class="content-main" id="div_username">
				<div class="content-header">
					<div class="header-inner">
						<h2 id="content-main-heading">“${keyword }”</h2>
						<p class="subheader">搜索到的用户</p>
					</div>
				</div>
				<div class="content-inner no-stream-end">
					<div class="GridTimeline-items" role="list">
						<div class="Grid Grid--withGutter">
							<c:forEach var="su" items="${search_list }">
								<div class="Grid-cell u-size1of2 u-lg-size1of3 u-mb10">
									<div class="DashboardProfileCard  module">
										<a class="DashboardProfileCard-bg u-bgUserColor u-block"
											href="u/${su.username }" tabindex="-1" aria-hidden="true"></a>
										<div class="DashboardProfileCard-content">
											<a class="DashboardProfileCard-avatarLink u-inlineBlock"
												href="u/${su.username }" title="${su.username }"
												tabindex="-1" aria-hidden="true"> <img
												class="DashboardProfileCard-avatarImage" src="${su.avatar }"
												alt="">
											</a>
											<div class="DashboardProfileCard-userFields">
												<div class="DashboardProfileCard-name u-textTruncate">
													<a class="u-textInheritColor" href="u/${su.username }">${su.nickname
														}</a>
												</div>
												<span
													class="DashboardProfileCard-screenname u-inlineBlock u-dir"
													dir="ltr"> <a
													class="DashboardProfileCard-screennameLink u-linkComplex u-linkClean"
													href="u/${su.username }">@<span
														class="u-linkComplex-target">${su.username }</span></a>
												</span>
											</div>
											<!-- 关注按钮+条件判断	 -->
											<c:if test="${! rs_set_suid.contains(su.uid) }">
												<div class="not-following"
													style="position: absolute;left: 90px;top: 60px;">
													<button type="button"
														class="small-follow-btn follow-btn btn small follow-button"
														data-uid="${su.uid }">
														<div class="follow-text action-text">
															<span class="Icon Icon--follow"></span>关注
														</div>
													</button>
												</div>
											</c:if>
											<p class="ProfileCard-bio u-dir" dir="ltr"
												style="padding: 0 10px;">${su.bio }</p>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
				<!-- tweet -->
				<div class="content-header">
					<div class="header-inner">
						<h2 id="content-main-heading">“${keyword }”</h2>
						<p class="subheader">搜索到的推文</p>
					</div>
				</div>
				<div class="content-main top-timeline-tweetbox" id="timeline">
					<div class="stream-container conversations-enabled">
						<div class="stream">
							<ol class="stream-items js-navigable-stream" id="stream-items-id">
								<c:forEach var="t" items="${list }">
									<li class="stream-item">
										<div class="tweet">
											<div class="content">
												<div class="stream-item-header">
													<a class="account-group" href="u/${t.users.username }">
														<img class="avatar" src="${t.users.avatar }" alt="">
														<strong class="fullname">${t.users.nickname }</strong> <span>‏</span><span
														class="username"><s>@</s><b>${t.users.username
																}</b></span>
													</a> <small class="time"><span class="_timestamp">${t.fmtTime()
															}</span></small>
												</div>
												<div class="js-tweet-text-container">
													<p class="TweetTextSize">${t.tcontent }</p>
												</div>
												<div style="text-align: right;font-size: 12px;">
													<button class="srh_tweet" data-tid="${t.tid }">点击查看</button>
												</div>
											</div>
										</div>
									</li>
								</c:forEach>
							</ol>
							<div class="stream-footer ">
								<div class="timeline-end has-items has-more-items">
									<div class="stream-end" style="display: block;">
										<div class="stream-end-inner">
											<span class="Icon Icon--large Icon--logo"></span>
											<p>
												<button type="button" class="btn-link back-to-top"
													style="display: inline-block;">回到顶部 ↑</button>
											</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="search_or_topic" value="${type }" />
	<input type="hidden" id="topic_list" value="${key_topic.t2ts.size() }" />
	<input type="hidden" id="topic_keyword" value="${keyword }" />
</body>
</html>