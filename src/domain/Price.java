package domain;

import org.json.JSONObject;

public class Price {
	public static final String ID = "id";
	public static final String WHITELITED = "whitelisted";
	public static final String BUYS = "buys";
	public static final String SELLS = "sells";

	private Integer id;
	private Boolean whitelisted;
	private Coins buys;
	private Coins sells;

	public Price(JSONObject json) {
		fromJSON(json);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getWhitelisted() {
		return whitelisted;
	}

	public void setWhitelisted(Boolean whitelisted) {
		this.whitelisted = whitelisted;
	}

	public Coins getBuys() {
		return buys;
	}

	public void setBuys(Coins buys) {
		this.buys = buys;
	}

	public Coins getSells() {
		return sells;
	}

	public void setSells(Coins sells) {
		this.sells = sells;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Price [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (whitelisted != null) {
			builder.append("whitelisted=");
			builder.append(whitelisted);
			builder.append(", ");
		}
		if (buys != null) {
			builder.append("buys=");
			builder.append(buys);
			builder.append(", ");
		}
		if (sells != null) {
			builder.append("sells=");
			builder.append(sells);
		}
		builder.append("]");
		return builder.toString();
	}

	public void fromJSON(JSONObject json) {
		if (json.has(ID)) {
			setId(json.getInt(ID));
		}
		if (json.has(WHITELITED)) {
			setWhitelisted(json.getBoolean(WHITELITED));
		}
		if (json.has(BUYS)) {
			setBuys(new Coins(json.getJSONObject(BUYS)));
		}
		if (json.has(SELLS)) {
			setSells(new Coins(json.getJSONObject(SELLS)));
		}
	}
}
