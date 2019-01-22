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

}
