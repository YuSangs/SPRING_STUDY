<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h3>메인 페이지</h3>

<ul>
	<li><a href="/webzineList/listProc.do">웹진형 게시판</a></li>
	<li></li>
	<li></li>
</ul>

<script type="text/javascript">
	var blockTF = ${blockTF};
	if(blockTF == true){
		alert("잘못된 접근입니다.");
	}
</script>