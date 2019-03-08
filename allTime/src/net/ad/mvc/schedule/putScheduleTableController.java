package net.ad.mvc.schedule;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ad.schedule.scheduleDAO;
import net.ad.schedule.scheduleDTO;

/**
 * Servlet implementation class putScheduleTableController
 */
@WebServlet("/putScheduleTableController")
public class putScheduleTableController extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      HttpSession session = request.getSession();

      response.setCharacterEncoding("UTF-8");
      response.setContentType("text/html;charset=UTF-8");

      String id = (String) session.getAttribute("id");
      String className = request.getParameter("classTitle");
      String professor = request.getParameter("classTeacher");
      String time = request.getParameter("classTime");
      String place = request.getParameter("classPlace");
      String type = request.getParameter("type");
      if (place == null || place.equals("")) {
         place = " ";
      }

      String college = (String) session.getAttribute("college");
      scheduleDAO dao = new scheduleDAO();
      scheduleDTO bean = new scheduleDTO();
      String r = "1";

      if (type.equals("man")) {
         bean.setTime(time);
         bean.setId(id);
         bean.setPlace(place);
         bean.setTeacher(professor);
         bean.setTitle(className);
         int flag = dao.insert_schedule(bean);
         response.getWriter().write(flag + "");
      } else {
         if (college.equals("전북대")) {
            for (int i = 1; i < time.split(",").length; i = i + 2) {
               String times = time.split(",")[i];
               String day = times.split(" ")[0];
               String t = times.split(" ")[1];
               String num = t.split("-")[0];
               String td = "";
               if (day.equals("월"))
                  td = "a,#" + num;
               else if (day.equals("화"))
                  td = "b,#" + num;
               else if (day.equals("수"))
                  td = "c,#" + num;
               else if (day.equals("목"))
                  td = "d,#" + num;
               else if (day.equals("금"))
                  td = "e,#" + num;
               else
                  td = "f,#" + num;

               bean.setTime(td);
               bean.setId(id);
               bean.setPlace(place);
               bean.setTeacher(professor);
               bean.setTitle(className);
               int flag = dao.insert_schedule(bean);
               if (flag != 1)
                  r = "-1";
            }
            response.getWriter().write(r);
         } else if (college.equals("서울대")) {
            String ar[] = time.split("#");
            String days[] = new String[ar.length];
            String starts[] = new String[ar.length];
            String ends[] = new String[ar.length];
            String places[] = new String[ar.length];
            int check[] = new int[ar.length];
            for (int i=0;i<check.length;i++ ) {
               check[i] = 1;
            }
            for (int i = 0; i < ar.length; i++) {
               days[i] = ar[i].split("\\(")[0];
               starts[i] = ar[i].substring(2, 7);
               ends[i] = ar[i].substring(8, 13);
               places[i] = place.split("#")[i];

               if (i != 0) {
                  if (days[i].equals(days[i - 1])) {
                     if (starts[i].equals(starts[i - 1]) && ends[i].equals(ends[i - 1])) {
                        String temp = places[i - 1];
                        places[i - 1] = temp + "," + places[i];
                        places[i] = temp + "," + places[i];
                        check[i] = -1;
                     }
                  }
               }
            }
            for (int i = 0; i < ar.length; i++) {

               System.out.println(i + days[i] + " : " + starts[i] + "~" + ends[i] + " " + places[i]);

               String d = "";
               if (days[i].equals("월"))
                  d = "a,#";
               else if (days[i].equals("화"))
                  d = "b,#";
               else if (days[i].equals("수"))
                  d = "c,#";
               else if (days[i].equals("목"))
                  d = "d,#";
               else if (days[i].equals("금"))
                  d = "e,#";
               else
                  d = "f,#";

               int start_h = Integer.parseInt(starts[i].split(":")[0]);
               int start_m = Integer.parseInt(starts[i].split(":")[1]);
               int end_h = Integer.parseInt(ends[i].split(":")[0]);
               int end_m = Integer.parseInt(ends[i].split(":")[1]);
               int cnt = end_h - start_h;
               if (end_m - start_m > 30) {
                  cnt++;
               }
               for (int j = 0; j < cnt; j++) {
                  String td = d + (start_h - 8 + j);
                  System.out.println(td);
                  System.out.println(places[i]);
                  if(places[i].equals("undefined")) {
                     places[i]="강의실 미정";
                  }
                  bean.setTime(td);
                  bean.setId(id);
                  bean.setPlace(places[i]);
                  bean.setTeacher(professor);
                  bean.setTitle(className);
                  int flag = 1;
                  System.out.println("check : "+check[i]);
                  if (check[i] == 1) {
                     flag = dao.insert_schedule(bean);
                  }
                  
                  System.out.println(flag);
                  if (flag != 1)
                     r = "-1";

               }

            }
            response.getWriter().write(r);
         } else if (college.equals("경희대")) {

            if (className.contains("졸업논문")) {
               r = "0";
            } else {
               String ar[] = time.split("#");
               String days[] = new String[ar.length];
               int t[] = new int[ar.length];
               for (int i = 0; i < ar.length; i++) {

                  String day = ar[i].split(" ")[0];
                  System.out.println("day " + day);
                  String d = "";
                  if (day.equals("월"))
                     d = "a,#";
                  else if (day.equals("화"))
                     d = "b,#";
                  else if (day.equals("수"))
                     d = "c,#";
                  else if (day.equals("목"))
                     d = "d,#";
                  else if (day.equals("금"))
                     d = "e,#";
                  else
                     d = "f,#";
                  days[i] = d;
                  String times = ar[i].split(" ")[1];

                  int start = Integer.parseInt(times.split(":")[0]);
                  t[i] = start;
                  String td = d + (start - 8);

                  bean.setTime(td);
                  bean.setId(id);
                  bean.setPlace(place);
                  bean.setTeacher(professor);
                  bean.setTitle(className);
                  int flag = dao.insert_schedule(bean);
                  if (flag != 1) {
                     r = "-1";
                  }

               }
               for (int i = 1; i < days.length; i++) {
                  if (days[i - 1].equals(days[i])) {
                     int s = t[i] - 9;
                     String td = days[i] + s;

                     bean.setTime(td);
                     bean.setId(id);
                     bean.setPlace(place);
                     bean.setTeacher(professor);
                     bean.setTitle(className);
                     int flag = dao.insert_schedule(bean);
                     if (flag != 1) {
                        r = "-1";
                     }
                  }
               }
            }

            response.getWriter().write(r);
         }
      }

   }
}