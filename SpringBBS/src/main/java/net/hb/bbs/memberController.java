package net.hb.bbs;

import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class memberController {
	private static final Logger logger = LoggerFactory.getLogger(memberController.class);

	@Inject
	@Autowired
	memberDAO dao;
	
	@Autowired
	private ServletContext application;
	
	@Autowired
	HttpSession Session; 
	
	@RequestMapping("/login.do")
	public String loginpage() {
		return "login";
	}

	@RequestMapping("/join.do")
	public String join() {
		return "join";
	}
	
	@RequestMapping("/logincheck.do")
	public String check(memberDTO dto,HttpServletResponse response)throws Exception {
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("text/html; charset=utf-8");
		
		if(dto.getuserid()==null||dto.getuserid().equals("")) {
			out.println("<script>");
			out.println("alert('로그인 실패!');");
			out.println("</script>");
			out.flush();
			return "login";
		}
		dao.loginCheck(dto);
		Session.setAttribute("userid",dto.getuserid());
		return "redirect:/list.do";
	}
	@RequestMapping("/logout.do")
	public String logout() {
		Session.invalidate();
		return "redirect:/list.do";
	}
	
	@RequestMapping("/memberinsert.do")
	public String member_insert(memberDTO dto,HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("text/html; charset=utf-8");
		try {
			dao.dbinsert(dto);
		}catch(Exception e) {
			out.println("<script>");
			out.println("alert('회원가입 실패!');");
			out.println("</script>");
			out.flush();
			return "join";
		}
		Session.setAttribute("userid",dto.getuserid());
		return "redirect:/list.do";
	}
	
	@RequestMapping("/checkid.do")
		public void checkId(HttpServletRequest req, HttpServletResponse res,
				   ModelMap model) throws Exception {
				  PrintWriter out = res.getWriter();
				  try {

				   // 넘어온 ID를 받는다.
				   String paramId = (req.getParameter("prmId") == null) ? "" : String
				     .valueOf(req.getParameter("prmId"));

				   memberDTO vo = new memberDTO();
				   vo.setuserid(paramId.trim());
				   int chkPoint = dao.selectAdmin(vo);

				   out.print(chkPoint);
				   out.flush();
				   out.close();
				  } catch (Exception e) {
				   e.printStackTrace();
				   out.print("1");
				  }
	}

}
