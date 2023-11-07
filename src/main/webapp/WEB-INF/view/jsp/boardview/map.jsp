<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="mapjs/olsetting/ol-v6.4.3/ol.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/layerlist.css">
<script type="text/javascript"src="mapjs/olsetting/ol-v6.4.3/ol.js"></script>
<script type="text/javascript" src="mapjs/olsetting/proj4js-2.3.15/proj4.js"></script>
<script type="text/javascript"src="mapjs/olsetting/jquery-3.2.0.min.js"></script>
<script type="text/javascript"src="mapjs/setting/ProjSetting.js"></script>
<script type="text/javascript"src="mapjs/setting/MapSetting.js"></script>
<script type="text/javascript"src="mapjs/datasource/Source.js"></script>
<script type="text/javascript"src="mapjs/layerManager.js"></script>
<script type="text/javascript"src="mapjs/map.js"></script>
<script type="text/javascript"src="mapjs/layerSearch.js"></script>
<script type="text/javascript"src="mapjs/layerEvent.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GEOMEX Map Engine</title>
<link rel="icon" href="#">
</head>
<body>
	<div id="map"></div>
	<div id="layer-list">
    <ul>
        <li id="emdLayerContainer">
            <span class="toggle-emd">읍면동</span>
            <ul class="sub-list"  style="display:none">             
            </ul>
        </li>
        <li id="koreaLayer">한반도 경계</li>
        <li id="sigLayer">춘천시 면적</li>
        <li id="cctvLayer">cctv 포인트</li>
    </ul>
    <div id="popup" class="ol-popup">
  	<a href="#" id="popup-closer" class="ol-popup-closer"></a>
  	<div id="popup-content">
  	</div>
  	
</div>
</div>	
</body>
</html>