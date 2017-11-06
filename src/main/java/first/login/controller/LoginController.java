package first.login.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.common.common.CommandMap;
import first.login.service.LoginService;

@Controller
public class LoginController {
	
	@Resource(name="loginService")
	LoginService loginService;
	
	@RequestMapping(value="/Login/LoginPage.do")
	public ModelAndView loginPage(HttpSession session) throws Exception{
		ModelAndView mv;
		if(session.getAttribute("USER_ID") != null && session.getAttribute("USER_NAME") != null){
			String URL = "redirect:/sample/openBoardList.do";
			mv = new ModelAndView(URL);
			return mv;
		}
		else{
			mv = new ModelAndView("/Login/LoginPage");
			return mv;
		}
	}
	
	@RequestMapping(value="/Login/LoginCheck.do")
	public ModelAndView loginCheck(HttpSession session, CommandMap commandMap) throws Exception{
		ModelAndView mv;
		if(loginService.checkUser(session, commandMap.getMap()))
		{
			String URL = "redirect:/sample/openBoardList.do";
			mv = new ModelAndView(URL);
			return mv;
		}
		else{
			String URL = "redirect:/Login/LoginPage.do";
			mv = new ModelAndView(URL);
			return mv;
		}
	}
}
