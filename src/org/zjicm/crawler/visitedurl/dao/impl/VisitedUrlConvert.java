package org.zjicm.crawler.visitedurl.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.zjicm.crawler.url.pojo.UnVisitedUrl;
import org.zjicm.crawler.url.pojo.VisitedUrl;
import org.zjicm.crawler.util.JsonUtil;
import org.zjicm.crawler.visitedurl.dao.IVisitedUrlConvert;
import org.zjicm.crawler.visitedurl.rpc.VisitedurlClient;

public class VisitedUrlConvert implements IVisitedUrlConvert {

	static VisitedurlClient vClient = new VisitedurlClient();
	static JsonUtil jsonUtil = new JsonUtil();

	@Override
	public List filterVisitedUrl(List<UnVisitedUrl> list) {
		// 在这里会传来一个list，解析成字符串吧
		String str = jsonUtil.ListToJson_U(list);

		Text result = vClient.getVisitedurlClient().filterVisitedUrl(
				new Text(str));

		// 获取结果后，得到一个字符串，解析成list吧

		return jsonUtil.JsonToList_U(result.toString());
	}

	@Override
	public int getVisitedUrlSize() {
		return vClient.getVisitedurlClient().getVisitedUrlSize();
	}

	@Override
	public void setVisitedUrl(VisitedUrl v) {

		vClient.getVisitedurlClient().setVisitedUrl(jsonUtil.BeanToJson(v));
	}

	@Override
	public void clearVisitedUrl() {

		vClient.getVisitedurlClient().clearVisitedUrl();
	}

	// @Override
	// public Map<String, VisitedUrl> getVisitedUrlMap() {
	// String result = vClient.getVisitedurlClient().getVisitedUrlMap();
	//
	// return jsonUtil.JsonToMap(result);
	// }

	@Override
	public boolean SetVisitedUrlListToDB(VisitedUrl v) {
		String result = jsonUtil.BeanToJson(v);
		return vClient.getVisitedurlClient().SetVisitedUrlListToDB(result);
	}

	@Override
	public boolean FilterVisitedUrlByMd5FromDB(String md5) {

		return vClient.getVisitedurlClient().FilterVisitedUrlByMd5FromDB(md5);
	}

	@Override
	public boolean saveVisitedUrl() {

		return vClient.getVisitedurlClient().saveVisitedUrl();
	}

}
