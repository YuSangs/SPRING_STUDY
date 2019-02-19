package kr.co.front.main;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.co.common.CommonFile;
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
	
	/**
	 * 게시판 글작성 페이지
	 * 
	 * @param Map<String, Object>
	 * @return String
	 * */
	@RequestMapping("/list/writeForm")
	public String writeForm(@RequestParam Map<String, Object> param) throws Exception {
		
		return "front/list/writeForm";
	}
	
	/**
	 * 게시판 글작성
	 * 
	 * @param Map<String, Object>
	 * @return String
	 * */
	@RequestMapping("/list/writeProc")
	public String writeProc(@RequestParam Map<String, Object> param, MultipartHttpServletRequest multi) throws Exception {
		
		int result = service.writeProc(param, multi);
		
		if(result == 1) {
			System.out.println("글작성 성공");
		}else {
			System.out.println("글작성 실패");
		}
		
		return "redirect:/list/list.do";
	}
	
	/**
	 * 첨부파일 다운로드
	 * 
	 * @param Map<String, Object>
	 * @return String
	 * */
	@RequestMapping("/download")
	public void download(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommonFile commonFile = new CommonFile();
		
		Map<String, Object> map = service.download(param);
		
		commonFile.fileDownload(request, response, map);
	}
	
	/**
	 * 엑셀 다운로드 시도
	 * 
	 * @param Map<String, Object>
	 * @return String
	 * */
	@RequestMapping("/excelDownload")
	public void excelDownload(@RequestParam Map<String, Object> param, HttpServletResponse response) throws Exception {
		CommonFile commonFile = new CommonFile();
		
		List<Map<String, Object>> excelDataList = null;
		
		commonFile.ExcelDownloadXls(response, excelDataList, param.get("fileName").toString());
		
	}
	
	/**
	 * 엑셀 업로드 시도
	 * 
	 * @param Map<String, Object>
	 * @return String
	 * */
	@RequestMapping("/excelUpload")
	public void excelUplo(@RequestParam Map<String, Object> param, HttpServletRequest request, MultipartHttpServletRequest multi) throws Exception {
		CommonFile commonFile = new CommonFile();
		
		List<Map<String, Object>> fileList = commonFile.fileUpload(multi, "upload/excel/");
		param.put("STORED_FILE", fileList.get(0).get("stored_file").toString());
		param.put("FILE_PATH", fileList.get(0).get("file_path").toString());
		
		List<Map<String, Object>> dataList = commonFile.excelUpload(request, param);
		
		System.out.println("엑셀 데이터 시작");
		for(int i=0;i<dataList.size();i++) {
			System.out.println(i+"번 엑셀 데이터");
			System.out.println("data1 ::::: "+dataList.get(i).get("data1"));
			System.out.println("data2 ::::: "+dataList.get(i).get("data2"));
		}
		System.out.println("엑셀 데이터 끝");
	}
}
