package net.ad.mvc.bbs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ad.bbs.bbs1DAO;
import net.ad.bbs.bbs1DTO;

/**
 * Servlet implementation class bbs1Controller
 */
@WebServlet("/bbs1Controller")
public class bbs1Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String title = request.getParameter("bbs1Title");
		String content = request.getParameter("bbs1Content");
		String nickname = (String) session.getAttribute("nick");
		String id =(String)session.getAttribute("id");
		bbs1DAO dao = new bbs1DAO();
		bbs1DTO bean = new bbs1DTO();

		bean.setTitle(title);
		bean.setContent(content);
		bean.setNickname(nickname);
		bean.setId(id);
		int flag = dao.insert_bbs1(bean);
	
		response.getWriter().write(flag + "");

	}

}
