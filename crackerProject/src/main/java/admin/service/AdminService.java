package admin.service;

import java.util.Map;



public interface AdminService {

	public String adminLogin(Map<String, String> map);

	public void adminWrite(Map<String, String> map);

	public Map<String, Object> getadminMemberList(String pg);

	public void adminMemberDelete(String[] check);

	public Map<String, Object> adminMemberSearch(Map<String, String> map);
		
		
	

}
