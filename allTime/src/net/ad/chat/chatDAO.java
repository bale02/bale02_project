package net.ad.chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import net.ad.bbs.bbs1DTO;

public class chatDAO {
	Connection CN = null;
	PreparedStatement PST;
	Statement ST;
	ResultSet RS;
	public chatDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			CN = DriverManager.getConnection(url, "project", "project");
			
		} catch (Exception ex) {
			System.out.println("db error:" + ex);
		}
	}
	public int insert_chat(chatDTO bean) {
		try {
			String msg = "insert into chat values(?,?,sysdate,?)";
			PST = CN.prepareStatement(msg);
			PST.setString(1, bean.getNickname());
			PST.setString(2, bean.getComment());
			PST.setString(3, bean.getId());

			return PST.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return -1;
	
	}
	public ArrayList<chatDTO> get_chat() {
		ArrayList<chatDTO> list = new ArrayList<>();
		String msg;
		try {
			
			msg = "select * from(select * from chat order by rownum desc)where rownum<= 30 order by chat_date asc";
			ST = CN.createStatement();
			RS = ST.executeQuery(msg);

			while (RS.next() == true) {
				chatDTO bean = new chatDTO();
				bean.setNickname(RS.getString("chat_nickname"));
				bean.setComment(RS.getString("chat_comment"));
				bean.setDate(RS.getTimestamp("chat_date"));
				bean.setId(RS.getString("chat_id"));
				list.add(bean);
			} // while end
			RS.close();
			ST.close();
			CN.close();
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return list;

	}
	
	
}
