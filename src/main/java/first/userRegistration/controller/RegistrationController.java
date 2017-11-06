package first.userRegistration.controller;

import javax.annotation.Resource;




import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.common.common.CommandMap;
import first.userRegistration.service.UserRegistrationService;

@Controller
public class RegistrationController {
	protected Log log = LogFactory.getLog(this.getClass());
	@Resource(name="userRegistrationService")
	UserRegistrationService userRegistrationService; 
	
	@RequestMapping(value="/userRegistration.do")
	public ModelAndView userRegistrationPage() throws Exception{
		ModelAndView mv = new ModelAndView("/registration/userRegistration");
		return mv;
	}
	
	@RequestMapping(value="/userRegistration/inserUserInfo.do")
	public ModelAndView insertUserInfo(CommandMap commandMap) throws Exception{
		ModelAndView mv;
		String URL;
		log.info(commandMap.getMap());
		if(userRegistrationService.insertUserInfo(commandMap.getMap())){
			URL = "redirect:/Login/LoginPage.do";
			mv = new ModelAndView(URL);
			return mv;
		}
		else{
			URL = "redirect:/userRegistration.do";
			mv = new ModelAndView(URL);
			mv.addObject("DuplicatedId", "중복된 아이디 입니다");
			return mv;
		}
		
	}
}