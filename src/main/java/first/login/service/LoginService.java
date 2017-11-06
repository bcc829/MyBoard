package first.login.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

public interface LoginService {
	boolean checkUser(HttpSession session, Map<String, Object> map) throws Exception;
}
