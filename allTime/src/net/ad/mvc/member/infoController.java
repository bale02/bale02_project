package net.ad.mvc.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ad.member.memberDAO;
import net.ad.member.memberDTO;

/**
 * Servlet implementation class infoController
 */
@WebServlet("/infoController")
public class infoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		memberDAO dao = new memberDAO();
		memberDTO bean = new memberDTO();
		bean.setId(id);
		memberDTO info = new memberDTO();
		info = dao.dbgetinfo(bean);

        RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
	    request.setAttribute("memberId",info.getId());
	    request.setAttribute("memberName",info.getName());
	    request.setAttribute("memberNickname",info.getNickname());
	    request.setAttribute("memberEmail",info.getEmail());
	    request.setAttribute("memberCollege",info.getCollege());
	    request.setAttribute("memberDate",info.getDate());
	    dispatcher.forward(request, response);

		
	}

}
