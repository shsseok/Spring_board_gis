package geomex.notice.model;

public class LayerVo {
	private int id;
	private String layerName;
	private String layerType;
	private String typeName;
	private String requestType;
	public String getRequestType() {
		return requestType;
	}

	public void setRequetType(String requestType) {
		this.requestType = requestType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLayerName() {
		return layerName;
	}

	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}

	public String getLayerType() {
		return layerType;
	}

	public void setLayerType(String layerType) {
		this.layerType = layerType;
	}

}