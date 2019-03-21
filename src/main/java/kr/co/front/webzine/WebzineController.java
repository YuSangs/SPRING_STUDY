package kr.co.front.webzine;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
}
