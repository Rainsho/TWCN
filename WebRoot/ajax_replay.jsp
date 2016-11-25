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
	<div class="replay_area_d0">
		<div class="replay_area_d1">
			<img class="size24" src="${r.usersByHuid.avatar }">
		</div>
		<div class="replay_area_d2">
			<div>
				<a href="u/${r.usersByHuid.username }">@${r.usersByHuid.username
					}</a>&nbsp;
				<c:if test="${r.usersBySuid != null }">回复&nbsp;<a
						href="u/${r.usersBySuid.username }">@${r.usersBySuid.username
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