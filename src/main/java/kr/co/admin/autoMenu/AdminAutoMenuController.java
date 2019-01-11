package kr.co.admin.autoMenu;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminAutoMenuController {

	@Autowired
	AdminAutoMenuService service;
	
	/**
	 * 메인
	 * 
	 * @param Map<String, Object>
	 * @return ModelAndView
	 * */
	@RequestMapping("/autoMenu/autoMenuUpdateForm.do")
	public ModelAndView autoMenuUpdateForm() throws Exception {
		ModelAndView mav = new ModelAndView("autoMenu/autoMenuU");
		
		
		
		return mav;
	}
	
}
