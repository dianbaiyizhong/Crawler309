package org.zjicm.crawler.util.pojo;

public class TaskConfig {
	private int taskId;
	private int monitorTime;
	private int isLucene;
	private int isApplyTemplate;
	private int isStoreVisitedUrl;

	public int getIsApplyTemplate() {
		return isApplyTemplate;
	}

	public void setIsApplyTemplate(int isApplyTemplate) {
		this.isApplyTemplate = isApplyTemplate;
	}

	public int getIsStoreVisitedUrl() {
		return isStoreVisitedUrl;
	}

	public void setIsStoreVisitedUrl(int isStoreVisitedUrl) {
		this.isStoreVisitedUrl = isStoreVisitedUrl;
	}

	public int getIsLucene() {
		return isLucene;
	}

	public void setIsLucene(int isLucene) {
		this.isLucene = isLucene;
	}

	public int getMonitorTime() {
		return monitorTime;
	}

	public void setMonitorTime(int monitorTime) {
		this.monitorTime = monitorTime;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

}
