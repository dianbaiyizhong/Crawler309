package org.zjicm.crawler.analyzer.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.zjicm.crawler.analyzer.pojo.Page;
import org.zjicm.crawler.url.pojo.UnVisitedUrl;

public class PageUrlTool {

	/**
	 * 注意一下获取新url的所经过的步骤，以下已经用序号进行标记
	 * 
	 * @param page
	 * @return
	 */
	public List<UnVisitedUrl> extracLinksToList(Page page) {
		List<UnVisitedUrl> list = new ArrayList<UnVisitedUrl>();
		// 针对目前正则表达式的不完善，可能还会获取诸如target=这些东西，因此，我们要去掉.但是，先不搞

		Pattern p1 = Pattern
				.compile("(?<=[\\s+]?href[\\s+]?=[\\s+]?('|\")?)[\\s+]*[^(\"|')>]+?(?=\"|')");
		Matcher m = p1.matcher(page.getPageText());

		// 获取DirectDomain
		String DirectDomain = page.getGatherwebsite().getDirectDomain();
		// 获取DirectUrl
		String DirectUrl = page.getGatherwebsite().getDirectUrl();
		// 获取FilterDomain
		String FilterDomain = page.getGatherwebsite().getFilterDomain();
		while (m.find()) {

			// 得到新的url
			String url = m.group();

			// 1. 在这里判断是否一些链接只有后半部分，如果没有就加上
			if (url.startsWith("/")) {
				url = "http://" + DirectDomain + url;
			}

			// 2. 判断是否是定向url，如html，shtml，cn甚至是没有.后缀的域名
			boolean factor1 = IsDirectedUrl(url, DirectUrl);
			// 3. 判断是否是定向一级域名
			boolean factor2 = IsDirectDomain(url, DirectDomain);
			// 4.去除奇葩后缀，例如css，gif
			boolean factor3 = IsFilterDomain(url, FilterDomain);

		
			if (factor1 && factor2 && factor3) {

				UnVisitedUrl u = new UnVisitedUrl();
				u.setUrl(url);
				u.setNowDepth(page.getNowDepth() + 1);
				u.setGatherwebsite(page.getGatherwebsite());
				list.add(u);

			}

		}

		return list;

	}

	private boolean IsFilterDomain(String url, String directDomain) {

		List<String> list = getFilterDomainList(directDomain);
		for (int j = 0; j < list.size(); j++) {

			if (url.endsWith(list.get(j))) {
				return false;

			}

		}

		if (list.size() == 0) {
			return true;
		}
		return true;
	}

	/**
	 * 判断url是否是定向url，也就是jsp，html，shtml等的过滤
	 * 
	 * @param url
	 * @return
	 */
	private boolean IsDirectedUrl(String url, String DirectedUrl) {
		List<String> list = getDirectUrlList(DirectedUrl);

		for (int j = 0; j < list.size(); j++) {

			if (url.endsWith(list.get(j))) {
				return true;

			}

		}

		if (list.size() == 0) {
			return true;
		}
		return false;

	}

	/**
	 * 分割字符串并返回链表
	 * 
	 * @param page
	 * @return
	 */
	private List<String> getDirectUrlList(String DirectedUrl) {
		List<String> list = new ArrayList<String>();
		try {
			String Arr[] = DirectedUrl.split(",");
			for (int i = 0; i < Arr.length; i++) {
				list.add(Arr[i]);
			}
		} catch (Exception e) {
			System.out.println("warm:DirectUrl is not set");

		}

		return list;

	}

	/**
	 * 分割字符串并返回链表
	 * 
	 * @param page
	 * @return
	 */
	private List<String> getFilterDomainList(String FilterDomain) {
		List<String> list = new ArrayList<String>();
		try {
			String Arr[] = FilterDomain.split(",");
			for (int i = 0; i < Arr.length; i++) {
				list.add(Arr[i]);
			}
		} catch (Exception e) {
			System.out.println("warm:FilterDomain is not set");

		}
		return list;

	}

	private boolean IsDirectDomain(String url, String DirectDomain) {

		if (DirectDomain == null || DirectDomain == "") {

			// 如果定向的字符串为空的话，就直接返回
			System.out.println("warm:DirectDomain is not set");
			return true;
		}

		if (url.contains(DirectDomain)) {
			return true;
		} else {
			return false;
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}