package kr.co.front.main;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	/**
	 * 메인 페이지
	 * */
	@RequestMapping(value="/main/index")
	public ModelAndView listProc(@RequestParam Map<String, Object> param, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("front/main/index");
		mv.addObject("blockTF", request.getAttribute("blockTF"));
		return mv;
	}
}
