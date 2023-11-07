package geomex.notice.page;

public class PageManager {
	private int pageNo;// 현재 페이지의 번호
	private int countPerpage;// 페이지 당 보여지고 싶은 게시물의 개수
	private int limit;// DB LIMIT뒤에 --> 몇개의 페이지를 보여줄 것인가
	private int offset;// DB OFFSET에 --> 몇 번째 부터 시작을 해서 보여줄 것인가
	private int totalPagecount;//전체 페이지 개수 게시물의 양의 따라서 전체페이지 수가 달라짐 ,(검색이라면 검색어와 타입을 넘겨서 해당 개수를 반환해 전체 개수를 센다)
	private int boardCount;// 전체 게시물의 개수 총 게시물의 개수를 통해서 전체 나올 페이지수를 구할 수 있다.

	//전체 페이지 게시물의 개수를 받아온다.
	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}
	
	public int getTotalPagecount() {
	    if (boardCount % countPerpage == 0) { //나누어 떨어질 때는 1을 증가하지 않는데 한페이지에 8개만 보여주는데 2페이지가 나올수 있기때문에
	        totalPagecount = boardCount / countPerpage;
	    } else {
	        totalPagecount = (boardCount / countPerpage) + 1;
	    }
	    return totalPagecount;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public void setCountPerpage(int countPerpage) {
		this.countPerpage = countPerpage;
	}
	// limit 반환 여기서는 반환만한다
	public int getLimit() {
		limit = countPerpage;// 한 페이지에 몇개의 게시물을 보여주는가
		return limit;
	}

	// offset 반환 여기서는 반환만한다
	public int getOffset() {
		offset = (pageNo - 1) * countPerpage;
		return offset;
	}

}
