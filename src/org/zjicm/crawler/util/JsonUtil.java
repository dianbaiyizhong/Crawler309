package org.zjicm.crawler.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.zjicm.crawler.url.pojo.UnVisitedUrl;
import org.zjicm.crawler.url.pojo.VisitedUrl;

public class JsonUtil {

	// 把一个VisitedUrl对象的list转为json
	public String ListToJson_V(List<VisitedUrl> list) {
		JSONObject json_array = JSONObject.fromObject(list);

		return json_array.toString();

	}

	public String ListToJson_U(List<UnVisitedUrl> list) {
		JSONArray json_array = JSONArray.fromObject(list);
		return json_array.toString();

	}

	public List<VisitedUrl> JsonToList_V(String str) {

		return JSONArray.toList(JSONArray.fromObject(str), new VisitedUrl(),
				new JsonConfig());

	}

	public List<UnVisitedUrl> JsonToList_U(String str) {

		return JSONArray.toList(JSONArray.fromObject(str), new UnVisitedUrl(),
				new JsonConfig());

	}

	public String BeanToJson(VisitedUrl v) {
		JSONObject jsonObj1 = JSONObject.fromObject(v);

		return jsonObj1.toString();

	}

	public Map<String, VisitedUrl> JsonToMap(String jsonArrayData) {
		JSONObject jsonMap = JSONObject.fromObject(jsonArrayData);
		Iterator<String> it = jsonMap.keys();
		Map<String, VisitedUrl> map = new HashMap<String, VisitedUrl>();
		while (it.hasNext()) {

			String key = (String) it.next();

			VisitedUrl v = (VisitedUrl) JSONObject.toBean(

			JSONObject.fromObject(jsonMap.get(key)),

			VisitedUrl.class);

			map.put(key, v);

		}

		return map;
	}
}
