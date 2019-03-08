package net.hb.bbs.reply;

import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class replyController {
	private static final Logger logger = LoggerFactory.getLogger(replyController.class);
	
	@Inject
	@Autowired
	replyDAO dao;
	
	@Autowired
	private ServletContext application;
	
	@Autowired
	HttpSession Session;
	
	
	@RequestMapping("/reply_list.do")
	public ModelAndView reply_List(@RequestParam("idx") int data) {
		ModelAndView mav = new ModelAndView();
		List<replyDTO> list = dao.dbSelect(data);
		mav.addObject("list",list);
		mav.setViewName("bbsreply");
		return mav;
	}
	
	@RequestMapping("/replyinsert.do")
	public String replyInsert(replyDTO dto,HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("text/html; charset=utf-8");
		if(Session.getAttribute("userid")==null ||((String)Session.getAttribute("userid")).equals("")) {
			out.println("<script>");
			out.println("alert('로그인 후에 작성하세요!');");
			out.println("</script>");
			out.flush();
			return "login";
		}else {
			dao.dbInsert(dto);
		}
		return "redirect:/detail.do?idx="+dto.getBbs_idx();
	}
	
	@RequestMapping("/replydelete.do")
	public String replyDelete(@RequestParam("idx") int idx, @RequestParam("bbs_idx") int bbs_idx) {
		dao.dbDelete(idx);
		return "redirect:/detail.do?idx="+bbs_idx;
	}
}
