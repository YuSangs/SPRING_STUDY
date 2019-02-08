<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includeInc.jsp" %>

<form action="/list/writeProc.do" id="writeForm" method="post" enctype="multipart/form-data">
	<table class="listBox">
		<tr>
			<th>제목</th>
			<td><input type="text" name="title"/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><input type="text" name="content"/></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td><input type="file" name="file"/></td>
		</tr>
	</table>
</form>
<button onclick="writeProc();">글작성</button>
<script type="text/javascript">
	function writeProc(){
		if($("input[name=title]").val() == ""){
			alert("제목을 입력해주세요.");
			return;
		}
		
		if($("input[name=content]").val() == ""){
			alert("내용을 입력해주세요.");
			return;
		}
		
		$("#writeForm").submit();
	}
</script>