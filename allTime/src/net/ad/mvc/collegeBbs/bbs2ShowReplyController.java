package net.ad.mvc.collegeBbs;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ad.reply.replyDAO;
import net.ad.reply.replyDTO;

/**
 * Servlet implementation class bbs1ShowReplyController
 */
@WebServlet("/bbs2ShowReplyController")
public class bbs2ShowReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		ArrayList<replyDTO> List = null;
		StringBuffer result = new StringBuffer("");

		result.append("{\"result\":[");
		replyDAO DAO = new replyDAO();
		int num = Integer.parseInt(request.getParameter("num"));
		
			List = DAO.get_bbs2reply(num);
			for (int i = 0; i < List.size(); i++) {
				result.append("[{\"value\": \"" + List.get(i).getNickname() + "\"},");
				result.append("{\"value\": \"" + List.get(i).getContent() + "\"},");
				result.append("{\"value\": \"" + List.get(i).getId() + "\"},");
				result.append("{\"value\": \""
						+ List.get(i).getDate().toString().substring(0, List.get(i).getDate().toString().length() - 2)
						+ "\"},");
				result.append("{\"value\": \"" + List.get(i).getReplyNum() + "\"},");
				result.append("{\"value\": \"" + List.get(i).getNum() + "\"}]");
				if (i != List.size() - 1)
					result.append(",");
			}
			result.append("]}");
		
		response.getWriter().write(result.toString());

	}

}
