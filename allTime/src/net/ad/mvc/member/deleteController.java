package net.ad.mvc.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ad.member.memberDAO;

/**
 * Servlet implementation class deleteController
 */
@WebServlet("/deleteController")
public class deleteController extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8"); 
      PrintWriter out=response.getWriter();
      
      String data = request.getParameter("id");
      memberDAO dao = new memberDAO();
      int flag =dao.deleteId(data);
      System.out.println("f"+flag);
  	if (flag == 1) {
  		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('탈퇴하셨습니다. ㅠㅠ')");
		script.println("location.href='logout.jsp'");
		script.println("</script>");
  		

	} else {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('탈퇴에 실패했습니다.')");
		script.println("histroy.back();'");
		script.println("</script>");
		
	}
   }

}