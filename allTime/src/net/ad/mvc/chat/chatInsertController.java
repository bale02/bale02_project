package net.ad.mvc.chat;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ad.chat.chatDAO;
import net.ad.chat.chatDTO;
import net.ad.mvc.member.loginController;
/**
 * Servlet implementation class chatInsertController
 */
@WebServlet("/chatInsertController")
public class chatInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		String comment = request.getParameter("chatContent");
		String nickname = (String) session.getAttribute("nick");
		String id = (String) session.getAttribute("id");
		
		chatDAO dao = new chatDAO();
		chatDTO bean = new chatDTO();

	
		bean.setNickname(nickname);
		bean.setComment(comment);
		bean.setId(id);

		int flag = dao.insert_chat(bean);
		/*for (HttpSession i : loginController.clients) {
	         String servletid = i.getId();
	         System.out.println("1 :" + servletid);
	         ServletContext servlet = i.getServletContext();
	         RequestDispatcher dispatcher = servlet.getRequestDispatcher("/chatShowController");
	         dispatcher.forward(request, response);
	        
	      }
	       */
		response.getWriter().write(""+flag);
	}

}
