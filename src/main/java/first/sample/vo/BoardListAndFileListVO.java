package first.sample.vo;

import java.util.List;

public class BoardListAndFileListVO {
	private int boardIdx;
	private String boardTitle;
	private int hitCnt;
	private String creaDtm;
	private List<FileListVO> fileList;
	public int getBoardId() {
		return boardIdx;
	}
	public void setBoardId(int boardId) {
		this.boardIdx = boardId;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public int getHitCnt() {
		return hitCnt;
	}
	public void setHitCnt(int hitCnt) {
		this.hitCnt = hitCnt;
	}
	public String getCreaDtm() {
		return creaDtm;
	}
	public void setCreaDtm(String creaDtm) {
		this.creaDtm = creaDtm;
	}
	public List<FileListVO> getFileList() {
		return fileList;
	}
	public void setFileList(List<FileListVO> fileList) {
		this.fileList = fileList;
	}
	
	
}
