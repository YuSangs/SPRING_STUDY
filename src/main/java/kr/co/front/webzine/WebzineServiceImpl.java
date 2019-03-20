package kr.co.front.webzine;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import kr.co.common.CommonDAO;
import kr.co.front.member.MemberServiceImpl;

@Service
public class WebzineServiceImpl extends CommonDAO implements WebzineService{

	protected Log log = LogFactory.getLog(MemberServiceImpl.class);
	
	@Override
	public ModelAndView listProc(Map<String, Object> param) {
		ModelAndView mv = new ModelAndView("front/webzine/webzineL");
		
		int listCnt = (int) super.select("webzine.selectWebzineCnt", param);
		
		log.debug("리스트 갯수 파악 ::::: "+listCnt);
		
		mv.addObject("webzineList", super.selectList("webzine.selectWebzineList", param));
		
		return mv;
	}

}