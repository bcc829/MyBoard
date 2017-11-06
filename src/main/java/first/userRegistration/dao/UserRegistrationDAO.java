package first.userRegistration.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import first.common.dao.AbstractDAO;

@Repository("userRegistrationDAO")
public class UserRegistrationDAO extends AbstractDAO {
	public void insertUserInfo(Map<String, Object> params){
		super.insert("insertUserInfo", params);
	}
	public boolean checkDuplicateId(Map<String, Object> params){
		if(super.selectOne("selectUserId", params)!=null){
			return true;
		}
		else{
			return false;
		}
	}
}
