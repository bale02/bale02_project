package net.ad.reply;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class replyDAO {
	Connection CN = null;
	PreparedStatement PST;
	Statement ST;
	ResultSet RS;

	public replyDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			CN = DriverManager.getConnection(url, "project", "project");

		} catch (Exception ex) {
			System.out.println("db error:" + ex);
		}
	}

	public int insert_bbs1Reply(replyDTO bean) {
		try {
			String msg = "insert into bbs1_reply values(?,?,?,sysdate,?,bbs1_reply_seq.nextval)";
			PST = CN.prepareStatement(msg);
			PST.setInt(1, bean.getNum());
			PST.setString(2, bean.getNickname());
			PST.setString(3, bean.getContent());
			PST.setString(4, bean.getId());
			return PST.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return -1;

	}
	public int insert_bbs2Reply(replyDTO bean) {
		try {
			String msg = "insert into bbs2_reply values(?,?,?,?,bbs2_reply_seq.nextval,sysdate)";
			PST = CN.prepareStatement(msg);
			PST.setInt(2, bean.getNum());
			PST.setString(3, bean.getNickname());
			PST.setString(4, bean.getContent());
			PST.setString(1, bean.getId());
			return PST.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return -1;

	}

	public ArrayList<replyDTO> get_bbs1reply(int num) {
		ArrayList<replyDTO> list = new ArrayList<>();
		try {
			String msg = "select * from bbs1_reply where num=" + num + " order by b_r_date desc ";

			ST = CN.createStatement();
			RS = ST.executeQuery(msg);

			while (RS.next() == true) {
				replyDTO bean = new replyDTO();
				bean.setNum(RS.getInt("num"));
				bean.setContent(RS.getString("content"));
				bean.setNickname(RS.getString("nickname"));
				bean.setDate(RS.getTimestamp("b_r_date"));
				bean.setId(RS.getString("id"));
				bean.setReplyNum(RS.getInt("reply_num"));
				list.add(bean);
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}

		return list;

	}
	
	public ArrayList<replyDTO> get_bbs2reply(int num) {
		ArrayList<replyDTO> list = new ArrayList<>();
		try {
			String msg = "select * from bbs2_reply where num=" + num + " order by b_r_date desc ";

			ST = CN.createStatement();
			RS = ST.executeQuery(msg);

			while (RS.next() == true) {
				replyDTO bean = new replyDTO();
				bean.setNum(RS.getInt("num"));
				bean.setContent(RS.getString("content"));
				bean.setNickname(RS.getString("nickname"));
				bean.setDate(RS.getTimestamp("b_r_date"));
				bean.setId(RS.getString("id"));
				bean.setReplyNum(RS.getInt("reply_num"));
				list.add(bean);
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}

		return list;

	}
	public int deleteBbs1_Reply(String replynum) {
		try {
			String msg = "delete from bbs1_reply where reply_num ='"+replynum+"'";
			ST = CN.createStatement();
			return ST.executeUpdate(msg);

		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return -1;
	}
	public int deleteBbs2_Reply(String replynum) {
		try {
			String msg = "delete from bbs2_reply where reply_num ='"+replynum+"'";
			ST = CN.createStatement();
			return ST.executeUpdate(msg);

		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return -1;
	}

	public int deleteBbs3_Reply(String replynum) {
		try {
			String msg = "delete from bbs3_reply where reply_num ='"+replynum+"'";
			ST = CN.createStatement();
			return ST.executeUpdate(msg);

		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return -1;
	}

	public int insert_bbs3Reply(replyDTO bean) {
		try {
			String msg = "insert into bbs3_reply values(?,?,?,?,bbs3_reply_seq.nextval,sysdate)";
			PST = CN.prepareStatement(msg);
			PST.setInt(2, bean.getNum());
			PST.setString(3, bean.getNickname());
			PST.setString(4, bean.getContent());
			PST.setString(1, bean.getId());
			return PST.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return -1;
	}

	public ArrayList<replyDTO> get_bbs3reply(int num) {
		ArrayList<replyDTO> list = new ArrayList<>();
		try {
			String msg = "select * from bbs3_reply where num=" + num + " order by b_r_date desc ";

			ST = CN.createStatement();
			RS = ST.executeQuery(msg);

			while (RS.next() == true) {
				replyDTO bean = new replyDTO();
				bean.setNum(RS.getInt("num"));
				bean.setContent(RS.getString("content"));
				bean.setNickname(RS.getString("nickname"));
				bean.setDate(RS.getTimestamp("b_r_date"));
				bean.setId(RS.getString("id"));
				bean.setReplyNum(RS.getInt("reply_num"));
				list.add(bean);
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}

		return list;

	}
}
