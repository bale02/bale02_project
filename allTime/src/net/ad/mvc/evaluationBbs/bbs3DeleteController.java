package net.ad.mvc.evaluationBbs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.ad.evaluationBbs.bbs3DAO;

/**
 * Servlet implementation class bbs3DeleteController
 */
@WebServlet("/bbs3DeleteController")
public class bbs3DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int num = Integer.parseInt(request.getParameter("num"));
		
		bbs3DAO dao = new bbs3DAO();
		dao.deleteBbs3(num);
		response.sendRedirect("evaluationBbs.jsp");
		
	}

}
