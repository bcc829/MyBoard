package first.restful.service;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

public interface RestfulService {

	String getUserInfoByJson(String USER_ID);

	String getUserInfoAllByJson();
	
}
