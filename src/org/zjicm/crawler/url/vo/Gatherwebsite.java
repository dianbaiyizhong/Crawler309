package org.zjicm.crawler.url.vo;

/**
 * Gatherwebsite entity. @author MyEclipse Persistence Tools
 */

public class Gatherwebsite implements java.io.Serializable {

	// Fields

	private Integer siteId;
	@Override
	public String toString() {
		return "Gatherwebsite [siteId=" + siteId + ", taskId=" + taskId
				+ ", siteName=" + siteName + ", siteType=" + siteType
				+ ", siteArea=" + siteArea + ", siteWeight=" + siteWeight
				+ ", siteEnterUrl=" + siteEnterUrl + ", crawlerDepth="
				+ crawlerDepth + ", filterDomain=" + filterDomain
				+ ", userName=" + userName + ", password=" + password
				+ ", remark=" + remark + ", template=" + template
				+ ", directDomain=" + directDomain + ", contentLimitFields="
				+ contentLimitFields + ", directUrl=" + directUrl + "]";
	}

	private Integer taskId;
	private String siteName;
	private String siteType;
	private String siteArea;
	private String siteWeight;
	private String siteEnterUrl;
	private String crawlerDepth;
	private String filterDomain;
	private String userName;
	private String password;
	private String remark;
	private String template;
	private String directDomain;
	private String contentLimitFields;
	private String directUrl;

	// Constructors

	/** default constructor */
	public Gatherwebsite() {
	}

	/** full constructor */
	public Gatherwebsite(Integer taskId, String siteName, String siteType,
			String siteArea, String siteWeight, String siteEnterUrl,
			String crawlerDepth, String filterDomain, String userName,
			String password, String remark, String template,
			String directDomain, String contentLimitFields, String directUrl) {
		this.taskId = taskId;
		this.siteName = siteName;
		this.siteType = siteType;
		this.siteArea = siteArea;
		this.siteWeight = siteWeight;
		this.siteEnterUrl = siteEnterUrl;
		this.crawlerDepth = crawlerDepth;
		this.filterDomain = filterDomain;
		this.userName = userName;
		this.password = password;
		this.remark = remark;
		this.template = template;
		this.directDomain = directDomain;
		this.contentLimitFields = contentLimitFields;
		this.directUrl = directUrl;
	}

	// Property accessors

	public Integer getSiteId() {
		return this.siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public Integer getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getSiteName() {
		return this.siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteType() {
		return this.siteType;
	}

	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}

	public String getSiteArea() {
		return this.siteArea;
	}

	public void setSiteArea(String siteArea) {
		this.siteArea = siteArea;
	}

	public String getSiteWeight() {
		return this.siteWeight;
	}

	public void setSiteWeight(String siteWeight) {
		this.siteWeight = siteWeight;
	}

	public String getSiteEnterUrl() {
		return this.siteEnterUrl;
	}

	public void setSiteEnterUrl(String siteEnterUrl) {
		this.siteEnterUrl = siteEnterUrl;
	}

	public String getCrawlerDepth() {
		return this.crawlerDepth;
	}

	public void setCrawlerDepth(String crawlerDepth) {
		this.crawlerDepth = crawlerDepth;
	}

	public String getFilterDomain() {
		return this.filterDomain;
	}

	public void setFilterDomain(String filterDomain) {
		this.filterDomain = filterDomain;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTemplate() {
		return this.template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getDirectDomain() {
		return this.directDomain;
	}

	public void setDirectDomain(String directDomain) {
		this.directDomain = directDomain;
	}

	public String getContentLimitFields() {
		return this.contentLimitFields;
	}

	public void setContentLimitFields(String contentLimitFields) {
		this.contentLimitFields = contentLimitFields;
	}

	public String getDirectUrl() {
		return this.directUrl;
	}

	public void setDirectUrl(String directUrl) {
		this.directUrl = directUrl;
	}

}