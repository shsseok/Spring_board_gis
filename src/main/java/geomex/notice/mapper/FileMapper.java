package geomex.notice.mapper;

import java.util.ArrayList;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import geomex.notice.model.FileVo;

@Mapper
public interface FileMapper {
	ArrayList<FileVo> selectFileById(int boardId);
}
