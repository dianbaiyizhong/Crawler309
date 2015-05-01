package org.zjicm.crawler.controller.vo;

/**
 * UnvisitedurlCache entity. @author MyEclipse Persistence Tools
 */

public class UnvisitedurlCache implements java.io.Serializable {

	// Fields

	private Integer id;
	private String url;
	private Integer depth;

	// Constructors

	/** default constructor */
	public UnvisitedurlCache() {
	}

	/** full constructor */
	public UnvisitedurlCache(String url, Integer depth) {
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