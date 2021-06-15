<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<%-- <link href="<%=CTX_PATH%>/bootstrap-3.3.2/css/bootstrap.min.css" rel="stylesheet"> --%>
<style type="text/css">
	dl{
	display: block;
	margin-block-start:1em;
	margin-block-end:1em;
	margin-inline-start:0px;
	margin-inline-end:0px;
	}
	dt{
	display : block;
	float: left; 
	width: 10px;
	margin-right: 10px;
	}
	dd{
	display : block;
/* 	margin-inline-start : 40px; */
	margin-inline-start : 0px;
	}
	</style>

<script type="text/javascript">
<!-- START : 댓글 처리 스크립트   -->
var curPage = 1;
var rowSizePerPage = 10 ;
var parentNo = ${movie.movieCd};

// 댓글목록을 구하는 함수 
function fn_reply_list(){	
	params = {"curPage":curPage, "rowSizePerPage":rowSizePerPage 
			      , "reCategory" : "MOVIE" , "reParentNo": parentNo } 
	$.ajax({
		type :"POST",
		url : '<c:url value="/reply/replyList" />',
		dataType : 'json',
		data : params,
		success : function(data) {
			console.log("data", data);
			if(data.result){
				$area = $('#id_reply_list_area');
				$.each(data.data, function(i, row) {
					str = '';
					str += '<div class="row"  data-re-no="'+ row.reNo +'" >';
					str += '	<div class="col-sm-2 text-right" >' + row.reMemName +'</div>';
					str += '	<div class="col-sm-6"><pre>' + row.reContent +'</pre></div>';
					str += '	<div class="col-sm-2" >' + row.reRegDate +'</div>';
					str += '	<div class="col-sm-2">';
					if(row.reMemId == '${sessionScope.USER_INFO.userId}' || ${sessionScope.USER_INFO.userRole == "A" } ){
						str += '		<button name="btn_reply_edit" type="button" class=" btn btn-sm btn-info">수정</button>';
						str += '		<button name="btn_reply_delete" type="button" class="btn btn-sm btn-danger" >삭제</button>';
					}
					str += '	</div>';
					str += '</div>';
					$area.append(str);
				}) // $.each				
				
				curPage += 1;
				if(rowSizePerPage > data.count){
					$('#btn_reply_list_more').hide();  // 더보기 버튼 숨기기 
				}
			}else{
				alert(data.msg);
			}			
		},
		error : function(req, st, err) {
			alert(req);
		}				
	}); // ajax		
}; // fn_reply_list

	$(document).ready(function() {
		// 수정버튼 클릭
		$('#id_reply_list_area').on('click','button[name=btn_reply_edit]',function(e){
			e.preventDefault();
			
			$div = $(this).closest('div.row');
			$modal = $("#id_reply_edit_modal");
			reNo = $div.data('re-no');
			reContent = $div.find('div > pre').text();
			console.log(reNo);
			console.log(reContent);
			$('input[name=reNo]', $modal).val(reNo);
			$('textarea[name=reContent]', $modal).val(reContent);			
			$modal.modal('show');			
			// 입력영역 (textarea) 추가 , 기존 내용을 복사 , 기존 내용은 숨기고 
			// (수정)저장버튼(btn_reply_modify)을 보이게하시면 됩니다.			
		}); // btn_reply_edit.click
		
		// 모달창의 (수정)저장버튼 btn_reply_modify 클릭 
		$("#btn_reply_modify").click(function(e) {
			e.preventDefault(); 
 			f = document.forms.frm_reply_edit;
			params = $(f).serialize();
			$.ajax({
				type :"POST",
				url : '<c:url value="/reply/replyModify" />',
				dataType : 'json',
				data : params,
				success : function(data) {
					console.log("data", data);
					if(data.result){
						$modal = $("#id_reply_edit_modal");			
						console.log(f.reNo.value , f.reContent.value );
						$('#id_reply_list_area div.row[data-re-no='+ f.reNo.value +'] > div > pre').text(f.reContent.value) 
						$modal.modal('hide');
					}else {
						alert(data.msg);	
				}
			},
				error : function(req, st, err) {
					console.log("req", req);
					if (req.status == '401'){
						location.href = "<c:url value='/login/login.wow'/>"
					}else{
						alert("에러 발생");
					}				
				}
			}); // ajax
		}); // $("#btn_reply_modify").click
		
		// 삭제버튼 클릭
		$('#id_reply_list_area').on('click','button[name=btn_reply_delete]',function(e){
			e.preventDefault();
			res = confirm("삭제하시겠습니까?");
			if(res){
				$div = $(this).closest('div.row');
				params = "reNo="+$div.data('re-no');
				$.ajax({
					type :"POST",
					url : '<c:url value="/reply/replyDelete" />',
					dataType : 'json',
					data : params,
					success : function(data) {
						console.log("data", data);
						if(data.result){
							$div.remove();
						}else {
							alert(data.msg);	
						}
					},
					error : function(req, st, err) {
						console.log("req", req);
						if (req.status == '401'){
							location.href = "<c:url value='/login/login.wow'/>"
						}else{
							alert("에러 발생");
						}
					}
				}); // ajax
				
			}
			
		}); // btn_reply_delete.click
		
		
		// 더보기 버튼 클릭 
		$('#btn_reply_list_more').click(function(e) {
			fn_reply_list();
		}); // #btn_reply_list_more.click
		
		// 등록버튼 클릭 
		$("#btn_reply_regist").click(function(e) {
			e.preventDefault(); 
			f = document.forms.frm_reply
			
			params = $(f).serialize();
			//alert(params);
			$.ajax({
				type :"POST",
				url : '<c:url value="/reply/replyRegist" />',
				dataType : 'json',
				data : params,
				success : function(data) {
					console.log("data", data);
					if(data.result){
						// 등록이된 이후 화면에 추가 또는 fn_reply_list (curPage = 1) 처리
						$("#id_reply_list_area").empty();						
						document.forms.frm_reply.reContent.value = "";
						$('#btn_reply_list_more').show();
						curPage = 1;
						fn_reply_list();
					}else {
						alert(data.msg);	
					}
				},
				error : function(req, st, err) {
					console.log("req", req);
					if (req.status == '401'){
						location.href = "<c:url value='/login/login.wow'/>"
						//location.href = "${pageContext.request.contextPath}/login/login.wow"
					}else{
						alert("에러 발생");
					}
				}
			}); // ajax
		});
		
		// 찜하기 버튼 클릭시 스크립트
		$("#btn_keep_regist").click(function (e) {
			e.preventDefault();
			console.log("dd");
			$.ajax({
				type :"POST",
				url : '<c:url value="/keep/keepRegist.wow" />',
				dataType : 'json',
				data : {'keMovieCd': '${movie.movieCd}' },
				success : function(data) {
					console.log("data", data);
					if(data.result){
						alert(data.msg);
					}else {
						alert(data.msg);
					}
				}
				,error : function(req, st, err) {
					console.log("req", req);
					if (req.status == '401'){
						location.href = "<c:url value='/login/login.wow'/>"
					}else{
						alert("에러 발생");
					}
				}
			});
		}) // $("#btn_keep_regist").click
		
		
		
		// 초기화 함수 호출 		
		fn_reply_list();
	});  // ready

	
	function f_reg() {
		//this.preventDefault();
		console.log("dd");
		$.ajax({
			type :"POST",
			url : '<c:url value="/keep/keepRegist" />',
			dataType : 'json',
			data : params,
			success : function(data) {
				console.log("data", data);
				if(data.result){
					
					curPage = 1;
				}else {
					alert(data.msg);
				}
			}
			,error : function(req, st, err) {
				console.log("req", req);
				if (req.status == '401'){
					location.href = "<c:url value='/login/login'/>"
				}else{
					alert("에러 발생");
				}
			}
		});
	}
