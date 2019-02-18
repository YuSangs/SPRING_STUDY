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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class CommonFile {
	
	/**
	 * 
	 * 파일 업로드
	 * @param MultipartHttpServletRequest
	 * @param String
	 * @return List<Map<String, Object>>
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
	
	/**
	 * 
	 * 엑셀 다운로드(.xls)
	 * @param HttpServletResponse
	 * @param List<Map<String, Object>>
	 * @param String
	 * 
	 * poi 버전 : 3.17 
	 * */
	public void ExcelDownloadPoi(HttpServletResponse response, List<Map<String, Object>> excelDataList, String fileName){
		
		//엑셀 읽기, 쓰기 API 세트 (HSSF)
		HSSFWorkbook objWorkBook = new HSSFWorkbook();
		HSSFSheet objSheet = null;
		HSSFRow objRow = null;
		HSSFCell objCell = null;
		
		HSSFFont font = objWorkBook.createFont();
		font.setFontHeightInPoints((short)9);
		font.setBold(true); //폰트 굵기 설정 
//		font.setBold((short)font.BOLDWEIGHT_BOLD); 폰트 굵기 설정 버전 : 3.11 이하
		font.setFontName("맑은고딕");

		//제목 스타일에 폰트 적용, 정렬
		HSSFCellStyle styleHd = objWorkBook.createCellStyle();    //제목 스타일
		styleHd.setFont(font);
//		styleHd.setAlignment(HSSFCellStyle.ALIGN_CENTER); //가운데 정렬 설정
//		styleHd.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER); //수직 중앙 정렬 설정
		
		objSheet = objWorkBook.createSheet("첫번째 시트");     //워크시트 생성
		
		// 제목 행
		objRow = objSheet.createRow(0);
		objRow.setHeight ((short) 0x150);
		
		objCell = objRow.createCell(0);
		objCell.setCellValue("번호");
		objCell.setCellStyle(styleHd);
		
		objCell = objRow.createCell(1);
		objCell.setCellValue("이름");
		objCell.setCellStyle(styleHd);
		
		//엑셀에 뿌려줄 데이터들
		for(int i=0;i<excelDataList.size();i++) {
			objRow = objSheet.createRow(i+1);
			objRow.setHeight ((short) 0x150);
			
			objCell = objRow.createCell(0);
			objCell.setCellValue(i+1);
			objCell.setCellStyle(styleHd);
			
			objCell = objRow.createCell(1);
			objCell.setCellValue(excelDataList.get(i).get("name").toString());
			objCell.setCellStyle(styleHd);
		}
		
		try {
			response.setContentType("Application/Msexcel");
			response.setHeader("Content-Disposition", "ATTachment; Filename="+URLEncoder.encode(fileName, "UTF-8") + ".xls");
			objWorkBook.write(response.getOutputStream());
		} catch (IOException e) {
			System.out.println("엑셀 다운로드 중 오류 발생!");
			e.printStackTrace();
		}finally {
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
