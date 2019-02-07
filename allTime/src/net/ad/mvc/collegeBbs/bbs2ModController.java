package net.ad.mvc.collegeBbs;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ad.collegeBbs.bbs2DAO;
import net.ad.collegeBbs.bbs2DTO;

/**
 * Servlet implementation class bbs1Controller
 */
@WebServlet("/bbs2ModController")
public class bbs2ModController extends HttpServlet {
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
		bbs2DAO dao = new bbs2DAO();
		bbs2DTO bean = new bbs2DTO();

		bean.setTitle(title);
		bean.setContent(content);
		bean.setNickname(nickname);
		bean.setId(id);
		bean.setNum(no);
		bean.setDate(new Date());
		dao.mod_bbs2(bean);

		RequestDispatcher dispatcher = request.getRequestDispatcher("bbs2View.jsp?num=" + no);
		request.setAttribute("bbs2Title", bean.getTitle());
		request.setAttribute("bbs2Content", bean.getContent());
		request.setAttribute("bbs2Date", bean.getDate().toString().substring(0, bean.getDate().toString().length() - 2));
		request.setAttribute("bbs2Nickname", bean.getNickname());
		request.setAttribute("bbs2Id", bean.getId());
		dispatcher.forward(request, response);

	}

}
