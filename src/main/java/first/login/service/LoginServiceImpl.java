package first.login.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import first.login.dao.LoginDAO;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Resource(name = "loginDAO")
	private LoginDAO loginDAO;

	@Override
	public boolean checkUser(HttpSession session, Map<String, Object> map)
			throws Exception {

		Map<String, Object> userInfo = new HashMap<String, Object>();
		userInfo = loginDAO.checkUser(map);
		if (userInfo == null) {
			return false;
		} else {
			session.setAttribute("USER_ID", userInfo.get("USER_ID"));
			session.setAttribute("USER_NAME", userInfo.get("USER_NAME"));
			return true;
		}

	}

}
