package net.ad.schedule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import net.ad.reply.replyDTO;

public class scheduleDAO {
	Connection CN = null;
	PreparedStatement PST;
	Statement ST;
	ResultSet RS;

	public scheduleDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			CN = DriverManager.getConnection(url, "project", "project");

		} catch (Exception ex) {
			System.out.println("db error:" + ex);
		}
	}
	public int delete_schedule(String id, String title,String teacher) {
	      try {
	         String msg = "delete from schedule where id = '" + id+"' and title = '" + title + "' and teacher = '" +teacher+"'";
	         ST = CN.createStatement();
	         return ST.executeUpdate(msg);

	      } catch (Exception ex) {
	         System.out.println("Error: " + ex);
	      }
	      return -1;
	      
	   }
	public int insert_schedule(scheduleDTO bean) {
		try {
			int r = checkSchedule(bean);
			if(r==-1)
				return -1;
			String msg = "insert into schedule values(?,?,?,?,?)";
			PST = CN.prepareStatement(msg);
			PST.setString(1, bean.getId());
			PST.setString(2, bean.getTitle());
			PST.setString(3, bean.getTeacher());
			PST.setString(4, bean.getTime());
			PST.setString(5, bean.getPlace());
			return PST.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return -1;

	}
	public int checkSchedule(scheduleDTO bean) {
		
		try {
			String msg = "select time from schedule where id = '" + bean.getId() + "'";

			ST = CN.createStatement();
			RS = ST.executeQuery(msg);

			while (RS.next() == true) {
				if(bean.getTime().equals(RS.getString("time")))
					return -1;
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}

		return 1;
	}

	public ArrayList<scheduleDTO> get_mySchedule(String id) {
		ArrayList<scheduleDTO> list = new ArrayList<>();
		try {
			String msg = "select * from schedule where id = '" + id + "'";

			ST = CN.createStatement();
			RS = ST.executeQuery(msg);

			while (RS.next() == true) {
				scheduleDTO bean = new scheduleDTO();
				bean.setId(id);
				bean.setPlace(RS.getString("place"));
				bean.setTeacher(RS.getString("teacher"));
				bean.setTime(RS.getString("time"));
				bean.setTitle(RS.getString("title"));
				list.add(bean);
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}

		return list;
	}
}