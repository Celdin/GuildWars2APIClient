package domain;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Equipement {
	public final static String ID = "id";
	public final static String SLOT = "slot";
	public final static String UPGRADES = "upgrades";
	public final static String INFUSION = "infusions";
	public final static String SKIN = "skin";

	private Integer id;
	private String slot;
	private List<Integer> upgrades;
	private List<Integer> infusions;
	private Integer skin;

	public Equipement(JSONObject json) {
		fromJSON(json);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	public List<Integer> getUpgrades() {
		return upgrades;
	}

	public void setUpgrades(List<Integer> upgrades) {
		this.upgrades = upgrades;
	}

	public List<Integer> getInfusions() {
		return infusions;
	}

	public void setInfusions(List<Integer> infusions) {
		this.infusions = infusions;
	}

	public Integer getSkin() {
		return skin;
	}

	public void setSkin(Integer skin) {
		this.skin = skin;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Equipement [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (slot != null) {
			builder.append("slot=");
			builder.append(slot);
			builder.append(", ");
		}
		if (upgrades != null) {
			builder.append("upgrades=");
			builder.append(upgrades);
			builder.append(", ");
		}
		if (infusions != null) {
			builder.append("infusions=");
			builder.append(infusions);
			builder.append(", ");
		}
		if (skin != null) {
			builder.append("skin=");
			builder.append(skin);
		}
		builder.append("]");
		return builder.toString();
	}

	public void fromJSON(JSONObject json) {
		if (json.has(ID)) {
			setId(json.getInt(ID));
		}
		if (json.has(SLOT)) {
			setSlot(json.getString(SLOT));
		}
		if (json.has(UPGRADES)) {
			JSONArray array = json.getJSONArray(UPGRADES);
			List<Integer> upgrades = new ArrayList<Integer>();
			for (int i = 0; i < array.length(); i++) {
				upgrades.add(array.getInt(i));
			}
			setUpgrades(upgrades);
		}
		if (json.has(INFUSION)) {
			JSONArray array = json.getJSONArray(INFUSION);
			List<Integer> infusions = new ArrayList<Integer>();
			for (int i = 0; i < array.length(); i++) {
				infusions.add(array.getInt(i));
			}
			setInfusions(infusions);
		}
		if (json.has(SKIN)) {
			setSkin(json.getInt(SKIN));
		}
	}
}
