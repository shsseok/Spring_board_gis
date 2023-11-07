var LayerSource = {
    loadLayerSource: function() {
        $.ajax({
        	async: false,
            url: 'layer/info.do',
            type: 'POST',
            success: function(infodata) {
               // console.log("layerInfo", infodata);                              
                    infodata.forEach(function(layerInfo) {
                    	//console.log('layerRequestType',layerInfo.requestType);
                    	//console.log('layerTypeName',layerInfo.typeName);
                    	var source;
                        if(layerInfo.requestType === 'WMS') {
                            source = new ol.source.TileWMS({
                                url: 'http://127.0.0.1:8000/xeus/wms',
                                params: {
                                    'LAYERS': layerInfo.typeName,
                                    'FORMAT': 'image/png',
                                    'STYLES': '',
                                    'TRANSPARENT': 'TRUE'
                                }
                            }); 
                            LayerManager.createLayer(layerInfo, source); // WMS 경우에도 레이어를 생성하도록 수정
                        } else{
                        	 
                            source = new ol.source.Vector({
                                strategy: ol.loadingstrategy.bbox,
                                loader: function(extent, resolution, projection) {
                                	//console.log('WFS요청 시작');
                                    var _format = new ol.format.GeoJSON();
                                    $.ajax({
                                        url: "http://127.0.0.1:8000/xeus/wfs",                                       
                                        data: {                                       
                                            service: 'WFS',
                                            version: '1.1.0',
                                            request: 'GetFeature',
                                            typename: layerInfo.typeName, 
                                            outputFormat: 'json',
                                            srsname: 'EPSG:5186',
                                            bbox: extent.join(',') + ',' + 'EPSG:5186'
                                        },
                                        dataType: 'json',
                                        success: function(data) {
                                            var features = _format.readFeatures(data);
                                            //console.log('features',features);
                                            source.addFeatures(features);
                                            //console.log('source들',source);                                                                                          
                                            },
                                        error: function(err) {
                                            console.error("실패", layerInfo.layerName, "err", err);
                                        }
                                    });
                                }
                            });
                            
                            LayerManager.createLayer(layerInfo, source);
                        }                                                    
                    });                                           
            }
        });
    }
	
}
