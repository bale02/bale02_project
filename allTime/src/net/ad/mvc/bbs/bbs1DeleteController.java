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
 * Servlet implementation class bbs1DeleteController
 */
@WebServlet("/bbs1DeleteController")
public class bbs1DeleteController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println(num);
		bbs1DAO dao = new bbs1DAO();
		dao.deleteBbs1(num);
		
		response.sendRedirect("bbs.jsp");
		
	}

}
