package geomex.notice.model;

import javax.validation.constraints.NotBlank;

public class BoardVo {
	
	private int boardId;
	@NotBlank(message="제목 사항은 필수입니다.")
	private String boardTitle;
	@NotBlank(message="내용 사항은 필수입니다.")
	private String boardContent;
	private String boardDate;
	private String userId;
	private String userName;
	private int viewsCount;
	public int getViewsCount() {
		return viewsCount;
	}
	public void setViewsCount(int viewsCount) {
		this.viewsCount = viewsCount;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
