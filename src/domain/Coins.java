package domain;

import org.json.JSONObject;

public class Coins {
	public static final String QUANTITY = "quantity";
	public static final String UNIT_PRICE = "unit_price";

	private Integer quantity;
	private Integer unitPrice;

	public Integer getQuantity() {
		return quantity;
	}

	public Coins(JSONObject json) {
		fromJSON(json);
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Coins [");
		if (quantity != null) {
			builder.append("quantity=");
			builder.append(quantity);
			builder.append(", ");
		}
		if (unitPrice != null) {
			builder.append("unitPrice=");
			builder.append(unitPrice);
		}
		builder.append("]");
		return builder.toString();
	}

	public void fromJSON(JSONObject json) {
		if (json.has(QUANTITY)) {
			setQuantity(json.getInt(QUANTITY));
		}
		if (json.has(UNIT_PRICE)) {
			setUnitPrice(json.getInt(UNIT_PRICE));
		}
	}
}
