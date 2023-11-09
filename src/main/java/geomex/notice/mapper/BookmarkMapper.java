package geomex.notice.mapper;

import geomex.notice.model.BookMarkVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookmarkMapper {
    
    // 북마크 추가
    int insertBookmark(BookMarkVo bookmarkVo);
    
    // 북마크 삭제
    int deleteBookmark(int bookmarkId);
    
}
