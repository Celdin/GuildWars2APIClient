package domain;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Inventory {
	public final static String ID = "id";
	public final static String ITEM_ID = "item_id";
	public final static String COUNT = "count";
	public final static String SKIN = "skin";
	public final static String UPGRADES = "upgrades";
	public final static String INFUSION = "infusions";
	public final static String BINDING = "binding";
	public final static String BOUND_TO = "bound_to";

	private Integer id;
	private Integer count;
	private Integer skin;
	private List<Integer> upgrades;
	private List<Integer> infusions;
	private String binding;
	private String boundTo;

	public Inventory(JSONObject json) {
		fromJSON(json);
	}

	public Inventory clone() {
		Inventory inventory = new Inventory();
		inventory.setId(id);
		inventory.setCount(count);
		inventory.setSkin(skin);
		inventory.setUpgrades(upgrades);
		inventory.setInfusions(infusions);
		inventory.setBinding(BINDING);
		inventory.setBoundTo(boundTo);
		return inventory;
	}

	public Inventory() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSkin() {
		return skin;
	}

	public void setSkin(Integer skin) {
		this.skin = skin;
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

	public String getBinding() {
		return binding;
	}

	public void setBinding(String binding) {
		this.binding = binding;
	}

	public String getBoundTo() {
		return boundTo;
	}

	public void setBoundTo(String boundTo) {
		this.boundTo = boundTo;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Inventory [");
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
		if (skin != null) {
			builder.append("skin=");
			builder.append(skin);
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
		if (binding != null) {
			builder.append("binding=");
			builder.append(binding);
			builder.append(", ");
		}
		if (boundTo != null) {
			builder.append("boundTo=");
			builder.append(boundTo);
		}
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void fromJSON(JSONObject json) {
		if (json.has(ID)) {
			setId(json.getInt(ID));
		}
		if (json.has(ITEM_ID)) {
			setId(json.getInt(ITEM_ID));
		}
		if (json.has(COUNT)) {
			setCount(json.getInt(COUNT));
		}
		if (json.has(SKIN)) {
			setSkin(json.getInt(SKIN));
		}
		if (json.has(UPGRADES)) {
			List<Integer> upgrades = new ArrayList<Integer>();
			JSONArray array = json.getJSONArray(UPGRADES);
			for (int i = 0; i < array.length(); i++) {
				upgrades.add(array.getInt(i));
			}
			setUpgrades(upgrades);
		}
		if (json.has(INFUSION)) {
			List<Integer> infusions = new ArrayList<Integer>();
			JSONArray array = json.getJSONArray(INFUSION);
			for (int i = 0; i < array.length(); i++) {
				infusions.add(array.getInt(i));
			}
			setUpgrades(infusions);
		}
		if (json.has(BINDING)) {
			setBinding(json.getString(BINDING));
		}
		if (json.has(BOUND_TO)) {
			setBoundTo(json.getString(BOUND_TO));
		}
	}
}
