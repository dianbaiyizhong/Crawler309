package org.zjicm.crawler.analyzer.vo;

/**
 * Webcontinfo entity. @author MyEclipse Persistence Tools
 */

public class Webcontinfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String taskId;
	private String siteId;
	private String pageUrl;
	private String colectTime;
	private String textTitle;
	private String text = "没有正文";
	private String pageTime;
	private String reproter;
	private String joiner;
	private String commenter;
	private String emotionClass;
	private String remark;
	private String state;

	// Constructors

	/** default constructor */
	public Webcontinfo() {
	}

	/** full constructor */
	public Webcontinfo(String taskId, String siteId, String pageUrl,
			String colectTime, String textTitle, String text, String pageTime,
			String reproter, String joiner, String commenter,
			String emotionClass, String remark, String state) {
		this.taskId = taskId;
		this.siteId = siteId;
		this.pageUrl = pageUrl;
		this.colectTime = colectTime;
		this.textTitle = textTitle;
		this.text = text;
		this.pageTime = pageTime;
		this.reproter = reproter;
		this.joiner = joiner;
		this.commenter = commenter;
		this.emotionClass = emotionClass;
		this.remark = remark;
		this.state = state;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTaskId() {
		return this.taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getSiteId() {
		return this.siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getPageUrl() {
		return this.pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getColectTime() {
		return this.colectTime;
	}

	public void setColectTime(String colectTime) {
		this.colectTime = colectTime;
	}

	public String getTextTitle() {
		return this.textTitle;
	}

	public void setTextTitle(String textTitle) {
		this.textTitle = textTitle;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPageTime() {
		return this.pageTime;
	}

	public void setPageTime(String pageTime) {
		this.pageTime = pageTime;
	}

	public String getReproter() {
		return this.reproter;
	}

	public void setReproter(String reproter) {
		this.reproter = reproter;
	}

	public String getJoiner() {
		return this.joiner;
	}

	public void setJoiner(String joiner) {
		this.joiner = joiner;
	}

	public String getCommenter() {
		return this.commenter;
	}

	public void setCommenter(String commenter) {
		this.commenter = commenter;
	}

	public String getEmotionClass() {
		return this.emotionClass;
	}

	public void setEmotionClass(String emotionClass) {
		this.emotionClass = emotionClass;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}