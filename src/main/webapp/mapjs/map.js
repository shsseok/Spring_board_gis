var console = window.console || {
    log: function () {}
};
var map;
$(document).ready(function () {	
	map=mapsetting.createMap();
	map.addLayer(mapsetting.createDaumLayer());
	LayerSource.loadLayerSource();	
	//CCTV정보를 보여주기 위한 태그들과 레이어 핸들러에서 쓰인다.
	var emdLayer = LayerSearch.getLayerByName('읍면동')
	var cctvLayer = LayerSearch.getLayerByName('cctv');	
	//시군구 핸들링
	var sigLayer = LayerSearch.getLayerByName('시군구');
	var sigTag= document.getElementById('sigLayer');
	
	
	clickEventHandler.overInit();
	clickEventHandler.cctvHandle(cctvLayer);
	clickEventHandler.sigOnOffHandle(sigLayer,sigTag);			
	clickEventHandler.emdCCTVHandle(emdLayer);
	
	/*clickEventHandler.cctvPixelClick();*/
});