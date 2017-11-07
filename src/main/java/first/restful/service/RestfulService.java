package first.restful.service;



public interface RestfulService {

	String getUserInfoByJson(String USER_ID);

	String getUserInfoAllByJson();

	String getBoardAllByJson();

	String getBoardInfo(String IDX);
	
}
