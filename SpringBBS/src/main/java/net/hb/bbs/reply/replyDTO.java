package net.hb.bbs.reply;

import java.util.Date;
import org.springframework.web.multipart.MultipartFile;

public class replyDTO {
	private int bbsreply_idx;
	private String id;
	private Date r_date;
	private String content;
	private int bbs_idx;
	
	public int getBbsreply_idx() {
		return bbsreply_idx;
	}
	public void setBbsreply_idx(int bbsreply_idx) {
		this.bbsreply_idx = bbsreply_idx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getR_date() {
		return r_date;
	}
	public void setR_date(Date r_date) {
		this.r_date = r_date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getBbs_idx() {
		return bbs_idx;
	}
	public void setBbs_idx(int bbs_idx) {
		this.bbs_idx = bbs_idx;
	}

}
