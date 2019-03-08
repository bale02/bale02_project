package net.ad.mvc.chat;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ad.chat.chatDAO;
import net.ad.chat.chatDTO;

/**
 * Servlet implementation class chatShowController
 */
@WebServlet("/chatShowController")
public class chatShowController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		ArrayList<chatDTO> List = null;
		StringBuffer result = new StringBuffer("");
		chatDAO DAO = new chatDAO();

		List= DAO.get_chat();
		
		result.append("{\"result\":[");
		for (int i = 0; i < List.size(); i++) {
			result.append("[{\"value\": \"" + List.get(i).getNickname() + "\"},");
			result.append("{\"value\": \"" + List.get(i).getComment() + "\"},");
			result.append("{\"value\": \""
					+ List.get(i).getDate().toString().substring(0, List.get(i).getDate().toString().length() - 2)
					+ "\"},");

			result.append("{\"value\": \"" + List.get(i).getId() + "\"}]");
			if (i != List.size() - 1)
				result.append(",");
		}
		result.append("]}");
		
		response.getWriter().write(result.toString());

	}

}
