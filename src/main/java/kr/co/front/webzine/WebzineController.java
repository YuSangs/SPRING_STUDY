package kr.co.front.webzine;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebzineController {

	@Autowired
	WebzineService service;
	
	/**
	 * 웹진형 리스트 Proc
	 * 
	 * @param Map<String, Object>
	 * @return ModelAndView
	 * */
	@RequestMapping(value="/webzineList/listProc")
	public ModelAndView listProc(@RequestParam Map<String, Object> param) throws Exception {
		return service.listProc(param);
	}
	
	/**
	 * 웹진형 글작성 페이지
	 * 
	 * @return String
	 * */
	@RequestMapping(value="/webzineList/writeForm")
	public String writeForm() throws Exception {
		return "front/webzineList/webzineF";
	}
	
	/**
	 * 웹진형 글작성 Proc
	 * 
	 * @param Map<String, Object>
	 * @param MultipartHttpServletRequest
	 * @return ModelAndView
	 * */
	@RequestMapping(value="/webzineList/writeProc")
	public ModelAndView writeProc(@RequestParam Map<String, Object> param, MultipartHttpServletRequest multi) throws Exception {
		return service.writeProc(param, multi);
	}
	
	/**
	 * 웹진형 상세 페이지
	 * 
	 * @param Map<String, Object>
	 * @return ModelAndView
	 * */
	@RequestMapping(value="/webzineList/viewProc")
	public ModelAndView viewProc(@RequestParam Map<String, Object> param) throws Exception {
		return service.viewProc(param);
	}
}
