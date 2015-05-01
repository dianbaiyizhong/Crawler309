package org.zjicm.crawler.gather.dao;

import org.zjicm.crawler.url.pojo.UnVisitedUrl;

public class Test {
	public static void main(String[] args) {
		String url = "http://tieba.baidu.com/";
		HttpClientDao hcd = new HttpClientDao();
		UnVisitedUrl u = new UnVisitedUrl();
		u.setUrl(url);
		hcd.HttpClient(u);
	}
}
