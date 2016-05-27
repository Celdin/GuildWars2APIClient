package domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Account {
	public final static String ID = "id";
	public final static String NAME = "name";
	public final static String WORLD = "world";
	public final static String COMMANDER = "commander";
	public final static String GUILDS = "guilds";
	public final static String CREATED = "created";
	public final static String ACCESS = "access";
	public final static String FRACTAL_LEVEl = "fractal_level";
	public final static String DAILY_AP = "daily_ap";
	public final static String MONTHLY_AP = "monthly_ap";

	private String id;
	private String name;
	private Integer world;
	private Boolean commander;
	private List<String> guilds;
	private Date created;
	private String access;
	private Integer fractalLevel;
	private Integer dayliAp;
	private Integer monthlyAp;

	public Account(JSONObject json) throws JSONException, ParseException {
		fromJSON(json);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getWorld() {
		return world;
	}

	public void setWorld(Integer world) {
		this.world = world;
	}

	public Boolean getCommander() {
		return commander;
	}

	public void setCommander(Boolean commander) {
		this.commander = commander;
	}

	public List<String> getGuilds() {
		return guilds;
	}

	public void setGuilds(List<String> guilds) {
		this.guilds = guilds;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public Integer getFractalLevel() {
		return fractalLevel;
	}

	public void setFractalLevel(Integer fractalLevel) {
		this.fractalLevel = fractalLevel;
	}

	public Integer getDayliAp() {
		return dayliAp;
	}

	public void setDayliAp(Integer dayliAp) {
		this.dayliAp = dayliAp;
	}

	public Integer getMonhltyAp() {
		return monthlyAp;
	}

	public void setMonhltyAp(Integer monhltyAp) {
		this.monthlyAp = monhltyAp;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Account [");
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
		if (world != null) {
			builder.append("world=");
			builder.append(world);
			builder.append(", ");
		}
		if (commander != null) {
			builder.append("commander=");
			builder.append(commander);
			builder.append(", ");
		}
		if (guilds != null) {
			builder.append("guilds=");
			builder.append(guilds);
			builder.append(", ");
		}
		if (created != null) {
			builder.append("created=");
			builder.append(created);
			builder.append(", ");
		}
		if (access != null) {
			builder.append("access=");
			builder.append(access);
			builder.append(", ");
		}
		if (fractalLevel != null) {
			builder.append("fractalLevel=");
			builder.append(fractalLevel);
			builder.append(", ");
		}
		if (dayliAp != null) {
			builder.append("dayliAp=");
			builder.append(dayliAp);
			builder.append(", ");
		}
		if (monthlyAp != null) {
			builder.append("monthlyAp=");
			builder.append(monthlyAp);
		}
		builder.append("]");
		return builder.toString();
	}

	public void fromJSON(JSONObject json) throws JSONException, ParseException {
		if (json.has(ID)) {
			setId(json.getString(ID));
		}
		if (json.has(NAME)) {
			setName(json.getString(NAME));
		}
		if (json.has(WORLD)) {
			setWorld(json.getInt(WORLD));
		}
		if (json.has(COMMANDER)) {
			setCommander(json.getBoolean(COMMANDER));
		}
		if (json.has(GUILDS)) {
			List<String> guilds = new ArrayList<String>();
			JSONArray array = json.getJSONArray(GUILDS);
			for (int i = 0; i < array.length(); i++) {
				String guild = array.getString(i);
				guilds.add(guild);
			}
			setGuilds(guilds);
		}
		if (json.has(CREATED)) {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ssX");
			setCreated(format.parse(json.getString(CREATED)));
		}
		if (json.has(ACCESS)) {
			setAccess(json.getString(ACCESS));
		}
		if (json.has(FRACTAL_LEVEl)) {
			setFractalLevel(json.getInt(FRACTAL_LEVEl));
		}
		if (json.has(DAILY_AP)) {
			setDayliAp(json.getInt(DAILY_AP));
		}
		if (json.has(MONTHLY_AP)) {
			setMonhltyAp(json.getInt(MONTHLY_AP));
		}
	}
}
