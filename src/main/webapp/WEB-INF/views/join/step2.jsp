<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
<%-- <%@include file="/WEB-INF/inc/header.jsp"%> --%>
<link href="<%=request.getContextPath()%>/bootstrap-3.3.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" >
<title>회원가입 1단계</title>
</head>
<body>
<div class="container">

<div class="row col-md-8 col-md-offset-2">
	<div class="page-header">
		<h3>회원가입 2단계</h3>
	</div>

	<form action="step3" method="post">
	<div>
		<form:errors path="memberJoin" />
		<hr>
		<form:errors path="memberJoin.*" />	
	</div>
	<table class="table" >
		<colgroup>
			<col width="20%" />
			<col />
		</colgroup>
		<tr>
			<th>ID</th>
			<td>
				<input type="text" name="memId" class="form-control input-sm" value="${memberJoin.memId}">	
				<form:errors path="memberJoin.memId" /> 		     
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
				<input type="password" name="memPass" class="form-control input-sm" value="" >
				<form:errors path="memberJoin.memPass" /> 
			</td>
		</tr>
		<tr>
			<th>비밀번호 확인</th>
			<td>
				<input type="password" name="memPassConfirm" class="form-control input-sm" value="" >
			</td>
		</tr>
		<tr class="form-group-sm">
			<th>회원명</th>
			<td>
				<input type="text" name="memName" class="form-control input-sm" value="${memberJoin.memName}" >
				<form:errors path="memberJoin.memName" /> 
			</td>
		</tr>
			
		<tr>
			<td colspan="2">
				<div class="pull-left" >
					<a href="/movie" class="btn btn-sm btn-default" >
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
						&nbsp;&nbsp;취 소
					</a>
				</div>
				<div class="pull-right">
					<button type="submit" class="btn btn-sm btn-primary" >
						<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 
						&nbsp;&nbsp;다 음 
					</button>
				</div>
			</td>
		</tr>	
	</table>
	</form>
</div>

</div> <!-- END : 메인 콘텐츠  컨테이너  -->
</body>
</html>



