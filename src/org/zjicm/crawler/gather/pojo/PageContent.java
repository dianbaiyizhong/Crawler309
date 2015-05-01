package org.zjicm.crawler.gather.pojo;

import java.io.Serializable;

public class PageContent implements Serializable {
	private String url;
	private String md5;
	private StringBuilder source; // 网页源代码

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public StringBuilder getSource() {
		return source;
	}

	public void setSource(StringBuilder source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "PageContent [url=" + url + ", md5=" + md5 + ", source="
				+ source + "]";
	}

	public PageContent() {
		super();
	}

	public PageContent(String url, String md5, StringBuilder source) {
		super();
		this.url = url;
		this.md5 = md5;
		this.source = source;
	}

}