</script>
</head>
<body>
<%-- ${movie} --%>
<c:if test="${empty movie}">
	<p>영화 상세 조회에 실패했습니다.</p>
</c:if>
<c:if test="${not empty movie}">
<!-- Page Header -->
	<div class="jumbotron">
		<div class="container">
			<c:if test='${sessionScope.USER_INFO.userRole=="A"}'>
						<a href="mainEdit.wow?movieCd=${movie.movieCd}"
							class="btn btn-success btn-sm"> <span
								class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
								&nbsp;영화정보수정
						</a>
				</c:if>
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto">
					<div class="site-heading">
						<div style="float: left; position: relative; margin-right: 20px;">
							<a href="/movie/images/movie/${movie.movieImg}" target="_blank">
							<img style="width: 200px; height: 230px;" alt="" src='<c:url value="/images/movie/${movie.movieImg}" />'>
							</a>
						</div>
						<div class="alx">
							<dl>
								<!-- <dt>영화명</dt> -->
								<dd>영화명 : ${movie.movieNm}</dd>
								<!-- <dt>상영시간</dt> -->
								<dd>상영시간 : ${movie.showTm}분</dd>
								<!-- <dt>개봉일자</dt> -->
								<dd>개봉일자 : ${movie.openDt}</dd>
							</dl>
							<dl>
								<c:forEach items="${actors}" var="vo">
									${vo.actorNm} (${vo.cast})
								</c:forEach>							
							</dl>
							<dl>
								<c:forEach items="${directors}" var="vo">
									감독 : ${vo.peopleNm}
								</c:forEach>							
							</dl>
							<dl>
								<c:forEach items="${companys}" var="vo">
									${vo.companyPartNm} : ${vo.companyNm} <br>
								</c:forEach>							
							</dl>
							<dl>
								<c:forEach items="${genres}" var="vo">
									 # ${vo.genreNm}
								</c:forEach>							
							</dl>
							<dl>
								<dd>
								<a id="btn_keep_regist" href='#'  class="btn btn-danger btn-sm"><i class="fa fa-heart"></i>&nbsp;찜하기</a>
								</dd>
							</dl>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/inc/top_menu.jsp"%>
	<!-- Main Content -->
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto">
					<div class="post-preview">
						<h5>시놉시스</h5>
						<p>${movie.movieContent}</p>
					</div>
					<hr>
					<div class="post-preview">
						<h5>추천</h5>
						<c:forEach items="${movieList}" var="vo">
							<li style="float: left; list-style: none; position: relative; margin-right: 3px;">
								<p>
									<a href="mainView.wow?movieCd=${vo.movieCd}">
										<img style="width: 130px; height: 180px;" alt="${vo.movieNm}" src='<c:url value="/images/movie/thumb/${vo.movieImg}" />'>
									</a>
								</p>
								<p style="font-size: 10px">${vo.movieNm}</p>
							</li>	 
						</c:forEach>
					</div>
					<hr>
				</div>
			</div>
		</div>
			<!-- START : 댓글 관련 영역-->
	<div class="container">
		<!-- START : 댓글 입력 영역-->
		<div class="panel panel-default">
			<div class="panel-body form-horizontal">
				<form name="frm_reply" action="<c:url value='/reply/replyRegist' />"
					method="post" onclick="return false;">
					<input type="hidden" name="reParentNo" value="${movie.movieCd}">
					<input type="hidden" name="reCategory" value="MOVIE">
					<div class="form-group">
						<label class="col-sm-2 control-label">댓글</label>
						<div class="col-sm-8">
							<textarea rows="2" name="reContent" class="form-control"></textarea>
						</div>
						<div class="col-sm-2">
							<button id="btn_reply_regist" type="button"
								class="btn btn-sm btn-info">등록</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- END : 댓글 입력 영역 -->
		<!-- START : 댓글 목록 영역-->
		<div id="id_reply_list_area"></div>
		<!-- END : 댓글 목록 영역-->
		<!-- START : 더보기 버튼 영역-->
		<div class="row text-center" id="id_reply_list_more">
			<button id="btn_reply_list_more">
				<span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span>
				더보기
			</button>
		</div>
		<!-- END : 더보기 버튼 영역-->
		<!-- START : 댓글 수정용 Modal창-->
		<div class="modal fade" id="id_reply_edit_modal" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<form name="frm_reply_edit"
						action="<c:url value='/reply/replyModify' />" method="post"
						onclick="return false;">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">×</button>
							<h4 class="modal-title">댓글수정</h4>
						</div>
						<div class="modal-body">
							<input type="hidden" name="reNo" value="">
							<textarea rows="3" name="reContent" class="form-control"></textarea>
						</div>
						<div class="modal-footer">
							<button id="btn_reply_modify" type="button"
								class="btn btn-sm btn-info">저장</button>
							<button type="button" class="btn btn-default btn-sm"
								data-dismiss="modal">닫기</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- END : 댓글 수정용 Modal 창-->
	</div>
	<!-- END : 댓글 관련 영역-->
	</c:if>
	<hr>

	<!-- Footer -->
	<footer>
		<%@include file="/WEB-INF/inc/footer.jsp"%>
	</footer>

</body>
</html>