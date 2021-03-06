package net.ad.mvc.collegeBbs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ad.reply.replyDAO;
import net.ad.reply.replyDTO;



/**
 * Servlet implementation class bbs1InsertReplyController
 */
@WebServlet("/bbs2InsertReplyController")
public class bbs2InsertReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		String content = request.getParameter("bbs2ReplyContent");
		String nickname = (String) session.getAttribute("nick");
		String id = (String) session.getAttribute("id");
		int num = Integer.parseInt(request.getParameter("num"));
		replyDAO dao = new replyDAO();
		replyDTO bean = new replyDTO();

		bean.setContent(content);
		bean.setNickname(nickname);
		bean.setNum(num);
		bean.setId(id);
	
		int flag = dao.insert_bbs2Reply(bean);
		
		response.getWriter().write(flag + "");
	}

}
