<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<link href="<%=request.getContextPath()%>/bootstrap-3.3.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" >
<title>로그인</title>
</head>
<body>

	<div class="container" style="width: 500px;">
		<div class="login-form" >
			<form action="<%=request.getContextPath()%>/login/login.wow" method="post">
				
				 <!-- LOGIN 글씨 -->
				<h1 class="text-center"> 
				<a href="/movie" style="color: red;"> NEXTFLIX </a>
				</h1>
				
				<div class="form-group" >
					<div class="input-group">
						<span class="input-group-addon"></span>
						<input type="text" class="form-control" name="userId"
							placeholder="아이디" required="required">
					</div>
				</div>
				
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon"></span>
						<input type="password" class="form-control" name="userPass"
							placeholder="패스워드" required="required">
					</div>
				</div>
				<div class="clearfix">
					<input type="checkbox" name="idRemember"> Remember ID
				</div>
				<br>
				<div class="form-group" style="text-align:center">
					<button type="submit" class="btn btn-primary login-btn " style="width: 250px" >로 그 인</button>
				</div>

				<!-- 네이버 로그인 창으로 이동 -->							
				<div id="naver_id_login" style="text-align:center" ><a href="${url}">
					<img style="width: 250px"  src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png"/></a></div>
				<div class="or-seperator"></div>
			</form>
			
			<p class="text-center text-muted small">
				<h6 class="post-subtitle">아직 회원이 아니신가요?&nbsp;&nbsp; <a style=color:blue; href="<c:url value='/join/join' />"> 회원가입</a> </h6>
				
			
			
		</div>
	</div>
</body>
</html>