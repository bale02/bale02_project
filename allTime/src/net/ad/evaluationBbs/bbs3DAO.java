package net.ad.evaluationBbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;



public class bbs3DAO {
	Connection CN = null;
	PreparedStatement PST;
	Statement ST;
	ResultSet RS;

	public bbs3DAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			CN = DriverManager.getConnection(url, "project", "project");
		
		} catch (Exception ex) {
			System.out.println("db error:" + ex);
		}
	}


	public int insert_bbs3(bbs3DTO bean) {
		try {
			String msg = "insert into bbs3 values(?,bbs3_seq.nextval,?,?,?,?,?,?,sysdate)";
			PST = CN.prepareStatement(msg);
			PST.setString(1, bean.getId());
			PST.setString(2, bean.getTitle());
			PST.setString(3, bean.getContent());
			PST.setString(4, bean.getNickname());
			PST.setString(5, bean.getCollege());
			PST.setString(6, bean.getClasstitle());
			PST.setString(7, bean.getClassteacher());
			return PST.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return -1;
	}


	public int deleteBbs3(int num) {
		try {
			String msg = "delete from bbs3 where num = " + num;
			ST = CN.createStatement();
			return ST.executeUpdate(msg);

		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return -1;
		
	}


	public bbs3DTO get_bbs3(int num) {
		bbs3DTO bean = new bbs3DTO();
		try {
			String msg = "select * from bbs3 where num=" + num + " order by num desc";

			ST = CN.createStatement();
			RS = ST.executeQuery(msg);
			RS.next();
			bean.setNum(RS.getInt("num"));
			bean.setTitle(RS.getString("title"));
			bean.setContent(RS.getString("content"));
			bean.setNickname(RS.getString("nickname"));
			bean.setDate(RS.getTimestamp("b_date"));
			bean.setId(RS.getString("id"));
			bean.setClasstitle(RS.getString("classtitle"));
			bean.setClassteacher(RS.getString("classteacher"));

		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return bean;
	}


	public ArrayList<bbs3DTO> get_Maintitle(String college) {
		   String msg = null;
	         ArrayList<bbs3DTO> list = new ArrayList<>();
	         try {
	            msg = "select * from (select * from bbs3 order by num desc) where college ='"+ college +"' and rownum < 5";
	            ST = CN.createStatement();
	            RS = ST.executeQuery(msg);
	            while (RS.next() == true) {
	               bbs3DTO bean = new bbs3DTO();
	               bean.setNum(RS.getInt("num"));
	               bean.setTitle(RS.getString("title"));
	               bean.setContent(RS.getString("content"));
	               bean.setNickname(RS.getString("nickname"));
	               bean.setClassteacher(RS.getString("classteacher"));
	               bean.setClasstitle(RS.getString("classtitle"));
	               bean.setDate(RS.getTimestamp("b_date"));
	               list.add(bean);
	            } 
	            
	         }catch(Exception e) {
	            System.out.println("Error : " + e);
	         }
	         return list;
	}



	public int mod_bbs3(bbs3DTO bean) {
		
		try {
			String msg = "update bbs3 set title=?, content=? , b_date=sysdate where num=?";
			PST = CN.prepareStatement(msg);
			PST.setString(1, bean.getTitle());
			PST.setString(2, bean.getContent());
			PST.setInt(3,bean.getNum());
			return PST.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return -1;
	}


	public ArrayList<bbs3DTO> get_bbs3(String title, String college) {
		ArrayList<bbs3DTO> list = new ArrayList<>();
		String msg;
		try {
			
			msg = "select * from bbs3 where title like '%"+title+"%' and college ='"+college+
			"' order by num desc";
			ST = CN.createStatement();
			RS = ST.executeQuery(msg);

			while (RS.next() == true) {
				bbs3DTO bean = new bbs3DTO();
				bean.setNum(RS.getInt("num"));
				bean.setTitle(RS.getString("title"));
				bean.setContent(RS.getString("content"));
				bean.setNickname(RS.getString("nickname"));
				bean.setClasstitle(RS.getString("classtitle"));
				bean.setClassteacher(RS.getString("classteacher"));
				bean.setDate(RS.getTimestamp("b_date"));
				list.add(bean);
			} // while end
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return list;
	}
}
