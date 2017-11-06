package first.login.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import first.common.dao.AbstractDAO;

@Repository("loginDAO")
public class LoginDAO extends AbstractDAO{
	@SuppressWarnings("unchecked")
	public Map<String, Object> checkUser(Map<String, Object> map){
		return (Map<String, Object>)selectOne("SelectUser", map);
	}
}
