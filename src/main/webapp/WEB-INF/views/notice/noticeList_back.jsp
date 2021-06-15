<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="mytag" tagdir="/WEB-INF/tags"  %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<!-- 부트스트랩 -->
<link href="<%=request.getContextPath()%>/bootstrap-3.3.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" >
<title>noticeList.jsp</title>
<script type="text/javascript">	
	$(document).ready(function() {
		// 폼 변수 
		var f = document.forms['frm_search'];		
		
		// 페이지 클릭 
		$('ul.pagination > li > a').click(function(e) {
			e.preventDefault(); // 이벤트 전파 방지
			var p = $(this).data('page');  // this.dataset.page
			f.curPage.value = p;
			f.submit();			
		}); // ul.pagination > li > a.click
		
		// 로우수 변경 버튼 클릭
		// select 박스의 값을 폼의 rowSizePerPage 에 설정 후 서브밋 호출  
		
		
		// 초기화 버튼 클릭
		$('#id_btn_reset').click(function() {
			// $(f).find('input[name=curPage]').val(1);
			// $('input[name=searchWord]').val('');			
			f.curPage.value = 1;
			f.searchWord.value = '';			 
			f.searchType.options[0].selected = true; 
			f.searchCategory.options[0].selected = true;
		}); // id_btn_reset.click
		
		
		// 검색 버튼 클릭 
		$('form[name=frm_search]').submit(function(e) {
			e.preventDefault();
			f.curPage.value = 1;
			f.submit();
		}); // form[name=frm_search].submit
		
		
		
	}); // document.ready 
</script>
</head>
<body>
<%-- 1 : ${NoticeSearchVO} <br> --%>
<%-- 2 : ${SearchVO} <br> --%>
<%@ include file="/WEB-INF/inc/top_menu.jsp" %>
<header class="masthead"
		style="background-image: url('<%=request.getContextPath()%>/startbootstrap/img/about-bg.jpg')">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto">
					<div class="site-heading">
						<h1>NEXTFLIX 공지사항</h1>
					</div>
				</div>
			</div>
		</div>
	</header>
<div class="container">
		<div class="page-header">
			<h3>공지사항</h3>			
		</div>
<div class="panel panel-default">
  <div class="panel-body">
    <form name="frm_search" action="noticeList.wow" method="get" class="form-horizontal">
        <input type="hidden" name="curPage" value="${searchVO.curPage}">
        <input type="hidden" name="rowSizePerPage" value="${searchVO.rowSizePerPage}">
          <div class="form-group">
            <label for="id_searchType" class="col-sm-2 control-label">검색</label>
            <div class="col-sm-2">
                <select id="id_searchType" name="searchType" class="form-control input-sm">			    		
                    <option value="T" ${searchVO.searchType eq 'T' ? 'selected="selected"' : ''}>제목</option>
                    <option value="W" ${searchVO.searchType eq 'W' ? 'selected="selected"' : ''}>작성자</option>
                    <option value="C" ${searchVO.searchType eq 'C' ? 'selected="selected"' : ''}>내용</option>
                </select>
            </div>
            <div class="col-sm-2">	
              <input type="text" name="searchWord" class="form-control input-sm" 
                     value="${searchVO.searchWord}" placeholder="검색어" >			      
            </div>
            <label for="id_searchCategory" class="col-sm-2 col-sm-offset-1 control-label">분류</label>
            <div class="col-sm-2">
                <select id="id_searchCategory" name="searchCategory" class="form-control input-sm" >
                    <option value="">-- 전체 --</option>
                    <c:forEach items="${cateList}" var="code">
                        <option value="${code.commCd}" 
                               ${code.commCd == searchVO.searchCategory ? 'selected="selected"' : ''} >
                              ${code.commNm}
                        </option>
                    </c:forEach>
                </select>
            </div>
          </div>
          <div class="form-group">
          	<div class="col-sm-2 col-sm-offset-8 text-right" >
          		<button type="button" id="id_btn_reset" class="btn btn-sm btn-default">
                    <i class="fa fa-sync fa-spin "></i>
                    &nbsp;&nbsp;초기화 
                </button>
          	</div>          	
            <div class="col-sm-1 text-right" >
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
    <div class="col-sm-6 form-inline" style="margin-bottom: 5px;" >
        전체 ${searchVO.totalRowCount} 건 조회
        <select id="id_rowSizePerPage" name="rowSizePerPage" class="form-control input-sm" >
            <option value="10" ${searchVO.rowSizePerPage == 10 ? 'selected="selected"' : '' } >10</option>
            <option value="20" ${searchVO.rowSizePerPage == 20 ? 'selected="selected"' : '' } >20</option>
            <option value="30" ${searchVO.rowSizePerPage == 30 ? 'selected="selected"' : '' } >30</option>
            <option value="50" ${searchVO.rowSizePerPage == 50 ? 'selected="selected"' : '' } >50</option>
        </select>
        <button type="button" id="id_btn_rowsize_change" class="btn btn-sm btn-default">
             <i class="fa fa-check"></i>
             &nbsp;&nbsp;선택 
         </button>
    </div>
    <div class="col-sm-2 col-sm-offset-4 text-right" style="margin-bottom: 5px;" >
        <a href="noticeForm.wow" class="btn btn-primary btn-sm"> 
        	<span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>&nbsp;새글쓰기
				</a>
    </div>
</div>
		
		<table class="grid table table-striped table-bordered table-hover">
			<colgroup>
				<col width="10%" />
				<col width="15%" />
				<col />
				<col width="10%" />
				<col width="15%" />
				<col width="10%" />
			</colgroup>
			<thead>
				<tr>
					<th>글번호</th>
					<th>분류</th>
					<th>제목</th>
					<th>작성자</th>
					<th>등록일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="notice" items="${noticeList}">
					<tr class="text-center">
						<td>${notice.noNum}</td>
						<td>${notice.noCategoryNm}</td>
						<td class="text-left">
							<a href="noticeView.wow?noNum=${notice.noNum}">${notice.noTitle} </a>
							<c:forEach var="f" items="${notice.attaches}">
								<a href="<c:url value='/attach/download/${f.atchNo}' />" 
								   target="_blank"
								   title="${f.atchOriginalName}">
									 <span class="glyphicon glyphicon-save" aria-hidden="true"></span>
								</a>
							</c:forEach>
						</td>
						<td>${notice.noWriter}</td>
						<td>${notice.noRegDate}</td>
						<td>${notice.noHit }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<nav class="text-center">
				<mytag:paging pagingVO="${searchVO}" linkPage="noticeList.wow" />
		</nav>
		
	</div>
</body>
</html>




