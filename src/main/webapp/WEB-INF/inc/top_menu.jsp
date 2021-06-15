<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
 <!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
<!-- 		//화면 작아졌을때 메뉴버튼 -->
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
	      	</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}/">
				<img alt=""	src="${pageContext.request.contextPath}/images/nextFlix.png"
				style="width: 10%">
			</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				
				<li><a	href="${pageContext.request.contextPath}/main/mainSearchList.wow">영화검색</a></li>
				<li><a  href="<c:url value='/notice/noticeList.wow' />">공지사항</a></li>
				<c:if test='${sessionScope.USER_INFO.userRole=="A"}'>
						<li>
							<a href="<c:url value='/member/memberList.wow' />">회원목록</a>
						</li>
						<li>
						<a	href="<c:url value='/main/mainForm.wow' />">영화등록</a>
						</li>
				</c:if>
<%-- 				<li><a	href="<c:url value='/post/post.wow'   />">고객센터</a></li> --%>
				<c:if test="${empty sessionScope.USER_INFO.userName}">
					<li><a href="<c:url value='/login/login.wow' />">로그인</a></li>
				</c:if>

				<c:if test="${not empty sessionScope.USER_INFO}">
					<li><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
							${sessionScope.USER_INFO.userName}님 <span class="caret"></span> </a>
						<ul class="dropdown-menu" role="menu"> 
<%-- 							<li><a href="<c:url value='#' />"> <i class="fa fa-credit-card"></i>&nbsp;&nbsp; 결제 내역 --%>
<!-- 							</a></li> -->
							<li><a href="<c:url value='/keep/keepList.wow' />"> <i
									class="fa fa-shopping-cart"></i>&nbsp;&nbsp; 나의 찜목록
							</a></li>
							<li><a href="<c:url value='/member/memberMypage.wow?memId=${sessionScope.USER_INFO.userId}' />">
								<i class="fa fa-user"></i>&nbsp;&nbsp;&nbsp; 마이 페이지</a></li>
							<li class="divider"></li>
							<li><a href="<c:url value='/login/logout.wow' />"> <i
									class="fa fa-sign-out-alt"></i>&nbsp;&nbsp; 로그아웃
							</a>
						</ul></li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>

	<!-- Page Header -->
<c:if test="${empty movie}">
	<div class="jumbotron">
		<div class="container">
		<div class="row">
<!-- 				<div class="col-lg-8 col-md-10 mx-auto"> -->
				<div class="text-center">
					<div class="site-heading">
						<a href="${pageContext.request.contextPath}/"><img alt="" src="${pageContext.request.contextPath}/images/nextFlix.png"></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</c:if>
