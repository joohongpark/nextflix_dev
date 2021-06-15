<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%-- <%@ include file="/WEB-INF/inc/header.jsp"%> --%>
<!-- 부트스트랩 -->
<link href="<%=request.getContextPath()%>/bootstrap-3.3.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" >
<title>공지 수정</title>
</head>
<body>
<%-- 	<%@ include file="/WEB-INF/inc/top_menu.jsp" %> --%>
	<div class="container">
		<h3>공지사항 수정</h3>		
			<form:form action="noticeModify.wow" modelAttribute="notice">
				<form:errors path="noPass"  />
			</form:form>
			<form action="noticeModify.wow" method="post">
				<input type="hidden" name="noNum" value="${notice.noNum}">
				<table class="table table-striped table-bordered">
					<colgroup>
						<col style="width:20%;" />
						<col />
					</colgroup>
					<tbody>
						<tr>
							<th>글번호</th>
							<td>${notice.noNum}</td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input type="password" name="noPass" class="form-control input-sm">
									<form:errors path="notice.noPass" />
									<span >관리자 비밀번호를 입력하세요</span> 
							</td>
						</tr>
						<tr>
							<th>글제목</th>
							<td><input type="text" name="noTitle"
								class="form-control input-sm" value="${notice.noTitle}">
								<form:errors path="notice.noTitle" />
								</td>
						</tr>
						<tr>
							<th>글분류</th>
							<td>
								<c:set var="category" value="${notice.noCategory }"></c:set>
								<select name="noCategory" class="form-control input-sm">
									<option value="">-- 분류 선택 --</option>
									<c:forEach items="${cateList}" var="code">
										<option value="${code.commCd}"
											${code.commCd == notice.noCategory ? "selected = 'selected'" : " "}>${code.commNm }</option>
									</c:forEach>
							</select>
						</tr>
<!-- 						<tr> -->
<!-- 							<th>작성자명</th> -->
<%-- 							<td>${sessionScope.USER_INFO.userName} --%>
<!-- 							<input type="text" name="noWriter" -->
<%-- 								class="form-control input-sm" value="${notice.noWriter }"> --%>
<%-- 								<form:errors path="notice.noWriter" /> --%>
<!-- 							</td> -->
<!-- 						</tr> -->
						<tr>
							<th>내용</th>
							<td><textarea name="noContent" rows="3" class="form-control input-sm">${notice.noContent}</textarea>	
							</td>
						</tr>
						<tr>
							<th>등록자 IP</th>
							<td>${notice.noIp}</td>
						</tr>
						<tr>
							<th>최근 수정 일자</th>
							<td>${notice.noModDate}</td>
						</tr>
						<tr>
							<td colspan="2"><a href="noticeList.wow"
								class="btn btn-default btn-sm"> <span
									class="glyphicon glyphicon-list" aria-hidden="true"></span>
									&nbsp;목록
							</a>
								<button type="submit" formaction="noticeDelete.wow" class="btn btn-danger btn-sm">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
									&nbsp;삭제
								</button>
								<button type="submit" formaction="noticeModify.wow" class="btn btn-primary btn-sm">
									<span class="glyphicon glyphicon-save" aria-hidden="true"></span>
									&nbsp;수정
								</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
	
	<hr>
	<!-- Footer -->
	<footer>
		<%@include file="/WEB-INF/inc/footer.jsp"%>
	</footer>
	</div>
</body>
</html>