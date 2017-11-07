package first.sample.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import first.common.common.CommandMap;
import first.sample.service.SampleService;


@Controller
public class SampleController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="sampleService")
	private SampleService sampleService;
	
	@RequestMapping(value="/sample/openBoardList.do")

	public ModelAndView openBoardList(CommandMap commandMap) throws Exception{

	    ModelAndView mv = new ModelAndView("/sample/boardList");
	    Map<String,Object> resultMap = sampleService.selectBoardList(commandMap.getMap());
	    mv.addObject("paginationInfo", (PaginationInfo)resultMap.get("paginationInfo"));
	    mv.addObject("list", resultMap.get("result"));

	     
	    return mv;
	}

	
	@RequestMapping(value="/sample/openBoardWrite.do")
	public ModelAndView openBoardWrite(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/sample/boardWrite");
		
		return mv;
	}
	
	@RequestMapping(value="/sample/insertBoard.do")
	public ModelAndView insertBoard(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.do");
		
		sampleService.insertBoard(commandMap.getMap(), request);
		
		return mv;
	}
	
	@RequestMapping(value="/sample/openBoardDetail.do")
	public ModelAndView openBoardDetail(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/sample/boardDetail");
		
		Map<String,Object> map = sampleService.selectBoardDetail(commandMap.getMap());
		mv.addObject("map", map.get("map"));
		mv.addObject("list", map.get("list"));
		return mv;
	}
	
	@RequestMapping(value="/sample/openBoardUpdate.do")
	public ModelAndView openBoardUpdate(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/sample/boardUpdate");
		
		Map<String,Object> map = sampleService.selectBoardDetail(commandMap.getMap());
		mv.addObject("map", map.get("map"));
		mv.addObject("list", map.get("list"));
		
		return mv;
	}
	
	@RequestMapping(value="/sample/updateBoard.do")
	public ModelAndView updateBoard(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardDetail.do");
		
		sampleService.updateBoard(commandMap.getMap(), request);
		
		mv.addObject("IDX", commandMap.get("IDX"));
		return mv;
	}
	
	@RequestMapping(value="/sample/deleteBoard.do")
	public ModelAndView deleteBoard(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.do");
		
		sampleService.deleteBoard(commandMap.getMap());
		
		return mv;
	}
	
	@RequestMapping(value="/sample/searchBoard.do")
	public ModelAndView searchBoard(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/sample/boardList");
		
		long searchBordListStart = 0;
		long searchBordListEnd = 0;
		long searchFileListStart = 0;
		long searchFileListEnd = 0;
		//원래 검색을 하면 실행할 코드
//		Map<String,Object> resultMap = sampleService.searchBoard(commandMap.getMap());
//		mv.addObject("paginationInfo", (PaginationInfo)resultMap.get("paginationInfo"));
//		mv.addObject("list", resultMap.get("result"));
		
		//내가 생각한 시간을 줄일수 있는 방법
//		searchBordListStart = System.currentTimeMillis();
//		List<Map<String,Object>> resultMap = sampleService.searchBoard(commandMap.getMap());
//		searchBordListEnd = System.currentTimeMillis();
//		
//		
//		searchFileListStart = System.currentTimeMillis();
//		List<Map<String,Object>> fileMap = sampleService.searchFileList(commandMap.getMap());
//		for(int i = 0 ; i < resultMap.size() ; i++){
//			String boardIDX = String.valueOf(resultMap.get(i).get("IDX"));
//			List<Map<String,Object>> myList = new ArrayList<Map<String, Object>>();
//			for(int j = 0; j < fileMap.size(); j++){			
//				if(boardIDX.equals(String.valueOf(fileMap.get(j).get("BOARD_IDX")))){
//					myList.add(fileMap.get(j));
//				}
//			}
//			resultMap.get(i).put("fileList", myList);
//		}
//		searchFileListEnd = System.currentTimeMillis();

		//조인을 해서 가져오는 쿼리
//		searchBordListStart = System.currentTimeMillis();
//		List<BoardListAndFileListVO> resultMap = sampleService.seletBoardListAndFileListWithJoin(commandMap.getMap());
//		searchBordListEnd = System.currentTimeMillis();
//      서버 과제 재현 코드		
		searchBordListStart = System.currentTimeMillis();
		List<Map<String,Object>> resultMap = sampleService.searchBoard(commandMap.getMap());
		searchBordListEnd = System.currentTimeMillis();

		searchFileListStart = System.currentTimeMillis();
		for(int i = 0 ; i < resultMap.size() ; i++){
			
			Object boardIDX = resultMap.get(i).get("IDX");
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("IDX", boardIDX);
			List<Map<String ,Object>> fileList = sampleService.selectFileList(param);
			resultMap.get(i).put("fileList", fileList);
		}
		searchFileListEnd = System.currentTimeMillis();
//		서버 과제 재현 코드 끝		
		log.info(resultMap);
		mv.addObject("list", resultMap);
		log.info("총 걸린시간 :" + (searchFileListEnd - searchBordListStart));
		return mv;
		
	}
}
