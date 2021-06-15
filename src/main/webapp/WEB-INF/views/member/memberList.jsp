<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<link href="<%=CTX_PATH%>/bootstrap-3.3.2/css/bootstrap.min.css" rel="stylesheet">
<%-- <link rel="stylesheet" href="<%=CTX_PATH%>/css/main.css" > --%>
<title>memberList.jsp</title>

<!--  
<script type="text/javascript">
	$(document).ready(function() {

		//폼 변수
		var f = document.forms['frm_search'];

		/* //폼 서브및 처리 함수
		var fn_submit = function() {
			f.submit();
		} */

		// 페이지 클릭
		$("ul.pagination > li > a[data-page]").click(function(e) {
			e.preventDefault(); //이벤트 전파 방지
			var p = $(this).data('page'); //this.dataset.page
			f.curPage.value = p;
			f.submit();
		});

		// 로우수 변경 버튼 클릭	
		$("#id_btn_rowsize_change").click(function() {
			f.rowSizePerPage.value = $("select[name=rowSizePerPage]").val();
			f.submit();
		}); //id_btn_rowsize_change.click

		// 초기화 버튼 클릭
		$("#id_btn_reset").click(function() {
			f.curPage.value = 1;
			f.searchWord.value = '';
			f.searchType.options[0].selected = true;
			//f.searchType.value = $("#id_searchType option").eq(0).val();
			f.searchJob.value = '';
			f.searchLike.value = '';
		}); //id_btn_reset.click

		// 검색 버튼 클릭
		$("form[name=frm_search]").submit(function(e) {
			e.preventDefault();
			f.curPage.value = 1;
			f.submit();
		}); //form[name=frm_search].submit

	}); //document.ready
</script>-->
</head>
<body>
<%@include file="/WEB-INF/inc/top_menu.jsp"%>
	<!-- 조회목록 위의 검색영역 생성 -->
	<div class="container">
		<div class="page-header">
			<h3>회원목록</h3>		
		</div>
	<div class="panel panel-default">
		<div class="panel-body">
			<form name="frm_search" action="memberList.wow" method="post"
				class="form-horizontal">
				<input type="hidden" name="curPage" value="${searchVO.curPage}">
				<input type="hidden" name="rowSizePerPage"
					value="${searchVO.rowSizePerPage}">
				<div class="form-group">
					<label for="id_searchType" class="col-sm-2 control-label">검색</label>
					<div class="col-sm-2">
						<select id="id_searchType" name="searchType"
							class="form-control input-sm">
							<option value="ID"
								${searchVO.searchType eq 'ID' ? 'selected="selected"' : ''}>아이디</option>
							<option value="NM"
								${searchVO.searchType eq 'NM' ? 'selected="selected"' : ''}>회원명</option>
							<option value="HP"
								${searchVO.searchType eq 'HP' ? 'selected="selected"' : ''}>핸드폰</option>
						</select>
					</div>
					<div class="col-sm-2">
						<input type="text" name="searchWord" class="form-control input-sm"
							value="${searchVO.searchWord}" placeholder="검색어">
					</div>
<!-- 					<div class="col-sm-2 col-sm-offset-2"> -->
<!-- 						<button type="button" id="id_btn_reset" -->
<!-- 							class="btn btn-sm btn-default "> -->
<!-- 							<i class="fas fa-sync fa-spin"></i> -->
<!-- 							 &nbsp;&nbsp;초기화 -->
<!-- 						</button> -->
<!-- 					</div> -->
					<div class="col-sm-2 text-right">
						<button type="submit" class="btn btn-sm btn-primary ">
							<i class="fa fa-search"></i>
							&nbsp;&nbsp;검 색
						</button>
					</div>
				</div>
				</form>
		</div>
	</div>

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
			<col style="width: 10%" />
			<col style="width: 10%" />
			<col style="width: 5%" />
			<col style="width: 5%" />
			<col style="width: 20%" />
			<col style="width: 10%" />
		</colgroup>
		<thead>
			<tr>
				<th>ID</th>
				<th>회원명</th>
				<th>성별</th>
				<th>권한</th>
				<th>주소</th>
				<th>HP</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="member" items="${memberList}">
				<tr class="text-center" >
					<td><a href="memberView.wow?memId=${member.memId}">${member.memId}</a></td>
					<td>${member.memName}</td>
					<td>${member.memSex}</td>
					<td>${member.memAuth}</td>
					<td>${member.memAdd1}${member.memAdd2}</td>
					<td>${member.memHp}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!--  조회목록 아래의 페이지네이션 생성 -->
		<nav class="text-center">
			<mytag:paging pagingVO="${searchVO}" linkPage="memberList.wow"/>
		</nav>
	</div>
	
	<hr>
	<!-- Footer -->
	<footer>
		<%@include file="/WEB-INF/inc/footer.jsp"%>
	</footer>
</body>
</html>