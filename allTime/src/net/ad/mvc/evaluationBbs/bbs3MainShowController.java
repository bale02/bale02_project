package net.ad.mvc.evaluationBbs;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ad.evaluationBbs.bbs3DAO;
import net.ad.evaluationBbs.bbs3DTO;

/**
 * Servlet implementation class bbs3MainShowController
 */
@WebServlet("/bbs3MainShowController")
public class bbs3MainShowController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
	      request.setCharacterEncoding("utf-8");
	      response.setContentType("text/html;charset=UTF-8");
	      StringBuffer result = new StringBuffer("");
	      
	      String college = (String) session.getAttribute("college");
	      
	      bbs3DAO dao = new bbs3DAO();
	      
	      ArrayList<bbs3DTO> List = dao.get_Maintitle(college);
	    
	      result.append("{\"result\":[");
	      for (int i = 0; i < List.size(); i++) {
	    	  result.append("[{\"value\": \"" + List.get(i).getClasstitle() + "\"},");
	    	  result.append("{\"value\": \"" + List.get(i).getClassteacher() + "\"},");
	         result.append("{\"value\": \"" + List.get(i).getTitle() + "\"},");
	         result.append("{\"value\": \"" + List.get(i).getNickname() + "\"},");
	         result.append("{\"value\": \""
	               + List.get(i).getDate().toString().substring(0, List.get(i).getDate().toString().length() - 2)
	               + "\"},");

	         result.append("{\"value\": \"" + List.get(i).getNum() + "\"}]");
	         if (i != List.size() - 1)
	            result.append(",");
	      }
	      result.append("]}");
	      response.getWriter().write(result.toString());
	}

}
