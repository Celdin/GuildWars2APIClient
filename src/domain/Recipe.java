package domain;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Recipe {
	public static final String ID = "id";
	public static final String TYPE = "type";
	public static final String OUTPUT_ITEM_ID = "output_item_id";
	public static final String OUTPUT_ITEM_COUNT = "output_item_count";
	public static final String TIME_TO_CRAFT_MS = "time_to_craft_ms";
	public static final String DISCIPLINES = "disciplines";
	public static final String MIN_RATING = "min_rating";
	public static final String FLAGS = "flags";
	public static final String INGREDIENTS = "ingredients";
	public static final String OUTPUT_UPGRADE_ID = "outputUpgradeId";
	public static final String CHAT_LINK = "chat_link";

	private Integer id;
	private String type;
	private Integer outputItemId;
	private Integer outputItemCount;
	private Integer timeToCraftMs;
	private List<String> disciplines;
	private Integer minRating;
	private List<String> flags;
	private List<Inventory> ingredients;
	private Integer outputUpgradeId;
	private String chatLink;

	public Recipe(JSONObject json) {
		fromJSON(json);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getOutputItemId() {
		return outputItemId;
	}

	public void setOutputItemId(Integer outputItemId) {
		this.outputItemId = outputItemId;
	}

	public Integer getOutputItemCount() {
		return outputItemCount;
	}

	public void setOutputItemCount(Integer outputItemCount) {
		this.outputItemCount = outputItemCount;
	}

	public Integer getTimeToCraftMs() {
		return timeToCraftMs;
	}

	public void setTimeToCraftMs(Integer timeToCraftMs) {
		this.timeToCraftMs = timeToCraftMs;
	}

	public List<String> getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(List<String> disciplines) {
		this.disciplines = disciplines;
	}

	public Integer getMinRating() {
		return minRating;
	}

	public void setMinRating(Integer minRating) {
		this.minRating = minRating;
	}

	public List<String> getFlags() {
		return flags;
	}

	public void setFlags(List<String> flags) {
		this.flags = flags;
	}

	public List<Inventory> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Inventory> ingredients) {
		this.ingredients = ingredients;
	}

	public Integer getOutputUpgradeId() {
		return outputUpgradeId;
	}

	public void setOutputUpgradeId(Integer outputUpgradeId) {
		this.outputUpgradeId = outputUpgradeId;
	}

	public String getChatLink() {
		return chatLink;
	}

	public void setChatLink(String chat_link) {
		this.chatLink = chat_link;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Recipe [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (type != null) {
			builder.append("type=");
			builder.append(type);
			builder.append(", ");
		}
		if (outputItemId != null) {
			builder.append("outputItemId=");
			builder.append(outputItemId);
			builder.append(", ");
		}
		if (outputItemCount != null) {
			builder.append("outputItemCount=");
			builder.append(outputItemCount);
			builder.append(", ");
		}
		if (timeToCraftMs != null) {
			builder.append("timeToCraftMs=");
			builder.append(timeToCraftMs);
			builder.append(", ");
		}
		if (disciplines != null) {
			builder.append("disciplines=");
			builder.append(disciplines);
			builder.append(", ");
		}
		if (minRating != null) {
			builder.append("minRating=");
			builder.append(minRating);
			builder.append(", ");
		}
		if (flags != null) {
			builder.append("flags=");
			builder.append(flags);
			builder.append(", ");
		}
		if (ingredients != null) {
			builder.append("ingredients=");
			builder.append(ingredients);
			builder.append(", ");
		}
		if (outputUpgradeId != null) {
			builder.append("outputUpgradeId=");
			builder.append(outputUpgradeId);
			builder.append(", ");
		}
		if (chatLink != null) {
			builder.append("chatLink=");
			builder.append(chatLink);
		}
		builder.append("]");
		return builder.toString();
	}

	public void fromJSON(JSONObject json) {
		if (json.has(ID)) {
			setId(json.getInt(ID));
		}
		if (json.has(TYPE)) {
			setType(json.getString(TYPE));
		}
		if (json.has(OUTPUT_ITEM_ID)) {
			setOutputItemId(json.getInt(OUTPUT_ITEM_ID));
		}
		if (json.has(OUTPUT_ITEM_COUNT)) {
			setOutputItemCount(json.getInt(OUTPUT_ITEM_COUNT));
		}
		if (json.has(TIME_TO_CRAFT_MS)) {
			setTimeToCraftMs(json.getInt(TIME_TO_CRAFT_MS));
		}
		if (json.has(DISCIPLINES)) {
			List<String> disciplines = new ArrayList<String>();
			JSONArray array = json.getJSONArray(DISCIPLINES);
			for (int i = 0; i < array.length(); i++) {
				disciplines.add(array.getString(i));
			}
			setDisciplines(disciplines);
		}
		if (json.has(MIN_RATING)) {
			setMinRating(json.getInt(MIN_RATING));
		}
		if (json.has(FLAGS)) {
			List<String> flags = new ArrayList<String>();
			JSONArray array = json.getJSONArray(FLAGS);
			for (int i = 0; i < array.length(); i++) {
				flags.add(array.getString(i));
			}
			setFlags(flags);
		}
		if (json.has(INGREDIENTS)) {
			List<Inventory> ingredients = new ArrayList<Inventory>();
			JSONArray array = json.getJSONArray(INGREDIENTS);
			for (int i = 0; i < array.length(); i++) {
				ingredients.add(new Inventory(array.getJSONObject(i)));
			}
			setIngredients(ingredients);
		}
		if (json.has(OUTPUT_UPGRADE_ID)) {
			setOutputUpgradeId(json.getInt(OUTPUT_UPGRADE_ID));
		}
		if (json.has(CHAT_LINK)) {
			setChatLink(json.getString(CHAT_LINK));
		}
	}
}
