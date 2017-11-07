package first.sample.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import first.common.util.FileUtils;
import first.sample.dao.SampleDAO;
import first.sample.vo.BoardListAndFileListVO;

@Service("sampleService")
public class SampleServiceImpl implements SampleService{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Resource(name="sampleDAO")
	private SampleDAO sampleDAO;
	
	@Override
	@Cacheable(value="boardList")
	public Map<String, Object> selectBoardList(Map<String, Object> map) throws Exception  {
		
		Map<String, Object> boardList = sampleDAO.selectBoardList(map);

		return boardList;
	}

	@Override
	public void insertBoard(Map<String, Object> map, HttpServletRequest request) throws Exception {
		//map.put("CREA_ID", session.getAttribute("USER_ID"));
		map.put("CREA_ID", request.getSession().getAttribute("USER_ID"));
	    //script실행 방지용 코드 삽입
	    String encodeTitle = (String) map.get("TITLE");
	    String encodeContents = (String) map.get("CONTENTS");
	    encodeTitle = encodeTitle.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	    encodeContents = encodeContents.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	    map.put("TITLE", encodeTitle);
	    map.put("CONTENTS", encodeContents);
	    
		sampleDAO.insertBoard(map);
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(map, request);
		for(int i=0, size=list.size(); i<size; i++){
			sampleDAO.insertFile(list.get(i));
		}
	}

	@Override
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception {
		sampleDAO.updateHitCnt(map);

	    Map<String, Object> resultMap = new HashMap<String,Object>();
	    Map<String, Object> tempMap = sampleDAO.selectBoardDetail(map);
	    
	    //script실행 방지용 코드 삽입
	    String encodeTitle = (String) tempMap.get("TITLE");
	    String encodeContents = (String) tempMap.get("CONTENTS");
	    encodeTitle = encodeTitle.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	    encodeContents = encodeContents.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	    tempMap.put("TITLE", encodeTitle);
	    tempMap.put("CONTENTS", encodeContents);
	 
	    
	    resultMap.put("map", tempMap);	     
	    List<Map<String,Object>> list = sampleDAO.selectFileList(map);
	    resultMap.put("list", list);
	    return resultMap;
	}

	@Override
	public void updateBoard(Map<String, Object> map, HttpServletRequest request) throws Exception{
		sampleDAO.updateBoard(map);
		sampleDAO.deleteFileList(map);
	    List<Map<String,Object>> list = fileUtils.parseUpdateFileInfo(map, request);
	    Map<String,Object> tempMap = null;
	    for(int i=0, size=list.size(); i<size; i++){
	        tempMap = list.get(i);
	        if(tempMap.get("IS_NEW").equals("Y")){
	            sampleDAO.insertFile(tempMap);
	        }
	        else{
	            sampleDAO.updateFile(tempMap);
	        }
	    }

	}

	@Override
	public void deleteBoard(Map<String, Object> map) throws Exception {
		sampleDAO.deleteBoard(map);
	}

	@Override
	@Cacheable(value="boardList")
	public List<Map<String, Object>> searchBoard(Map<String, Object> map)
			throws Exception {

		return sampleDAO.searchBoard(map);
	}

	@Override
	public List<Map<String, Object>> selectFileList(Map<String, Object> map) throws Exception{
		return sampleDAO.selectFileList(map);
	}

	@Override
	@Cacheable(value="fileList")
	public List<Map<String, Object>> searchFileList(Map<String, Object> map)
			throws Exception {
		return sampleDAO.searchFileList(map);
	}

	@Override
	public List<BoardListAndFileListVO> seletBoardListAndFileListWithJoin(
			Map<String, Object> map) throws Exception {
		return sampleDAO.seletBoardListAndFileListWithJoin(map);
	}

}
