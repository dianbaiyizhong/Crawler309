package org.zjicm.crawler.analyzer;

import java.util.Map;

import org.zjicm.crawler.analyzer.pojo.Page;
import org.zjicm.crawler.analyzer.template.dao.Template;
import org.zjicm.crawler.analyzer.util.StringUtil;
import org.zjicm.crawler.analyzer.vo.Webcontinfo;

public class GetPageContent {
	static Template template = new Template();

	static StringUtil su = new StringUtil();
	String PageText = "no text";

	public Webcontinfo getOtherContent(Page page) {
		Webcontinfo webcontinfo = new Webcontinfo();

		Map<String, String> map = template.getDataByTemplate(
				page.getPageText(), page.getGatherwebsite().getTemplate());
		webcontinfo.setTextTitle(map.get("标题"));
		webcontinfo.setPageUrl(page.getPageUrl());
		webcontinfo.setReproter(map.get("作者"));
		webcontinfo.setText(su.htmlRemoveTag(page.getPageText().toString()));

		return webcontinfo;

	}

	/**
	 * 这里获取的是天涯论坛的内容
	 * 
	 * @param page
	 * @return
	 */
	public Webcontinfo getBBSTianyaContent(Page page) {
		Webcontinfo webcontinfo = new Webcontinfo();

		Map<String, String> map = template.getDataByTemplate(
				page.getPageText(), page.getGatherwebsite().getTemplate());

		String time = map.get("发表时间");

		int timeindex_1 = time.indexOf("时间");
		int timeindex_2 = time.indexOf("点");

		time = time.substring(timeindex_1 + 3, timeindex_2).trim();

		webcontinfo.setPageTime(time);
		webcontinfo.setText((map.get("正文")));

		webcontinfo.setTextTitle(map.get("标题"));

		String writer = map.get("作者");
		int writerindex_1 = writer.indexOf("楼主");
		int writerindex_2 = writer.indexOf("时");

		writer = writer.substring(writerindex_1 + 3, writerindex_2).trim();
		webcontinfo.setReproter(writer);

		webcontinfo.setPageUrl(page.getPageUrl());

		return webcontinfo;
	}

	/**
	 * 这里获取的是新浪新闻的内容
	 * 
	 * @param page
	 * @return
	 */
	public Webcontinfo getNewsSinaContent(Page page) {
		Webcontinfo webcontinfo = new Webcontinfo();

		Map<String, String> map = template.getDataByTemplate(
				page.getPageText(), page.getGatherwebsite().getTemplate());

		String time = map.get("发表时间");
		webcontinfo.setPageTime(time);

		webcontinfo.setText((map.get("正文")));

		webcontinfo.setTextTitle(map.get("标题"));
		webcontinfo.setReproter(map.get("作者"));

		webcontinfo.setPageUrl(page.getPageUrl());

		return webcontinfo;
	}

	/**
	 * 这里获取的是百度贴吧的内容
	 * 
	 * @param page
	 * @return
	 */
	public Webcontinfo getTiebaContent(Page page) {
		Webcontinfo webcontinfo = new Webcontinfo();

		Map<String, String> map = template.getDataByTemplate(
				page.getPageText(), page.getGatherwebsite().getTemplate());

		String time = map.get("发表时间");
		webcontinfo.setPageTime(time);

		webcontinfo.setText((map.get("正文")));

		webcontinfo.setTextTitle(map.get("标题"));
		webcontinfo.setReproter(map.get("作者"));

		webcontinfo.setPageUrl(page.getPageUrl());

		return webcontinfo;
	}
}