<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<!-- 부트스트랩 -->
<link href="<%=CTX_PATH%>/bootstrap-3.3.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" >
<title>공지사항 등록</title>
<script type="text/javascript">
	
	$(document).ready(function(){
		// closest : 상위에서 가장 가까운 검색 
		// find : 하위 검색 
		
		// 추가버튼 클릭 
		$('#id_btn_new_file').click(function(){
			$('.file_area').append(
					 '<div class="form-inline">'
			     + '<input type="file" name="noFiles" class="form-control">'
			     + ' <button type="button" class="btn_delete btn btn-sm">삭제</button>'
			     + '</div>');
		}); // id_btn_new_file.click
		
		// 삭제 버튼 클릭
		$('.file_area').on('click','.btn_delete', function(){
			$(this).closest('div').remove();
		}); // .btn_delete click
		
	}); // document.ready
	
	
</script>
</head>
<body>
<%-- <%@ include file="/WEB-INF/inc/top_menu.jsp" %> --%>
	<div class="container" style="width: 800px;">
		<div class="page-header">
			<h3>글 등록</h3>
		</div>
		<div class="row">
			<form:form  modelAttribute="notice" action="noticeRegist.wow"
			            method="post" enctype="multipart/form-data"  style="width:800px;">			
				<table class="table table-striped table-bordered ">
					<colgroup>
						<col width="20%" />
						<col />
					</colgroup>
					<tr>
						<th>제목</th>
						<td><form:input path="noTitle" cssClass="form-control" />
							  <form:errors path="noTitle" />
						</td>
						
					</tr>
<!-- 					<tr> -->
<!-- 						<th>작성자</th> -->
<!-- 						<td> -->
<%-- 						<input type="text" name="noWriter" value="${sessionScope.USER_INFO.userName}"class="form-control" readonly> --%>
<%-- 								<form:errors path="noWriter" /> --%>
<!-- 						</td> -->
<!-- 					</tr> -->
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="noPass" value=""	class="form-control" title="4글자 이상 입력">
								<form:errors path="noPass" />						 
							<span>수정 	또는 삭제시에 필요합니다.</span>
						</td>
					</tr>
					<tr>
						<th>분류</th>
						<td>
							<form:select path="noCategory" cssClass="form-control" required="required">
								<option value="">-- 선택하세요--</option>
								<form:options items="${cateList}" itemLabel="commNm" itemValue="commCd" />
							</form:select>
					</td>
					</tr>
					<tr>
						<th>IP</th>
						<td>${pageContext.request.remoteAddr}</td>
					</tr>
					
					<tr>
						<th>첨부파일
							<button type="button" id="id_btn_new_file">추가</button>
						</th>
						<td class="file_area">
							<div class="form-inline">
								<input type="file" name="noFiles" class="form-control" >
								<button type="button" class="btn_delete btn btn-sm">삭제</button>
							</div>
						</td>
					</tr>
					
					<tr>
						<th>내용</th>
						<td>
							<form:textarea path="noContent" rows="3" cssClass="form-control" />							
						</td>
					</tr>					
					
					<tr>
						<td colspan="2">
							<div class="pull-left">
								<a href="noticeList.wow" class="btn btn-sm btn-default">목록으로</a>
							</div>
							<div class="pull-right">
								<button type="submit" class="btn btn-sm btn-primary">저장하기</button>								
							</div>
						</td>
					</tr>
				</table>
			</form:form>
		</div>

	</div>
</body>
</html>


