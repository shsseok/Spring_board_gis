package geomex.notice.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import geomex.notice.mapper.LayerMapper;
import geomex.notice.model.LayerStyleVo;
import geomex.notice.model.LayerVo;

@Service
public class LayerService {
	@Autowired
	LayerMapper layerMapper;
	public LayerStyleVo getLayerStyle(String layerName,String requestType)
	{
		return layerMapper.selectLayerStyle(layerName,requestType);
	}
	public ArrayList<LayerVo> getLayerInfo()
	{
		return layerMapper.selectLayerInfo();
	}
	
}
