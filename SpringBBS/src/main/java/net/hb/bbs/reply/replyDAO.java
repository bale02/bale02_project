package net.hb.bbs.reply;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class replyDAO {
	
	@Autowired
	SqlSessionTemplate temp;

	public List<replyDTO> dbSelect(int data) {
		
		return temp.selectList("bbs_reply.select",data);
	}

	public void dbInsert(replyDTO dto) {
		temp.insert("bbs_reply.replyadd",dto);
	}

	public void dbDelete(int idx) {
		temp.delete("bbs_reply.delete",idx);
		
	}
	
}
