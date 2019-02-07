package net.ad.mvc.collegeBbs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ad.bbs.bbs1DAO;
import net.ad.bbs.bbs1DTO;
import net.ad.collegeBbs.bbs2DAO;
import net.ad.collegeBbs.bbs2DTO;

/**
 * Servlet implementation class bbs1Controller
 */
@WebServlet("/bbs2Controller")
public class bbs2Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String title = request.getParameter("bbs2Title");
		String content = request.getParameter("bbs2Content");
		String nickname = (String) session.getAttribute("nick");
		String id =(String)session.getAttribute("id");
		String college= (String)session.getAttribute("college");
		bbs2DAO dao = new bbs2DAO();
		bbs2DTO bean = new bbs2DTO();

		bean.setTitle(title);
		bean.setContent(content);
		bean.setNickname(nickname);
		bean.setId(id);
		bean.setCollege(college);
		int flag = dao.insert_bbs2(bean);
	
		response.getWriter().write(flag + "");

	}

}
