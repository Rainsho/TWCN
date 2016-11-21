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
<script type="text/javascript" src="js/page_index.js"></script>

</head>

<body>
	<div class="topbar js-topbar">
		<div class="global-nav">
			<div class="global-nav-inner">
				<div class="container">
					<div role="navigation" style="display: inline-block;">
						<ul class="nav" id="global-actions">
							<li id="global-nav-home" class="home active new"><a href="/">
									<span class="Icon Icon--home Icon--large"></span> <span
									class="text">主页</span>
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
										<li><a href="/settings/account" class="js-nav">设置</a></li>
										<li class="js-signout-button" id="signout-button"><a
											href="#" class="dropdown-link">登出</a></li>
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
	</div>
	<div id="page-outer">
		<div id="page-container" class="AppContent wrapper wrapper-home">
			<div class="dashboard dashboard-left">
				<!--user info-->
				<div class="DashboardProfileCard  module">
					<a class="DashboardProfileCard-bg u-bgUserColor u-block"
						href="/Rainsho" tabindex="-1" aria-hidden="true"></a>
					<div class="DashboardProfileCard-content">
						<a class="DashboardProfileCard-avatarLink u-inlineBlock"
							href="/Rainsho" title="Rainsho" tabindex="-1" aria-hidden="true">
							<img class="DashboardProfileCard-avatarImage"
							src="img/twitter-icon.png" alt="">
						</a>
						<div class="DashboardProfileCard-userFields">
							<div class="DashboardProfileCard-name u-textTruncate">
								<a class="u-textInheritColor" href="/Rainsho">nickname</a>
							</div>
							<span class="DashboardProfileCard-screenname u-inlineBlock u-dir"
								dir="ltr"> <a
								class="DashboardProfileCard-screennameLink u-linkComplex u-linkClean"
								href="/Rainsho">@<span class="u-linkComplex-target">username</span></a>
							</span>
						</div>
						<div class="ProfileCardStats">
							<ul
								class="ProfileCardStats-statList Arrange Arrange--bottom Arrange--equal">
								<li class="ProfileCardStats-stat Arrange-sizeFit"><a
									class="ProfileCardStats-statLink u-textUserColor u-linkClean u-block"
									href="/Rainsho"> <span
										class="ProfileCardStats-statLabel u-block">推文</span> <span
										class="ProfileCardStats-statValue">21</span>
								</a></li>
								<li class="ProfileCardStats-stat Arrange-sizeFit"><a
									class="ProfileCardStats-statLink u-textUserColor u-linkClean u-block"
									href="/Rainsho/following"> <span
										class="ProfileCardStats-statLabel u-block">正在关注</span> <span
										class="ProfileCardStats-statValue">16</span>
								</a></li>
							</ul>
						</div>
						<div id="dashboard-profile-prompt"></div>
					</div>
				</div>

				<!--tends-->
				<div class="Trends module trends">
					<div class="trends-inner">
						<div class="flex-module trends-container context-trends-container">
							<div class="flex-module-header">
								<h3>
									<span class="trend-location">趋势</span>
								</h3>
							</div>
							<div class="flex-module-inner">
								<ul class="trend-items">
									<li class="trend-item context-trend-item"><a
										class="pretty-link u-linkComplex "
										href="/hashtag/trickortreat?src=tren"> <span
											class="u-linkComplex-target trend-name" dir="ltr">#trickortreat</span>
											<div class="trend-item-stats">10.5万 推文</div>
									</a></li>
									<li class="trend-item context-trend-item"><a
										class="pretty-link u-linkComplex "
										href="/hashtag/trickortreat?src=tren"> <span
											class="u-linkComplex-target trend-name" dir="ltr">#trickortreat</span>
											<div class="trend-item-stats">10.5万 推文</div>
									</a></li>
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
							src="img/twitter-icon.png" alt="">
						<form class="t1-form tweet-form condensed" method="get" action=""
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
									</span> <span class="TweetBoxExtras-item"><div
											class="FoundMediaSearch found-media-search dropdown"></div></span>
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
				<!--home-tweet-box end-->

				<div class="content-header visuallyhidden">
					<div class="header-inner">
						<h2 id="content-main-heading" class="js-timeline-title">推文</h2>
					</div>
				</div>

				<div class="stream-container conversations-enabled">

					<div
						class="stream-item js-new-items-bar-container new-tweets-bar-visible"
						style="display: none;">
						<div class="new-tweets-bar js-new-tweets-bar">查看 2 条新推文</div>
					</div>

					<div class="stream">
						<ol class="stream-items js-navigable-stream" id="stream-items-id">
							<!--test html content begin-->
							<li class="stream-item">
								<div class="tweet">
									<div class="content">
										<!--userinfo-->
										<div class="stream-item-header">
											<a class="account-group" href="/"> <img class="avatar"
												src="img/twitter-icon.png" alt=""> <strong
												class="fullname">niakname</strong> <span>‏</span><span
												class="username"><s>@</s><b>username</b></span>
											</a> <small class="time"><span class="_timestamp">1分钟前</span></small>
										</div>
										<!--msg-->
										<div class="js-tweet-text-container">
											<p class="TweetTextSize">图片测试</p>
										</div>
										<!--media-->
										<div class="AdaptiveMedia">
											<div class="AdaptiveMedia-container">
												<div class="AdaptiveMedia-doublePhoto">
													<div class="AdaptiveMedia-halfWidthPhoto">
														<div class="AdaptiveMedia-photoContainer">
															<img src="img/bg2.jpg" alt=""
																style="height: 100%; left: -62px;">
														</div>
													</div>
													<div class="AdaptiveMedia-halfWidthPhoto">
														<div class="AdaptiveMedia-photoContainer">
															<img src="img/bg3.jpg" alt=""
																style="height: 100%; left: -62px;">
														</div>
													</div>
												</div>
											</div>
										</div>
										<!--option-->
										<div class="stream-item-footer">
											<!--replay-->
											<div class="ProfileTweet-actionList">
												<div class="ProfileTweet-action ProfileTweet-action--reply">
													<button
														class="ProfileTweet-actionButton u-textUserColorHover"
														type="button">
														<div class="IconContainer">
															<span class="Icon Icon--reply"></span>
														</div>
														<div class="IconTextContainer">
															<span
																class="ProfileTweet-actionCount ProfileTweet-actionCountForPresentation">18</span>
														</div>
													</button>
												</div>
												<!--forwart-->
												<div
													class="ProfileTweet-action ProfileTweet-action--retweet">
													<button class="ProfileTweet-actionButton" type="button">
														<div class="IconContainer js-tooltip">
															<span class="Icon Icon--retweet"></span>
														</div>
														<div class="IconTextContainer">
															<span
																class="ProfileTweet-actionCount ProfileTweet-actionCountForPresentation">1200</span>
														</div>
													</button>
												</div>
												<!--like-->
												<div
													class="ProfileTweet-action ProfileTweet-action--favorite">
													<button class="ProfileTweet-actionButton" type="button">
														<div class="IconContainer">
															<div class="HeartAnimationContainer">
																<div class="HeartAnimation"></div>
															</div>
														</div>
														<div class="IconTextContainer">
															<span
																class="ProfileTweet-actionCount ProfileTweet-actionCountForPresentation">3100</span>
														</div>
													</button>
												</div>
												<!--more option-->
												<div class="ProfileTweet-action ProfileTweet-action--more">
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
																<li class="share-via-dm">
																	<button type="button" class="dropdown-link">私信分享</button>
																</li>
																<li class="block-link">
																	<button type="button" class="dropdown-link">屏蔽
																		@username</button>
																</li>
																<li class="dropdown-divider"></li>
															</ul>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</li>
							<!--test html content end-->

							<!--test html content begin-->
							<li class="stream-item">
								<div class="tweet">
									<div class="content">
										<!--userinfo-->
										<div class="stream-item-header">
											<a class="account-group" href="/"> <img class="avatar"
												src="img/twitter-icon.png" alt=""> <strong
												class="fullname">niakname</strong> <span>‏</span><span
												class="username"><s>@</s><b>username</b></span>
											</a> <small class="time"><span class="_timestamp">1分钟前</span></small>
										</div>
										<!--msg-->
										<div class="js-tweet-text-container">
											<p class="TweetTextSize">
												Business Plans aren't just a formality – and here's why<span>&nbsp;</span>
												<a href="#" class="twitter-timeline-link">http</a><span>&nbsp;</span>
											</p>
										</div>
										<!--media-->
										<div class="AdaptiveMedia">
											<div class="AdaptiveMedia-container">
												<div class="AdaptiveMedia-singlePhoto">
													<div class="AdaptiveMedia-photoContainer">
														<img src="img/bg1.jpg" alt=""
															style="width: 100%; top: -0px;">
													</div>
												</div>
											</div>
										</div>
										<!--option-->
										<div class="stream-item-footer">
											<!--replay-->
											<div class="ProfileTweet-actionList">
												<div class="ProfileTweet-action ProfileTweet-action--reply">
													<button
														class="ProfileTweet-actionButton u-textUserColorHover"
														type="button">
														<div class="IconContainer">
															<span class="Icon Icon--reply"></span>
														</div>
														<div class="IconTextContainer">
															<span
																class="ProfileTweet-actionCount ProfileTweet-actionCountForPresentation">18</span>
														</div>
													</button>
												</div>
												<!--forwart-->
												<div
													class="ProfileTweet-action ProfileTweet-action--retweet">
													<button class="ProfileTweet-actionButton" type="button">
														<div class="IconContainer js-tooltip">
															<span class="Icon Icon--retweet"></span>
														</div>
														<div class="IconTextContainer">
															<span
																class="ProfileTweet-actionCount ProfileTweet-actionCountForPresentation">1200</span>
														</div>
													</button>
												</div>
												<!--like-->
												<div
													class="ProfileTweet-action ProfileTweet-action--favorite">
													<button class="ProfileTweet-actionButton" type="button">
														<div class="IconContainer">
															<div class="HeartAnimationContainer">
																<div class="HeartAnimation"></div>
															</div>
														</div>
														<div class="IconTextContainer">
															<span
																class="ProfileTweet-actionCount ProfileTweet-actionCountForPresentation">3100</span>
														</div>
													</button>
												</div>
												<!--more option-->
												<div class="ProfileTweet-action ProfileTweet-action--more">
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
																<li class="share-via-dm">
																	<button type="button" class="dropdown-link">私信分享</button>
																</li>
																<li class="block-link">
																	<button type="button" class="dropdown-link">屏蔽
																		@username</button>
																</li>
																<li class="dropdown-divider"></li>
															</ul>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</li>
							<!--test html content end-->

							<!--test html content begin-->
							<li class="stream-item">
								<div class="tweet">
									<div class="content">
										<!--userinfo-->
										<div class="stream-item-header">
											<a class="account-group" href="/"> <img class="avatar"
												src="img/twitter-icon.png" alt=""> <strong
												class="fullname">niakname</strong> <span>‏</span><span
												class="username"><s>@</s><b>username</b></span>
											</a> <small class="time"><span class="_timestamp">1分钟前</span></small>
										</div>
										<!--msg-->
										<div class="js-tweet-text-container">
											<p class="TweetTextSize">test only</p>
										</div>
										<!--option-->
										<div class="stream-item-footer">
											<!--replay-->
											<div class="ProfileTweet-actionList">
												<div class="ProfileTweet-action ProfileTweet-action--reply">
													<button
														class="ProfileTweet-actionButton u-textUserColorHover"
														type="button">
														<div class="IconContainer">
															<span class="Icon Icon--reply"></span>
														</div>
														<div class="IconTextContainer">
															<span
																class="ProfileTweet-actionCount ProfileTweet-actionCountForPresentation">18</span>
														</div>
													</button>
												</div>
												<!--forwart-->
												<div
													class="ProfileTweet-action ProfileTweet-action--retweet">
													<button class="ProfileTweet-actionButton" type="button">
														<div class="IconContainer js-tooltip">
															<span class="Icon Icon--retweet"></span>
														</div>
														<div class="IconTextContainer">
															<span
																class="ProfileTweet-actionCount ProfileTweet-actionCountForPresentation">1200</span>
														</div>
													</button>
												</div>
												<!--like-->
												<div
													class="ProfileTweet-action ProfileTweet-action--favorite">
													<button class="ProfileTweet-actionButton" type="button">
														<div class="IconContainer">
															<div class="HeartAnimationContainer">
																<div class="HeartAnimation"></div>
															</div>
														</div>
														<div class="IconTextContainer">
															<span
																class="ProfileTweet-actionCount ProfileTweet-actionCountForPresentation">3100</span>
														</div>
													</button>
												</div>
												<!--more option-->
												<div class="ProfileTweet-action ProfileTweet-action--more">
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
																<li class="share-via-dm">
																	<button type="button" class="dropdown-link">私信分享</button>
																</li>
																<li class="block-link">
																	<button type="button" class="dropdown-link">屏蔽
																		@username</button>
																</li>
																<li class="dropdown-divider"></li>
															</ul>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</li>
							<!--test html content end-->

						</ol>

						<div class="stream-footer ">
							<div class="timeline-end has-items has-more-items">
								<div class="stream-end" style="display: block;">
									<div class="stream-end-inner">
										<h1>test info</h1>
										<h1>test info</h1>
										<h1>test info</h1>
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

			<!-- twitter contents end-->

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
							<div class="account-summary">
								<div class="dismiss">
									<span class="Icon Icon--close"></span>
								</div>
								<div class="content">
									<a class="account-group user-thumb" href="/jerryxenos"> <img
										class="avatar" src="img/twitter-icon.png" alt=""> <span
										class="account-group-inner"><b class="fullname">nickname</b>
											<span class="username"><s>@</s><span
												class="js-username">username</span></span> </span>
									</a>
									<div class="user-actions not-following not-muting">
										<button type="button"
											class="small-follow-btn follow-btn btn small follow-button">
											<div class="follow-text action-text">
												<span class="Icon Icon--follow"></span>关注
											</div>
										</button>
									</div>
								</div>
							</div>
							<div class="account-summary">
								<div class="dismiss">
									<span class="Icon Icon--close"></span>
								</div>
								<div class="content">
									<a class="account-group user-thumb" href="/jerryxenos"> <img
										class="avatar" src="img/twitter-icon.png" alt=""> <span
										class="account-group-inner"><b class="fullname">nickname</b>
											<span class="username"><s>@</s><span
												class="js-username">username</span></span> </span>
									</a>
									<div class="user-actions not-following not-muting">
										<button type="button"
											class="small-follow-btn follow-btn btn small follow-button">
											<div class="follow-text action-text">
												<span class="Icon Icon--follow"></span>关注
											</div>
										</button>
									</div>
								</div>
							</div>
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
									href="/about">关于</a></li>
								<li class="Footer-item"><a class="Footer-link"
									href="//support.twitter.com">帮助</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<!--who to follow and copyright end-->
</body>

</html>
