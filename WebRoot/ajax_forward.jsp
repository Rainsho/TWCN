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
	<div class="forward_area_d0">
		<div class="forward_area_d1">
			<img class="size24" src="${forward.users.avatar }">
		</div>
		<div class="forward_area_d2">
			<div>
				<a href="u/${forward.users.username }">@${forward.users.username
					}</a>&nbsp; ：&nbsp;<span>${forward.fcontent }</span>
			</div>
			<div style="margin-top: 1px;">
				<span>&nbsp;&nbsp;时间：${forward.fmtTime() }</span><span
					class="forward_span"> <c:if
						test="${forward.users.uid==sessionScope.LOGIN_USER.uid }">
						<button class="forward-del-btn" data-fid="${forward.fid }">删除</button>
					</c:if></span>
			</div>
		</div>
	</div>
</body>
</html>