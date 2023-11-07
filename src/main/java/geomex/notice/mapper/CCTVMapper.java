package geomex.notice.mapper;

import java.util.ArrayList;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import geomex.notice.model.CctvVo;
import geomex.notice.model.CoordinateVo;
import geomex.notice.model.GeometryVo;

@Mapper
public interface CCTVMapper {
	ArrayList<CctvVo> selectCctvInEmd(GeometryVo geometry);

	int selectEmdCctvCount(GeometryVo geometry);

	int selectCCTVCountInBuffer(CoordinateVo coordinate);
}
