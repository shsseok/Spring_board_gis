package geomex.notice.mapper;

import java.util.ArrayList;


import org.apache.ibatis.annotations.Param;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import geomex.notice.model.BoardVo;
import geomex.notice.model.FileVo;

@Mapper
public interface BoardMapper {
	
	void insertBoard(BoardVo boardVo);//밑에와 트랜잭션 
	
	void insertFile(FileVo fileVo);
	
	ArrayList<BoardVo> selectBoardlist(@Param("offset")int offset,@Param("limit")int limit);
	
	ArrayList<BoardVo> searchBoardlist(@Param("offset")int offset,@Param("limit")int limit,@Param("text")String text,@Param("type")String type);
	
	BoardVo selectBoardById(int boardId);
	
	int updateBoard(BoardVo boardvo);
	
	int deleteBoard(int boardId);
	
	int selectBoardCount();	

	int selectSearchCount(@Param("text")String text,@Param("type")String type);
	
	void updateViewsCount(int boardId);
	
	String selectWriter(int boardId);
}
