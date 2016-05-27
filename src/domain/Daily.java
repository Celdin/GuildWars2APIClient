package domain;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Daily {
	public final static String ID = "id";
	public final static String LEVEL = "level";
	public final static String REQUIRED_ACCESS = "required_access";

	private Integer id;
	private Level level;
	private List<String> requiredAccess;

	public Daily(JSONObject json) {
		fromJSON(json);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public List<String> getRequiredAccess() {
		return requiredAccess;
	}

	public void setRequiredAccess(List<String> requiredAccess) {
		this.requiredAccess = requiredAccess;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Daily [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (level != null) {
			builder.append("level=");
			builder.append(level);
			builder.append(", ");
		}
		if (requiredAccess != null) {
			builder.append("requiredAccess=");
			builder.append(requiredAccess);
		}
		builder.append("]");
		return builder.toString();
	}

	public void fromJSON(JSONObject json) {
		if (json.has(ID)) {
			setId(json.getInt(ID));
		}
		if (json.has(LEVEL)) {
			setLevel(new Level(json.getJSONObject(LEVEL)));
		}
		if (json.has(REQUIRED_ACCESS)) {
			JSONArray array = json.getJSONArray(REQUIRED_ACCESS);
			List<String> requiredAccess = new ArrayList<String>();
			for (int j = 0; j < array.length(); j++) {
				requiredAccess.add(array.getString(j));
			}
			setRequiredAccess(requiredAccess);
		}
	}
}
