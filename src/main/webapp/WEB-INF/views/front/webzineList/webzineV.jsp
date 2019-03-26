<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<table>
	<tr>
		<th>제목</th>
		<td>${webzineView.TITLE }</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>${webzineView.CONTENT }</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${webzineView.USER_NAME }</td>
	</tr>
	<tr>
		<th>섬네일</th>
		<td><img alt="${webzineView.FILE_ORI }" src="${webzineView.FILE_PATH }${webzineView.FILE_STO }"></td>
	</tr>
	<tr>
		<th>등록일</th>
		<td>${webzineView.REG_DATE }</td>
	</tr>
	<tr>
		<th>조회수</th>
		<td>${webzineView.VIEW_CNT }</td>
	</tr>
</table>

<script type="text/javascript">
	
</script>