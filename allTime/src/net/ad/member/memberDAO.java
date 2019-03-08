package net.ad.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import net.ad.member.memberDTO;

public class memberDAO {
	Connection CN = null;
	PreparedStatement PST;
	Statement ST;
	ResultSet RS;

	public memberDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			CN = DriverManager.getConnection(url, "project", "project");
			
		} catch (Exception ex) {
			System.out.println("db error:" + ex);
		}
	}

	public int dbInsert(memberDTO bean) {
		try {
			String msg = "insert into member values(?,?,sysdate,?,?,?,?)";
			PST = CN.prepareStatement(msg);
			PST.setString(1, bean.getId());
			PST.setString(2, bean.getPwd());
			PST.setString(3, bean.getEmail());
			PST.setString(4, bean.getName());
			PST.setString(5, bean.getNickname());
			PST.setString(6, bean.getCollege());
			return PST.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return -1;
	}// end

	   public int dbModid(memberDTO bean) {
		      try {
		         String msg = "update member set m_date = sysdate,email = ?,name=?,nickname=?,college=? where id = '"+bean.getId()+"'";
		         PST = CN.prepareStatement(msg);
		         PST.setString(1, bean.getEmail());
		         PST.setString(2, bean.getName());
		         PST.setString(3, bean.getNickname());
		         PST.setString(4, bean.getCollege());
		         return PST.executeUpdate();
		      }catch(Exception ex) {
		         System.out.println("Error : " + ex);
		      }
		      return -1;
		   }
	public int dbcheckid(memberDTO bean) {
		try {
			
			String msg = "select count(*) as cnt from member where id=" + "'" + bean.getId() + "'";
			ST = CN.createStatement();
			RS = ST.executeQuery(msg);
			RS.next();
			int result = RS.getInt("cnt");
			return result;

		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return -1;
	}

	public int dblogincheck(memberDTO bean) {
		try {
			String msg = "select count(*) as cnt from member where id=" + "'" + bean.getId() + "'" + " and pw=" + "'"
					+ bean.getPwd() + "'";
			ST = CN.createStatement();
			RS = ST.executeQuery(msg);
			RS.next();
			int result = RS.getInt("cnt");
			return result;

		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return -1;
	}

	public String dbgetnickname(memberDTO bean) {
		try {
			String msg = "select nickname from member where id=" + "'" + bean.getId() + "'";
			ST = CN.createStatement();
			RS = ST.executeQuery(msg);
			RS.next();
			String result = RS.getString("nickname");
			return result;

		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}

		return "";
	}
	public String dbgetCollege(memberDTO bean) {
		try {
			String msg = "select college from member where id=" + "'" + bean.getId() + "'";
			ST = CN.createStatement();
			RS = ST.executeQuery(msg);
			RS.next();
			String result = RS.getString("college");
			return result;

		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}

		return "";
	}

	public memberDTO dbgetinfo(memberDTO bean) {
		memberDTO member = new memberDTO();

		try {
			String msg = "select * from member where id='" + bean.getId() + "'";
			ST = CN.createStatement();
			RS = ST.executeQuery(msg);
			RS.next();
			member.setId(RS.getString("id"));
			member.setName(RS.getString("name"));
			member.setNickname(RS.getString("nickname"));
			member.setEmail(RS.getString("email"));
			member.setCollege(RS.getString("college"));
			member.setDate(RS.getTimestamp("m_date"));
			return member;
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}

		return null;
	}

	public int deleteId(String id) {
		try {
			String msg = "delete from member where id = '" + id+"'";
			ST = CN.createStatement();
			return ST.executeUpdate(msg);

		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return -1;
	}
}
