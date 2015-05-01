package org.zjicm.crawler.analyzer.template.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.zjicm.crawler.analyzer.template.pojo.Attribute;

public class Template {
	static StringTool stringTool = new StringTool();

	public Map<String, String> getDataByTemplate(StringBuilder HtmlCode,
			String Template) {
		List<Attribute> list = getAttributeFromXML(Template);
		Map<String, String> map = getDataByRegex(list, HtmlCode);
		return map;
	}

	public Map<String, String> getDataByRegex(List<Attribute> list,
			StringBuilder HtmlCode) {
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i++) {

			String s_looplimit = list.get(i).getLooplimit();
			int i_looplimit = 0;
			try {
				i_looplimit = Integer.parseInt(s_looplimit);
			} catch (Exception e) {
				i_looplimit = 1;
			}

			Pattern p = Pattern.compile(list.get(i).getPattern());

			try {
				Matcher m = p.matcher(HtmlCode.toString());

				String result = "";
				int count = 0;
				while (m.find() && count != i_looplimit) {
					count++;
					result = result + m.group();

				}

				map.put(list.get(i).getName(), stringTool.WipeTextTag(result)
						.trim());

			} catch (Exception e) {
				System.out.println("HtmlCode maybe is null...");
			}

		}
		return map;

	}

	public List<Attribute> getAttributeFromXML(String Template) {
		List<Attribute> list = new ArrayList<Attribute>();

		try {

			Document doc = DocumentHelper.parseText(Template.trim());
			Element root = doc.getRootElement();
			// 有多少个信息需要抓取，List里面已经获取存放的元素
			List<Element> param = root.elements();

			for (int i = 0; i < param.size(); i++) {
				Attribute attribute = new Attribute();
				// 首先判断节点的assistant的属性，判断是否需要多次抓取内容
				String s_looplimit = param.get(i).element("assistant")
						.attributeValue("looplimit");
				// 其次获取正则表达式
				String pattern = param.get(i).elementText("pattern");

				// 最后获取name
				String name = param.get(i).elementText("name");

				attribute.setLooplimit(s_looplimit);
				attribute.setPattern(pattern);
				attribute.setName(name);
				list.add(attribute);

			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return list;
	}

}
