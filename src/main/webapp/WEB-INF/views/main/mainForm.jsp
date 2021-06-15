<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<script type="text/javascript">
var curPage =1;          // 현재 페이지 번호(1)
var rowSizePerPage =10;   // 한 페이지당 레코드 수(10)

// 배우목록을 구하는 함수 
function fn_actor_list(){	
	params = $('form[name=frm_actor_search]').serialize();
	$.ajax({
		type :"GET",
		url : '<c:url value="/main/getActorList" />',
		dataType : 'json',
		data : params,
		success : function(data) {
			//console.log("data", data);
			if(data.result){
				$area = $('#id_actorlist_modal  table  tbody');
				$area.empty();
				
				$.each(data.data, function(i, row) {
					if(row.actorNmEn==null){
						row.actorNmEn =' ';
					}
					str = ' <tr data-actor-cd="' +row.actorCd + '"><td>'+row.actorNm+'</td><td>'+row.actorNmEn+'</td></tr>    ';
					$area.append(str);
				}) // $.each
				
				fn_create_pagination( $('#id_actor_paging ul.pagination') , data.paging);
			}else{
				alert(data.msg);
			}			
		},
		error : function(req, st, err) {
			alert(req);
		}				
	}); // ajax		
}; // fn_actor_list

// 감독목록을 구하는 함수 
function fn_director_list(){	
	params = $('form[name=frm_director_search]').serialize(); 
	$.ajax({
		type :"GET",
		url : '<c:url value="/main/getDirectorList" />',
		dataType : 'json',
		data : params,
		success : function(data) {
			//console.log("data", data);
			if(data.result){
				$area = $('#id_directorlist_modal  table  tbody');
				$area.empty();
				$.each(data.data, function(i, row) {
					str = ' <tr data-people-cd="' +row.peopleCd + '"><td>'+row.peopleNm+'</td><td>'+row.peopleNmEn+'</td></tr>    ';
					$area.append(str);
				}) // $.each				
				
				fn_create_pagination( $('#id_director_paging ul.pagination') , data.paging);
			}else{
				alert(data.msg);
			}			
		},
		error : function(req, st, err) {
			alert(req);
		}				
	}); // ajax		
}; // fn_director_list

// 제작사목록을 구하는 함수 
function fn_company_list(){	
	params = $('form[name=frm_company_search]').serialize(); 
	$.ajax({
		type :"GET",
		url : '<c:url value="/main/getCompanyList" />',
		dataType : 'json',
		data : params,
		success : function(data) {
			//console.log("data", data);
			if(data.result){
				$area = $('#id_companylist_modal  table  tbody');
				$area.empty();
				$.each(data.data, function(i, row) {
					str = ' <tr data-company-cd="' +row.companyCd + '"><td>'+row.companyNm+'</td></tr>    ';
					$area.append(str);
				}) // $.each				
				
				fn_create_pagination( $('#id_company_paging ul.pagination') , data.paging);
			}else{
				alert(data.msg);
			}			
		},
		error : function(req, st, err) {
			alert(req);
		}				
	}); // ajax		
}; // fn_company_list

function fn_create_pagination( $area, paging ){
	var sb = "";
	
	if(paging.startPage > 1){
		sb += '<li>';
		sb += '<a href="?curPage=' + (paging.startPage - 1)  + '" data-page="' + (paging.startPage - 1) + '" ><span aria-hidden="true">&laquo;</span></a>';
		sb += '</li>';
	}
	for(i = paging.startPage; i <= paging.endPage; i++){
		sb += '<li><a href="?curPage=' + i + '" data-page="' + i + '">' + i + '</a></li>';			
	}	
	if(paging.endPage < paging.totalPageCount){
		sb += '<li>';
		sb += '<a href="?curPage=' + (paging.endPage + 1)  + '" data-page="' + (paging.endPage + 1) + '" ><span aria-hidden="true">&raquo;</span></a>';
		sb += '</li>';
	}
	
	//console.log('sb' , sb);
	$area.empty().append(sb);
}  // fn_create_pagination






