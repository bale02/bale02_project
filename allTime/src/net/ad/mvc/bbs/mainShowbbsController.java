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

@WebServlet("/mainShowbbsController")
public class mainShowbbsController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      response.setContentType("text/html;charset=UTF-8");
      StringBuffer result = new StringBuffer("");
      
      bbs1DAO dao = new bbs1DAO();
      
      ArrayList<bbs1DTO> List = dao.get_title();
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