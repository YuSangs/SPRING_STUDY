<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
	인증번호 입력 : <input type="text" id="temp_key" name="temp_key"/>
	<input type="hidden" id="user_id" name="user_id" value="${user_id }"/>
</div>
<input type="button" value="인증" onclick="certifi_chk();"/>

<script type="text/javascript">
	$(document).ready(function(){
		var result = "${result}";
		if(result == "true"){
			alert("인증 메일을 보냈습니다. 확인하시고 인증해주세요");
		}
	});
	
	/*ajax 호출 함수*/	
	function ajaxCall(reqUrl, reqData){
		$.ajax({
			url: reqUrl
			,data: reqData
			,type: "POST"
			,dataType: "json"
			,success: function(d){
				if(reqUrl == "/member/cerfitiChk.do"){
					if(d.result == true){
						location.href="/member/registComplete.do";
					}else{
						alert("인증키가 맞지 않습니다. 다시 확인해주세요.");
						$("#temp_key").focus();
					}
				}
			}
			,error: function(e){
				alert("ajax 통신 중 오류 발생!");
				console.log("error : "+e);
			}
		});
	}
	
	function certifi_chk(){
		if($("#temp_key").val() != ""){
			var reqUrl = "/member/cerfitiChk.do";
			var reqData = {};
			reqData.temp_key = $("#temp_key").val();
			reqData.user_id = $("#user_id").val();
			
			ajaxCall(reqUrl, reqData);
		}else{
			alert("인증키를 입력해주세요.");	
		}
	}
</script>