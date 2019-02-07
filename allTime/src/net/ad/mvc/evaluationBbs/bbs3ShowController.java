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
 * Servlet implementation class bbs3ShowController
 */
@WebServlet("/bbs3ShowController")
public class bbs3ShowController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String type = request.getParameter("type");
		String college = (String) session.getAttribute("college");
		ArrayList<bbs3DTO> List = null;
		StringBuffer result = new StringBuffer("");
		bbs3DAO DAO = new bbs3DAO();

		if (type.equals("bbs3")) {
			
			List = DAO.get_bbs3("",college);
		} else if (type.equals("search")) {
			
			String title = request.getParameter("title");
			
			if (title.equals(null)) {
			
				List = DAO.get_bbs3("",college);
			} else {
				
				List = DAO.get_bbs3(title,college);
			}
		}

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
