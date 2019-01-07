<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	</head>
	<body>
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
	</body>
</html>

