<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includeInc.jsp" %>
<div>
	<c:choose>
		<c:when test="${empty sessionScope.sessionVo}">
			<a href="/member/loginForm.do">로그인</a>
			<a href="/member/registForm.do">회원가입</a>
		</c:when>
		<c:otherwise>
			${sessionScope.sessionVo.login_id }님 안녕하세요
		</c:otherwise>
	</c:choose>
</div>
