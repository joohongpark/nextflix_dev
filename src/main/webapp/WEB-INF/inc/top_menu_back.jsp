<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
	<div class="container">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/">
		 	<img alt="" src="${pageContext.request.contextPath}/images/nextFlix.png" style="width: 10%"></a>
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarResponsive"
			aria-controls="navbarResponsive" aria-expanded="false"
			aria-label="Toggle navigation">
			Menu <i class="fas fa-bars"></i>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/main/mainSearchList.wow">검색</a></li>
				<li class="nav-item"><a class="nav-link" href="<c:url value='/notice/noticeList.wow' />">공지사항</a></li>				
				<li class="nav-item">
					<c:if test='${sessionScope.USER_INFO.userRole=="A"}'>
					 <a class="nav-link" href="<c:url value='/member/memberList.wow' />">회원목록</a> </li>
					</c:if>				
				<li class="nav-item"><a class="nav-link" href="<c:url value='/post/post.wow'   />">고객센터</a></li>
			<c:if test="${empty sessionScope.USER_INFO}">
				<li class="nav-item"><a class="nav-link" href="<c:url value='/login/login.wow' />">로그인</a></li>
      		</c:if>
      	
			<c:if test="${not empty sessionScope.USER_INFO}">
	        	<li class="nav-item">
	          <a href="#" data-toggle="dropdown" role="button" class="nav-link">
	          	${sessionScope.USER_INFO.userName}님
	          </a>
	          	<ul class="dropdown-menu" role="menu">
	          	<li><a href="<c:url value='#' />">
	            			<i class="fa fa-credit-card"></i>&nbsp;&nbsp; 결제 내역</a>
	            </li>          	
	            <li><a href="<c:url value='/keep/keepList.wow' />">
	            			<i class="fa fa-shopping-cart" ></i>&nbsp;&nbsp; 나의 찜목록</a>
	            </li>
	            <li><a href="#"><i class="fa fa-key" ></i>&nbsp;&nbsp;비밀번호 변경</a></li>
	            <li class="divider"></li>            
	            <li><a href="<c:url value='/login/logout.wow' />">
	            			<i class="fa fa-sign-out-alt" ></i>&nbsp;&nbsp; 로그아웃</a>
	          	</ul>
	        </li>
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
				<div class="col-lg-8 col-md-10 mx-auto">
					<div class="site-heading">
						<a href="${pageContext.request.contextPath}/"><img alt="" src="${pageContext.request.contextPath}/images/nextFlix.png"></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%-- <header class="masthead"
		style="">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto">
					<div class="site-heading">
						<a href="${pageContext.request.contextPath}/"><img alt="" src="${pageContext.request.contextPath}/images/nextFlix.png"></a>
					</div>
				</div>
			</div>
		</div>
	</header> --%>
</c:if>
