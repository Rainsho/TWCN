<%@page import="com.rainsho.entity.Relationships"%>
<%@page import="com.rainsho.entity.Users"%>
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

</head>

<body>

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
				<span>&nbsp;&nbsp;时间：${r.fmtTime() }</span><span class="replay_span"><button
						class="replay-replay-btn" data-suid="${r.usersByHuid.uid }"
						data-username="${r.usersByHuid.username
																		}">回复</button>
					<c:if test="${r.usersByHuid.uid==sessionScope.LOGIN_USER.uid }">&nbsp;|&nbsp;&nbsp;<button
							class="replay-del-btn" data-rid="${r.rid }">删除</button>
					</c:if></span>
			</div>
		</div>
	</div>

</body>

</html>