<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includeInc.jsp" %>
<script type="text/javascript">
	function allowDrop(ev){
		ev.preventDefault();
	}
	
	function drag(ev){
		ev.dataTransfer.setData("dragId", ev.target.id);
	}
	
	function drop(ev){
		ev.preventDefault();
		var dragId = ev.dataTransfer.getData("dragId"); //드래그되는 태그 id
		var dropId = ev.target.id; //드랍되는 태그 id
		var dropChildCnt = ev.target.children[0].childElementCount+1; //드랍태그에 들어가 있는 태그 갯수 +1
		var dropIdVal = $("#"+dropId).val(); //드랍태그의 value 값(뎁스)
		var dragNewValue = dropIdVal+"-"+dropChildCnt; //드래그태그의 새 값
		console.log(dragNewValue);
		
		console.log("이전 값 ::: "+$("#"+dragId).val());
		$("#"+dragId).val(""+dragNewValue);
		console.log("새로운 값 ::: "+$("#"+dragId).val());
		$("#"+dropId).children().append($("#"+dragId));
		
		console.log(ev);
	}
</script>
<ul>
	<li id="seq1" draggable="true" ondrop="drop(event);" ondragstart="drag(event);" ondragover="allowDrop(event);">
		리스트1
		<input type="hidden" value="1"/>
		<ul></ul>
	</li>
	<li id="seq2" draggable="true" ondrop="drop(event);" ondragstart="drag(event);" ondragover="allowDrop(event);" value="2">리스트2<ul></ul></li>
	<li id="seq3" draggable="true" ondrop="drop(event);" ondragstart="drag(event);" ondragover="allowDrop(event);" value="3">리스트3<ul></ul></li>
	<li id="seq4" draggable="true" ondrop="drop(event);" ondragstart="drag(event);" ondragover="allowDrop(event);" value="4">리스트4<ul></ul></li>
</ul>