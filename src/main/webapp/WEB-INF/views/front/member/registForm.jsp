<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includeInc.jsp" %>
<form id="registForm" action="/member/registProc.do" method="post" >
	<table>
		<tr>
			<th>아이디</th>
			<td>
				<div><input type="text" name="user_id" id="user_id" onchange="userIdChange(this);"/></div>
				<div><input type="button" value="중복확인" onclick="overlapChk();"/></div>
			</td>
		</tr>
		<tr>
			<th>패스워드</th>
			<td><input type="password" name="user_pw" id="user_pw"/></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="user_name" id="user_name"/></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>
				<input type="text" id="user_email_front"/> @ 
				<select id="user_email_back">
					<option value="">선택</option>
					<option value="naver.com">naver.com</option>
					<option value="gmail.com">gmail.com</option>
				</select>
				<input type="hidden" name="user_email" id="user_email"/>
			</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>
				<input type="text" id="user_phone_one"/> - <input type="text" id="user_phone_two"/> - <input type="text" id="user_phone_three"/>
				<input type="hidden" name="user_phone" id="user_phone"/>
			</td>
		</tr>
	</table>
</form>
<input type="button" value="가입하기" onclick="memberRegist();"/>
<script type="text/javascript">
	/*ajax 호출 함수*/	
	function ajaxCall(reqUrl, reqData){
		$.ajax({
			url: reqUrl
			,data: reqData
			,type: "POST"
			,dataType: "json"
			,success: function(d){
				if(reqUrl = "/member/overlapChk.do"){
					if(d.result == true){
						alert("사용할 수 있는 아이디입니다.");
						overlapTF = "true";
						$("#user_pw").focus();
					}else{
						alert("중복된 아이디입니다.");
						overlapTF = "false";
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
	
	/*중복 확인*/
	var overlapTF = "false";
	function overlapChk(){
		if($("#user_id").val() != ""){
			var reqUrl = "/member/overlapChk.do";
			var reqData = {};
			reqData.user_id = $("#user_id").val();
			
			ajaxCall(reqUrl, reqData);
		}else{
			alert("아이디를 입력해주세요.");
		}
	}
	
	/*아이디 항목 내용 변경시 중복 확인 초기화*/
	function userIdChange(){
		overlapTF = "false";
	}
	
	/*회원 가입*/
	function memberRegist(){
		if($("#user_id").val() == ""){
			alert("아이디를 입력해주세요");
			$("#user_id").focus();
			return;
		}else{
			if(overlapTF == "false"){
				alert("아이디 중복확인 해주세요");
				$("#user_id").focus();
				return;
			}
		}
		
		if($("#user_pw").val() == ""){
			alert("패스워드를 입력해주세요");
			$("#user_pw").focus();
			return;
		}else{
			var regExp = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%&*\^]).{8,15}$/;
			if(!regExp.test($("#user_pw").val())){
				alert("패스워드는 영문, 숫자, 특수문자로 이루어진 8~15글자 이어야 합니다.");
				$("#user_pw").focus();
				return;
			}	
		}
		
		if($("#user_name").val() == ""){
			alert("이름을 입력해주세요");
			$("#user_name").focus();
			return;
		}
		
		if($("#user_email_front").val() == "" || $("#user_email_back").val() == ""){
			alert("이메일을 입력해주세요");
			$("#user_email_front").focus();
			return;
		}else{
			$("#user_email").val($("#user_email_front").val()+"@"+$("#user_email_back").val());
		}
		
		if($("#user_phone_one").val() == "" || $("#user_phone_two").val() == "" || $("#user_phone_three").val() == ""){
			alert("전화번호를 입력해주세요");
			$("#user_phone_one").focus();
			return;
		}else{
			$("#user_phone").val($("#user_phone_one").val()+"-"+$("#user_phone_two").val()+"-"+$("#user_phone_three").val());
		}
		
		$("#registForm").submit();
	}
</script>