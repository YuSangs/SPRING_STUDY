package kr.co.common;

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
	
	
	
}