<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<!-- 부트스트랩 -->
<link href="<%=request.getContextPath()%>/bootstrap-3.3.2/css/bootstrap.min.css" rel="stylesheet">
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" > --%>
<title>noticeView.jsp</title>

</head>
<body>
<%@ include file="/WEB-INF/inc/top_menu.jsp" %>
	<div class="container">
		<h3>공지사항</h3>
		<c:if test="${empty notice}">
			<p>조회에 실패했습니다.</p>
		</c:if>
		<c:if test="${not empty notice}">
			<table class="table table-striped table-bordered">
				<tbody>
					<tr>
						<th>글번호</th>
						<td>${notice.noNum}</td>
					</tr>
					<tr>
						<th>글제목</th>
						<td>${notice.noTitle}</td>
					</tr>
					<tr>
						<th>글분류</th>
						<td>${notice.noCategoryNm}</td>
					</tr>
					<c:if test='${sessionScope.USER_INFO.userRole=="A"}'>
<!-- 					<tr> -->
<!-- 						<th>작성자명</th> -->
<%-- 						<td>${notice.noWriter }</td> --%>
<!-- 					</tr> -->
					<tr>
						<th>비밀번호</th>
						<td>${notice.noPass}</td>
					</tr>
					</c:if>
					<tr>
						<th>첨부파일</th>
						<td><c:forEach var="f" items="${notice.attaches}" varStatus="st">
								<div>
									# 파일 ${st.count} 
									<a href="<c:url value='/attach/download/${f.atchNo}' />"target="_blank">
									 <span class="glyphicon glyphicon-save" aria-hidden="true"></span> 
									 ${f.atchOriginalName}
									</a> 
									Size : ${f.atchFancySize} 
									Down : ${f.atchDownHit}
								</div>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<th style="width: 118px;">내용</th>
						<td style="height: 218px;">${notice.noContent}</td>
					</tr>
					<tr>
						<th>등록자 IP</th>
						<td>${notice.noIp}</td>
					</tr>
					<tr>
						<th>조회수</th>
						<td>${notice.noHit}</td>
					</tr>
					<tr>
						<th>등록일자</th>
						<td>${notice.noRegDate}</td>
					</tr>
					<tr>
						<th abbr="" data-milkis="사랑해요">삭제여부</th>
						<td>${notice.noDelYn}</td>
					</tr>
					<tr>
						<td colspan="2">
							<a href="noticeList.wow" class="btn btn-default btn-sm"> 
								<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
								&nbsp;&nbsp;목록
							</a> 
							<c:if test='${sessionScope.USER_INFO.userRole=="A"}'>
							<a href="noticeEdit.wow?noNum=${notice.noNum}" class="btn btn-success btn-sm"> 
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
								&nbsp;&nbsp;수정
						  </a>
						  </c:if>
					</tr>
				</tbody>
			</table>
		</c:if>
	</div>
	
<!-- START : 댓글 처리 스크립트   -->
<script type="text/javascript">
var curPage = 1;
var rowSizePerPage = 10;
var parentNo = ${notice.noNum};

// 댓글목록을 구하는 함수 
function fn_reply_list(){	
	params = {"curPage":curPage, "rowSizePerPage":rowSizePerPage 
			      , "reCategory" : "NOTICE" , "reParentNo": parentNo } 
	$.ajax({
		type :"POST",
		url : '<c:url value="/reply/replyList" />',
		dataType : 'json',
		data : params,
		success : function(data, st, xhr) {
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
		
		// 삭제버튼 클릭
		$("#id_reply_list_area").on("click", "button[name=btn_reply_delete]", function() {
			$btn = $(this);
			res = confirm("글을 삭제하시겠습니까?");
			if(res){
				$div = $btn.closest("div.row");
				reNo = $div.data("re-no");
				params = "reNo=" + reNo;// {"reNo": reNo }
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
						console.log("st", st);
						console.log("err", err);
						if (req.status == '401') {
							location.href = '<c:url value="/login/login.wow"/>'
						} else {
						alert("에러 발생");					
						}
					}
				}); // ajax
			}			
		}); // btn_reply_delete.click 
		
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
			$modal.modal();			
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
					if (req.status == '401') {
						location.href = '<c:url value="/login/login.wow" />'
					} else {
					alert("에러 발생");					
					}
				}
			}); // ajax
		});
		
		// 더보기 버튼 클릭 
		$('#btn_reply_list_more').click(function(e) {
			fn_reply_list();
		}); // #btn_reply_list_more.click
		
		// 등록버튼 클릭 
		$("#btn_reply_regist").click(function(e) {
// 			e.preventDefault(); 
			f = document.forms.frm_reply
			params = $(f).serialize();  // "key1=val1&key2=val2&.." {"k1" : "v1", "k2" : ""}
			console.log(params);
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
				if (req.status == '401') {
				  location.href = '<c:url value="/login/login.wow" />' 
				} else {
          		alert("에러 발생");
				}
			}
		}); // ajax
	});
		
		// 초기화 함수 호출 		
		fn_reply_list();
	});  // ready
</script>
<!-- END : 댓글 처리 스크립트   -->

	<!-- START : 댓글 영역	-->
	<div class="container">
		<!-- START : 댓글 입력 영역 -->
		<div class="panel panel-default">
			<div class="panel-body form-horizontal">
				<form name="frm_reply" action="<c:url value='/reply/replyRegist' />"
					    method="post" onclick="return false;">
					<input type="hidden" name="reParentNo" value="${notice.noNum}">
					<input type="hidden" name="reCategory" value="NOTICE">
					<div class="form-group">
						<label class="col-sm-2 control-label">댓글</label>
						<div class="col-sm-8">
							<textarea rows="2" name="reContent" class="form-control"></textarea>
						</div>
						<div class="col-sm-2">
							<button id="btn_reply_regist" type="button" class="btn btn-sm btn-info">
							  등록
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- END : 댓글 입력 영역 -->

		<!-- START : 댓글 목록 영역 -->
		<div id="id_reply_list_area">
		
		</div>
		<!-- END : 댓글 목록 영역 -->
		
		<!-- START : 더보기 버튼 영역 -->
		<div class="row text-center" id="id_reply_list_more">
			<button id="btn_reply_list_more">
				<span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span>
				더보기
			</button>
		</div>
		<!-- END : 더보기 버튼 영역 -->
		
		<!-- START : 댓글 수정용 Modal창 -->
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
							<button id="btn_reply_modify" type="button" class="btn btn-sm btn-info">
							  저장
							</button>
							<button type="button" class="btn btn-default btn-sm" data-dismiss="modal">
							  닫기
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- END : 댓글 수정용 Modal 창 -->
	</div>
	<!-- END : 댓글 관련 영역 -->
	
	<hr>
	<!-- Footer -->
	<footer>
		<%@include file="/WEB-INF/inc/footer.jsp"%>
	</footer>
</body>
</html>






