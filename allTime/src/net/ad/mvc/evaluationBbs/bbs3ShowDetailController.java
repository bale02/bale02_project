package net.ad.mvc.evaluationBbs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ad.evaluationBbs.bbs3DAO;
import net.ad.evaluationBbs.bbs3DTO;



/**
 * Servlet implementation class bbs3ShowDetailController
 */
@WebServlet("/bbs3ShowDetailController")
public class bbs3ShowDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		int num = Integer.parseInt(request.getParameter("num"));
		bbs3DAO dao = new bbs3DAO();
		bbs3DTO bean = new bbs3DTO();

		bean = dao.get_bbs3(num);
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("bbs3View.jsp?num=" + num);
		request.setAttribute("bbs3ClassTitle", bean.getClasstitle());
		request.setAttribute("bbs3ClassTeacher", bean.getClassteacher());
		request.setAttribute("bbs3Title", bean.getTitle());
		request.setAttribute("bbs3Content", bean.getContent());
		request.setAttribute("bbs3Date", bean.getDate().toString().substring(0, bean.getDate().toString().length() - 2));
		request.setAttribute("bbs3Nickname", bean.getNickname());
		request.setAttribute("bbs3Id", bean.getId());
		dispatcher.forward(request, response);
	}

	

}
