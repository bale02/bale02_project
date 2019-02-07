package net.ad.mvc.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ad.member.memberDAO;
import net.ad.member.memberDTO;

/**
 * Servlet implementation class infoController
 */
@WebServlet("/modController")
public class modController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.setCharacterEncoding("UTF-8");
	  response.setCharacterEncoding("UTF-8");
      response.setContentType("text/html;charset=UTF-8");
      
      HttpSession session = request.getSession();
      String id = (String) session.getAttribute("id");
      String pwd =request.getParameter("memberPwd");
      String nick = request.getParameter("memberNick");
      String name = request.getParameter("memberName");
      String college = request.getParameter("memberCollege");
      String email = request.getParameter("memberEmail");
      
      memberDAO dao = new memberDAO();
      memberDTO bean = new memberDTO();
      
      bean.setId(id);
      bean.setPwd(pwd);
      bean.setEmail(email);
      bean.setName(name);
      bean.setNickname(nick);
      bean.setCollege(college);
      int flag = dao.dbModid(bean);
      
      if(flag == 1) {
         PrintWriter script = response.getWriter();
	     session.setAttribute("nick", nick);
         script.println("<script>");
         script.println("location.href='infoController'");
         script.println("</script>");
      }else {
         PrintWriter script = response.getWriter();
         script.println("<script>");
         script.println("alert('수정에 실패했습니다.')");
         script.println("location.href='profileMod.jsp?name="+name+" &email="+email+" &college="+college+" '");
         script.println("</script>");
      }

   }
}