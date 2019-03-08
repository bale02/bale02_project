package net.ad.mvc.collegeBbs;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ad.collegeBbs.bbs2DAO;
import net.ad.collegeBbs.bbs2DTO;

@WebServlet("/bbs2MainShowController")
public class bbs2MainShowController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     HttpSession session = request.getSession();
      request.setCharacterEncoding("utf-8");
      response.setContentType("text/html;charset=UTF-8");
      StringBuffer result = new StringBuffer("");
      
      String college = (String) session.getAttribute("college");
      
      bbs2DAO dao = new bbs2DAO();
      
      ArrayList<bbs2DTO> List = dao.get_Maintitle(college);
     
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