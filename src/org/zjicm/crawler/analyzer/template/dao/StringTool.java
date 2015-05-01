package org.zjicm.crawler.analyzer.template.dao;

public class StringTool {
	public String CombineLable(String[] c) {
		String result = null;
		for (int i = 0; i < c.length; i++) {
			result = result + c[i] + "\n" + "\n" + "\n";

		}
		return result;

	}

	public String WipeTextTag(String text) {

		text = text.replaceAll("&nbsp;", "").replaceAll("<[^>]*>|</.*>", "")
				.trim();

		return text;

	}
}
