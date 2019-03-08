package net.hb.bbs;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class memberDAO {
	
	@Autowired
	SqlSessionTemplate temp;
	
	public int dbinsert(memberDTO dto) {
		int count = temp.insert("member.add",dto);
		return count;
	}
	
	public void loginCheck(memberDTO dto) {
		temp.selectOne("member.check",dto);
	}
	
	public int selectAdmin(memberDTO dto) {
		return temp.selectOne("member.idcheck",dto);
	}
}
