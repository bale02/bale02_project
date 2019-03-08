package net.ad.mvc.collegeBbs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ad.collegeBbs.bbs2DAO;

/**
 * Servlet implementation class bbs1DeleteController
 */
@WebServlet("/bbs2DeleteController")
public class bbs2DeleteController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int num = Integer.parseInt(request.getParameter("num"));
		
		bbs2DAO dao = new bbs2DAO();
		dao.deleteBbs2(num);
		response.sendRedirect("collegeBbs.jsp");
		
	}

}
