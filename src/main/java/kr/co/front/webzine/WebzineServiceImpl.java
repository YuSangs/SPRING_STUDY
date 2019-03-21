package kr.co.front.webzine;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import kr.co.common.CommonDAO;
import kr.co.common.ListPager;
import kr.co.front.member.MemberServiceImpl;

@Service
public class WebzineServiceImpl extends CommonDAO implements WebzineService{

	protected Log log = LogFactory.getLog(MemberServiceImpl.class);
	
	@Override
	public ModelAndView listProc(Map<String, Object> param) throws Exception {
		ModelAndView mv = new ModelAndView("front/webzineList/webzineL");
		
		int listCnt = Integer.parseInt(super.select("webzine.selectWebzineCnt", param).toString());
		if(listCnt == 0) {
			mv.addObject("emptyYN", "Y");
		}else {
			int curPage = 1;
			if(param.get("curPage") != null) {
				curPage = Integer.parseInt(param.get("curPage").toString());
			}
			ListPager listPager = new ListPager(listCnt, curPage);
			
			param.put("begin", listPager.getPageBegin());
			param.put("end", listPager.getPageEnd());
			
			mv.addObject("listPager", listPager);
			mv.addObject("webzineList", super.selectList("webzine.selectWebzineList", param));
		}
		
		return mv;
	}

}