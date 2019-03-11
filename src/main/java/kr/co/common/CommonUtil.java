package kr.co.common;

import java.security.MessageDigest;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CommonUtil {
	
	protected Log log = LogFactory.getLog(CommonUtil.class);
	
	/**
	 * 인증키 생성
	 * 숫자, 영대문자 합쳐 5글자의 랜덤
	 * */
	public String createTempKey() {
		Random random = new Random();
		String tempKey = "";
		
		for(int i=0;i<5;i++) {
			//0 : 정수 / 1 : 영대문자
			if(random.nextInt(2) == 0) {
				tempKey += random.nextInt(10);
			}else {
				tempKey += String.valueOf((char) (random.nextInt(26) + 65)); //아스키코드 
			}
		}
		return tempKey;
	}
	
	/**
	 * SHA256 해싱
	 * 비밀번호 암호화
	 * */
	public String sha256(String password) {
		
		String SHA = "";
		
		try{
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(password.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			
			SHA = sb.toString();
			
		}catch(Exception e){
			log.error("sha256 해싱중 오류 발생!!");
			SHA = null; 
		}
		return SHA;
	}
	
	/**
	 * MD5 해싱
	 * 비밀번호 암호화
	 * 암호화 결함이 있어 사용할 경우 SHA256과 같이 사용하는 것을 권장
	 * */
	public String md5(String password) {
		
		String MD5 = "";
		
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer();
			
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			
			MD5 = sb.toString();
			
		}catch(Exception e){
			log.error("md5 해싱중 오류 발생!!");
			MD5 = null;
		}
		
		return MD5; 
	}
}