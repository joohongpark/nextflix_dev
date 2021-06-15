<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<link href="<%=CTX_PATH%>/bootstrap-3.3.2/css/bootstrap.min.css" rel="stylesheet">
<title>찜목록</title>


<script type="text/javascript">
	$(document).ready(function() {

		// 로우수 변경 버튼 클릭	
		$("#id_btn_rowsize_change").click(function() {
			f.rowSizePerPage.value = $("select[name=rowSizePerPage]").val();
			f.submit();
		}); //id_btn_rowsize_change.click

		
		//찜목록 삭제버튼 클릭
// 		$('#id_btn_delete').submit(function(e) {
// 			e.preventDefault();
// 			f.curPage.value = 1;
// 			f.submit();
// 		}); // form[name=frm_search].submit

	}); //document.ready
</script>
</head>
<body>
	<%@include file="/WEB-INF/inc/top_menu.jsp"%>
	<!-- 조회목록 위의 검색영역 생성 -->
	<div class="container">
		<div class="page-header">
			<h3>나의 찜목록</h3>		
		</div>
<!-- 	<div class="panel panel-default"> -->
<!-- 		<div class="panel-body"> -->
<!-- 			<form name="frm_search" action="memberList.wow" method="post" -->
<!-- 				class="form-horizontal"> -->
<%-- 				<input type="hidden" name="curPage" value="${searchVO.curPage}"> --%>
<!-- 				<input type="hidden" name="rowSizePerPage" -->
<%-- 					value="${searchVO.rowSizePerPage}"> --%>
<!-- 				<div class="form-group"> -->
<!-- 					<label for="id_searchType" class="col-sm-2 control-label">검색</label> -->
<!-- 					<div class="col-sm-2"> -->
<!-- 					</div> -->
<!-- 					<div class="col-sm-2 text-right"> -->
<!-- 						<button type="submit" class="btn btn-sm btn-primary "> -->
<!-- 							<i class="fa fa-search"></i> -->
<!-- 							&nbsp;&nbsp;검 색 -->
<!-- 						</button> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				</form> -->
<!-- 		</div> -->
<!-- 	</div> -->
<%-- 	${keepList} --%>
	<div class="row">
		<div class="col-sm-3 form-inline" style="margin-bottom: 5px;">
			전체 ${searchVO.totalRowCount} 건 조회 <select id="id_rowSizePerPage"
				name="rowSizePerPage" class="form-control input-sm">
				<option value="10"
					${searchVO.rowSizePerPage == 10 ? 'selected="selected"' : ''}>10</option>
				<option value="20"
					${searchVO.rowSizePerPage == 20 ? 'selected="selected"' : ''}>20</option>
				<option value="30"
					${searchVO.rowSizePerPage == 30 ? 'selected="selected"' : ''}>30</option>
				<option value="50"
					${searchVO.rowSizePerPage == 50 ? 'selected="selected"' : ''}>50</option>
			</select>
			<button type="button" id="id_btn_rowsize_change"
				class="btn btn-sm btn-default ">
				<i class="fas fa-check-circle"></i> &nbsp;선택
			</button>
		</div>
		
		</div>
	<table class="table  table-bordered">
		<colgroup>
			<col style="width: 15%" />
			<col style="width: 8%" />
			<col style="width: 13%" />
			<col style="width: 8%" />
			<col style="width: 3%" />
		</colgroup> 
		<thead>
			<tr>
				<th>영화제목</th>
				<th>개봉일자</th>
				<th>장르</th>
				<th>찜한 날짜</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="keep" items="${keepList}">
				<tr class="text-center" >
					<td>
						<a href="${pageContext.request.contextPath}/main/mainView.wow?movieCd=${keep.keMovieCd}">
							${keep.keMovieName}</a>
					</td>
					<td>${keep.keOpenDt}</td>
					<td>
						${keep.keGenre }
<%-- 						<c:forEach items="${keep.keGenre}" var="vo"> --%>
<%-- 										 # ${keep.keGenre} --%>
<%-- 						</c:forEach> --%>
					</td>
					<td>${keep.keRegDt}</td>
					<td>
						<a href="keepDelete.wow?keMovieCd=${keep.keMovieCd }" class="btn btn-sm btn-danger "><i class="fa fa-ban"></i></a>
                	</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!--  조회목록 아래의 페이지네이션 생성 -->
		<nav class="text-center">
			<mytag:paging pagingVO="${searchVO}" linkPage="keepList.wow"/>
		</nav>
	</div>
	
	<hr>
	<!-- Footer -->
	<footer>
		<%@include file="/WEB-INF/inc/footer.jsp"%>
	</footer>
</body>
</html>