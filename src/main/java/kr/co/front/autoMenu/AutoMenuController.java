package kr.co.front.autoMenu;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AutoMenuController {

	@Autowired
	AutoMenuService service;
	
	/**
	 * 메인
	 * 
	 * @param Map<String, Object>
	 * @return String
	 * */
	@RequestMapping("/autoMenu/main.do")
	public String main(@RequestParam Map<String, Object> param) throws Exception {
		
//		mav.addObject("mainList", service.selectMain(param));
		return "autoMenu/autoMenuU";
	}
	
}
