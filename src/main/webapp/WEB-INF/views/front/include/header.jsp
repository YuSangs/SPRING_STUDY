<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/common/includeInc.jsp" %>
<div>
	<c:choose>
		<c:when test="${empty sessionScope.sessionVo}">
			<a href="/member/loginForm.do">로그인</a>
			<a href="/member/registForm.do">회원가입</a>
		</c:when>
		<c:otherwise>
			${sessionScope.sessionVo.login_id }님 안녕하세요
			<a href="/member/logout.do">로그아웃</a>
		</c:otherwise>
	</c:choose>
</div>
