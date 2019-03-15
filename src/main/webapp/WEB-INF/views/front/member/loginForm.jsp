<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includeInc.jsp" %>
<table>
	<tr>
		<th>아이디</th>
		<td><input type="text" name="user_id" id="user_id"/></td>
	</tr>
	<tr>
		<th>패스워드</th>
		<td><input type="password" name="user_pw" id="user_pw"/></td>
	</tr>
</table>
<input type="button" value="로그인" onclick="loginProc();"/>
<script type="text/javascript">
	/*ajax 호출 함수*/	
	function ajaxCall(reqUrl, reqData){
		$.ajax({
			url: reqUrl
			,data: reqData
			,type: "POST"
			,dataType: "json"
			,success: function(d){
				if(reqUrl = "/member/loginProc.do"){
					if(d.result == true){
						location.href = "/main/index.do";
					}else if(d.result == "false_unCertifi"){
						alert("인증을 받지 않은 계정입니다. 관리자에게 문의해주세요.");
					}else if(d.result == "false_unMatch"){
						alert("아이디와 비밀번호가 일치하지 않습니다.");
						$("#user_id").focus();
					}
				}
			}
			,error: function(e){
				alert("ajax 통신 중 오류 발생!");
				console.log("error : "+e);
			}
		});
	}
	
	/*회원 가입*/
	function loginProc(){
		if($("#user_id").val() == ""){
			alert("아이디를 입력해주세요");
			$("#user_id").focus();
			return;
		}
		
		if($("#user_pw").val() == ""){
			alert("패스워드를 입력해주세요");
			$("#user_pw").focus();
			return;
		}
		
		var reqUrl = "/member/loginProc.do";
		var reqData = {};
		reqData.user_id = $("#user_id").val();
		reqData.user_pw = $("#user_pw").val();
		
		ajaxCall(reqUrl, reqData);
	}
</script>