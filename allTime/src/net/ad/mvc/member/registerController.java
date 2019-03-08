package net.ad.mvc.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ad.member.memberDAO;
import net.ad.member.memberDTO;

/**
 * Servlet implementation class registerController
 */
@WebServlet("/registerController")
public class registerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("memberId");

		String pwd = request.getParameter("memberPwd");
		String email = request.getParameter("memberEmail");
		String name = request.getParameter("memberName");
		String nickname = request.getParameter("memberNick");
		String college = request.getParameter("memberCollege");

		memberDAO dao = new memberDAO();
		memberDTO bean = new memberDTO();

		bean.setId(id);
		bean.setPwd(pwd);
		bean.setEmail(email);
		bean.setName(name);
		bean.setNickname(nickname);
		bean.setCollege(college);
		int flag = dao.dbInsert(bean);

		if (flag == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("nick", nickname);
			session.setAttribute("college", college);
			response.sendRedirect("main.jsp");

		} else {
			response.sendRedirect("register.jsp");
		}
		}
}
