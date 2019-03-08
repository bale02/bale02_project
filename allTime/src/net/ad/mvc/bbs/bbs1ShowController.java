package net.ad.mvc.bbs;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ad.bbs.bbs1DAO;
import net.ad.bbs.bbs1DTO;

/**
 * Servlet implementation class bbs1ShowController
 */
@WebServlet("/bbs1ShowController")
public class bbs1ShowController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String type = request.getParameter("type");
		
		ArrayList<bbs1DTO> List = null;
		StringBuffer result = new StringBuffer("");
		bbs1DAO DAO = new bbs1DAO();

		if (type.equals("bbs1")) {
			List = DAO.get_bbs1("");
		} else if (type.equals("search")) {
			String title = request.getParameter("title");
			
			if (title==null||title.equals("")) {
				List = DAO.get_bbs1("");
			} else {
				List = DAO.get_bbs1(title);
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