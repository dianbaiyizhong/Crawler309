package org.zjicm.crawler.controller.vo;

/**
 * VisitedurlCache entity. @author MyEclipse Persistence Tools
 */

public class VisitedurlCache implements java.io.Serializable {

	// Fields

	private Integer id;
	private String md5;
	private String url;
	private Integer depth;

	// Constructors

	/** default constructor */
	public VisitedurlCache() {
	}

	/** full constructor */
	public VisitedurlCache(String md5, String url, Integer depth) {
		this.md5 = md5;
		this.url = url;
		this.depth = depth;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Integer getDepth() {
		return this.depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

}