package query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import client.Client;
import domain.Achievement;
import domain.Daily;
import lang.Locale;

public class AchievementQuery {
	private final static String URL_ACHIVEMENT = "achievements";
	private final static String URL_DAILY = URL_ACHIVEMENT + "/daily/";

	public static Achievement get(int id) {
		String data = Client.get(Locale.BASE_URL + URL_ACHIVEMENT + "/" + id);
		JSONObject json = new JSONObject(data);
		Achievement achievement = new Achievement(json);
		return achievement;
	}

	public static List<Achievement> get(List<Integer> ids) {
		List<Achievement> achievements = new ArrayList<>();
		String data = Client.get(Locale.BASE_URL + URL_ACHIVEMENT + "?ids=" + StringUtils.join(ids, ','));
		if (data.charAt(0) == '[') {
			JSONArray array = new JSONArray(data);
			int i = 0;
			Achievement achievement = new Achievement(array.getJSONObject(i));
			for (Integer id : ids) {
				if (achievement.getId().equals(id)) {
					achievements.add(achievement);
					if (i < array.length() - 1) {
						i++;
						achievement = new Achievement(array.getJSONObject(i));
					}
				} else {
					achievements.add(null);
				}
			}
		}
		return achievements;
	}

	public static Map<String, List<Daily>> getDalys(boolean tomorrow) {
		String data = Client.get(Locale.BASE_URL + URL_DAILY + (tomorrow ? "tomorrow" : ""));
		JSONObject json = new JSONObject(data);
		Map<String, List<Daily>> dailys = new HashMap<String, List<Daily>>();
		for (Object o : json.keySet()) {
			String type = (String) o;
			JSONArray array = json.getJSONArray(type);
			List<Daily> subDailys = new ArrayList<Daily>();
			for (int i = 0; i < array.length(); i++) {
				subDailys.add(new Daily(array.getJSONObject(i)));
			}
			dailys.put(type, subDailys);
		}
		return dailys;
	}
}
