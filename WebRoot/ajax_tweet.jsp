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
		<div class="stream">
			<ol class="stream-items js-navigable-stream" id="stream-items-id">
				<!-- core tweet -->
				<c:forEach var="t" items="${list }">
					<!--test html content begin-->
					<li class="stream-item">
						<div class="tweet">
							<div class="content">
								<!--userinfo-->
								<div class="stream-item-header">
									<a class="account-group" href="u/${t.users.username }"> <img
										class="avatar" src="${t.users.avatar }" alt=""> <strong
										class="fullname">${t.users.nickname }</strong> <span>‏</span><span
										class="username"><s>@</s><b>${t.users.username }</b></span>
									</a> <small class="time"><span class="_timestamp">${t.fmtTime()
														}</span></small>
								</div>
								<!--msg-->
								<div class="js-tweet-text-container">
									<p class="TweetTextSize">${t.tcontent }</p>
								</div>
								<!--media-->
								<c:if test="${t.t2ps.size() > 0 }">
									<div class="AdaptiveMedia">
										<div class="AdaptiveMedia-container">
											<c:choose>
												<c:when test="${t.t2ps.size()==1 }">
													<c:forEach var="t2p" items="${t.t2ps }">
														<div class="AdaptiveMedia-singlePhoto">
															<div class="AdaptiveMedia-photoContainer">
																<img src="${t2p.pics.ppath }" alt="${t2p.pics.pid }"
																	style="width: 100%; top: -0px;">
															</div>
														</div>
													</c:forEach>
												</c:when>
												<c:when test="${t.t2ps.size()==2 }">
													<div class="AdaptiveMedia-doublePhoto">
														<c:forEach var="t2p" items="${t.t2ps }">
															<div class="AdaptiveMedia-halfWidthPhoto">
																<div class="AdaptiveMedia-photoContainer">
																	<img src="${t2p.pics.ppath }" alt="${t2p.pics.pid }"
																		style="height: 100%; left: -62px;">
																</div>
															</div>
														</c:forEach>
													</div>
												</c:when>
												<c:when test="${t.t2ps.size()==3 }">
													<div class="AdaptiveMedia-triplePhoto">
														<c:forEach var="t2p" items="${t.t2ps }" begin="0" end="0">
															<div class="AdaptiveMedia-twoThirdsWidthPhoto">
																<div class="AdaptiveMedia-photoContainer">
																	<img src="${t2p.pics.ppath }" alt="${t2p.pics.pid }"
																		style="height: 100%; left: -83px;">
																</div>
															</div>
														</c:forEach>
														<div class="AdaptiveMedia-halfHeightPhotoContainer">
															<c:forEach var="t2p" items="${t.t2ps }" begin="1">
																<div class="AdaptiveMedia-halfHeightPhoto">
																	<div class="AdaptiveMedia-photoContainer">
																		<img src="${t2p.pics.ppath }" alt="${t2p.pics.pid }"
																			style="height: 100%; left: -41px;">
																	</div>
																</div>
															</c:forEach>
														</div>
													</div>
												</c:when>
												<c:otherwise>
													<div class="AdaptiveMedia-quadPhoto">
														<c:forEach var="t2p" items="${t.t2ps }" begin="0" end="0">
															<div class="AdaptiveMedia-threeQuartersWidthPhoto">
																<div class="AdaptiveMedia-photoContainer">
																	<img src="${t2p.pics.ppath }" alt="${t2p.pics.pid }"
																		style="height: 100%; left: -146px;">
																</div>
															</div>
														</c:forEach>
														<div class="AdaptiveMedia-thirdHeightPhotoContainer">
															<c:forEach var="t2p" items="${t.t2ps }" begin="1" end="3">
																<div class="AdaptiveMedia-thirdHeightPhoto">
																	<div class="AdaptiveMedia-photoContainer">
																		<img src="${t2p.pics.ppath }" alt="${t2p.pics.pid }"
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
								<c:if test="${t.videos != null }">
									<div class="AdaptiveMedia">
										<div class="AdaptiveMedia-container">
											<div class="AdaptiveMedia-video">
												<video controls="controls" src="${t.videos.vpath }"
													style="width: 506px;"></video>
											</div>
										</div>
									</div>
								</c:if>
								<!--option-->
								<div class="replay_area">
									<c:forEach var="r" items="${t.replayses }">
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
												<button data-tid="${t.tid }" class="replay-btn">评论</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</li>
				</c:forEach>
			</ol>
		</div>
	</div>
</body>
</html>