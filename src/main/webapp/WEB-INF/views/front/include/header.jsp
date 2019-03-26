<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 헤더 -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="#">유생 개인 공부</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<c:choose>
					<c:when test="${empty sessionScope.sessionVo}">
						<li class="nav-item">
							<a class="nav-link" href="/member/loginForm.do">로그인</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/member/registForm.do">회원가입</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="nav-item">
							${sessionScope.sessionVo.login_id }님 안녕하세요
							<a class="nav-link" href="/member/logoutProc.do">로그아웃</a>
						</li>
					</c:otherwise>
				</c:choose>
				<li class="nav-item">
					<a class="nav-link" href="/webzineList/listProc.do">웹진형 게시판</a>
				</li>
			</ul>
		</div>
	</div>
</nav>