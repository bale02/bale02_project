package net.ad.mvc.collegeBbs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ad.collegeBbs.bbs2DAO;
import net.ad.collegeBbs.bbs2DTO;

/**
 * Servlet implementation class bbs1ShowDetailController
 */
@WebServlet("/bbs2ShowDetailController")
public class bbs2ShowDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		int num = Integer.parseInt(request.getParameter("num"));
		bbs2DAO dao = new bbs2DAO();
		bbs2DTO bean = new bbs2DTO();

		bean = dao.get_bbs2(num);

		RequestDispatcher dispatcher = request.getRequestDispatcher("bbs2View.jsp?num=" + num);
		request.setAttribute("bbs2Title", bean.getTitle());
		request.setAttribute("bbs2Content", bean.getContent());
		request.setAttribute("bbs2Date", bean.getDate().toString().substring(0, bean.getDate().toString().length() - 2));
		request.setAttribute("bbs2Nickname", bean.getNickname());
		request.setAttribute("bbs2Id", bean.getId());
		dispatcher.forward(request, response);
	}

}
