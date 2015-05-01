package org.zjicm.crawler.analyzer.pojo;

import org.zjicm.crawler.url.vo.Gatherwebsite;

public class Page {
	@Override
	public String toString() {
		return "Page [pageUrl=" + pageUrl + ", pageText=" + pageText
				+ ", nowDepth=" + nowDepth + ", gatherwebsite=" + gatherwebsite
				+ "]";
	}

	private String pageUrl;
	private StringBuilder pageText;
	private int nowDepth;
	private Gatherwebsite gatherwebsite;

	public Gatherwebsite getGatherwebsite() {
		return gatherwebsite;
	}

	public void setGatherwebsite(Gatherwebsite gatherwebsite) {
		this.gatherwebsite = gatherwebsite;
	}

	public int getNowDepth() {
		return nowDepth;
	}

	public void setNowDepth(int nowDepth) {
		this.nowDepth = nowDepth;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public StringBuilder getPageText() {
		return pageText;
	}

	public void setPageText(StringBuilder pageText) {
		this.pageText = pageText;
	}

}
