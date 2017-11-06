package first.restful.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import first.common.dao.AbstractDAO;

@Repository("restfulDAO")
public class RestfulDAO extends AbstractDAO{
	@SuppressWarnings("unchecked")
	public Map<String, Object> getUserInfo(Map<String, Object> params){
		return (Map<String, Object>)super.selectOne("selectUserInfo", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getUserInfoAll(){
		return (List<Map<String, Object>>)super.selectList("selectUserInfoAll");
	}


}
