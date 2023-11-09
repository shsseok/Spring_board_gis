package geomex.notice.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import geomex.notice.model.BoardVo;
import geomex.notice.model.BookMarkVo;
import geomex.notice.service.BoardService;
import geomex.notice.service.BookmarkService;
import geomex.notice.session.SessionConst;

@RestController
public class BookMarkController {
	@Autowired
	private BookmarkService bookmarkService;
	@Autowired
	private BoardService boardService;

	// 북마크 추가
	@PostMapping("/board/{boardId}/AddBookmark")
	public int addBookmark(@SessionAttribute(name = SessionConst.USER_ID, required = false) String userId,
			@PathVariable int boardId) {
		
		int isSuccess = 0;
		if (userId != null) {
			BookMarkVo bookmarkVo =new BookMarkVo();
			bookmarkVo.setBoardId(boardId);
			bookmarkVo.setUserId(userId);
			bookmarkService.bookInsert(bookmarkVo);
			return isSuccess;
		}
		return isSuccess;

	}

	// 북마크 제거
	@PostMapping("/board/{boardId}/RemoveBookmark")
	public int removeBookmark(@SessionAttribute(name = SessionConst.USER_ID, required = false) String userId,
			@PathVariable int bookmarkId) {
		int isSuccess = 0;
		if (userId != null) {
			bookmarkService.bookDelete(bookmarkId);
			return isSuccess;
		}
		return isSuccess;
	}
	
	@GetMapping("/board/{boardId}/RemoveBookmark")
	public int findBookmark(@SessionAttribute(name = SessionConst.USER_ID, required = false) String userId,
			@PathVariable int bookmarkId) {
		int isSuccess = 0;
		if (userId != null) {
			bookmarkService.bookDelete(bookmarkId);
			return isSuccess;
		}
		return isSuccess;
	}

	/*// 북마크 목록 조회
	@GetMapping("/user/{userId}/Bookmarks")
	public ArrayList<BoardVo> getBookmarks(@PathVariable String userId) {
		
		
	}*/
}
