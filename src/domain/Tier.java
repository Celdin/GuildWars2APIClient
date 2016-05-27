package domain;

import org.json.JSONObject;

public class Tier {
	public final static String COUNT = "count";
	public final static String POINTS = "points";

	private Integer count;
	private Integer points;

	public Tier(JSONObject json) {
		fromJSON(json);
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tier [");
		if (count != null) {
			builder.append("count=");
			builder.append(count);
			builder.append(", ");
		}
		if (points != null) {
			builder.append("points=");
			builder.append(points);
		}
		builder.append("]");
		return builder.toString();
	}

	public void fromJSON(JSONObject json) {
		if (json.has(COUNT)) {
			setCount(json.getInt(COUNT));
		}
		if (json.has(POINTS)) {
			setPoints(json.getInt(POINTS));
		}
	}
}
