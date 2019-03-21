package kr.co.front.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import kr.co.common.CommonDAO;
import kr.co.common.CommonUtil;
import kr.co.common.MailUtil;
import kr.co.common.SessionVo;

@Service
public class MemberServiceImpl extends CommonDAO implements MemberService{

	@Autowired
	CommonUtil commonUtil;
	
	@Autowired
	JavaMailSender mailSender;
	
	protected Log log = LogFactory.getLog(MemberServiceImpl.class);
	
	@Override
	public Map<String, Object> overlapChk(Map<String, Object> param) throws Exception {
		Map<String, Object> json = new HashMap<String, Object>();
		
		if(super.select("member.selectMemberInfo", param) != null) {
			json.put("result", false);
		}else {
			json.put("result", true);
		}
		
		return json;
	}

	@Override
	public ModelAndView registProc(Map<String, Object> param) throws Exception {
		ModelAndView mv = new ModelAndView("front/member/certifiChk");
		param.put("temp_key", commonUtil.createTempKey());
		param.put("user_pw", commonUtil.sha256(param.get("user_pw").toString()));
		
		if(super.insert("member.insertMember", param) == 1) {
			mv.addObject("result", true);
			mv.addObject("user_id", param.get("user_id").toString());
			MailUtil mailUtil = new MailUtil(mailSender);
			
			String htmlContent = "";
			htmlContent += "<div> 인증번호 입니다. </div>";
			htmlContent += "<div> "+param.get("temp_key").toString()+" </div>";
			
			mailUtil.setSubject("회원가입 인증 메일입니다.");
			mailUtil.setText(htmlContent);
			mailUtil.setTo(param.get("user_email").toString());
			mailUtil.mailSend();
		}else {
			mv.addObject("result", false);
		}
		
		return mv;
	}

	@Override
	public Map<String, Object> cerfitiChk(Map<String, Object> param) throws Exception {
		Map<String, Object> json = new HashMap<String, Object>();
		
		if(super.select("member.selectMemberInfo", param) != null) {
			if(super.insert("member.updateMemberCerfitiYN", param) == 1) {
				json.put("result", true);
			}
		}else {
			json.put("result", false);
		}
		return json;
	}
	
	@Override
	public Map<String, Object> loginProc(Map<String, Object> param, HttpSession session) throws Exception {
		Map<String, Object> json = new HashMap<String, Object>();
		
		param.put("user_pw", commonUtil.sha256(param.get("user_pw").toString()));
		Map<String, Object> memberInfo = super.selectMap("member.selectMemberInfo", param);
		
		if(memberInfo != null) {
			if(memberInfo.get("CERTIFI_YN").toString().equals("Y")) {
				json.put("result", true);
				SessionVo sessionVo = new SessionVo();
				sessionVo.setLogin_idx(Integer.parseInt(memberInfo.get("IDX").toString()));
				sessionVo.setLogin_id(memberInfo.get("USER_ID").toString());
				sessionVo.setLogin_auth(memberInfo.get("USER_AUTH").toString());
				session.setAttribute("sessionVo", sessionVo);
			}else {
				json.put("result", "false_unCertifi");
			}
		}else {
			json.put("result", "false_unMatch");
		}
		
		return json;
	}

	@Override
	public String logoutProc(HttpSession session) throws Exception {
		session.removeAttribute("sessionVo");
		return "redirect:/main/index.do";
	}
}
