package geomex.notice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import geomex.notice.mapper.BookmarkMapper;
import geomex.notice.model.BookMarkVo;

@Service
public class BookmarkService {
	@Autowired
	private BookmarkMapper bookmarkMapper;
	
	public int bookInsert(BookMarkVo bookmarkVo)
	{	
		return bookmarkMapper.insertBookmark(bookmarkVo);
	}
	public int bookDelete(BookMarkVo bookmarkVo)
	{
		return bookmarkMapper.deleteBookmark(bookmarkVo);
	}
	public boolean bookSelect(BookMarkVo bookmarkVo)
	{
		boolean ok=bookmarkMapper.selectBookmark(bookmarkVo);
		System.out.println(ok);
		return ok;
	}
	
}
