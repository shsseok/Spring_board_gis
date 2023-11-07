var mapsetting = {
	_daumOrigin: [-30000, -60000],
	_daumResolutions: [2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1, 0.5, 0.25, 0.125],
	
	_daumProjection:function()
	{
		return new ol.proj.get('EPSG:5181');
	},

	createMap : function() {
		return new ol.Map({
			controls : ol.control.defaults().extend(
					[ new ol.control.FullScreen() ]),
			renderer : "webgl",
			logo : false,
			target : "map",
			layers : [],
			interactions : ol.interaction.defaults({
				dragPan : false,
				mouseWheelZoom : false
			}).extend([ new ol.interaction.DragPan({
				kinetic : false
			}), new ol.interaction.MouseWheelZoom({
				duration : 2000
			}) ]),
			view : new ol.View({
				projection : ol.proj.get("EPSG:5186"),
				center : [ 263846.4536899561, 586688.9485874075 ],
				zoom : 11,
				minZoom : 8,
				maxZoom : 23
			})

		});
	},
	createDaumLayer:function()
	{
		return new ol.layer.Tile({
			name : "다음맵",
			visible : true,
			source : new ol.source.XYZ({
				projection : this._daumProjection(), // 투영방법 어떤식으로 투영할 것인지
				tileUrlFunction : function(coordinate) {// 타일을 깔기 위해서
					var level = 14 - coordinate[0];// 타일을 가져오기 위해서 설정 이건 z축이다
													// coordinate[z,x,y] 배열 형식으로
					var row = (coordinate[2] * -1) - 1;
					var col = coordinate[1];
					var subdomain = ((level + col) % 4) + 1;
					return "http://map" + subdomain + ".daumcdn.net/map_2d/1909dms/L"
							+ level + "/" + row + "/" + col + ".png";// 타일이 필요할 때 계속
																		// 요청
				},
				tileGrid : new ol.tilegrid.TileGrid({
					origin : this._daumOrigin,
					resolutions :this. _daumResolutions
				})
			}),
		});
	}	
}