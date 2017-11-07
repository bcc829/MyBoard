package first.restful.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import first.restful.service.RestfulService;
@Controller
public class RestfulController {
	
	@Resource(name="restfulService")
	RestfulService restfulService;
	
	@RequestMapping(value="/rest/user/{USER_ID}.rest",  method = RequestMethod.GET, produces = "application/json; charset=utf8")
	@ResponseBody
	public String getUserInfo(@PathVariable String USER_ID){
		return restfulService.getUserInfoByJson(USER_ID);
	}
	
	@RequestMapping(value="/rest/user.rest", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	@ResponseBody
	public String getUserAll(){
		return restfulService.getUserInfoAllByJson();
	}
	
	@RequestMapping(value="/rest/board.rest", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	@ResponseBody
	public String getBoardAll(){
		return restfulService.getBoardAllByJson();
	}
	
	@RequestMapping(value="rest/board/{IDX}.rest", method = RequestMethod.GET, produces="application/json; charset=utf8")
	@ResponseBody
	public String getBoardInfo(@PathVariable String IDX){
		return restfulService.getBoardInfo(IDX);
	}
}
