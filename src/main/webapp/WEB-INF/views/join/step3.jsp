<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
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
		<h3>회원가입 3단계</h3>
	</div>

	<form action="regist" method="post">
	<table class="table" >
		<colgroup>
			<col width="20%" />
			<col />
		</colgroup>
		
		<tr class="form-group-sm">
			<th>성별</th>
			<td>남자<input type="radio" name="memSex" value="남자">
				여자<input type="radio" name="memSex" value="여자"></td>
		</tr>
		<tr class="form-group-sm">
			<th>생일</th>
			<td><input type="date" class="form-control input-sm col-md-5"  name="memBir" value="${memberJoin.memBir}"></td>
		</tr>
		<tr class="form-group-sm">
			<th>우편번호</th>
			<td><input type="text" name="memZip" class="form-control input-sm"  value="${memberJoin.memZip}"></td>
		</tr>
		<tr class="form-group-sm">
			<th>주소</th>
			<td><input type="text" name="memAdd1" class="form-control input-sm" value="${memberJoin.memAdd1}"> 
				  <input type="text" name="memAdd2" class="form-control input-sm" value="${memberJoin.memAdd2}">
			</td>
		</tr>
		<tr  class="form-group-sm">
			<th>핸드폰</th>
			<td><input type="text" class="form-control" name="memHp" value="${memberJoin.memHp}"></td>
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

</div></body>
</html>



