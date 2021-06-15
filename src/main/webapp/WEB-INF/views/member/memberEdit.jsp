<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%-- <%@ include file="/WEB-INF/inc/header.jsp"%> --%>
<title>member/ memberEdit.jsp</title>
<link href="<%=request.getContextPath()%>/bootstrap-3.3.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" >
</head>
<body>
	<%@ include file="/WEB-INF/inc/top_menu.jsp" %>
	<div class="container">
		<h3>회원 정보 수정</h3>
			<form action="memberModify.wow" method="post">
				<input type="hidden" name="memId" value="${member.memId}">
				<table class="table table-striped table-bordered">
					<tbody>
						<tr>
							<th>아이디</th>
							<td>${member.memId}</td>
						</tr>
						<tr>
							<th>회원명</th>
							<td><input type="text" name="memName"
								value="${member.memName}" class="form-control input-sm"></td>
						</tr>
						<tr>
						<th>성별</th>
						<td>남자<input type="radio" name="memSex" value="남자">
							여자<input type="radio" name="memSex" value="여자"></td>
						</tr>
						<tr>
							<th>우편번호</th>
							<td><input type="text" name="memZip"
								value="${member.memZip}" class="form-control input-sm"
								placeholder="우편번호"></td>
						</tr>
						<tr>
							<th>주소</th>
							<td><input type="text" name="memAdd1"
								value="${member.memAdd1}" class="form-control input-sm"
								placeholder="기본주소"> <input type="text" name="memAdd2"
								value="${member.memAdd2}" class="form-control input-sm"
								placeholder="상세주소"></td>
						</tr>
						<tr>
							<th>생일</th>
							<td><input type="text" name="memBir"
								value="${member.memBir}" class="form-control input-sm"></td>
						</tr>
						<tr>
							<th>핸드폰</th>
							<td><input type="tel" name="memHp" value="${member.memHp}"
								class="form-control input-sm"></td>
						</tr>
						<tr>
							<td colspan="2">
							 <c:if test='${sessionScope.USER_INFO.userRole=="A"}'>
							 <a href="memberList.wow" class="btn btn-default btn-sm"> 
								<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
									&nbsp;목록
							 </a>
							 </c:if>	
							
							<c:if test='${sessionScope.USER_INFO.userRole=="A"}'>
								<button type="submit" formaction="memberDelete.wow" class="btn btn-danger btn-sm">
									<span class="glyphicon glyphicon-delete" aria-hidden="true"></span>
									&nbsp;삭제
								</button>
							</c:if>	
								<button type="submit" class="btn btn-primary btn-sm">
									<span class="glyphicon glyphicon-save" aria-hidden="true"></span>
									&nbsp;저장
								</button>
							  </td>
						</tr>
					</tbody>
				</table>
			</form>
	</div>
</body>
</html>