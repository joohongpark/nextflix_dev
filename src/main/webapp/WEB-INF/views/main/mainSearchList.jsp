<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<link href="<%=CTX_PATH%>/bootstrap-3.3.2/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript">
	$(document).ready(function() {

		//폼 변수
		var f = document.forms['frm_search'];

		//폼 서브및 처리 함수
		var fn_submit = function() {
			f.submit();
		}

		// 페이지 클릭
		$("ul.pagination > li > a").click(function(e) {
			e.preventDefault(); //이벤트 전파 방지
			var p = $(this).data('page'); //this.dataset.page
			f.curPage.value = p;
			f.submit();
		});

		// 검색 버튼 클릭
		$("form[name=frm_search]").submit(function(e) {
			e.preventDefault();
			f.curPage.value = 1;
			f.submit();
		}); //form[name=frm_search].submit

	}); //document.ready
</script>
</head>
<body>
	<%@include file="/WEB-INF/inc/top_menu.jsp"%>
	<div class="panel panel-default">
			<div class="panel-body">
				<form name="frm_search" action="mainSearchList.wow" method="post"
					class="form-horizontal">
					<input type="hidden" name="curPage" value="${searchVO.curPage}">
					<input type="hidden" name="rowSizePerPage" value="${searchVO.rowSizePerPage}">
					<div class="form-group">
						<label for="id_searchCategory"
							class="col-sm-2 col-sm-offset-2 control-label">분류</label>
						<div class="col-sm-2">
							<select id="id_searchCategory" name="searchCategory"
								class="form-control input-sm">
								<option value="">-- 전체 --</option>
								<c:forEach items="${cateList}" var="code">
									<option value="${code.commCd}"
										${code.commCd eq searchVO.searchCategory ? 'selected="selected"' : ''}>${code.commNm}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-sm-2">
							<input type="text" name="searchWord"
								class="form-control input-sm" value="${searchVO.searchWord}" placeholder="검색어">
						</div>
						<div class="col-sm-2">
							<button type="submit" class="btn btn-sm btn-primary ">
								<i class="fa fa-search"></i> &nbsp;&nbsp;검 색
							</button>
						</div>
					</div>
				</form>
			<h5 class="text-center">장르로 검색하시고 싶을경우, 장르만 선택하셔서 검색을 누르시면 됩니다.</h5>
			</div>
		</div>
	<div class="container">
		<div class="row">
			<%-- ${movieSearchVO}a<br> --%>
			<%-- ${searchVO}<br> --%>
			<h2 class="post-title">영화목록</h2>
			<ul>
				<c:forEach var="movie" items="${movieList}">
					<li
						style="float: left; list-style: none; position: relative; margin-right: 3px;">
						<p>
							<a href="mainView.wow?movieCd=${movie.movieCd}"><img
								style="width: 200px; height: 250px;" alt="${movie.movieNm}"
								src='<c:url value="/images/movie/thumb/${movie.movieImg}" />'></a>
						</p>
						<p style="font-size: 15px; width: 200px; display: inline-block; text-align: center;
						width: 200px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${movie.movieNm}</p>
						
					</li>
				</c:forEach>
			</ul>
		</div>
		<nav class="text-center">
			<mytag:paging pagingVO="${searchVO}" linkPage="mainSearchList.wow"/>
		</nav>
	</div>

	<hr>

	<!-- Footer -->
	<footer>
		<%@include file="/WEB-INF/inc/footer.jsp"%>
	</footer>

</body>

</html>

