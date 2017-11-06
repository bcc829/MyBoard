package first.restful.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import first.restful.dao.RestfulDAO;

@Service("restfulService")
public class RestfulServiceImpl implements RestfulService {
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	@Resource(name="restfulDAO")
	RestfulDAO restfulDAO;
	
	@Override
	public String getUserInfoByJson(String USER_ID)  {
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> userInfo = new HashMap<String, Object>();
		ObjectMapper om = new ObjectMapper();

		param.put("USER_ID", USER_ID);
		userInfo = restfulDAO.getUserInfo(param);
		if(userInfo == null){
			return "{\"result\": \"false\"}";
		}
		else{
			String jsonStr = "";
			try {
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("result", "true");
				json.put("user", userInfo);
				jsonStr = om.writeValueAsString(json);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return jsonStr;
		}
	}

	@Override
	public String getUserInfoAllByJson() {
		ObjectMapper om = new ObjectMapper();
		List<Map<String, Object>> userInfoAll = restfulDAO.getUserInfoAll();
		if(userInfoAll == null){
			return "{\"result\": \"false\"}";
		}
		
		else{
			String jsonStr = "";
			try {
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("result", "true");
				json.put("user", userInfoAll);
				jsonStr = om.writeValueAsString(json);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return jsonStr;
		}
	}

}
