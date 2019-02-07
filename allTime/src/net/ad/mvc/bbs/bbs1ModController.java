package net.ad.mvc.bbs;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
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
@WebServlet("/bbs1ModController")
public class bbs1ModController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String nickname = (String) session.getAttribute("nick");
		String id = (String) session.getAttribute("id");
		int no = Integer.parseInt(request.getParameter("num"));
		bbs1DAO dao = new bbs1DAO();
		bbs1DTO bean = new bbs1DTO();

		bean.setTitle(title);
		bean.setContent(content);
		bean.setNickname(nickname);
		bean.setId(id);
		bean.setNum(no);
		bean.setDate(new Date());
		dao.mod_bbs1(bean);

		RequestDispatcher dispatcher = request.getRequestDispatcher("bbs1View.jsp?num=" + no);
		request.setAttribute("bbs1Title", bean.getTitle());
		request.setAttribute("bbs1Content", bean.getContent());
		request.setAttribute("bbs1Date", bean.getDate().toString().substring(0, bean.getDate().toString().length() - 2));
		request.setAttribute("bbs1Nickname", bean.getNickname());
		request.setAttribute("bbs1Id", bean.getId());
		dispatcher.forward(request, response);

	}

}
