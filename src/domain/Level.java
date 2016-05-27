package domain;

import org.json.JSONObject;

public class Level {
	public final static String MIN = "min";
	public final static String MAX = "max";

	private Integer min;
	private Integer max;

	public Level(JSONObject json) {
		fromJSON(json);
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Level [");
		if (min != null) {
			builder.append("min=");
			builder.append(min);
			builder.append(", ");
		}
		if (max != null) {
			builder.append("max=");
			builder.append(max);
		}
		builder.append("]");
		return builder.toString();
	}

	public void fromJSON(JSONObject json) {
		if (json.has(MIN)) {
			setMin(json.getInt(MIN));
		}
		if (json.has(MAX)) {
			setMax(json.getInt(MAX));
		}
	}

}
