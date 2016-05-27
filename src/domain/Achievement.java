package domain;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Achievement {

	public final static String ID = "id";
	public final static String ICON = "icon";
	public final static String NAME = "name";
	public final static String DESCRIPTION = "description";
	public final static String REQUIREMENT = "requirement";
	public final static String TYPE = "type";
	public final static String FLAGS = "flags";
	public final static String TIERS = "tiers";
	public final static String REWARDS = "rewards";
	public final static String BITS = "bits";
	public final static String POINT_CAP = "point_cap";

	private Integer id;
	private String icon;
	private String name;
	private String description;
	private String requirement;
	private String type;
	private List<String> flags;
	private List<Tier> tiers;
	private List<Reward> rewards;
	private List<Bit> bits;
	private Integer pointCap;

	public Achievement(JSONObject json) {
		fromJSON(json);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getFlags() {
		return flags;
	}

	public void setFlags(List<String> flags) {
		this.flags = flags;
	}

	public List<Tier> getTiers() {
		return tiers;
	}

	public void setTiers(List<Tier> tiers) {
		this.tiers = tiers;
	}

	public List<Reward> getRewards() {
		return rewards;
	}

	public void setRewards(List<Reward> rewards) {
		this.rewards = rewards;
	}

	public List<Bit> getBits() {
		return bits;
	}

	public void setBits(List<Bit> bits) {
		this.bits = bits;
	}

	public Integer getPointCap() {
		return pointCap;
	}

	public void setPointCap(Integer pointCap) {
		this.pointCap = pointCap;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Achievement [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (icon != null) {
			builder.append("icon=");
			builder.append(icon);
			builder.append(", ");
		}
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (description != null) {
			builder.append("description=");
			builder.append(description);
			builder.append(", ");
		}
		if (requirement != null) {
			builder.append("requirement=");
			builder.append(requirement);
			builder.append(", ");
		}
		if (type != null) {
			builder.append("type=");
			builder.append(type);
			builder.append(", ");
		}
		if (flags != null) {
			builder.append("flags=");
			builder.append(flags);
			builder.append(", ");
		}
		if (tiers != null) {
			builder.append("tiers=");
			builder.append(tiers);
			builder.append(", ");
		}
		if (rewards != null) {
			builder.append("rewards=");
			builder.append(rewards);
			builder.append(", ");
		}
		if (bits != null) {
			builder.append("bits=");
			builder.append(bits);
			builder.append(", ");
		}
		if (pointCap != null) {
			builder.append("pointCap=");
			builder.append(pointCap);
		}
		builder.append("]");
		return builder.toString();
	}

	public void fromJSON(JSONObject json) {

		if (json.has(ID)) {
			setId(json.getInt(ID));
		}
		if (json.has(ICON)) {
			setIcon(json.getString(ICON));
		}
		if (json.has(NAME)) {
			setName(json.getString(NAME));
		}
		if (json.has(DESCRIPTION)) {
			setDescription(json.getString(DESCRIPTION));
		}
		if (json.has(REQUIREMENT)) {
			setRequirement(json.getString(REQUIREMENT));
		}
		if (json.has(TYPE)) {
			setType(json.getString(TYPE));
		}
		if (json.has(FLAGS)) {
			List<String> strings = new ArrayList<String>();
			JSONArray array = json.getJSONArray(Achievement.FLAGS);
			for (int i = 0; i < array.length(); i++) {
				strings.add(array.getString(i));
			}
			setFlags(strings);
		}
		if (json.has(TIERS)) {
			List<Tier> tiers = new ArrayList<Tier>();
			JSONArray array = json.getJSONArray(Achievement.TIERS);
			for (int i = 0; i < array.length(); i++) {
				tiers.add(new Tier(array.getJSONObject(i)));
			}
			setTiers(tiers);
		}
		if (json.has(REWARDS)) {
			List<Reward> rewards = new ArrayList<Reward>();
			JSONArray array = json.getJSONArray(Achievement.REWARDS);
			for (int i = 0; i < array.length(); i++) {
				rewards.add(new Reward(array.getJSONObject(i)));
			}
			setRewards(rewards);
		}
		if (json.has(BITS)) {
			List<Bit> bits = new ArrayList<Bit>();
			JSONArray array = json.getJSONArray(Achievement.BITS);
			for (int i = 0; i < array.length(); i++) {
				bits.add(new Bit(array.getJSONObject(i)));
			}
			setBits(bits);
		}
		if (json.has(POINT_CAP)) {
			setPointCap(json.getInt(POINT_CAP));
		}
	}
}
