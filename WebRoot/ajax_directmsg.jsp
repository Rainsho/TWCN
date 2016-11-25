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
<link rel="stylesheet" href="css/twitter_core.bundle.css">
<link rel="stylesheet" href="css/twitter_more_1.bundle.css">
<link rel="stylesheet" href="css/twitter_more_2.bundle.css">
<link rel="stylesheet" href="css/my.css">

<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/page_user.js"></script>
</head>
<body>
	<div class="modal-overlay" style="z-index: 4001;display: block;"></div>
	<div id="dm_dialog" class="DMDialog modal-container"
		style="z-index: 4002;">
		<div class="close-modal-background-target js-close-dm"></div>
		<div class="modal is-autoPosition" id="dm_dialog-dialog">
			<div class="js-first-tabstop" tabindex="0"></div>
			<div class="DMActivity DMInbox js-ariaDocument u-chromeOverflowFix"></div>
			<div class="DMDock">
				<div class="DMDock-conversations">
					<div class="DMConversationContainer">
						<div
							class="DMActivity DMConversation js-ariaDocument u-chromeOverflowFix DMActivity--open">
							<div class="DMActivity-header">
								<div class="DMActivity-title js-ariaTitle" id="dm_dialog-header">
									<div class="DMUpdateAvatar" data-has-custom-avatar="false">
										<div class="DMPopover DMPopover--center">
											<button class="DMPopover-button" disabled="disabled">
												<div class="DMUpdateAvatar-avatar">
													<div class="DMAvatar DMAvatar--1 u-chromeOverflowFix">
														<span class="DMAvatar-container"> <img
															class="DMAvatar-image" src="${suser.avatar }"
															alt="${suser.username }">
														</span>
													</div>
												</div>
											</button>
										</div>
									</div>
									<div class="DMUpdateName u-textTruncate">
										<div class="DMUpdateName-header">
											<span
												class="DMUpdateName-name DMConversation-name u-textTruncate">${suser.username
												}</span>
										</div>
									</div>
								</div>
								<div class="DMActivity-toolbar">
									<button type="button"
										class="DMActivity-close js-close u-textUserColorHover">
										<span class="Icon Icon--close"></span> <span
											class="u-hiddenVisually">关闭</span>
									</button>
								</div>
							</div>
							<div class="DMActivity-container">
								<div
									class="DMActivity-body js-ariaBody DMConversation-container">
									<div class="DMConversation-scrollContainer js-dm-scroll">
										<div
											class="DMConversation-content dm-convo js-dm-conversation">
											<c:forEach var="dm" items="${dmlist }">
												<div
													class='DirectMessage clearfixdmjs-dm-item  ${dm.usersByHuid.uid == sessionScope.LOGIN_USER.uid ? "DirectMessage--sent" : "DirectMessage--received" }'>
													<div class="DirectMessage-container">
														<div class="DirectMessage-avatar">
															<a href="u/${dm.usersByHuid.username }"
																class="js-action-profile js-user-profile-link"> <span
																class="DMAvatar DMAvatar--1 u-chromeOverflowFix">
																	<span class="DMAvatar-container"> <img
																		class="DMAvatar-image" src="${dm.usersByHuid.avatar }"
																		alt="${dm.usersByHuid.username }">
																</span>
															</span>
															</a>
														</div>
														<div
															class='DirectMessage-message with-text Caret dm-message  ${dm.usersByHuid.uid == sessionScope.LOGIN_USER.uid ? "Caret--right" : "Caret--left" }'>
															<div class="DirectMessage-text">
																<div class="js-tweet-text-container">
																	<p class="TweetTextSize  js-tweet-text tweet-text"
																		lang="" data-aria-label-part="0">${dm.dcontent }</p>
																</div>
															</div>
														</div>
														<div class="DirectMessage-actions">
															<span class="DirectMessage-action"><button
																	type="button" class="DMDeleteMessageAction js-del-msg"
																	title="删除这条消息" data-did="${dm.did }">
																	<span class="Icon Icon--delete"></span> <span
																		class="u-hiddenVisually">删除这条消息</span>
																</button> </span>
														</div>
													</div>
													<div class="DirectMessage-footer">
														<span class="DirectMessage-footerItem"><span
															class="_timestamp js-relative-timestamp">${dm.fmtTime()
																}</span> </span>
													</div>
												</div>
											</c:forEach>
										</div>
									</div>
								</div>
								<div class="DMActivity-footer u-emptyHide">
									<div class="DMConversation-composer u-bgUserColorLightest">
										<form class="DMComposer tweet-form">
											<div class="DMComposer-container u-borderUserColorLighter">
												<input type="text" id="dm_msg"
													style="width: 432px;height: 26px;">
											</div>
											<div class="DMComposer-send">
												<button
													class="btn tweet-action primary-btn messaging tweet-btn"
													type="button" disabled="disabled" id="dm_send_btn"
													data-huid="${huser.uid }" data-suid="${suser.uid }">
													<span class="button-text messaging-text">发送</span>
												</button>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>