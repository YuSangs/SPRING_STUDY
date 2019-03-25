package kr.co.front.webzine;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.co.common.CommonDAO;
import kr.co.common.CommonFile;
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
			
			if(param.get("msg") != null) {
				mv.addObject("msg", param.get("msg").toString());
			}
			mv.addObject("listPager", listPager);
			mv.addObject("webzineList", super.selectList("webzine.selectWebzineList", param));
		}
		
		return mv;
	}

	@Override
	public ModelAndView writeProc(Map<String, Object> param, MultipartHttpServletRequest multi) throws Exception {
		//객체 준비
		ModelAndView mv = new ModelAndView("redirect:/webzineList/listProc.do");
		CommonFile commonFile = new CommonFile();
		param.put("file_path", "/webzine/");
		
		//파일 업로드
		List<Map<String, Object>> fileList = commonFile.fileUpload(multi, param.get("file_path").toString());
		for(int i=0;i<fileList.size();i++) {
			param.put("file_ori", fileList.get(i).get("file_ori").toString());
			param.put("file_sto", fileList.get(i).get("file_sto").toString());
			if(super.insert("file.insertFile", param) != 1) {
				log.error("파일 업로드 중 오류");
			}
		}
		
		//글 등록
		if(super.insert("webzine.insertWebzineList", param) != 1) {
			log.error("등록 중 오류");
			mv.addObject("msg", "등록에 문제가 발생했습니다. \n관리자에게 문의해주세요.");
		}else {
			mv.addObject("msg", "글작성이 완료되었습니다.");
		}
		
		return mv;
	}
}