package kr.co.front.main;

import java.util.List;
import java.util.Map;

public interface MainService {

	public List<?> selectMain(Map<String, Object> param) throws Exception;

	public int selectListCnt(Map<String, Object> param) throws Exception;
	
	public List<?> selectList(Map<String, Object> param) throws Exception;
	
}
