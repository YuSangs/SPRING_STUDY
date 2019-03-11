package kr.co.front.member;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

public interface MemberService {

	Map<String, Object> overlapChk(Map<String, Object> param) throws Exception;

	ModelAndView registProc(Map<String, Object> param) throws Exception;

	Map<String, Object> cerfitiChk(Map<String, Object> param) throws Exception;

	Map<String, Object> loginProc(Map<String, Object> param, HttpSession session) throws Exception;

}
