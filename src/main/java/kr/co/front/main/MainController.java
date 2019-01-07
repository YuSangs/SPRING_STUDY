package kr.co.front.main;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@Autowired
	MainService service;
	
	/**
	 * 메인
	 * 
	 * @param Map<String, Object>
	 * @return ModelAndView
	 * */
	@RequestMapping("/main/main.do")
	public ModelAndView main(@RequestParam Map<String, Object> param) throws Exception {
		ModelAndView mav = new ModelAndView("front/main/main");
		
		mav.addObject("mainList", service.main(param));
		return mav;
	}
}
