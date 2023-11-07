package geomex.notice.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import geomex.notice.model.LayerStyleVo;
import geomex.notice.model.LayerVo;
import geomex.notice.service.LayerService;

@RestController //->controller랑은 다르게 json형태로 데이터를 반환(controller와 responsebody가 합쳐진거라 생각)
public class LayerController {
		@Autowired
	    private LayerService layerService;

	    @PostMapping("layer/style.do")
	    public LayerStyleVo getLayerStyle(@RequestParam String layerName, @RequestParam String requestType) {
	    		return layerService.getLayerStyle(layerName,requestType);
	    }
	    @PostMapping("layer/info.do")
	    public ArrayList<LayerVo> getLayerInfo() {
	        return layerService.getLayerInfo();
	    }
	   
}
