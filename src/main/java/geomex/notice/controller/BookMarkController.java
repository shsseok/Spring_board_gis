package geomex.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import geomex.notice.model.BookMarkVo;
import geomex.notice.service.BookmarkService;
import geomex.notice.session.SessionConst;

@RestController
@RequestMapping("/bookmarks") // 공통 경로 설정
public class BookMarkController {
    @Autowired
    private BookmarkService bookmarkService;

    // 북마크 추가
    @PostMapping("/{boardId}")
    public ResponseEntity<?> addBookmark(@SessionAttribute(name = SessionConst.USER_ID, required = false) String userId,
                                         @PathVariable int boardId) {
        if (userId == null) {
            return ResponseEntity.badRequest().body("해당 사용자에 대한 권한이 없습니다");
        }
        BookMarkVo bookmarkVo = new BookMarkVo();
        bookmarkVo.setBoardId(boardId);
        bookmarkVo.setUserId(userId);
        bookmarkService.bookInsert(bookmarkVo);
        return ResponseEntity.ok("즐겨찾기 완료");
    }

    // 북마크 제거
    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> removeBookmark(@SessionAttribute(name = SessionConst.USER_ID, required = false) String userId,
                                            @PathVariable("boardId") int boardId) {
        if (userId == null) {
            return ResponseEntity.badRequest().body("해당 사용자에 대한 권한이 없습니다");
        }
        BookMarkVo bookmarkVo = new BookMarkVo();
        bookmarkVo.setBoardId(boardId);
        bookmarkVo.setUserId(userId);
        bookmarkService.bookDelete(bookmarkVo);
        return ResponseEntity.ok("즐겨찾기 제거");
    }

    // 북마크 조회
    @GetMapping("/{boardId}/findBySingle")
    public ResponseEntity<?> findBookmark(@SessionAttribute(name = SessionConst.USER_ID, required = false) String userId,
                                          @PathVariable("boardId") int boardId) {    	
        if (userId == null) {
            return ResponseEntity.badRequest().body("해당 사용자에 대한 권한이 없습니다");
        }
        BookMarkVo bookmarkVo = new BookMarkVo();
        bookmarkVo.setBoardId(boardId);
        bookmarkVo.setUserId(userId);
        boolean exists = bookmarkService.bookSelect(bookmarkVo);
        return ResponseEntity.ok(exists);
    }

    // 북마크 목록 조회 (구현 예시)
    /* @GetMapping("/user/{userId}")
    public ResponseEntity<?> getBookmarks(@PathVariable String userId) {
        // 로직 구현
    } */
}
