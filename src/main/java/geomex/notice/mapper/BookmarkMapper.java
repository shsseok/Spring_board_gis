package geomex.notice.mapper;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import geomex.notice.model.BookMarkVo;


@Mapper
public interface BookmarkMapper {
    
    // 즐겨찾기 추가
    int insertBookmark(BookMarkVo bookmarkVo);
    
    // 즐겨찾기 삭제
    int deleteBookmark(BookMarkVo bookmarkVo);
    
    // 해당 사용자가 그 게시물을 즐겨찾기 하고 있는지 없는지 여부를 알기 위해선
    boolean selectBookmark(BookMarkVo bookmarkVo);
}
