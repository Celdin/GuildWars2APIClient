package domain;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Specialization {
	public final static String ID = "id";
	public final static String NAME = "name";
	public final static String PROFFESSION = "proffession";
	public final static String ELITE = "elite";
	public final static String ICON = "icon";
	public final static String BACKGROUND = "background";
	public final static String TRAITS = "traits";
	public final static String MINOR_TRAITS = "minor_traits";
	public final static String MAJOR_TRAITS = "major_traits";

	private Integer id;
	private String name;
	private String proffession;
	private Boolean elite;
	private String icon;
	private String background;
	private List<Integer> traits;
	private List<Integer> minorTraits;
	private List<Integer> majorTraits;

	public Specialization(JSONObject json) {
		fronJSON(json);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProffession() {
		return proffession;
	}

	public void setProffession(String proffession) {
		this.proffession = proffession;
	}

	public Boolean getElite() {
		return elite;
	}

	public void setElite(Boolean elite) {
		this.elite = elite;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public List<Integer> getTraits() {
		return traits;
	}

	public void setTraits(List<Integer> traits) {
		this.traits = traits;
	}

	public List<Integer> getMinorTraits() {
		return minorTraits;
	}

	public void setMinorTraits(List<Integer> minorTraits) {
		this.minorTraits = minorTraits;
	}

	public List<Integer> getMajorTraits() {
		return majorTraits;
	}

	public void setMajorTraits(List<Integer> majorTraits) {
		this.majorTraits = majorTraits;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Specialization [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (proffession != null) {
			builder.append("proffession=");
			builder.append(proffession);
			builder.append(", ");
		}
		if (elite != null) {
			builder.append("elite=");
			builder.append(elite);
			builder.append(", ");
		}
		if (icon != null) {
			builder.append("icon=");
			builder.append(icon);
			builder.append(", ");
		}
		if (background != null) {
			builder.append("background=");
			builder.append(background);
			builder.append(", ");
		}
		if (traits != null) {
			builder.append("traits=");
			builder.append(traits);
			builder.append(", ");
		}
		if (minorTraits != null) {
			builder.append("minorTraits=");
			builder.append(minorTraits);
			builder.append(", ");
		}
		if (majorTraits != null) {
			builder.append("majorTraits=");
			builder.append(majorTraits);
		}
		builder.append("]");
		return builder.toString();
	}

	public void fronJSON(JSONObject json) {
		if (json.has(ID)) {
			setId(json.getInt(ID));
		}
		if (json.has(TRAITS)) {
			JSONArray array2 = json.getJSONArray(TRAITS);
			List<Integer> traits = new ArrayList<Integer>();
			for (int i = 0; i < array2.length(); i++) {
				if (array2.get(i) instanceof Integer) {
					traits.add(array2.getInt(i));
				}
			}
			setTraits(traits);
		}
	}
}
