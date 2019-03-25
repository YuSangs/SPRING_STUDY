package kr.co.front.webzine;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface WebzineService {

	ModelAndView listProc(Map<String, Object> param) throws Exception;

	ModelAndView writeProc(Map<String, Object> param, MultipartHttpServletRequest multi) throws Exception;
	
}
