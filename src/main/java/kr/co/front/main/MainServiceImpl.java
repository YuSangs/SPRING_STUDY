package kr.co.front.main;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.co.common.CommonDAO;

@Service
public class MainServiceImpl extends CommonDAO implements MainService{

	@Override
	public List<?> selectMain(Map<String, Object> param) throws Exception{
		return super.list("main.main", param);
	}

	@Override
	public int selectListCnt(Map<String, Object> param) throws Exception{
		return Integer.parseInt(super.select("main.selectListCnt", param).toString());
	}
	
	@Override
	public List<?> selectList(Map<String, Object> param) throws Exception{
		return super.list("main.selectList", param);
	}
}
