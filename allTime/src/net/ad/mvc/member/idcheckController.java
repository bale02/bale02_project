package net.ad.mvc.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ad.member.memberDAO;
import net.ad.member.memberDTO;

/**
 * Servlet implementation class idcheckController
 */
@WebServlet("/idcheckController")
public class idcheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8"); 
		
		String id = request.getParameter("id");
		System.out.println(id);
		memberDAO dao=new memberDAO();
		memberDTO bean=new memberDTO();
		
		bean.setId(id);
		int flag = dao.dbcheckid(bean);
		System.out.println(flag);
		response.getWriter().write(flag+"");
	}

}
