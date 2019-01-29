<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includeInc.jsp" %>

<table class="listBox">
	<thead>
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>내용</td>
			<td>게시일자</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list }" var="list" varStatus="status">
			<tr>
				<td>${(listPager.listCnt - status.index) - ((listPager.curPage - 1) * 10) }</td>
				<td>${list.TITLE }</td>
				<td>${list.CONTENT }</td>
				<td>${list.REGDATE }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<form id="commonForm" method="post">
	<input type="hidden" id="curPage" name="curPage"/> <!-- 이동할 페이지 번호 -->
</form>

<!-- 페이징 처리 -->
<a href="javascript:listPaging(1)">완전 처음 페이지</a>
<a href="javascript:listPaging('${listPager.prevBlock }')">이전 블록 첫 페이지</a>
<a href="javascript:listPaging('${listPager.prevPage }')">이전 페이지</a>
<c:forEach var="i" begin="${listPager.blockBegin }" end="${listPager.blockEnd }">
	<c:choose>
		<c:when test="${i == listPager.curPage }">
			<span style="color:red">${i }</span>
		</c:when>
		<c:otherwise>
			<a href="javascript:listPaging('${i }')">${i }</a>
		</c:otherwise>
	</c:choose>
</c:forEach>
<a href="javascript:listPaging('${listPager.nextPage }')">다음 페이지</a>
<a href="javascript:listPaging('${listPager.nextBlock }')">다음 블록 첫 페이지</a>
<a href="javascript:listPaging('${listPager.pageCnt }')">완전 끝 페이지</a>

<script type="text/javascript">
	function listPaging(pageNum){
		$("#curPage").val(pageNum);
		$("#commonForm").prop("action", "/list/list.do");
		$("#commonForm").submit();
	}
</script>