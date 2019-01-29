package kr.co.front.main;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.common.ListPager;

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
	@RequestMapping("/main/main")
	public ModelAndView main(@RequestParam Map<String, Object> param) throws Exception {
		ModelAndView mav = new ModelAndView("main/main");
		
		mav.addObject("mainList", service.selectMain(param));
		return mav;
	}
	
	/**
	 * 게시판 리스트
	 * 
	 * @param Map<String, Object>
	 * @return ModelAndView
	 * */
	@RequestMapping("/list/list")
	public ModelAndView list(@RequestParam Map<String, Object> param) throws Exception {
		ModelAndView mav = new ModelAndView("front/list/list");
		
		int countList = service.selectListCnt(param);
		int curPage = 1;
		if(param.get("curPage") != null) {
			curPage = Integer.parseInt(param.get("curPage").toString());
		}
		
		ListPager listPager = new ListPager(countList, curPage);
		param.put("pageBegin", listPager.getPageBegin());
		param.put("pageEnd", listPager.getPageEnd());
		
		mav.addObject("list", service.selectList(param));
		mav.addObject("listPager", listPager);
		return mav;
	}
	
}
