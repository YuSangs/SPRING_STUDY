<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includeInc.jsp" %>
<table>
	<tr>
		<td>제목</td>
		<td>콘텐츠</td>
	</tr>
	<c:forEach items="${mainList }" var="list">
		<tr>
			<td>${list.title }</td>
			<td>${list.content }</td>
		</tr>
	</c:forEach>	
</table>

