package net.ad.mvc.evaluationBbs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ad.evaluationBbs.bbs3DAO;
import net.ad.evaluationBbs.bbs3DTO;


/**
 * Servlet implementation class bbs3Controller
 */
@WebServlet("/bbs3Controller")
public class bbs3Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String title = request.getParameter("bbs3Title");
		String content = request.getParameter("bbs3Content");
		String nickname = (String) session.getAttribute("nick");
		String id =(String)session.getAttribute("id");
		String college= (String)session.getAttribute("college");
		String classtitle = request.getParameter("bbs3ClassTitle");
		String classteacher = request.getParameter("bbs3ClassTeacher");
		
		System.out.println("title :"+title+"/ content : "+content+"/ nickname : "+nickname+"/ id :"+id+"/college : "+college+"/ classtitle : "+classtitle+"/ classteacher : "+classteacher);
		
		bbs3DAO dao = new bbs3DAO();
		bbs3DTO bean = new bbs3DTO();

		bean.setTitle(title);
		bean.setContent(content);
		bean.setNickname(nickname);
		bean.setId(id);
		bean.setCollege(college);
		bean.setClasstitle(classtitle);
		bean.setClassteacher(classteacher);
		
		int flag = dao.insert_bbs3(bean);
	
		response.getWriter().write(flag + "");

	}

}
