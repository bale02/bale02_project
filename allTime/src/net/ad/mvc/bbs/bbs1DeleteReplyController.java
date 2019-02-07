package net.ad.mvc.bbs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ad.bbs.bbs1DAO;
import net.ad.bbs.bbs1DTO;
import net.ad.reply.replyDAO;

/**
 * Servlet implementation class bbs1DeleteReplyController
 */
@WebServlet("/bbs1DeleteReplyController")
public class bbs1DeleteReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String sid = (String)session.getAttribute("id");
		String id = request.getParameter("name");
		String num = request.getParameter("num");
		String replynum = request.getParameter("replynum");
		
		if(sid.equals(id)|| sid.equals("admin")) {
			replyDAO dao = new replyDAO();
			bbs1DAO bdao = new bbs1DAO();
			
			bbs1DTO bean = bdao.get_bbs1(Integer.parseInt(num));
			
			int flag = dao.deleteBbs1_Reply(replynum);
			
			if(flag==1) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("bbs1View.jsp?num=" + num);
				request.setAttribute("bbs1Title", bean.getTitle());
				request.setAttribute("bbs1Content", bean.getContent());
				request.setAttribute("bbs1Date", bean.getDate().toString().substring(0, bean.getDate().toString().length() - 2));
				request.setAttribute("bbs1Nickname", bean.getNickname());
				request.setAttribute("bbs1Id", bean.getId());
				dispatcher.forward(request, response);
				
			}
		}else {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('댓글 작성한 사람만 삭제할 수 있습니다..')");
			script.println("history.back()");
			script.println("</script>");
		}
	}

}
