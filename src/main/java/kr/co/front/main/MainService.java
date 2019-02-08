package kr.co.front.main;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface MainService {

	public List<?> selectMain(Map<String, Object> param) throws Exception;

	public int selectListCnt(Map<String, Object> param) throws Exception;
	
	public List<?> selectList(Map<String, Object> param) throws Exception;

	public int writeProc(Map<String, Object> param, MultipartHttpServletRequest multi) throws Exception;

	public Map<String, Object> download(Map<String, Object> param) throws Exception;
	
}
