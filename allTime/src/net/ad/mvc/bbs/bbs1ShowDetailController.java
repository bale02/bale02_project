package net.ad.mvc.bbs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ad.bbs.bbs1DAO;
import net.ad.bbs.bbs1DTO;

/**
 * Servlet implementation class bbs1ShowDetailController
 */
@WebServlet("/bbs1ShowDetailController")
public class bbs1ShowDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		int num = Integer.parseInt(request.getParameter("num"));
		bbs1DAO dao = new bbs1DAO();
		bbs1DTO bean = new bbs1DTO();

		bean = dao.get_bbs1(num);

		RequestDispatcher dispatcher = request.getRequestDispatcher("bbs1View.jsp?num=" + num);
		request.setAttribute("bbs1Title", bean.getTitle());
		request.setAttribute("bbs1Content", bean.getContent());
		request.setAttribute("bbs1Date", bean.getDate().toString().substring(0, bean.getDate().toString().length() - 2));
		request.setAttribute("bbs1Nickname", bean.getNickname());
		request.setAttribute("bbs1Id", bean.getId());
		dispatcher.forward(request, response);
	}

}
