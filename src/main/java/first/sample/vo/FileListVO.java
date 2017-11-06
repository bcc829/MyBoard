package first.sample.vo;

public class FileListVO {
	String originalFileName;
	int fileIdx;
	int fileSize;
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public int getFileId() {
		return fileIdx;
	}
	public void setFileId(int fileId) {
		this.fileIdx = fileId;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
}
