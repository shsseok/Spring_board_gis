var LayerManager = {// 레이어의 요청 requestType에 따라 Vector를 생성할지 Tile를 생성하지 구분
    requestLayerStyle: function(layerInfo,callback) {
    	// console.log("들어옴",layerInfo.requestType);
    	if(layerInfo.requestType==='WFS') 
    	{
    		$.ajax({
    			async: false,
    	        url: 'layer/style.do',
    	        data: { layerName: layerInfo.layerName, 
    	        		requestType: layerInfo.requestType},
    	        type: 'POST',
    	        success: function(layerStyle) {
    	        	console.log(layerStyle);
    	        	callback(layerStyle);// 콜백함수 함수안에서 함수를 호출할 수 있고 인자를 함수로
											// 받는다.
    	        }
    	    });
    	}
    	else
    	{
    		callback(null);// WMS요청은 따로 스타일 지정을 안한다.
    	}	
    },
    applyLayerStyle: function(layer, layerStyle,layerInfo) {
    	// console.log('레이어 스타일',layerStyle)
        if (layerStyle) {        	      	
            layer.setStyle(function(feature, resolution) {
            	var layerName = layer.get('name');            					
            	if (layerName === 'cctv') {
            			console.log('cctv스타일',layerName);
            			 return new ol.style.Style({
            			        image: new ol.style.Circle({
            			            radius: 5, 
            			            fill: new ol.style.Fill({
            			                color: 'rgba(255, 0, 0, 1)' 
            			            }),
            			            stroke: new ol.style.Stroke({
            			                color: 'rgba(255, 255, 255, 1)', 
            			                width: 1 
            			            })
            			        })
            			 });
                } 
            	else {                    
                    	return new ol.style.Style({
                        stroke: new ol.style.Stroke({
                            color: layerStyle.strokeColor,
                            width: layerStyle.strokeWidth
                        }), 
                        fill: new ol.style.Fill({
                        	color: layerStyle.fillColor
                        }),
                        text: new ol.style.Text({
                            font: layerStyle.textFont,                             
                            text: feature.get('emd_kor_nm'),                                                                              
                            fill: new ol.style.Fill({
                                color: layerStyle.fillColor
                         })                        
                        })
                    });
                }
               
            });
        }
    },   
    createLayer: function(layerInfo,source) {
        // console.log('source',source);
    	var layer;
        if(layerInfo.requestType === 'WFS') {
            layer = new ol.layer.Vector({source: source,
            							 name:layerInfo.layerName});
        } else {
            layer = new ol.layer.Tile({source: source,
            						   name:layerInfo.layerName          						   
            						   });
         
        }
    	this.requestLayerStyle(layerInfo,(layerStyle) => {
            this.applyLayerStyle(layer, layerStyle,layerInfo);// -->함수를호출할때 해
																// // 객체의 함수를
            map.addLayer(layer);             
    	});
    	
    }
};