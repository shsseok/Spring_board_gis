var selectedFeature = null;
var highlightedFeatures=[];
var clickEventHandler = {
    overlay: null,

    overInit: function() {
    	var container = document.createElement('div');
    	container.className = 'popup';
    	document.body.appendChild(container);
    	this.overlay = new ol.Overlay({           
        	element:container,
        	autoPan: true,
            autoPanAnimation: {
                duration: 250
            }
        });
        map.addOverlay(this.overlay);
    },   

    cctvHandle: function(cctvLayer) {
        var that = this;                       
        map.on("click", function(e) {
            map.forEachFeatureAtPixel(e.pixel, function(feature, layer) {
                if (layer === cctvLayer) {
                    let values = feature.getProperties();
                    let popupContent = '<div><strong>CCTV 이름:</strong> ' + values.cctv_nm + '</div>';                                                            
                    that.cctvPopup(feature.getGeometry().getCoordinates(), popupContent, cctvLayer);
                }
            }, {
                hitTolerance: 2,
                layerFilter: function(layer) {
                    return layer === cctvLayer;
                }
            });
        });	
    },
    
    sigOnOffHandle: function(sigLayer, sigTag) {
        $(sigTag).on("click", function(e) {
            var visibility = sigLayer.getVisible();
            sigLayer.setVisible(!visibility);
        });
    },
   
    emdCCTVHandle: function(emdLayer) {   
    	
    	var that=this;   
    	map.on('click', function(e) {
    		 if (selectedFeature) {
                 selectedFeature.setStyle(undefined); 
             }
    	    map.forEachFeatureAtPixel(e.pixel, function(feature, layer) {
    	    	var coordinate = map.getCoordinateFromPixel(e.pixel);
    	        if (layer === emdLayer) {
    	        	console.log('무슨동이지',feature);
    	        	feature.setStyle(new ol.style.Style({                     
                        stroke: new ol.style.Stroke({
                            color: '#9E37D1', // 선 색상
                            width: 5 // 선 두께
                        })
                    }));
    	        	highlightedFeatures.push(feature);
    	            selectedFeature = feature;
    	        	var ok=feature.getGeometry().intersectsCoordinate(coordinate);
    	        	console.log('타입이 머야',feature.getGeometry().getType());
    	        	console.log('맞아',ok);
    	        	console.log("WKT변환전",feature.getGeometry()); //객체자체를 넘겨주는 방식이아니라
    	        	
    	            var formatWKT = new ol.format.WKT();
    	            var emdBoundary = formatWKT.writeGeometry(feature.getGeometry()); //WKT로 변환후에 다시 보내준다.   	            
   	           
    	            console.log("WKT변환전",emdBoundary);
    	            $.ajax({
    	                url: 'emd/cctvInEmd.do',
    	                type: 'POST',
    	                contentType: 'application/json',
    	                data: JSON.stringify({ geometry: emdBoundary }),
    	                dataType: 'json',
    	                success: function(response) {    	                   
    	                	   var popupContentElement = document.getElementById('popup-content');
    	                	   var listHtml = '<ul>';
   	                        response.forEach(function(cctvData) {
   	                            listHtml += '<li><a href="#" onclick="clickEventHandler.focus(\'' + cctvData.mgrNo + '\');">' +
   	                            'CCTV명: ' + cctvData.cctvNm +
   	                            '</a></li>';
   	                        });
   	                        listHtml += '</ul>';    	                        
    	                        popupContentElement.innerHTML = listHtml;;   	                  
    	                        $('#popup').show();
    	                },
    	                error: function(xhr, status, error) {
    	                    console.error('cctv데이터를 가져오는데 먼가 문제가 생겼다.', status, error);
    	                }
    	            });
    	        }
    	    }
    	   );
    	});
    	$('#popup-closer').on('click', function() {        
            $('#popup').hide();    
            return false;
        });
    }, 
    focus:function(mgrNo)
    {          
    	var that=this;
    	         
           if (highlightedFeatures.length > 0) {
               highlightedFeatures.forEach(function(feature) {
                   feature.setStyle(undefined); 
               });               
           }
           console.log('focusCCTV',cctvLayer.getSource());
           var cctvLayer = LayerSearch.getLayerByName('cctv');  
           cctvLayer.getSource().forEachFeature(function(feature) {       	   
               if (feature.getId('mgrNo') === 'asset_cctv.' + mgrNo) {
                   var geometry = feature.getGeometry();
                   var coordinate = geometry.getCoordinates();                                   
                   console.log('좌표',coordinate);
                   map.getView().animate({
                       center: coordinate,
                       zoom: 15, 
                       duration: 1000 
                   });                                                     
                   that.highlightFeature(feature);
               }
           });    
           document.getElementById('resetButton').style.display = 'block';
    },
    reset:function()
    {
    	if (highlightedFeatures.length > 0) {
            highlightedFeatures.forEach(function(feature) {
                feature.setStyle(undefined); 
            });
            highlightedFeatures = []; 
        }
    	 map.getView().setCenter([263846.4536899561, 586688.9485874075]);
    	 map.getView().setZoom(11);
    	 document.getElementById('resetButton').style.display = 'none';
    },
    highlightFeature : function(feature)
    {
    	feature.setStyle(new ol.style.Style({
    	    stroke: new ol.style.Stroke({
    	        color: 'yellow', 
    	        width: 4         
    	    }),
    	    fill: new ol.style.Fill({
    	        color: 'rgba(255, 255, 0, 0.2)' 
    	    }),
    	    image: new ol.style.Circle({ 
    	        radius: 10,
    	        fill: new ol.style.Fill({
    	            color: 'rgba(255, 215, 0, 0.6)' 
    	        }),
    	        stroke: new ol.style.Stroke({
    	            color: 'yellow', 
    	            width: 2         
    	        })
    	    })
    	}));
    	highlightedFeatures.push(feature);
    },
    cctvPopup: function(coordinate, PopUpContent, layer) {
    	//console.log('팝업에 들어온 레이어',layer);
        var that = this;
        var element = that.overlay.getElement();

        element.innerHTML = PopUpContent; // 팝업 내용을 설정합니다.

        if(layer.get('name') === 'cctv') {
            that.overlay.setPosition(coordinate); // 팝업 위치를 설정합니다.         
        } 
    }

};
