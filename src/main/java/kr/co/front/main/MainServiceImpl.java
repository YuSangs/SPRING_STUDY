package kr.co.front.main;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.common.CommonDAO;
import kr.co.common.CommonFile;

@Service
public class MainServiceImpl extends CommonDAO implements MainService{

	@Override
	public List<?> selectMain(Map<String, Object> param) throws Exception{
		return super.selectList("main.main", param);
	}

	@Override
	public int selectListCnt(Map<String, Object> param) throws Exception{
		return Integer.parseInt(super.select("main.selectListCnt", param).toString());
	}
	
	@Override
	public List<?> selectList(Map<String, Object> param) throws Exception{
		return super.selectList("main.selectList", param);
	}

	@Override
	public int writeProc(Map<String, Object> param, MultipartHttpServletRequest multi) throws Exception{
		CommonFile commonUtil = new CommonFile();
		List<Map<String, Object>> fileList = commonUtil.fileUpload(multi, "upload/list/");
		
		param.put("origin_file", fileList.get(0).get("origin_file"));
		param.put("stored_file", fileList.get(0).get("stored_file"));
		param.put("file_path", fileList.get(0).get("file_path"));
		
		return super.insert("main.insertWriteProc", param);
	}

	@Override
	public Map<String, Object> download(Map<String, Object> param) throws Exception {
		return (Map<String, Object>) super.selectMap("main.selectDownload", param);
	}
}
