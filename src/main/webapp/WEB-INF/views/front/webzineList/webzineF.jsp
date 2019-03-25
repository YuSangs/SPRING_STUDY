<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="writeForm" action="/webzineList/writeProc.do" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<th>제목</th>
			<td><input type="text" id="title" name="title"/></td>
			<th>내용</th>
			<td><input type="text" id="content" name="content"/></td>
			<th>섬네일</th>
			<td><input type="file" id="thumb" name="thumb"/></td>
			<input type="hidden" id="writer_idx" name="writer_idx" value="${sessionScope.sessionVo.login_idx }"/>
		</tr>
	</table>
</form>
<input type="button" value="글작성" onclick="writeProc();"/>
<input type="button" value="목록으로"/>
<script type="text/javascript">
	function writeProc(){
		if($("#title").val() == ""){
			alert("제목을 입력해 주세요.");
			return;
		}
		if($("#content").val() == ""){
			alert("내용을 입력해 주세요.");
			return;
		}
		if($("#thumb").val() == ""){
			alert("섬네일을 입력해 주세요.");
			return;
		}
		
		$("#writeForm").submit();
	}
</script>