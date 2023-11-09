package geomex.notice.service;

import org.springframework.stereotype.Service;

import geomex.notice.mapper.BookmarkMapper;
import geomex.notice.model.BookMarkVo;

@Service
public class BookmarkService {
	BookmarkMapper bookmarkMapper;
	public int bookInsert(BookMarkVo bookmarkVo)
	{	
		return bookmarkMapper.insertBookmark(bookmarkVo);
	}
	public int bookDelete(int bookmarkId)
	{
		return bookmarkMapper.deleteBookmark(bookmarkId);
	}
	
}
