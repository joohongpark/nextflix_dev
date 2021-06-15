<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
</head>
<style>
	div#main {
 		display: flex; 
 		justify-content: center; 
		align-items: center; 
	}
</style>
<body>
	<%@include file="/WEB-INF/inc/top_menu.jsp"%>
	<!-- Main Content -->
	<div class="container">
		<div class="row" id="main">
			<div class="col-lg-8 col-md-10 mx-auto">
				<div class="post-preview">
					<h2 class="post-title">코미디</h2>
					<hr>
					<ul>
						<c:forEach var="movie" items="${comedeList}">
							<li
								style="float: left; list-style: none; position: relative; margin-right: 3px;">
								<p>
									<a href="main/mainView.wow?movieCd=${movie.movieCd}">
										<img style="width: 130px; height: 186px;" alt="${movie.movieNm}" src='<c:url value="/images/movie/thumb/${movie.movieImg}" />'>
									</a>
								</p>
								<p style="font-size: 10px">${movie.movieNm}</p>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="col-lg-8 col-md-10 mx-auto">
				<div class="post-preview">
					<h2 class="post-title">액션</h2>
					<hr>
					<ul>
						<c:forEach var="movie" items="${actionList}">
							<li
								style="float: left; list-style: none; position: relative; margin-right: 3px;">
								<p>
									<a href="main/mainView.wow?movieCd=${movie.movieCd}">
										<img style="width: 130px; height: 186px;" alt="${movie.movieNm}" src='<c:url value="/images/movie/thumb/${movie.movieImg}" />'>
									</a>
								</p>
								<p style="font-size: 10px">${movie.movieNm}</p>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="col-lg-8 col-md-10 mx-auto">
				<div class="post-preview">
					<h2 class="post-title">멜로/로맨스</h2>
					<hr>
					<ul>
						<c:forEach var="movie" items="${romanList}">
							<li
								style="float: left; list-style: none; position: relative; margin-right: 3px;">
								<p>
									
									<a href="main/mainView.wow?movieCd=${movie.movieCd}">
										<img style="width: 130px; height: 186px;" alt="${movie.movieNm}" src='<c:url value="/images/movie/thumb/${movie.movieImg}" />'>
									</a>
								</p>
								<p style="font-size: 10px">${movie.movieNm}</p>
							</li>
						</c:forEach>
					</ul>
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

