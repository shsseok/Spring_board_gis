package geomex.notice.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import geomex.notice.mapper.FileMapper;
import geomex.notice.model.FileVo;
@Service
public class FileService {
	@Autowired
	private FileMapper fileMapper;
	
	public ArrayList<FileVo> getFileById(int boardId) {
        return fileMapper.selectFileById(boardId);
    }
}
