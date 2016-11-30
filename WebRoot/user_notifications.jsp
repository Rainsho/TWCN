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

<link rel="stylesheet" type="text/css" href="easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui/icon.css">
<link rel="stylesheet" type="text/css" href="easyui/demo.css">
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>

<script type="text/javascript" src="js/page_push.js"></script>

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
							<li class="people notifications active"><a
								href="i/notifications"> <span
									class="Icon Icon--notifications Icon--large"></span> <span
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
						href="u/${LOGIN_USER.username }" tabindex="-1" aria-hidden="true"></a>
					<div class="DashboardProfileCard-content">
						<a class="DashboardProfileCard-avatarLink u-inlineBlock"
							href="u/${LOGIN_USER.username }" title="${LOGIN_USER.username }"
							tabindex="-1" aria-hidden="true"> <img
							class="DashboardProfileCard-avatarImage"
							src="${LOGIN_USER.avatar }" alt="">
						</a>
						<div class="DashboardProfileCard-userFields">
							<div class="DashboardProfileCard-name u-textTruncate">
								<a class="u-textInheritColor" href="u/${LOGIN_USER.username }">${LOGIN_USER.nickname
									}</a>
							</div>
							<span class="DashboardProfileCard-screenname u-inlineBlock u-dir"
								dir="ltr"> <a
								class="DashboardProfileCard-screennameLink u-linkComplex u-linkClean"
								href="u/${LOGIN_USER.username }">@<span
									class="u-linkComplex-target">${LOGIN_USER.username }</span></a>
							</span>
						</div>
						<div class="ProfileCardStats">
							<ul
								class="ProfileCardStats-statList Arrange Arrange--bottom Arrange--equal">
								<li class="ProfileCardStats-stat Arrange-sizeFit"><a
									class="ProfileCardStats-statLink u-textUserColor u-linkClean u-block"
									href="u/${LOGIN_USER.username }"> <span
										class="ProfileCardStats-statLabel u-block">推文</span> <span
										class="ProfileCardStats-statValue">${LOGIN_USER.tweetses.size()
											}</span>
								</a></li>
								<li class="ProfileCardStats-stat Arrange-sizeFit"><a
									class="ProfileCardStats-statLink u-textUserColor u-linkClean u-block"
									href="u/${LOGIN_USER.username }/following"> <span
										class="ProfileCardStats-statLabel u-block">正在关注</span> <span
										class="ProfileCardStats-statValue">${LOGIN_USER.relationshipsesForHuid.size()
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
									href="https://github.com/rainsho/twcn">源码</a></li>
								<li class="Footer-item"><a class="Footer-link"
									href="mailto:rainsho@126.com">联系作者</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div role="main" aria-labelledby="content-main-heading"
				class="content-main" id="div_username">
				<div class="content-header">
					<div class="header-inner">
						<h2 id="content-main-heading">${LOGIN_USER.nickname
							}&nbsp;@${LOGIN_USER.username }</h2>
						<p class="subheader">您的评论和点赞</p>
					</div>
				</div>
				<div class="content-inner no-stream-end">
				<div class="easyui-tabs" style="border-radius: 4px;">
					<div title="收到的评论" style="padding:10px;">
						<c:forEach var="r" items="${receive_list }">
							<div class="replay_area_d0" style="margin-bottom: 5px;">
								<div class="replay_area_d1">
									<a href="u/${r.usersByHuid.username }"><img class="size24"
										src="${r.usersByHuid.avatar }"></a>
								</div>
								<div class="replay_area_d2">
									<div style="padding-left:5px;">
										<c:if test="${r.usersBySuid != null }">回复<a
												href="u/${r.usersBySuid.username }">@${r.usersBySuid.username
												}</a>：
										</c:if>
										<span>${r.rcontent }</span>
									</div>
									<div
										style="margin: 8px 2px;padding:8px 4px;border: 1px dotted #e1e8ed;"
										class="ntf-tweet-div" data-tid="${r.tweets.tid }"
										data-suid="${r.usersByHuid.uid }">
										${r.usersBySuid != null ? "回复" : "评论" }<a
											href="u/${r.tweets.users.username }">@${r.tweets.users.username
											}</a>的推特：${r.tweets.tcontent }
									</div>
									<div style="margin-top: 1px;padding-left:5px;">
										<span>时间：${r.fmtTime() }</span><span class="replay_span">
											<button class="ntf-replay-btn" data-tid="${r.tweets.tid }"
												data-suid="${r.usersByHuid.uid }">查看|回复</button>
										</span>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<div title="发出的评论" style="padding:10px;">
						<c:forEach var="r" items="${LOGIN_USER.rep4HN2O() }">
							<div class="replay_area_d0" style="margin-bottom: 5px;">
								<div class="replay_area_d1">
									<a href="u/${r.usersByHuid.username }"><img class="size24"
										src="${r.usersByHuid.avatar }"></a>
								</div>
								<div class="replay_area_d2">
									<div style="padding-left:5px;">
										<c:if test="${r.usersBySuid != null }">回复<a
												href="u/${r.usersBySuid.username }">@${r.usersBySuid.username
												}</a>：
										</c:if>
										<span>${r.rcontent }</span>
									</div>
									<div
										style="margin: 8px 2px;padding:8px 4px;border: 1px dotted #e1e8ed;"
										class="ntf-tweet-div" data-tid="${r.tweets.tid }"
										data-suid="${r.usersByHuid.uid }">
										${r.usersBySuid != null ? "回复" : "评论" }<a
											href="u/${r.tweets.users.username }">@${r.tweets.users.username
											}</a>的推特：${r.tweets.tcontent }
									</div>
									<div style="margin-top: 1px;padding-left:5px;">
										<span>时间：${r.fmtTime() }</span><span class="replay_span">
											<button class="replay-del-btn" data-rid="${r.rid }">删除</button>
										</span>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<div title="收到的点赞" style="padding:10px;">
						<c:forEach var="l" items="${like_list }">
							<div class="replay_area_d0" style="margin-bottom: 5px;">
								<div class="replay_area_d1">
									<a href="u/${l.users.username }"><img class="size24"
										src="${l.users.avatar }"></a>
								</div>
								<div class="replay_area_d2">
									<div style="padding-left:5px;">
										<span><a href="u/${l.users.username }">@${l.users.username
												}</a>赞了<a href="u/${l.tweets.users.username }">@${l.tweets.users.username
												}</a>的推特： </span>
									</div>
									<div
										style="margin: 8px 2px;padding:8px 8px;border: 1px dotted #e1e8ed;"
										class="ntf-tweet-div" data-tid="${l.tweets.tid }"
										data-suid="${l.tweets.users.uid }">
										<a href="u/${l.tweets.users.username }">@${l.tweets.users.username
											}</a>：${l.tweets.tcontent }
									</div>
									<div style="margin-top: 1px;padding-left:5px;">
										<span>时间：${l.fmtTime() }</span>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<div title="发出的点赞" style="padding:10px;">
						<c:forEach var="l" items="${LOGIN_USER.lkN2O() }">
							<div class="replay_area_d0" style="margin-bottom: 5px;">
								<div class="replay_area_d1">
									<a href="u/${l.users.username }"><img class="size24"
										src="${l.users.avatar }"></a>
								</div>
								<div class="replay_area_d2">
									<div style="padding-left:5px;">
										<span><a href="u/${l.users.username }">@${l.users.username
												}</a>赞了<a href="u/${l.tweets.users.username }">@${l.tweets.users.username
												}</a>的推特： </span>
									</div>
									<div
										style="margin: 8px 2px;padding:8px 8px;border: 1px dotted #e1e8ed;"
										class="ntf-tweet-div" data-tid="${l.tweets.tid }"
										data-suid="${l.tweets.users.uid }">
										<a href="u/${l.tweets.users.username }">@${l.tweets.users.username
											}</a>：${l.tweets.tcontent }
									</div>
									<div style="margin-top: 1px;padding-left:5px;">
										<span>时间：${l.fmtTime() }</span>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				</div>
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
</body>
</html>