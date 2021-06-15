<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>member/ memberForm.jsp</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<%@include file="/WEB-INF/inc/top_menu.jsp"%>
	<div class="container">
		<h3>회원가입</h3>
		<form name="frm_member" action="memberRegist.wow" method="post">
			<table class="table table-striped table-bordered">
				<tbody>
					<tr>
						<th>아이디</th>
						<td><input type="text" name="memId" pattern="\w{6,12}"
							placeholder="영문자, 숫자, 언더바 6글자 이상 12글자 이하" required="required"
							class="form-control input-sm">
							<form:errors path="member.memId" />
							</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="memPass" pattern="\w{4,12}"
							placeholder="영문자, 숫자, 언더바 4글자 이상 12글자 이하" required="required"
							class="form-control input-sm"></td>
					</tr>
					<tr>
						<th>회원명</th>
						<td><input type="text" name="memName" pattern="[가-힝]{2,5}"
							required="required" placeholder="한글 2글자 이상"
							class="form-control input-sm"></td>
					</tr>
					
					<tr>
						<th>우편번호</th>
						<td><input type="text" name="memZip"
							class="form-control input-sm" placeholder="우편번호"></td>
					</tr>
					<tr>
						<th>주소</th>
						<td><input type="text" name="memAdd1"
							class="form-control input-sm" placeholder="기본주소"> <input
							type="text" name="memAdd2" class="form-control input-sm"
							placeholder="상세주소"></td>
					</tr>
					<tr>
						<th>생일</th>
						<td><input type="date" name="memBir"
							class="form-control input-sm"></td>
					</tr>
					<tr>
						<th>핸드폰</th>
						<td><input type="tel" name="memHp"
							class="form-control input-sm"></td>
					</tr>
					
					<tr>
						<td colspan="2"><button type="submit"
							class="btn btn-primary btn-sm">회원가입</button></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

</body>
</html>