$(document).ready(function() {
	
	// 각 검색목록의 서브밋 버튼 클릭시 처리 (공통으로 처리함 ) 
	$('form.cls_modal_search').submit(function(e) {
		e.preventDefault(); // 이벤트 전파방지  
		//console.log('원 서브밋 버튼 제어 ');
		// 각 form 의 action 을 구해서 , ajax 로 서버 호출
		// form 의 curPage=1 로 설정 
		// form 의 data-gubun 값에 따라서 함수 콜
		f.curPage.value = 1;
		f_d.curPage.value = 1;
		f_c.curPage.value = 1;
		gubun = $(this).closest('form').data('gubun');			
		//console.log('gubun=' , gubun);
		switch (gubun) {
		case 'actor':  fn_actor_list(); break;
		case 'director':  fn_director_list(); break;
		case 'company':  fn_company_list(); break;

		default: break;
		}
	});
	
	var f = document.forms['frm_actor_search'];
	
	// 페이지 클릭
	$('#id_actor_paging ul.pagination').on('click','li a', function(e){
		e.preventDefault(); //이벤트 전파 방지			
		var p = $(this).data('page'); //this.dataset.page
		f.curPage.value = p;
		fn_actor_list();
	});
	
	var f_d = document.forms['frm_director_search'];
	// 페이지 클릭
	$('#id_director_paging ul.pagination').on('click','li a', function(e){
		e.preventDefault(); //이벤트 전파 방지			
		var p = $(this).data('page'); //this.dataset.page
		f_d.curPage.value = p;
		fn_director_list();
	});
	
	var f_c = document.forms['frm_company_search'];
	// 페이지 클릭
	$('#id_company_paging ul.pagination').on('click','li a', function(e){
		e.preventDefault(); //이벤트 전파 방지			
		var p = $(this).data('page'); //this.dataset.page
		f_c.curPage.value = p;
		fn_company_list();
	});
	
	
	
	
	
	//actor
	$('button[id=btn_actor_add]').click(function(e){
		e.preventDefault();
		$modal = $("#id_actorlist_modal");
		$modal.modal();
		fn_actor_list();
	}); // b

	$('#id_actorlist_modal  table  tbody').on('dblclick','tr', function(e){
		alert('선택되었습니다.');
		$tr = $(this);
		//alert($tr.data('people-cd') + '=='  + $tr.find('td:first').text());
		// 넣고자하는 영역  선택 
		// 결과는 <span id='id_actor_2000089'>
		//          <input type="hidden" name="actorId" value="2000089">
		//          송강호 
		//          <button class="btn_actor_delete">삭제</button>
		//        </span>
		
		$('.actor_area').append('<span><input type="hidden" name="actorCd" value="'
				+$tr.data('actor-cd')+'">'+$tr.find('td:first').text()
				+'<button class="btn_actor_delete">삭제</button></span>')
	});	
	
	$('.actor_area').on('click','button[class="btn_actor_delete"]',function(e){
		e.preventDefault();
		res = confirm("삭제하시겠습니까?");
		if(res){
			$div = $(this).closest('div.actor_area span');
			$div.remove();
		}
	}); // 
	
	
	
	//director
	$('button[id=btn_director_add]').click(function(e){
		e.preventDefault();
		$modal = $("#id_directorlist_modal");
		$modal.modal();
		fn_director_list();
	}); // 

	$('#id_directorlist_modal  table  tbody').on('dblclick','tr', function(e){
		alert('선택되었습니다.');
		$tr = $(this);
		$('.director_area').append('<span><input type="hidden" name="peopleCd" value="'
				+$tr.data('people-cd')+'">'+$tr.find('td:first').text()
				+'<button class="btn_director_delete">삭제</button></span>')
	});	
	
	$('.director_area').on('click','button[class="btn_director_delete"]',function(e){
		e.preventDefault();
		res = confirm("삭제하시겠습니까?");
		if(res){
			$div = $(this).closest('div.director_area span');
			$div.remove();
		}
	}); // 
	
	
	//company
	$('button[id=btn_company_add]').click(function(e){
		e.preventDefault();
		$modal = $("#id_companylist_modal");
		$modal.modal();
		fn_company_list();
	}); // 

	$('#id_companylist_modal  table  tbody').on('dblclick','tr', function(e){
		alert('선택되었습니다.');
		$tr = $(this);
		$('.company_area').append('<span><input type="hidden" name="companyCd" value="'
				+$tr.data('company-cd')+'">'+$tr.find('td:first').text()
				+'<button class="btn_company_delete">삭제</button></span>')
	});	
	
	$('.company_area').on('click','button[class="btn_company_delete"]',function(e){
		e.preventDefault();
		res = confirm("삭제하시겠습니까?");
		if(res){
			$div = $(this).closest('div.company_area span');
			$div.remove();
		}
	}); // 
	
	
	
	
	
	
});
</script>
</head>
<body>
	<%@include file="/WEB-INF/inc/top_menu.jsp"%>
	<div class="container">
		<div class="page-header">
			<h3>영화 등록</h3>
		</div>
		<div class="row">
			<form name="frm_movie" action="mainRegist.wow" method="post" enctype="multipart/form-data">
				<table class="table table-striped table-bordered">
					<tbody>
						<tr>
							<th>영화명</th>
							<td>
								<input type="text" name="movieNm" class="form-control input-sm">
								</td>
						</tr>
						<tr>
							<th>영화포스터</th>
							<td><input type="file" name="moviePoster" class="form-control input-sm">
								</td>
						</tr>
						<tr>
							<th>상영시간</th>
							<td><input type="number" name="showTm" class="form-control input-sm">
								</td>
						</tr>
						<tr>
							<th>개봉일자</th>
							<td><input type="date" name="openDt" class="form-control input-sm">
								</td>
						</tr>
						<tr>
							<th>시놉시스</th>
							<td><textarea rows="" cols="5" name="movieContent" class="form-control input-sm"></textarea>
								</td>
						</tr>
						<tr>
							<th>출연배우<button type="button" id="btn_actor_add">추가</button></th>
							<td>
								<div class="actor_area">
								</div>
							</td>
						</tr>
						<tr>
							<th>감독<button type="button" id="btn_director_add">추가</button></th>
							<td><div class="director_area">
								</div>
								</td>
						</tr>
						<tr>
							<th>제작<button type="button" id="btn_company_add">추가</button></th>
							<td><div class="company_area">
								</div>
								</td>
						</tr>
						<tr>
							<th>장르</th>
							<td>
									<c:forEach var="code" items="${cateList}">
										<input type="checkbox" name="commCd" value="${code.commCd}">${code.commNm}
									</c:forEach>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="pull-left">
									<a href="mainList.wow" class="btn btn-sm btn-default">목록으로</a>
								</div>
								<div class="pull-right">
									<button type="submit" class="btn btn-sm btn-primary">저장하기</button>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<!-- // START : 배우검색 모달창  -->
	<div class="modal " id="id_actorlist_modal" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h4 class="modal-title">배우</h4>
				</div>
				<div class="modal-body">
					<form action="/next/" name="frm_actor_search" class="cls_modal_search"  data-gubun="actor" >
						<input type="hidden" name="curPage" value="1">
						<input type="text" name="searchWord" value="">
						<button type="submit" class="btn_search">검색</button>						
					</form>
					<table class="table table-strip">
						<thead>
							<tr>
								<th>배우명</th>
								<th>영문명</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<div id="id_actor_paging">
						<ul class="pagination">
						
						</ul>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
	<!-- // END : 배우검색 모달창  -->
	
	<!-- // START : 감독검색 모달창  -->	
	<div class="modal fade" id="id_directorlist_modal" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h4 class="modal-title">감독</h4>
				</div>
				<div class="modal-body">
					<form action="/next/" name="frm_director_search" class="cls_modal_search"  data-gubun="director" >
						<input type="hidden" name="curPage" value="1">
						<input type="text" name="searchWord" value="">
						<button type="submit" class="btn_search">검색</button>						
					</form>
					<table class="table table-strip">
						<thead>
							<tr>
								<th>감독명</th>
								<th>영문명</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<div id="id_director_paging">
						<ul class="pagination">

						</ul>
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="id_companylist_modal" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h4 class="modal-title">회사</h4>
				</div>
				<div class="modal-body">
					<form action="/next/" name="frm_company_search" class="cls_modal_search"  data-gubun="company" >
						<input type="hidden" name="curPage" value="1">
						<input type="text" name="searchWord" value="">
						<button type="submit" class="btn_search">검색</button>						
					</form>
					<table class="table table-strip">
						<thead>
							<tr>
								<th>회사명</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<div id="id_company_paging">
						<ul class="pagination">

						</ul>
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
	<hr>
	<!-- Footer -->
	<footer>
		<%@include file="/WEB-INF/inc/footer.jsp"%>
	</footer>
</body>

</html>

