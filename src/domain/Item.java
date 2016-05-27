package domain;

import org.json.JSONObject;

public class Item {

	public static final String ID = "id";
	public static final String NAME = "name";

	// TODO

	private Integer id;
	private String name;

	public Item() {
	}

	public Item(JSONObject json) {
		fromJSON(json);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Item [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (name != null) {
			builder.append("name=");
			builder.append(name);
		}
		builder.append("]");
		return builder.toString();
	}

	public void fromJSON(JSONObject json) {
		if (json.has(ID)) {
			setId(json.getInt(ID));
		}
		if (json.has(NAME)) {
			setName(json.getString(NAME));
		}
		// TODO
	}
}
