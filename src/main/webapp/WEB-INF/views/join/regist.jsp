<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<title>${resultMessage.title}</title>
</head>
<body>
<div class="container">
		<div class="row col-md-8 col-md-offset-2">
			<div class="page-header">
				<h3>${resultMessage.title}</h3>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<p>${resultMessage.message}</p>
				</div>
				<div class="panel-body">
					<a href="${pageContext.request.contextPath}/"
						class="btn btn-primary"> <span
						class="glyphicon glyphicon-home" aria-hidden="true"></span>
						&nbsp;Home
					</a>
					
						<a href="#" onclick="history.back()" class="btn btn-default">
							<span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>
							&nbsp;뒤로가기
						</a> 
						<c:if test="${not empty resultMessage.url}">
							<a href="${pageContext.request.contextPath}${resultMessage.url}"
								class="btn btn-warning"> <span class="glyphicon glyphicon-new-window aria-hidden="true"></span>
								&nbsp;${resultMessage.urlTitle}
							</a>
						</c:if>
					</div>
				</div>
			</div>
		</div>
</body>
</html>