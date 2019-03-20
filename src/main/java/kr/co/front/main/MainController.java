package kr.co.front.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	/**
	 * 메인 페이지
	 * */
	@RequestMapping(value="/main/index")
	public String listProc() {
		return "front/main/index";
	}
}
