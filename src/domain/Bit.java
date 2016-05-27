package domain;

import org.json.JSONObject;

public class Bit {
	public final static String TYPE = "type";
	public final static String ID = "id";
	public final static String TEXT = "text";

	private String type;
	private Integer id;
	private String text;

	public Bit(JSONObject json) {
		fromJSON(json);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bit [");
		if (type != null) {
			builder.append("type=");
			builder.append(type);
			builder.append(", ");
		}
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (text != null) {
			builder.append("text=");
			builder.append(text);
		}
		builder.append("]");
		return builder.toString();
	}

	public void fromJSON(JSONObject json) {
		if (json.has(TYPE)) {
			setType(json.getString(TYPE));
		}
		if (json.has(ID)) {
			setId(json.getInt(ID));
		}
		if (json.has(TEXT)) {
			setText(json.getString(TEXT));
		}
	}
}
