package domain;

import org.json.JSONObject;

public class Reward {
	public final static String TYPE = "type";
	public final static String ID = "id";
	public final static String COUNT = "count";
	public final static String REGION = "region";

	private String type;
	private Integer id;
	private Integer count;
	private String region;

	public Reward(JSONObject json) {
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Reward [");
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
		if (count != null) {
			builder.append("count=");
			builder.append(count);
			builder.append(", ");
		}
		if (region != null) {
			builder.append("region=");
			builder.append(region);
		}
		builder.append("]");
		return builder.toString();
	}

	public void fromJSON(JSONObject json) {
		if (json.has(TYPE)) {
			setType(json.getString(TYPE));
			if (getType().equals("Item")) {
				if (json.has(ID)) {
					setId(json.getInt(ID));
				}
				if (json.has(COUNT)) {
					setCount(json.getInt(COUNT));
				}
			} else if (getType().equals("Mastery")) {
				if (json.has(REGION)) {
					setRegion(json.getString(REGION));
				}
			}
		}
	}
}
