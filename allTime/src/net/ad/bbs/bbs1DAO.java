package net.ad.bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import net.ad.member.memberDTO;

public class bbs1DAO {
	Connection CN = null;
	PreparedStatement PST;
	Statement ST;
	ResultSet RS;

	public bbs1DAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			CN = DriverManager.getConnection(url, "project", "project");
		
		} catch (Exception ex) {
			System.out.println("db error:" + ex);
		}
	}

	public int deleteBbs1(int num) {
		try {
			String msg = "delete from bbs1 where num = " + num;
			ST = CN.createStatement();
			return ST.executeUpdate(msg);

		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return -1;
	}
	public int insert_bbs1(bbs1DTO bean) {
		try {
			String msg = "insert into bbs1 values(bbs1_seq.nextval,?,?,?,sysdate,?)";
			PST = CN.prepareStatement(msg);
			PST.setString(1, bean.getTitle());
			PST.setString(2, bean.getContent());
			PST.setString(3, bean.getNickname());
			PST.setString(4, bean.getId());
			return PST.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return -1;
	}// end

	public ArrayList<bbs1DTO> get_title(){
	      String msg = null;
	      ArrayList<bbs1DTO> list = new ArrayList<bbs1DTO>();
	      try {
	         msg = "select * from (select * from bbs1 order by num desc) where rownum < 5";
	         ST = CN.createStatement();
	         RS = ST.executeQuery(msg);
	         while (RS.next() == true) {
	            bbs1DTO bean = new bbs1DTO();
	            bean.setNum(RS.getInt("num"));
	            bean.setTitle(RS.getString("title"));
	            bean.setContent(RS.getString("content"));
	            bean.setNickname(RS.getString("nickname"));
	            bean.setDate(RS.getTimestamp("b_date"));
	            list.add(bean);
	         } 
	         
	      }catch(Exception e) {
	         System.out.println("Error : " + e);
	      }
	      return list;
	   }
	public int mod_bbs1(bbs1DTO bean) {
		try {
			String msg = "update bbs1 set title=?, content=? , b_date=sysdate where num=?";
			PST = CN.prepareStatement(msg);
			PST.setString(1, bean.getTitle());
			PST.setString(2, bean.getContent());
			PST.setInt(3,bean.getNum());
			return PST.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return -1;
	}// end
	
	public ArrayList<bbs1DTO> get_bbs1(String title) {
		ArrayList<bbs1DTO> list = new ArrayList<bbs1DTO>();
		String msg;
		try {
			
			msg = "select * from bbs1 where title like '%"+title+"%' order by num desc";
			ST = CN.createStatement();
			RS = ST.executeQuery(msg);

			while (RS.next() == true) {
				bbs1DTO bean = new bbs1DTO();
				bean.setNum(RS.getInt("num"));
				bean.setTitle(RS.getString("title"));
				bean.setContent(RS.getString("content"));
				bean.setNickname(RS.getString("nickname"));
				bean.setDate(RS.getTimestamp("b_date"));
				list.add(bean);
			} // while end
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return list;
	}

	public bbs1DTO get_bbs1(int num) {
		bbs1DTO bean = new bbs1DTO();
		try {
			String msg = "select * from bbs1 where num=" + num + " order by num desc";

			ST = CN.createStatement();
			RS = ST.executeQuery(msg);
			RS.next();
			bean.setNum(RS.getInt("num"));
			bean.setTitle(RS.getString("title"));
			bean.setContent(RS.getString("content"));
			bean.setNickname(RS.getString("nickname"));
			bean.setDate(RS.getTimestamp("b_date"));
			bean.setId(RS.getString("id"));

		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return bean;

	}
}