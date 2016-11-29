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
<script type="text/javascript" src="js/page_ajax_load.js"></script>

</head>

<body>
	<div class="topbar js-topbar">
		<div class="global-nav">
			<div class="global-nav-inner">
				<div class="container">
					<div role="navigation" style="display: inline-block;">
						<ul class="nav" id="global-actions">
							<li id="global-nav-home" class="home active"><a href="i">
									<span class="Icon Icon--home Icon--large"></span> <span
									class="text">主页</span>
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
									src="${LOGIN_USER.avatar }" alt="未登陆"></a>
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
															class="screen-name hidden" dir="ltr">@${LOGIN_USER.nickname
															}</span> <small class="metadata">查看个人主页</small>
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
	<div id="page-outer" style="height: auto;">
		<div class="close-modal-background-target index_cc"></div>
		<div id="page-container" class="AppContent wrapper wrapper-home">
			<div class="dashboard dashboard-left">
				<!--user info-->
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
				<!--tends pending-->
				<div class="Trends module trends">
					<div class="trends-inner">
						<div class="flex-module trends-container context-trends-container">
							<div class="flex-module-header">
								<h3>
									<span class="trend-location">热门主题</span>
								</h3>
							</div>
							<div class="flex-module-inner">
								<ul class="trend-items">
									<c:forEach var="tp" items="${hot_topic }">
										<li class="trend-item context-trend-item"><a
											class="pretty-link u-linkComplex "
											href="usertopic.action?keyword=${tp.tpcontent }"> <span
												class="u-linkComplex-target trend-name" dir="ltr">#${tp.tpcontent
													}</span></a>
											<div class="trend-item-stats">${tp.t2ts.size() } 次提到</div></li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- twitter contents begin -->
			<!--tweet box-->
			<div role="main" aria-labelledby="content-main-heading"
				class="content-main top-timeline-tweetbox" id="timeline">
				<div id="above-timeline-prompt"></div>
				<div class="timeline-tweet-box">
					<!--home-tweet-box begin-->
					<div class="home-tweet-box tweet-box tweet-user">
						<img class="top-timeline-tweet-box-user-image avatar size32"
							src="${LOGIN_USER.avatar }" alt="未登陆">
						<form class="t1-form tweet-form condensed" method="post" action=""
							enctype="multipart/form-data">
							<div class="tweet-content">
								<div class="RichEditor">
									<div class="RichEditor-mozillaCursorWorkaround">&nbsp;</div>
									<div class="RichEditor-scrollContainer">
										<div aria-labelledby="tweet-box-home-timeline-label"
											name="tweet" id="tweet-box-home-timeline"
											class="tweet-box rich-editor" contenteditable="true"
											spellcheck="true" role="textbox" aria-multiline="true"
											dir="ltr" aria-autocomplete="list" aria-expanded="false"
											aria-owns="typeahead-dropdown-4">
											<div>有什么新鲜事?</div>
										</div>
									</div>
									<div class="RichEditor-mozillaCursorWorkaround">&nbsp;</div>
								</div>
							</div>
							<div class="TweetBoxUploadProgress-uploading" id="upload_div"
								style="display: none;">
								<span id="select_files">已选文件：123456...</span><br /> <span
									id="upload_state">正在上传</span>
								<div class="TweetBoxUploadProgress-bar" id="upload_bar">
									<div class="TweetBoxUploadProgress-barPosition"
										style="width: 33%;"></div>
								</div>
							</div>
							<div class="TweetBoxToolbar">
								<div class="TweetBoxExtras tweet-box-extras">
									<span class="TweetBoxExtras-item TweetBox-mediaPicker">
										<div class="photo-selector">
											<button aria-hidden="true" class="btn icon-btn js-tooltip"
												type="button" tabindex="-1" data-original-title="添加照片或视频">
												<span class="tweet-camera Icon Icon--camera"></span>
											</button>
											<div class="image-selector">
												<label class="t1-label"> <input type="file"
													name="media_empty"
													accept="image/gif,image/jpeg,image/jpg,image/png,video/mp4"
													multiple="" class="file-input">
												</label>
											</div>
										</div>
									</span>
								</div>
								<div class="TweetBoxToolbar-tweetButton tweet-button">
									<span class="tweet-counter">140</span>
									<button class="btn primary-btn tweet-action tweet-btn"
										type="button" disabled="disabled">
										<span class="button-text tweeting-text"><span
											class="Icon Icon--tweet"></span>发推</span>
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="content-header visuallyhidden">
					<div class="header-inner">
						<h2 id="content-main-heading" class="js-timeline-title">推文</h2>
					</div>
				</div>
				<div class="stream-container conversations-enabled">
					<div class="stream-item new-tweets-bar-visible"
						style="display: none;" id="new_tweet_bar">
						<div class="new-tweets-bar">查看 2 条新推文</div>
					</div>
					<div class="stream">
						<ol class="stream-items js-navigable-stream" id="stream-items-id">
							<!-- core tweet -->
							<c:forEach var="f" items="${forward_list }">
								<c:choose>
									<c:when test="${f.users == null }">
										<!--test html content begin-->
										<li class="stream-item">
											<div class="tweet">
												<div class="content">
													<!--userinfo-->
													<div class="stream-item-header">
														<a class="account-group"
															href="u/${f.tweets.users.username }"> <img
															class="avatar" src="${f.tweets.users.avatar }" alt="">
															<strong class="fullname">${f.tweets.users.nickname
																}</strong> <span>‏</span><span class="username"><s>@</s><b>${f.tweets.users.username
																	}</b></span>
														</a> <small class="time"><span class="_timestamp">${f.tweets.fmtTime()
																}</span></small>
													</div>
													<!--msg-->
													<div class="js-tweet-text-container">
														<p class="TweetTextSize">${f.tweets.tcontent }</p>
													</div>
													<!--media-->
													<c:if test="${f.tweets.t2ps.size() > 0 }">
														<div class="AdaptiveMedia">
															<div class="AdaptiveMedia-container">
																<c:choose>
																	<c:when test="${f.tweets.t2ps.size()==1 }">
																		<c:forEach var="t2p" items="${f.tweets.t2ps }">
																			<div class="AdaptiveMedia-singlePhoto">
																				<div class="AdaptiveMedia-photoContainer">
																					<img src="${t2p.pics.ppath }"
																						alt="${t2p.pics.pid }"
																						style="width: 100%; top: -0px;">
																				</div>
																			</div>
																		</c:forEach>
																	</c:when>
																	<c:when test="${f.tweets.t2ps.size()==2 }">
																		<div class="AdaptiveMedia-doublePhoto">
																			<c:forEach var="t2p" items="${f.tweets.t2ps }">
																				<div class="AdaptiveMedia-halfWidthPhoto">
																					<div class="AdaptiveMedia-photoContainer">
																						<img src="${t2p.pics.ppath }"
																							alt="${t2p.pics.pid }"
																							style="height: 100%; left: -62px;">
																					</div>
																				</div>
																			</c:forEach>
																		</div>
																	</c:when>
																	<c:when test="${f.tweets.t2ps.size()==3 }">
																		<div class="AdaptiveMedia-triplePhoto">
																			<c:forEach var="t2p" items="${f.tweets.t2ps }"
																				begin="0" end="0">
																				<div class="AdaptiveMedia-twoThirdsWidthPhoto">
																					<div class="AdaptiveMedia-photoContainer">
																						<img src="${t2p.pics.ppath }"
																							alt="${t2p.pics.pid }"
																							style="height: 100%; left: -83px;">
																					</div>
																				</div>
																			</c:forEach>
																			<div class="AdaptiveMedia-halfHeightPhotoContainer">
																				<c:forEach var="t2p" items="${f.tweets.t2ps }"
																					begin="1">
																					<div class="AdaptiveMedia-halfHeightPhoto">
																						<div class="AdaptiveMedia-photoContainer">
																							<img src="${t2p.pics.ppath }"
																								alt="${t2p.pics.pid }"
																								style="height: 100%; left: -41px;">
																						</div>
																					</div>
																				</c:forEach>
																			</div>
																		</div>
																	</c:when>
																	<c:otherwise>
																		<div class="AdaptiveMedia-quadPhoto">
																			<c:forEach var="t2p" items="${f.tweets.t2ps }"
																				begin="0" end="0">
																				<div class="AdaptiveMedia-threeQuartersWidthPhoto">
																					<div class="AdaptiveMedia-photoContainer">
																						<img src="${t2p.pics.ppath }"
																							alt="${t2p.pics.pid }"
																							style="height: 100%; left: -146px;">
																					</div>
																				</div>
																			</c:forEach>
																			<div class="AdaptiveMedia-thirdHeightPhotoContainer">
																				<c:forEach var="t2p" items="${f.tweets.t2ps }"
																					begin="1" end="3">
																					<div class="AdaptiveMedia-thirdHeightPhoto">
																						<div class="AdaptiveMedia-photoContainer">
																							<img src="${t2p.pics.ppath }"
																								alt="${t2p.pics.pid }"
																								style="height: 100%; left: -41px;">
																						</div>
																					</div>
																				</c:forEach>
																			</div>
																		</div>
																	</c:otherwise>
																</c:choose>
															</div>
														</div>
													</c:if>
													<c:if test="${f.tweets.videos != null }">
														<div class="AdaptiveMedia">
															<div class="AdaptiveMedia-container">
																<div class="AdaptiveMedia-video">
																	<video controls="controls"
																		src="${f.tweets.videos.vpath }" style="width: 506px;"></video>
																</div>
															</div>
														</div>
													</c:if>
													<!--option-->
													<div class="stream-item-footer">
														<!--replay-->
														<div class="ProfileTweet-actionList">
															<div
																class="ProfileTweet-action ProfileTweet-action--reply">
																<button
																	class="ProfileTweet-actionButton u-textUserColorHover js-replay-btn"
																	type="button">
																	<div class="IconContainer">
																		<span class="Icon Icon--reply"></span>
																	</div>
																	<div class="IconTextContainer">
																		<span
																			class="ProfileTweet-actionCount ProfileTweet-actionCountForPresentation">${f.tweets.replayses.size()
																			}</span>
																	</div>
																</button>
															</div>
															<!--forwart-->
															<div
																class="ProfileTweet-action ProfileTweet-action--retweet">
																<button class="ProfileTweet-actionButton js-forward-btn"
																	type="button">
																	<div class="IconContainer js-tooltip">
																		<span class="Icon Icon--retweet"></span>
																	</div>
																	<div class="IconTextContainer">
																		<span
																			class="ProfileTweet-actionCount ProfileTweet-actionCountForPresentation">${f.tweets.forwardses.size()
																			}</span>
																	</div>
																</button>
															</div>
															<!--like-->
															<div
																class="ProfileTweet-action ProfileTweet-action--favorite">
																<button class="ProfileTweet-actionButton js-like-btn"
																	type="button" data-tid="${f.tweets.tid }"
																	<c:forEach var="l" items="${f.tweets.likeses }">
														<c:if test="${l.users.uid == sessionScope.LOGIN_USER.uid }">data-like="1"</c:if>
														</c:forEach>>
																	<div class="IconContainer">
																		<div class="HeartAnimationContainer">
																			<div class="HeartAnimation"></div>
																		</div>
																	</div>
																	<div class="IconTextContainer">
																		<span
																			class="ProfileTweet-actionCount ProfileTweet-actionCountForPresentation">${f.tweets.likeses.size()
																			}</span>
																	</div>
																</button>
															</div>
															<!--more option-->
															<div
																class="ProfileTweet-action ProfileTweet-action--more">
																<div class="dropdown">
																	<button
																		class="ProfileTweet-actionButton u-textUserColorHover"
																		type="button">
																		<div class="IconContainer">
																			<span class="Icon Icon--dots"></span>
																		</div>
																	</button>
																	<div class="dropdown-menu">
																		<div class="dropdown-caret">
																			<div class="caret-outer"></div>
																			<div class="caret-inner"></div>
																		</div>
																		<ul>
																			<c:if test="${LOGIN_USER.uid != f.tweets.users.uid }">
																				<li class="share-via-dm">
																					<button type="button"
																						class="dropdown-link show-dm-area"
																						data-huid="${LOGIN_USER.uid }"
																						data-suid="${f.tweets.users.uid }">发送私信</button>
																				</li>
																			</c:if>
																			<%-- <li class="block-link">
																				<button type="button" class="dropdown-link">屏蔽
																					@${f.tweets.users.username }</button>
																			</li>
																			<li class="dropdown-divider"></li> --%>
																			<c:if test="${LOGIN_USER.uid == f.tweets.users.uid }">
																				<li class="js-actionDelete">
																					<button type="button" class="dropdown-link"
																						data-tid="${f.tweets.tid }">删除推文</button>
																				</li>
																			</c:if>
																			<c:if
																				test="${rs_set_suid.contains(f.tweets.users.uid) && f.tweets.users.uid != LOGIN_USER.uid }">
																				<li class="js-unfollowed">
																					<button type="button" class="dropdown-link"
																						data-uid="${f.tweets.users.uid }">取消关注@${f.tweets.users.username
																						}</button>
																				</li>
																			</c:if>
																		</ul>
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div class="replay_area">
														<c:forEach var="r" items="${f.tweets.replayses }">
															<div class="replay_area_d0">
																<div class="replay_area_d1">
																	<img class="size24" src="${r.usersByHuid.avatar }">
																</div>
																<div class="replay_area_d2">
																	<div>
																		<a href="u/${r.usersByHuid.username }">@${r.usersByHuid.username
																			}</a>&nbsp;
																		<c:if test="${r.usersBySuid != null }">
																	回复&nbsp;<a href="u/${r.usersBySuid.username }">@${r.usersBySuid.username
																				}</a>
																		</c:if>
																		：&nbsp;<span>${r.rcontent }</span>
																	</div>
																	<div style="margin-top: 1px;">
																		<span>&nbsp;&nbsp;时间：${r.fmtTime() }</span><span
																			class="replay_span"><button
																				class="replay-replay-btn"
																				data-suid="${r.usersByHuid.uid }"
																				data-username="${r.usersByHuid.username
																		}">回复</button>
																			<c:if
																				test="${r.usersByHuid.uid==sessionScope.LOGIN_USER.uid }">&nbsp;|&nbsp;&nbsp;<button
																					class="replay-del-btn" data-rid="${r.rid }">删除</button>
																			</c:if></span>
																	</div>
																</div>
															</div>
														</c:forEach>
														<div class="replay_area_d0" style="margin-top: 4px;">
															<div class="replay_area_d1">
																<img class="size24" src="${LOGIN_USER.avatar }">
															</div>
															<div class="replay_area_d2">
																<div style="margin-top: 1px;">
																	<input type="text" maxlength="200"
																		style="height: 14px; width: 396px; margin-right: 12px;"></input>
																	<button data-tid="${f.tweets.tid }" class="replay-btn">评论</button>
																</div>
															</div>
														</div>
													</div>
													<div class="forward_area">
														<c:forEach var="ff" items="${f.tweets.forwardses }">
															<div class="forward_area_d0">
																<div class="forward_area_d1">
																	<img class="size24" src="${ff.users.avatar }">
																</div>
																<div class="forward_area_d2">
																	<div>
																		<a href="u/${ff.users.username }">@${ff.users.username
																			}</a>&nbsp; ：&nbsp;<span>${ff.fcontent }</span>
																	</div>
																	<div style="margin-top: 1px;">
																		<span>&nbsp;&nbsp;时间：${ff.fmtTime() }</span><span
																			class="forward_span"> <c:if
																				test="${ff.users.uid==sessionScope.LOGIN_USER.uid }">
																				<button class="forward-del-btn"
																					data-fid="${ff.fid }">删除</button>
																			</c:if></span>
																	</div>
																</div>
															</div>
														</c:forEach>
														<div class="forward_area_d0" style="margin-top: 4px;">
															<div class="forward_area_d1">
																<img class="size24" src="${LOGIN_USER.avatar }">
															</div>
															<div class="forward_area_d2">
																<div style="margin-top: 1px;">
																	<input type="text" maxlength="200"
																		style="height: 14px; width: 396px; margin-right: 12px;"></input>
																	<button data-tid="${f.tweets.tid }" class="forward-btn">转发</button>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</li>
									</c:when>
									<c:otherwise>
										<li class="stream-item">
											<div class="tweet">
												<div class="content">
													<!--userinfo-->
													<div class="stream-item-header">
														<a class="account-group" href="u/${f.users.username }">
															<img class="avatar" src="${f.users.avatar }"
															alt="${f.users.username }"> <strong
															class="fullname">${f.users.nickname }</strong> <span>‏</span><span
															class="username"><s>@</s><b>${f.users.username
																	}</b></span>
														</a> <small class="time"><span class="_timestamp">${f.fmtTime()
																}</span></small>
													</div>
													<!--msg-->
													<div class="js-tweet-text-container">
														<p class="TweetTextSize" style="padding-top: 4px;">${f.fcontent
															}</p>
														<div
															style="margin: 8px 2px;padding:8px 8px 8px 36px;border: 1px dotted #e1e8ed;"
															class="ntf-tweet-div" data-tid="${f.tweets.tid }"
															data-suid="${f.tweets.users.uid }">
															<a href="u/${f.tweets.users.username }"><img
																class="size24" src="${f.tweets.users.avatar }"
																style="position: absolute;left: 80px;top: 64px;">@${f.tweets.users.username
																}</a>：${f.tweets.tcontent }
															<c:if test="${f.tweets.t2ps.size() > 0 }">
																<div class="AdaptiveMedia" style="zoom: 86%;">
																	<div class="AdaptiveMedia-container">
																		<c:choose>
																			<c:when test="${f.tweets.t2ps.size()==1 }">
																				<c:forEach var="t2p" items="${f.tweets.t2ps }">
																					<div class="AdaptiveMedia-singlePhoto">
																						<div class="AdaptiveMedia-photoContainer">
																							<img src="${t2p.pics.ppath }"
																								alt="${t2p.pics.pid }"
																								style="width: 100%; top: -0px;">
																						</div>
																					</div>
																				</c:forEach>
																			</c:when>
																			<c:when test="${f.tweets.t2ps.size()==2 }">
																				<div class="AdaptiveMedia-doublePhoto">
																					<c:forEach var="t2p" items="${f.tweets.t2ps }">
																						<div class="AdaptiveMedia-halfWidthPhoto">
																							<div class="AdaptiveMedia-photoContainer">
																								<img src="${t2p.pics.ppath }"
																									alt="${t2p.pics.pid }"
																									style="height: 100%; left: -62px;">
																							</div>
																						</div>
																					</c:forEach>
																				</div>
																			</c:when>
																			<c:when test="${f.tweets.t2ps.size()==3 }">
																				<div class="AdaptiveMedia-triplePhoto">
																					<c:forEach var="t2p" items="${f.tweets.t2ps }"
																						begin="0" end="0">
																						<div class="AdaptiveMedia-twoThirdsWidthPhoto">
																							<div class="AdaptiveMedia-photoContainer">
																								<img src="${t2p.pics.ppath }"
																									alt="${t2p.pics.pid }"
																									style="height: 100%; left: -83px;">
																							</div>
																						</div>
																					</c:forEach>
																					<div class="AdaptiveMedia-halfHeightPhotoContainer">
																						<c:forEach var="t2p" items="${f.tweets.t2ps }"
																							begin="1">
																							<div class="AdaptiveMedia-halfHeightPhoto">
																								<div class="AdaptiveMedia-photoContainer">
																									<img src="${t2p.pics.ppath }"
																										alt="${t2p.pics.pid }"
																										style="height: 100%; left: -41px;">
																								</div>
																							</div>
																						</c:forEach>
																					</div>
																				</div>
																			</c:when>
																			<c:otherwise>
																				<div class="AdaptiveMedia-quadPhoto">
																					<c:forEach var="t2p" items="${f.tweets.t2ps }"
																						begin="0" end="0">
																						<div class="AdaptiveMedia-threeQuartersWidthPhoto">
																							<div class="AdaptiveMedia-photoContainer">
																								<img src="${t2p.pics.ppath }"
																									alt="${t2p.pics.pid }"
																									style="height: 100%; left: -146px;">
																							</div>
																						</div>
																					</c:forEach>
																					<div
																						class="AdaptiveMedia-thirdHeightPhotoContainer">
																						<c:forEach var="t2p" items="${f.tweets.t2ps }"
																							begin="1" end="3">
																							<div class="AdaptiveMedia-thirdHeightPhoto">
																								<div class="AdaptiveMedia-photoContainer">
																									<img src="${t2p.pics.ppath }"
																										alt="${t2p.pics.pid }"
																										style="height: 100%; left: -41px;">
																								</div>
																							</div>
																						</c:forEach>
																					</div>
																				</div>
																			</c:otherwise>
																		</c:choose>
																	</div>
																</div>
															</c:if>
															<c:if test="${f.tweets.videos != null }">
																<div class="AdaptiveMedia" style="zoom: 86%;">
																	<div class="AdaptiveMedia-container">
																		<div class="AdaptiveMedia-video">
																			<video controls="controls"
																				src="${f.tweets.videos.vpath }"
																				style="width: 506px;"></video>
																		</div>
																	</div>
																</div>
															</c:if>
														</div>

													</div>
												</div>
											</div>
										</li>

									</c:otherwise>
								</c:choose>
							</c:forEach>
						</ol>
						<div class="stream-footer ">
							<div class="timeline-end has-items has-more-items">
								<div class="stream-end" style="display: block;">
									<div class="stream-end-inner">
										<span class="Icon Icon--large Icon--logo"></span>
										<p>
											<button type="button" class="btn-link back-to-top"
												style="display: none;">回到顶部 ↑</button>
										</p>
									</div>
									<div class="stream-loading" style="display: none;">
										<div class="stream-end-inner">
											<span class="spinner" title="正在载入..."></span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--who to follow and copyright begin-->
			<div class="dashboard dashboard-right">
				<div class="module roaming-module wtf-module has-content">
					<div class="flex-module">
						<div class="flex-module-header">
							<h3>推荐关注</h3>
						</div>
						<div class="dashboard-user-recommendations flex-module-inner"
							style="opacity: 1;">
							<!--who to follow-->
							<c:forEach var="ru" items="${recommend_user }">
								<div class="account-summary">
									<div class="content">
										<a class="account-group user-thumb" href="u/${ru.username }">
											<img class="avatar" src="${ru.avatar }" alt="${ru.username }">
											<span class="account-group-inner"><b class="fullname">${ru.nickname
													}</b> <span class="username"><s>@</s><span
													class="js-username">${ru.username }</span></span> </span>
										</a>
										<c:if test="${! rs_set_suid.contains(ru.uid) }">
											<div class="user-actions not-following not-muting">
												<button type="button"
													class="small-follow-btn follow-btn btn small follow-button"
													data-uid="${ru.uid }">
													<div class="follow-text action-text">
														<span class="Icon Icon--follow"></span>关注
													</div>
												</button>
											</div>
										</c:if>
									</div>
								</div>
							</c:forEach>
						</div>
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
		</div>
	</div>
</body>
</html>