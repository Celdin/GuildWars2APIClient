package domain;

import org.json.JSONObject;

public class Crafting {
	public final static String DISCIPLINE = "discipline";
	public final static String RATING = "rating";
	public final static String ACTIVE = "active";

	private String discipline;
	private Integer rating;
	private Boolean active;

	public Crafting(JSONObject json) {
		fromJSON(json);
	}

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Crafting [");
		if (discipline != null) {
			builder.append("discipline=");
			builder.append(discipline);
			builder.append(", ");
		}
		if (rating != null) {
			builder.append("rating=");
			builder.append(rating);
			builder.append(", ");
		}
		if (active != null) {
			builder.append("active=");
			builder.append(active);
		}
		builder.append("]");
		return builder.toString();
	}

	public void fromJSON(JSONObject json) {
		if (json.has(DISCIPLINE)) {
			setDiscipline(json.getString(DISCIPLINE));
		}
		if (json.has(RATING)) {
			setRating(json.getInt(RATING));
		}
		if (json.has(ACTIVE)) {
			setActive(json.getBoolean(ACTIVE));
		}
	}
}
