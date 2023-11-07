package geomex.notice.mapper;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import geomex.notice.model.UserVo;

@Mapper
public interface UserMapper {
	UserVo findByUserId(String userId);	
	void insertUser(UserVo uservo);
}
