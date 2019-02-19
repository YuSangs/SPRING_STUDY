package kr.co.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
	public void fileDownload(HttpServletRequest request, HttpServletResponse response, Map<String, Object> param){
		
		String storedFile = param.get("STORED_FILE").toString();
		String originalFile = param.get("ORIGIN_FILE").toString();
		String filePath = param.get("FILE_PATH").toString();
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
	public void ExcelDownloadXls(HttpServletResponse response, List<Map<String, Object>> excelDataList, String fileName){
		
		//엑셀 읽기, 쓰기 API 세트 (HSSF)
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		HSSFFont font = workbook.createFont();
		
		//폰트 설정
//		font.setFontHeightInPoints((short)9);
//		font.setBold(true); //폰트 굵기 설정 
//		font.setFontName("맑은고딕");

		//제목 스타일에 폰트 적용, 정렬
		HSSFCellStyle styleHd = workbook.createCellStyle();    //제목 스타일
		styleHd.setFont(font);
//		styleHd.setAlignment(HSSFCellStyle.ALIGN_CENTER); //가운데 정렬 설정
//		styleHd.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER); //수직 중앙 정렬 설정
		
		sheet = workbook.createSheet("첫번째 시트"); //워크시트 생성
		
		// 제목 행
		row = sheet.createRow(0);
		row.setHeight ((short) 0x150);
		
		cell = row.createCell(0);
		cell.setCellValue("번호");
		cell.setCellStyle(styleHd);
		
		cell = row.createCell(1);
		cell.setCellValue("이름");
		cell.setCellStyle(styleHd);
		
		//엑셀에 뿌려줄 데이터들
		for(int i=0;i<excelDataList.size();i++) {
			row = sheet.createRow(i+1);
			row.setHeight ((short) 0x150);
			
			cell = row.createCell(0);
			cell.setCellValue(i+1);
			cell.setCellStyle(styleHd);
			
			cell = row.createCell(1);
			cell.setCellValue(excelDataList.get(i).get("name").toString());
			cell.setCellStyle(styleHd);
		}
		
		try {
			response.setContentType("Application/Msexcel");
			response.setHeader("Content-Disposition", "ATTachment; Filename="+URLEncoder.encode(fileName, "UTF-8") + ".xls");
			workbook.write(response.getOutputStream());
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
	
	/**
	 * 
	 * 엑셀 다운로드(.xlsx)
	 * @param HttpServletResponse
	 * @param List<Map<String, Object>>
	 * @param String
	 * 
	 * poi 버전 : 3.17 
	 * */
	public void ExcelDownloadXlsx(HttpServletResponse response, List<Map<String, Object>> excelDataList, String fileName){
		
		//엑셀 읽기, 쓰기 API 세트 (XSSF)
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		XSSFFont font = workbook.createFont();
		
		//폰트 설정
//		font.setFontHeightInPoints((short)9);
//		font.setBold(true); //폰트 굵기 설정 
//		font.setFontName("맑은고딕");

		//제목 스타일에 폰트 적용, 정렬
		XSSFCellStyle styleHd = workbook.createCellStyle();    //제목 스타일
		styleHd.setFont(font);
//		styleHd.setAlignment(HSSFCellStyle.ALIGN_CENTER); //가운데 정렬 설정
//		styleHd.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER); //수직 중앙 정렬 설정
		
		sheet = workbook.createSheet("첫번째 시트"); //워크시트 생성
		
		// 제목 행
		row = sheet.createRow(0);
		row.setHeight ((short) 0x150);
		
		cell = row.createCell(0);
		cell.setCellValue("번호");
		cell.setCellStyle(styleHd);
		
		cell = row.createCell(1);
		cell.setCellValue("이름");
		cell.setCellStyle(styleHd);
		
		//엑셀에 뿌려줄 데이터들
		for(int i=0;i<excelDataList.size();i++) {
			row = sheet.createRow(i+1);
			row.setHeight ((short) 0x150);
			
			cell = row.createCell(0);
			cell.setCellValue(i+1);
			cell.setCellStyle(styleHd);
			
			cell = row.createCell(1);
			cell.setCellValue(excelDataList.get(i).get("name").toString());
			cell.setCellStyle(styleHd);
		}
		
		try {
			response.setContentType("Application/Msexcel");
			response.setHeader("Content-Disposition", "ATTachment; Filename="+URLEncoder.encode(fileName, "UTF-8") + ".xlsx");
			workbook.write(response.getOutputStream());
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
	
	/**
	 * 
	 * 엑셀 업로드
	 * @param HttpServletRequest
	 * @return List<Map<String, Object>>
	 * poi 버전 : 3.17 
	 * */
	@SuppressWarnings("deprecation")
	public List<Map<String, Object>> excelUpload(HttpServletRequest request, Map<String, Object> param) throws Exception {

		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		
		String storedFile = param.get("STORED_FILE").toString();
		String filePath = param.get("FILE_PATH").toString();
		String realPath = request.getSession().getServletContext().getRealPath("/") + filePath + storedFile;	//파일 저장 경로
		InputStream myxls = new FileInputStream(realPath);
		
		if (storedFile.endsWith(".xlsx")) {
			//확장자 .xlsx (XSSF)
			XSSFWorkbook workbook = new XSSFWorkbook(myxls);
			XSSFSheet sheet = null;
			XSSFRow row = null;
			XSSFCell cell = null;
			
			sheet = workbook.getSheetAt(0); //첫번째 시트
			int rows = sheet.getPhysicalNumberOfRows(); //한개의 시트에 데이터가 있는 행이 몇개인지 체크
			
			//제목 행을 제외한 나머지행 데이터
			//중간에 비어 있는 행이 있다면 오류가 발생할 수 있음
			//for문을 돌리면서 비어있는 행 구분하지 않고 있음
			for (int r=0;r<rows;r++) {
				Map<String, Object> map = new HashMap<String, Object>();
				row = sheet.getRow(r);
				int cells = row.getPhysicalNumberOfCells(); //한개의 행에 데이터가 있는 셀이 몇개인지 체크
				for (int c=0;c<cells;c++) {
					cell = row.getCell(c);
					String value = null;
					if (cell == null || cell.getStringCellValue().equals("")) {
						break;
					} else {
						switch (cell.getCellType()) {
						case XSSFCell.CELL_TYPE_FORMULA:
							value = cell.getCellFormula();
							break;
						case XSSFCell.CELL_TYPE_NUMERIC:
							value = String.valueOf(cell.getNumericCellValue());
							break;
						case XSSFCell.CELL_TYPE_STRING:
							value = cell.getStringCellValue();
							break;
						case XSSFCell.CELL_TYPE_BLANK:
							value = null;
							break;
						case XSSFCell.CELL_TYPE_BOOLEAN:
							value = String.valueOf(cell.getBooleanCellValue());
							break;
						case XSSFCell.CELL_TYPE_ERROR:
							value = String.valueOf(cell.getErrorCellValue());
							break;
						default:
						}
						if(c == 0) map.put("data1", value);
						else if(c == 1) map.put("data2", value);
					}
				}
				dataList.add(map);
			}
		} else {
			//확장자 .xls (HSSF)
			HSSFWorkbook workbook = new HSSFWorkbook(myxls);
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			
			sheet = workbook.getSheetAt(0); //첫번째 시트
			int rows = sheet.getPhysicalNumberOfRows(); //한개의 시트에 데이터가 있는 행이 몇개인지 체크
			
			//제목 행을 제외한 나머지행 데이터
			//중간에 비어 있는 행이 있다면 오류가 발생할 수 있음
			//for문을 돌리면서 비어있는 행 구분하지 않고 있음
			for (int r=0;r<rows;r++) {
				Map<String, Object> map = new HashMap<String, Object>();
				row = sheet.getRow(r);
				int cells = row.getPhysicalNumberOfCells(); //한개의 행에 데이터가 있는 셀이 몇개인지 체크
				for (int c=0;c<cells;c++) {
					cell = row.getCell(c);
					String value = null;
					if (cell == null || cell.getStringCellValue().equals("")) {
						break;
					} else {
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_FORMULA:
							value = cell.getCellFormula();
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							value = String.valueOf(cell.getNumericCellValue());
							break;
						case HSSFCell.CELL_TYPE_STRING:
							value = cell.getStringCellValue();
							break;
						case HSSFCell.CELL_TYPE_BLANK:
							value = null;
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN:
							value = String.valueOf(cell.getBooleanCellValue());
							break;
						case HSSFCell.CELL_TYPE_ERROR:
							value = String.valueOf(cell.getErrorCellValue());
							break;
						default:
						}
						if(c == 0) map.put("data1", value);
						else if(c == 1) map.put("data2", value);
					}
				}
				dataList.add(map);
			}
		}
		return dataList;
	}
}