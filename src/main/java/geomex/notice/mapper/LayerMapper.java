package geomex.notice.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import geomex.notice.model.LayerStyleVo;
import geomex.notice.model.LayerVo;

@Mapper
public interface LayerMapper {
	LayerStyleVo selectLayerStyle(@Param("layerName") String layerName, @Param("requestType") String requestType);
	ArrayList<LayerVo> selectLayerInfo();
}
