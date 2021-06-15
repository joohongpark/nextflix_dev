<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<link href="<%=CTX_PATH%>/bootstrap-3.3.2/css/bootstrap.min.css" rel="stylesheet">
<title>member Mypage.jsp</title>	
</head>
<body>
	<%@ include file="/WEB-INF/inc/top_menu.jsp" %>
	<div class="container">
		<h3>마이 페이지</h3>
		<div>
		<table class="table table-striped table-bordered">
			<tbody>
				<tr>
					<th>아이디</th>
					<td>${member.memId}</td>
				</tr>	
				<tr>
					<th>비밀번호</th>
					<td>${member.memPass}</td>
				</tr>
		</table>
		</div>
		<table class="table table-striped table-bordered">
			<tr>
				<th>회원명</th>
				<td>${member.memName}</td>
			</tr>
			<tr>
				<th>성별</th>
				<td>${member.memSex}</td>
			</tr>
			<tr>
				<th>생일</th>
				<td>${member.memBir}</td>
			</tr>
			<tr>
				<th>핸드폰</th>
				<td>${member.memHp}</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>${member.memAdd1}-${member.memAdd2}</td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td>${member.memZip}</td>
			</tr>

			<tr>
				<td colspan="2"><a href="memberEdit.wow?memId=${member.memId}"
					class="btn btn-success btn-sm"> <span
						class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
						&nbsp;수정
				</a></td>
			</tr>
			</tbody>
		</table>
	</div>
</body>
</html>