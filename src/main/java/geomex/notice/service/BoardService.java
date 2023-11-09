package geomex.notice.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.io.IOException;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.multipart.MultipartFile;
import egovframework.rte.fdl.property.EgovPropertyService;
import geomex.notice.mapper.BoardMapper;
import geomex.notice.model.BoardVo;
import geomex.notice.model.BookMarkVo;
import geomex.notice.model.FileVo;


@Service
public class BoardService {
	@Autowired
	private BoardMapper boardMapper;
	@Resource(name = "propertiesService")
	private EgovPropertyService propertiesService;
	@Resource(name = "transactionManager")
	private DataSourceTransactionManager tranScationMn;
	
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
		isSuceess = boardMapper.updateBoard(boardVo);
		if(isSuceess==1)
		{
			return isSuceess;
		}
		return isSuceess;
	}
	public int boardDelete(int boardId) {
		int isSuceess = 0;
		isSuceess = boardMapper.deleteBoard(boardId);
		if(isSuceess==1)
		{			
			return isSuceess;
		} 		
		return isSuceess;		
	}
	public String getWriterByBoardId(int boardId) {
        return boardMapper.selectWriter(boardId);
    }
	
	public boolean insertBoardAndFiles(BoardVo boardVo, List<MultipartFile> files) {
		TransactionStatus tsStatus = tranScationMn.getTransaction(new DefaultTransactionDefinition());
		File storeFile = null;
		try {	
			boardMapper.insertBoard(boardVo);			
			int boardId = boardVo.getBoardId();// 반환받은 게시판 id 이 게시판에 파일을 넣어야하기 때문에
			
			if (files != null && !files.isEmpty()) {
				for (MultipartFile file : files) {					
					if (!file.isEmpty()) {
						FileVo fileVo = new FileVo();
						String fileName = file.getOriginalFilename();
						System.out.println(fileName);
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
				}
				
			}
			if(boardId==73)
			{
				throw new RuntimeException("롤백해줘");				
			}
			tranScationMn.commit(tsStatus);
			return true;
		} catch (RuntimeException e) {
			if (storeFile != null && storeFile.exists()) {
	            storeFile.delete();
	        }
			tranScationMn.rollback(tsStatus);
			System.out.println("롤백됨");
			e.printStackTrace();
			return false;
		}
		catch(IOException i)
		{
			tranScationMn.rollback(tsStatus);
			System.out.println("롤백됨");
			i.printStackTrace();
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
