<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includeInc.jsp" %>
<c:forEach items="${list }" var="list">
	${list.TITLE }<br/>
	${list.CONTENT }	
</c:forEach>