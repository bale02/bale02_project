package net.ad.mvc.evaluationBbs;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ad.evaluationBbs.bbs3DAO;
import net.ad.evaluationBbs.bbs3DTO;

/**
 * Servlet implementation class bbs3ModController
 */
@WebServlet("/bbs3ModController")
public class bbs3ModController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String nickname = (String) session.getAttribute("nick");
		String id = (String) session.getAttribute("id");
		String classtitle = request.getParameter("bbs3ClassTitle");
		String classteacher = request.getParameter("bbs3ClassTeacher");
		int no = Integer.parseInt(request.getParameter("num"));
		bbs3DAO dao = new bbs3DAO();
		bbs3DTO bean = new bbs3DTO();

		bean.setClasstitle(classtitle);
		bean.setClassteacher(classteacher);
		bean.setTitle(title);
		bean.setContent(content);
		bean.setNickname(nickname);
		bean.setId(id);
		bean.setNum(no);
		bean.setDate(new Date());
		dao.mod_bbs3(bean);

		RequestDispatcher dispatcher = request.getRequestDispatcher("bbs3View.jsp?num=" + no);
		request.setAttribute("bbs3ClassTitle", bean.getClasstitle());
		request.setAttribute("bbs3ClassTeacher", bean.getClassteacher());
		request.setAttribute("bbs3Title", bean.getTitle());
		request.setAttribute("bbs3Content", bean.getContent());
		request.setAttribute("bbs3Date", bean.getDate().toString().substring(0, bean.getDate().toString().length() - 2));
		request.setAttribute("bbs3Nickname", bean.getNickname());
		request.setAttribute("bbs3Id", bean.getId());
		dispatcher.forward(request, response);
	}

	

}
