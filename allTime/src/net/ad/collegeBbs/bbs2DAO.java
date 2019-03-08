package net.ad.collegeBbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import net.ad.member.memberDTO;

public class bbs2DAO {
	Connection CN = null;
	PreparedStatement PST;
	Statement ST;
	ResultSet RS;

	public bbs2DAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			CN = DriverManager.getConnection(url, "project", "project");
		
		} catch (Exception ex) {
			System.out.println("db error:" + ex);
		}
	}


	   public ArrayList<bbs2DTO> get_Maintitle(String college){
	         String msg = null;
	         ArrayList<bbs2DTO> list = new ArrayList<>();
	         try {
	            msg = "select * from (select * from bbs2 order by num desc) where college ='"+ college +"' and rownum < 5";
	            ST = CN.createStatement();
	            RS = ST.executeQuery(msg);
	            while (RS.next() == true) {
	               bbs2DTO bean = new bbs2DTO();
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
	public int deleteBbs2(int num) {
		try {
			String msg = "delete from bbs2 where num = " + num;
			ST = CN.createStatement();
			return ST.executeUpdate(msg);

		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return -1;
	}
	public int insert_bbs2(bbs2DTO bean) {
		try {
			String msg = "insert into bbs2 values(?,bbs2_seq.nextval,?,?,?,sysdate,?)";
			PST = CN.prepareStatement(msg);
			PST.setString(1, bean.getId());
			PST.setString(2, bean.getTitle());
			PST.setString(3, bean.getContent());
			PST.setString(4, bean.getNickname());
			PST.setString(5, bean.getCollege());
			return PST.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return -1;
	}// end

	public ArrayList<bbs2DTO> get_title(){
	      String msg = null;
	      ArrayList<bbs2DTO> list = new ArrayList<>();
	      try {
	         msg = "select * from (select * from bbs2 order by num desc) where rownum < 5";
	         ST = CN.createStatement();
	         RS = ST.executeQuery(msg);
	         while (RS.next() == true) {
	            bbs2DTO bean = new bbs2DTO();
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
	public int mod_bbs2(bbs2DTO bean) {
		try {
			String msg = "update bbs2 set title=?, content=? , b_date=sysdate where num=?";
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
	
	public ArrayList<bbs2DTO> get_bbs2(String title, String college) {
		ArrayList<bbs2DTO> list = new ArrayList<>();
		String msg;
		try {
			
			msg = "select * from bbs2 where title like '%"+title+"%' and college ='"+college+
			"' order by num desc";
			ST = CN.createStatement();
			RS = ST.executeQuery(msg);

			while (RS.next() == true) {
				bbs2DTO bean = new bbs2DTO();
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

	public bbs2DTO get_bbs2(int num) {
		bbs2DTO bean = new bbs2DTO();
		try {
			String msg = "select * from bbs2 where num=" + num + " order by num desc";

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