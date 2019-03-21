<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 본문 -->
<table>
	<thead>
		<tr>
			<th>번호</th>
			<th>섬네일</th>
			<th>제목</th>
			<th>내용</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>등록일</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${emptyYN eq 'Y' }">
				작성된 글이 없습니다.
			</c:when>
			<c:otherwise>
				<c:forEach items="${webzineList }" var="list" varStatus="status">
					<tr>
						<td>
							${(listPager.listCnt - status.index) - ((listPager.curPage - 1) * 10 )}
							<input type="hidden" value="${list.IDX }"/>
						</td>
						<td><img alt="섬네일" src="${list.FILE_PATH }/${list.FILE_STO}"/></td>
						<td>${list.TITLE }</td>
						<td>${list.CONTENT }</td>
						<td>${list.USER_NAME }</td>
						<td>${list.VIEW_CNT }</td>
						<td>${list.REG_DATE }</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>

<input type="button" value="글작성" onclick="webzineForm();"/>

<!-- 공통 폼태그 -->
<form id="commonForm" method="post">
	<input type="hidden" id="curPage" name="curPage"/>
</form>

<!-- 페이징 -->
<c:if test="${listPager.curPage ne 1 }">
	<a href="javascript:pagingProc(1);"><<</a>
	<a href="javascript:pagingProc(${listPager.prevBlock });"><</a>
</c:if>
<c:forEach var="i" begin="${listPager.blockBegin }" end="${listPager.blockEnd }">
	<c:choose>
		<c:when test="${i eq listPager.curPage }">
			<a class="TEXT_COLOR_RED">${i }</a>
		</c:when>
		<c:otherwise>
			<a href="javascript:pagingProc(${i });">${i }</a>
		</c:otherwise>
	</c:choose>
</c:forEach>
<c:if test="${listPager.curPage ne listPager.pageCnt }">
	<a href="javascript:pagingProc(${listPager.nextBlock });">></a>
	<a href="javascript:pagingProc(${listPager.pageCnt });">>></a>
</c:if>

<!-- 스크립트 -->
<script type="text/javascript">
	function pagingProc(curPage){
		$("#curPage").val(curPage);
		$("#commonForm").prop("action", "/webzineList/listProc.do");
		$("#commonForm").submit();
	}
	
	function webzineForm(){
		var session = "${sessionScope.sessionVo}";
		
	}
</script>