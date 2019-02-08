package kr.co.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class CommonFile {
	
	/**
	 * 
	 * 파일 업로드
	 * @param MultipartHttpServletRequest
	 * @param String
	 * @return List<Map<String, Object>>
	 * 
	 * */
	public List<Map<String, Object>> fileUpload(MultipartHttpServletRequest multi, String path) {
		
		String originalFile; 		//기존 파일 이름
		String storedFile; 		//중복방지 파일 이름
		List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();			//DB에 저장할 파일 이름들
		
		String realPath = multi.getSession().getServletContext().getRealPath("/") + path;	//파일 저장 경로
		File dir = new File(realPath);

		//해당 이름의 폴더가 없을시 폴더 생성
		if(!dir.isDirectory()){
			dir.mkdir();
		}

		Iterator<String> iter = multi.getFileNames();
		MultipartFile file = null;
		
		while(iter.hasNext()){
			Map<String, Object> map = new HashMap<String, Object>();
			file = multi.getFile(iter.next());

			if(file.isEmpty() == false){
				originalFile = file.getOriginalFilename();
				
				//확장자
				String extension = originalFile.substring(originalFile.lastIndexOf(".")+1);
				
				//이름중복방지
				storedFile = System.currentTimeMillis() +"."+extension;
				
				//DB에 저장할 파일 이름
				map.put("origin_file", originalFile);
				map.put("stored_file", storedFile);
				map.put("file_path", path);
				fileList.add(map);
				
				try {
					file.transferTo(new File(realPath+storedFile));
				} catch (Exception e) {
					System.out.println("파일 업로드 중 오류 발생!");
					e.printStackTrace();
				}
			}
		}
		
		return fileList;
	}
	
	/**
	 * 
	 * 파일 다운로드
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param String
	 * 
	 * */
	public void fileDownload(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map){
		
		String storedFile = map.get("STORED_FILE").toString();
		String originalFile = map.get("ORIGIN_FILE").toString();
		String filePath = map.get("FILE_PATH").toString();
		String realPath = request.getSession().getServletContext().getRealPath("/") + filePath + storedFile;	//파일 저장 경로
		
		try {
			byte fileByte[] = FileUtils.readFileToByteArray(new File(realPath));
			//response객체를 통해 다운로드
			response.setContentType("application/octet-stream");
			response.setContentLength(fileByte.length);
			response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFile,"UTF-8")+"\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.getOutputStream().write(fileByte);
		} catch (IOException e) {
			System.out.println("파일 다운로드 중 오류 발생!");
			e.printStackTrace();
		} finally {
			try {
				response.getOutputStream().flush();
				response.getOutputStream().close();
			} catch (IOException e) {
				System.out.println("아웃풋 스트림 닫는중 오류 발생!");
				e.printStackTrace();
			}
		}
	}
}
