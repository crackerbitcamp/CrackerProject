package admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import admin.bean.AdminDTO;
import admin.bean.AdminPaging;
import admin.dao.AdminDAO;
import board.bean.BoardDTO;
import board.bean.BoardPaging;
import member.bean.MemberDTO;
import member.dao.MemberDAO;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDAO adminDAO;
	@Autowired
	private MemberDTO memberDTO;
	


	@Override
	public void adminWrite(Map<String, String> map) {
		adminDAO.adminWrite(map);
		
	}

	@Override
	public Map<String, Object> getadminMemberList(String pg) {
		
		//DB - 1페이지당 5개씩
		int endNum = Integer.parseInt(pg) * 3;
		int startNum = endNum - 2;
		System.out.println(startNum+"  /  "+endNum);
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("startNum",startNum);
		map.put("endNum", endNum);
		
		
		List<MemberDTO> list = adminDAO.getadminMemberList(map);
		System.out.println(list);

		
		Map<String,Object> sendMap = new HashMap<String,Object>();
		sendMap.put("list", list);

		
		return sendMap;
	}

	

	
	
	


}