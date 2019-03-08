package net.hb.bbs;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class bbsDAO {
	
	@Autowired
	SqlSessionTemplate temp;
	
	public void dbinsert(bbsDTO dto) {
		
		temp.insert("bbs.add",dto);
	}

	public List<bbsDTO> dbSelect(int start,int end,String skey,String sval) {
		bbsSearchPaging dto = new bbsSearchPaging();
		dto.setStart(start);
		dto.setEnd(end);
		dto.setSkey(skey);
		dto.setSval(sval);
		List<bbsDTO> list = temp.selectList("bbs.select",dto);
		for(int i=0; i<list.size(); i++) {
			int cnt=list.get(i).getCnt();
		}
		return list;
	}
	
	public int dbCount() {
		int cnt = (Integer)temp.selectOne("bbs.count");
		return cnt;
	}
	
	public int dbCount(String skey,String sval) {
		bbsSearchPaging dto = new bbsSearchPaging();
		dto.setSkey(skey);
		dto.setSval(sval);
		int cnt = (Integer)temp.selectOne("bbs.countOne",dto);
		return cnt;
	}

	public bbsDTO dbDetail(int data) {
		return temp.selectOne("bbs.detail",data);
	}

	public void dbDelete(int data) {
		temp.delete("bbs.delete",data);
	}
	
	public void dbEdit(bbsDTO dto) {
		temp.update("bbs.edit",dto);
	}
}
