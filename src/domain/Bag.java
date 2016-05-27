package domain;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Bag {
	public final static String ID = "id";
	public final static String SIZE = "size";
	public final static String INVENTORY = "inventory";

	private Integer id;
	private Integer size;
	private List<Inventory> inventory;

	public Bag(JSONObject json) {
		fromJSON(json);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public List<Inventory> getInventories() {
		return inventory;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventory = inventories;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bag [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (size != null) {
			builder.append("size=");
			builder.append(size);
			builder.append(", ");
		}
		if (inventory != null) {
			builder.append("inventory=");
			builder.append(inventory);
		}
		builder.append("]");
		return builder.toString();
	}

	public void fromJSON(JSONObject json) {
		if (json.has(ID)) {
			setId(json.getInt(ID));
		}
		if (json.has(SIZE)) {
			setSize(json.getInt(SIZE));
		}
		if (json.has(INVENTORY)) {
			JSONArray array = json.getJSONArray(INVENTORY);
			List<Inventory> inventories = new ArrayList<Inventory>();
			for (int i = 0; i < array.length(); i++) {
				if (array.get(i) instanceof JSONObject) {
					inventories.add(new Inventory(array.getJSONObject(i)));
				}
			}
			setInventories(inventories);
		}
	}
}
