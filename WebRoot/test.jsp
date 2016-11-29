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

<title>TWCN —— ${LOGIN_USER.nickname }</title>
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


</head>

<body>
	<div class="gallery-overlay"></div>
	<div class="Gallery">
		<div class="Gallery-closeTarget"></div>
		<div class="Gallery-content no-grid"
			style="width: 815px; min-height: 651px;">
			<button type="button" class="modal-btn modal-close js-close">
				<span class="Icon Icon--close Icon--large"> <span
					class="visuallyhidden">关闭</span>
				</span>
			</button>
			<div class="Gallery-media">
				<img alt="" class="media-image"
					src="https://pbs.twimg.com/media/Cx3HfevUkAAbotN.jpg:large"
					data-height="853" data-width="1280"
					style="width: 815px; height: 543px; margin-top: 0px; margin-bottom: 0px;">
			</div>
			<div class="GalleryNav GalleryNav--prev enabled"
				style="display: block;">
				<span class="GalleryNav-handle GalleryNav-handle--prev"> <span
					class="Icon Icon--caretLeft Icon--large"> <span
						class="u-hiddenVisually">上一页 </span>
				</span>
				</span>
			</div>
			<div class="GalleryNav GalleryNav--next enabled"
				style="display: block;">
				<span class="GalleryNav-handle GalleryNav-handle--next"> <span
					class="Icon Icon--caretRight Icon--large"> <span
						class="u-hiddenVisually">下一步 </span>
				</span>
				</span>
			</div>
		</div>
	</div>
</body>
</html>