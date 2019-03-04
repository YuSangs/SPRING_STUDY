package kr.co.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter{
	protected Log log = LogFactory.getLog(LoggerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(log.isDebugEnabled()) {
			log.debug("================================ 로그 시작 ================================");
			log.debug("Request URI :::::: "+request.getRequestURI());
			log.info("로그 인포 레벨 테스트");
			log.warn("로그 경고 레벨 테스트");
			log.error("로그 에러 레벨 테스트");
			log.fatal("로그 페이탈 레벨 테스트");
		}
		
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(log.isDebugEnabled()) {
			log.debug("================================  로그 끝  ================================");
		}
		
		super.postHandle(request, response, handler, modelAndView);
	}
}
