package geomex.notice.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.io.IOException;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import egovframework.rte.fdl.property.EgovPropertyService;
import geomex.notice.mapper.BoardMapper;
import geomex.notice.model.BoardVo;
import geomex.notice.model.FileVo;


@Service
public class BoardService {
	@Autowired
	private BoardMapper boardMapper;
	@Resource(name = "propertiesService")
	private EgovPropertyService propertiesService;
	@Autowired
	private UserService userService;
	
	public ArrayList<BoardVo> getBoardlist(int offset, int limit) {
		return boardMapper.selectBoardlist(offset, limit);
	}

	public ArrayList<BoardVo> getSearchBoardlist(int offset, int limit, String text, String type) {
		ArrayList<BoardVo> boardSearchList = boardMapper.searchBoardlist(offset, limit, text, type);
		return boardSearchList;
	}

	public BoardVo getBoardById(int boardId) {
		BoardVo boardVo=boardMapper.selectBoardById(boardId);
		return boardVo;
	}
	public void viewsCount(int boardId) {
		boardMapper.updateViewsCount(boardId);
	}
	
	public int boardUpdate(BoardVo boardVo) {
		int isSuceess = 0;		
		if(userCheck(boardVo))
		{
			isSuceess = boardMapper.updateBoard(boardVo);
			return isSuceess;
		} 
		else
		{
			return isSuceess;
		}
	}
	public int boardDelete(BoardVo boardVo) {
		int isSuceess = 0;
		if(userCheck(boardVo))
		{
			isSuceess = boardMapper.deleteBoard(boardVo.getBoardId());
			return isSuceess;
		} 
		else
		{
			return isSuceess;
		}
		
	}
	@Transactional(rollbackFor = RuntimeException.class)
	public boolean insertBoardAndFiles(BoardVo boardVo, List<MultipartFile> files) throws Exception {
	    boolean isCompleted = false;
	    File storeFile = null;
	    try {   
	        boardMapper.insertBoard(boardVo);            
	        int boardId = boardVo.getBoardId();
	        if (files != null && !files.isEmpty()) {
	            for (MultipartFile file : files) {                    
	                if (!file.isEmpty()) {
	                    FileVo fileVo = new FileVo();
	                    String fileName = file.getOriginalFilename();                        
	                    String storedFileName = UUID.randomUUID().toString() + "_" + fileName;

	                    storeFile = new File(
	                           propertiesService.getString("filePath") + File.separator + storedFileName);
	                    
	                    file.transferTo(storeFile);                              
	                    fileVo.setBoardId(boardId);
	                    fileVo.setFileName(fileName);
	                    fileVo.setStoredFileName(storedFileName);
	                    fileVo.setFileSize((int) file.getSize());
	                    fileVo.setFilePath(propertiesService.getString("filePath"));

	                    boardMapper.insertFile(fileVo);
	                }
	                // 테스트 롤백 시나리오
	                if (boardId == 69) {
	                    throw new RuntimeException("트랜잭션 롤백 테스트를 위해서");
	                }
	            }
	        }
	        isCompleted = true;
	    } catch (Exception e) {
	        if (storeFile != null && storeFile.exists()) {
	            storeFile.delete();
	        }
	        throw e; // 예외를 다시 던져서 스프링이 처리하게 함
	    }
	    return isCompleted;
	}

	public boolean userCheck(BoardVo boardVo)
	{
		String userId=userService.getUserId(boardVo.getUserId());
		if(userId!=null && userId.equals(boardVo.getUserId()))
		{
			return true;
		} 
		else
		{
			return false;
		}		
	}
	// 전체 게시판 조회에 대한 페이징 처리를 위해서
	public int getBoardCount() {
		return boardMapper.selectBoardCount();
	}

	// 전체 게시판이 아닌 검색 조회에 대한 페이징 처리를 위해
	public int getSearchBoardCount(String searchText, String searchType) {		 
		return boardMapper.selectSearchCount(searchText, searchType);	
	}

}
