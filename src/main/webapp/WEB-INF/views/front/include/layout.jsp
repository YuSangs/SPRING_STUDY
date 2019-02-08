<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
		<title><tiles:getAsString name="title"/></title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	</head>
	<body>
		<div>
			<tiles:insertAttribute name="header" />
		</div>
		<div>
			<tiles:insertAttribute name="content" />
		</div>
		<div>
			<tiles:insertAttribute name="footer" />
		</div>
	</body>
</html>