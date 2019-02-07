package net.ad.mvc.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ad.member.memberDAO;
import net.ad.member.memberDTO;
import net.ad.mvc.schedule.getScheduleListController;

/**
 * Servlet implementation class loginController
 */
@WebServlet("/loginController")
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static Set<HttpSession> clients = Collections.synchronizedSet(new HashSet<HttpSession>());
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String id = request.getParameter("memberId");
		String pwd = request.getParameter("memberPwd");
		
		
		
		memberDAO dao = new memberDAO();
		memberDTO bean = new memberDTO();

		bean.setId(id);
		bean.setPwd(pwd);
		
		int flag = dao.dblogincheck(bean);
		
		if (flag == 1) {
			
			String college =dao.dbgetCollege(bean);
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			String nickname = dao.dbgetnickname(bean);
			session.setAttribute("nick", nickname);
			session.setAttribute("college", college);
			clients.add(session);
			response.sendRedirect("main.jsp");
			
			if(id.equals("admin")) {
				getScheduleListController.getlist();
			}
			

		} else {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인에 실패했습니다.')");
			script.println("location.href='main.jsp'");
			script.println("</script>");
			
		}
	}

}
