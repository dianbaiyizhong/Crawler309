package org.zjicm.crawler.filter.vo;

/**
 * Visitedurl entity. @author MyEclipse Persistence Tools
 */

public class Visitedurl implements java.io.Serializable {

	// Fields

	private String md5;
	private String url;
	private String time;

	// Constructors

	/** default constructor */
	public Visitedurl() {
	}

	/** full constructor */
	public Visitedurl(String url, String time) {
		this.url = url;
		this.time = time;
	}

	// Property accessors

	public String getMd5() {
		return this.md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}