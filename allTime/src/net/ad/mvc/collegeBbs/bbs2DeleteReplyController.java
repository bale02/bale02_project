package net.ad.mvc.collegeBbs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ad.collegeBbs.bbs2DAO;
import net.ad.collegeBbs.bbs2DTO;
import net.ad.reply.replyDAO;

/**
 * Servlet implementation class bbs1DeleteReplyController
 */
@WebServlet("/bbs2DeleteReplyController")
public class bbs2DeleteReplyController extends HttpServlet {
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
			bbs2DAO bdao = new bbs2DAO();
			
			bbs2DTO bean = bdao.get_bbs2(Integer.parseInt(num));
			
			int flag = dao.deleteBbs2_Reply(replynum);
			System.out.println(flag);
			if(flag==1) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("bbs2View.jsp?num=" + num);
				request.setAttribute("bbs2Title", bean.getTitle());
				request.setAttribute("bbs2Content", bean.getContent());
				request.setAttribute("bbs2Date", bean.getDate().toString().substring(0, bean.getDate().toString().length() - 2));
				request.setAttribute("bbs2Nickname", bean.getNickname());
				request.setAttribute("bbs2Id", bean.getId());
				dispatcher.forward(request, response);
				
			}
		}else {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('댓글 작성한 사람만 삭제할 수 있습니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
	}

}
