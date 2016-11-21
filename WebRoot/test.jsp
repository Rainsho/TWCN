<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>test page</title>
</head>

<body>
	This is my JSP page.
	<br>
	<h3>${user.nickname }</h3>
	<h3>
		<c:forEach var="suid" items="${user.relationshipsesForSuid }">
	${suid.usersByHuid.nickname }
	</c:forEach>
	</h3>
	<h3>
		<c:forEach var="t" items="${user.tweetses }">
	${t.tcontent }
	</c:forEach>
	</h3>
</body>
</html>
