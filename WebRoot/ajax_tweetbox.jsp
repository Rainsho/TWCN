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
</head>
<body>
	<div class="modal-overlay" style="z-index: 4001;display: block;"></div>
	<div id="dm_dialog" class="DMDialog modal-container"
		style="z-index: 4002;">
		<div class="close-modal-background-target js-close-dm"></div>
		<div role="main" aria-labelledby="content-main-heading"
			class="content-main top-timeline-tweetbox" id="timeline">
			<div class="timeline-tweet-box">
				<div class="home-tweet-box tweet-box tweet-user">
					<img class="top-timeline-tweet-box-user-image avatar size32"
						src="${LOGIN_USER.avatar }" alt="未登陆">
					<form class="t1-form tweet-form" method="post" action=""
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
		</div>
	</div>
</body>
</html>