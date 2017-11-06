package first.userRegistration.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import first.userRegistration.dao.UserRegistrationDAO;

@Service("userRegistrationService")
public class UserRegistrationServiceImpl implements UserRegistrationService {

	@Resource(name = "userRegistrationDAO")
	UserRegistrationDAO userRegistrationDAO;

	@Override
	public boolean insertUserInfo(Map<String, Object> params) {

		if (!userRegistrationDAO.checkDuplicateId(params)) {
			userRegistrationDAO.insertUserInfo(params);
			return true;
		} else {
			return false;
		}
	}

}
