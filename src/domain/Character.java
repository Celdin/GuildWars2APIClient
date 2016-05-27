package domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Character {
	public final static String NAME = "name";
	public final static String RACE = "race";
	public final static String GENDER = "gender";
	public final static String PROFESSION = "profession";
	public final static String LEVEL = "level";
	public final static String GUILD = "guild";
	public final static String CREATED = "created";
	public final static String AGE = "age";
	public final static String DEATH = "deaths";
	public final static String CRAFTING = "crafting";
	public final static String SPECIALIZATION = "specializations";
	public final static String EQUIIPMENT = "equipment";
	public final static String RECIPES = "recipes";
	public final static String BAGS = "bags";

	private String name;
	private String race;
	private String gender;
	private String profession;
	private Integer level;
	private String guild;
	private Date created;
	private Integer age;
	private Integer deaths;
	private List<Crafting> crafting;
	private Map<String, List<Specialization>> specializations;
	private List<Equipement> equipements;
	private List<Integer> recipes;
	private List<Bag> bags;

	public Character(JSONObject json) throws JSONException, ParseException {
		fromJSON(json);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getGuild() {
		return guild;
	}

	public void setGuild(String guild) {
		this.guild = guild;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getDeaths() {
		return deaths;
	}

	public void setDeaths(Integer deaths) {
		this.deaths = deaths;
	}

	public List<Crafting> getCrafting() {
		return crafting;
	}

	public void setCrafting(List<Crafting> crafting) {
		this.crafting = crafting;
	}

	public Map<String, List<Specialization>> getSpecializations() {
		return specializations;
	}

	public void setSpecializations(
			Map<String, List<Specialization>> specializations) {
		this.specializations = specializations;
	}

	public List<Equipement> getEquipements() {
		return equipements;
	}

	public void setEquipements(List<Equipement> equipements) {
		this.equipements = equipements;
	}

	public List<Integer> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Integer> recipes) {
		this.recipes = recipes;
	}

	public List<Bag> getBags() {
		return bags;
	}

	public void setBags(List<Bag> bags) {
		this.bags = bags;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Character [");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (race != null) {
			builder.append("race=");
			builder.append(race);
			builder.append(", ");
		}
		if (gender != null) {
			builder.append("gender=");
			builder.append(gender);
			builder.append(", ");
		}
		if (profession != null) {
			builder.append("profession=");
			builder.append(profession);
			builder.append(", ");
		}
		if (level != null) {
			builder.append("level=");
			builder.append(level);
			builder.append(", ");
		}
		if (guild != null) {
			builder.append("guild=");
			builder.append(guild);
			builder.append(", ");
		}
		if (created != null) {
			builder.append("created=");
			builder.append(created);
			builder.append(", ");
		}
		if (age != null) {
			builder.append("age=");
			builder.append(age);
			builder.append(", ");
		}
		if (deaths != null) {
			builder.append("deaths=");
			builder.append(deaths);
			builder.append(", ");
		}
		if (crafting != null) {
			builder.append("crafting=");
			builder.append(crafting);
			builder.append(", ");
		}
		if (specializations != null) {
			builder.append("specializations=");
			builder.append(specializations);
			builder.append(", ");
		}
		if (equipements != null) {
			builder.append("equipements=");
			builder.append(equipements);
			builder.append(", ");
		}
		if (recipes != null) {
			builder.append("recipes=");
			builder.append(recipes);
			builder.append(", ");
		}
		if (bags != null) {
			builder.append("bags=");
			builder.append(bags);
		}
		builder.append("]");
		return builder.toString();
	}

	private void fromJSON(JSONObject json) throws JSONException, ParseException {
		if (json.has(NAME)) {
			setName(json.getString(NAME));
		}
		if (json.has(RACE)) {
			setRace(json.getString(RACE));
		}
		if (json.has(GENDER)) {
			setGender(json.getString(GENDER));
		}
		if (json.has(PROFESSION)) {
			setProfession(json.getString(PROFESSION));
		}
		if (json.has(LEVEL)) {
			setLevel(json.getInt(LEVEL));
		}
		if (json.has(GUILD) && json.get(GUILD) instanceof String) {
			setGuild(json.getString(GUILD));
		}
		if (json.has(CREATED)) {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ssX");
			setCreated(format.parse(json.getString(CREATED)));
		}
		if (json.has(AGE)) {
			setAge(json.getInt(AGE));
		}
		if (json.has(DEATH)) {
			setDeaths(json.getInt(DEATH));
		}
		if (json.has(CRAFTING)) {
			List<Crafting> craftings = new ArrayList<Crafting>();
			JSONArray array = json.getJSONArray(CRAFTING);
			for (int i = 0; i < array.length(); i++) {
				craftings.add(new Crafting(array.getJSONObject(i)));
			}
			setCrafting(craftings);
		}
		if (json.has(SPECIALIZATION)) {
			Map<String, List<Specialization>> map = new HashMap<String, List<Specialization>>();
			for (Object o : json.getJSONObject(SPECIALIZATION).keySet()) {
				String key = (String) o;
				List<Specialization> specializations = new ArrayList<Specialization>();
				JSONArray array = json.getJSONObject(SPECIALIZATION)
						.getJSONArray(key);
				for (int i = 0; i < array.length(); i++) {
					if (array.get(i) instanceof JSONObject) {
						specializations.add(new Specialization(array
								.getJSONObject(i)));
					}
				}
				map.put(key, specializations);
			}
			setSpecializations(map);
		}
		if (json.has(EQUIIPMENT)) {
			JSONArray array = json.getJSONArray(EQUIIPMENT);
			List<Equipement> equipements = new ArrayList<Equipement>();
			for (int i = 0; i < array.length(); i++) {
				equipements.add(new Equipement(array.getJSONObject(i)));
			}
			setEquipements(equipements);
		}
		if (json.has(RECIPES)) {
			List<Integer> recipes = new ArrayList<Integer>();
			JSONArray array = json.getJSONArray(RECIPES);
			for (int i = 0; i < array.length(); i++) {
				recipes.add(array.getInt(i));
			}
			setRecipes(recipes);
		}
		if (json.has(BAGS)) {
			JSONArray array = json.getJSONArray(BAGS);
			List<Bag> bags = new ArrayList<Bag>();
			for (int i = 0; i < array.length(); i++) {
				if (array.get(i) instanceof JSONObject) {
					bags.add(new Bag(array.getJSONObject(i)));
				}
			}
			setBags(bags);
		}
	}
}
