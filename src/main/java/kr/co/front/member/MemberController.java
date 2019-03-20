package kr.co.front.member;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {

	protected Log log = LogFactory.getLog(MemberController.class);
	
	@Autowired
	MemberService service;

	/**
	 * 회원가입 페이지 이동
	 * 
	 * @return String
	 * */
	@RequestMapping("/member/registForm")
	public String registForm() throws Exception {
		return "front/member/registForm";
	}
	
	/**
	 * 아이디 중복확인
	 * 
	 * @param Map<String, Object>
	 * @return Map<String, Object>
	 * */
	@RequestMapping("/member/overlapChk")
	public @ResponseBody Map<String, Object> overlapChk(@RequestParam Map<String, Object> param) throws Exception {
		return service.overlapChk(param);
	}
	
	/**
	 * 회원가입 실행
	 * 
	 * @param Map<String, Object>
	 * @return ModelAndView
	 * */
	@RequestMapping("/member/registProc")
	public ModelAndView registProc(@RequestParam Map<String, Object> param) throws Exception {
		return service.registProc(param);
	}
	
	/**
	 * 계정 인증 실행
	 * 테스트
	 * @param Map<String, Object>
	 * @return Map<String, Object>
	 * */
	@RequestMapping("/member/cerfitiChk")
	public @ResponseBody Map<String, Object> cerfitiChk(@RequestParam Map<String, Object> param) throws Exception {
		return service.cerfitiChk(param);
	}
	
	/**
	 * 회원가입 성공 페이지 이동
	 * 
	 * @return String
	 * */
	@RequestMapping("/member/registComplete")
	public String registComplete() throws Exception {
		return "front/member/registComplete";
	}
	
	/**
	 * 로그인 페이지 이동
	 * 
	 * @return String
	 * */
	@RequestMapping("/member/loginForm")
	public String loginForm() throws Exception {
		return "front/member/loginForm";
	}
	
	/**
	 * 로그인 실행
	 * 
	 * @param Map<String, Object>
	 * @return Map<String, Object>
	 * */
	@RequestMapping("/member/loginProc")
	public @ResponseBody Map<String, Object> loginProc(@RequestParam Map<String, Object> param, HttpSession session) throws Exception {
		return service.loginProc(param, session);
	}
}
