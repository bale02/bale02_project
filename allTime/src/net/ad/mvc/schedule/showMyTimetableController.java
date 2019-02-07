package net.ad.mvc.schedule;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ad.bbs.bbs1DAO;
import net.ad.bbs.bbs1DTO;
import net.ad.schedule.scheduleDAO;
import net.ad.schedule.scheduleDTO;

@WebServlet("/showMyTimetableController")
public class showMyTimetableController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		StringBuffer result = new StringBuffer("");
		scheduleDAO DAO = new scheduleDAO();
		ArrayList<scheduleDTO> List = DAO.get_mySchedule(id);

		result.append("{\"result\":[");
		for (int i = 0; i < List.size(); i++) {
			result.append("[{\"value\": \"" + List.get(i).getTitle() + "\"},");
			result.append("{\"value\": \"" + List.get(i).getTeacher() + "\"},");
			result.append("{\"value\": \"" + List.get(i).getPlace() + "\"},");
			result.append("{\"value\": \"" + List.get(i).getTime() + "\"}]");
			if (i != List.size() - 1)
				result.append(",");
		}
		result.append("]}");
		response.getWriter().write(result.toString());

	}

}
