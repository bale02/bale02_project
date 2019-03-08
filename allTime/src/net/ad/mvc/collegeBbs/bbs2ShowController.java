package net.ad.mvc.collegeBbs;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ad.bbs.bbs1DTO;
import net.ad.collegeBbs.bbs2DAO;
import net.ad.collegeBbs.bbs2DTO;

/**
 * Servlet implementation class bbs1ShowController
 */
@WebServlet("/bbs2ShowController")
public class bbs2ShowController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String type = request.getParameter("type");
		String college = (String) session.getAttribute("college");
		ArrayList<bbs2DTO> List = null;
		StringBuffer result = new StringBuffer("");
		bbs2DAO DAO = new bbs2DAO();

		if (type.equals("bbs2")) {
			List = DAO.get_bbs2("",college);
		} else if (type.equals("search")) {
			String title = request.getParameter("title");
			
			if (title.equals(null)) {
				List = DAO.get_bbs2("",college);
			} else {
				List = DAO.get_bbs2(title,college);
			}
		}

		result.append("{\"result\":[");
		for (int i = 0; i < List.size(); i++) {
			result.append("[{\"value\": \"" + List.get(i).getTitle() + "\"},");
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