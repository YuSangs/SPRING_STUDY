package kr.co.front.webzine;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

public interface WebzineService {

	ModelAndView listProc(Map<String, Object> param);

}
