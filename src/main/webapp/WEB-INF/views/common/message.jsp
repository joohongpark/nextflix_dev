<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<link href="<%=request.getContextPath()%>/bootstrap-3.3.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" >
<title>${messageVO.title}</title>
</head>
<body>
	<div class="container">
		<div class="row col-md-8 col-md-offset-2">
			<div class="page-header">
				<h3>${messageVO.title}</h3>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<p>${messageVO.message}</p>
				</div>
				<div class="panel-body">
					<a href="${pageContext.request.contextPath}/"
						class="btn btn-default"> <span
						class="glyphicon glyphicon-home" aria-hidden="true"></span>
						&nbsp;메인화면으로
					</a>
							&nbsp;&nbsp;
<%-- 						<c:if test='${sessionScope.USER_INFO.userRole=="A"}' > --%>
							<a href="<c:url value='${messageVO.url}' />"
								class="btn btn-success">
							 <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
								&nbsp;${messageVO.urlTitle}
							</a>
<%-- 						</c:if> --%>
					</div>
				</div>
			</div>
		</div>
</body>
</html>