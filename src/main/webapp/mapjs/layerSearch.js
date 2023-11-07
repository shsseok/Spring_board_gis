var LayerSearch={
	getLayerByName: function(layerName) {
			//console.log('레이어들의 컬렉션',map.getLayers());
	        layers = map.getLayers().getArray();
	        /*console.log("레이어들의 배열",layers);
	        console.log("배열 길이",layers.length);*/
	        /*setTimeout(function(){*/
	        	//console.log(layers)
	        	//console.log(layers.length)
	        	for (let i = 0; i < layers.length; i++) {
	        		
	        		//console.log(layers[i].get('name'));
	        		//console.log('layername',layerName);
	        		if (layers[i].get('name') === layerName) {
	        			return layers[i];
	        		}
	        	}
	        /*}, 1000)*/
	        return null; // 레이어를 찾지 못했을 경우 null 반환
	  }

}


