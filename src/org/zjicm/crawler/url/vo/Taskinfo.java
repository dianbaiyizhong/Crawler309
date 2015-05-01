package org.zjicm.crawler.url.vo;

/**
 * Taskinfo entity. @author MyEclipse Persistence Tools
 */

public class Taskinfo implements java.io.Serializable {

	// Fields

	private Integer taskId;
	private String taskName;
	private String taskDescription;
	private String gatherThead;
	private String infoDb;
	private String sourceFileSave;
	private String sourceFileDb;
	private String infoUpdateServer;
	private String taskBeginTime;
	private String taskRemark;
	private String startTime;
	private String stopTime;
	private String operateType;
	private String totalDataSize;
	private String lastGathDateSize;
	private String taskState;

	// Constructors

	/** default constructor */
	public Taskinfo() {
	}

	/** full constructor */
	public Taskinfo(String taskName, String taskDescription,
			String gatherThead, String infoDb, String sourceFileSave,
			String sourceFileDb, String infoUpdateServer, String taskBeginTime,
			String taskRemark, String startTime, String stopTime,
			String operateType, String totalDataSize, String lastGathDateSize,
			String taskState) {
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.gatherThead = gatherThead;
		this.infoDb = infoDb;
		this.sourceFileSave = sourceFileSave;
		this.sourceFileDb = sourceFileDb;
		this.infoUpdateServer = infoUpdateServer;
		this.taskBeginTime = taskBeginTime;
		this.taskRemark = taskRemark;
		this.startTime = startTime;
		this.stopTime = stopTime;
		this.operateType = operateType;
		this.totalDataSize = totalDataSize;
		this.lastGathDateSize = lastGathDateSize;
		this.taskState = taskState;
	}

	// Property accessors

	public Integer getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return this.taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getGatherThead() {
		return this.gatherThead;
	}

	public void setGatherThead(String gatherThead) {
		this.gatherThead = gatherThead;
	}

	public String getInfoDb() {
		return this.infoDb;
	}

	public void setInfoDb(String infoDb) {
		this.infoDb = infoDb;
	}

	public String getSourceFileSave() {
		return this.sourceFileSave;
	}

	public void setSourceFileSave(String sourceFileSave) {
		this.sourceFileSave = sourceFileSave;
	}

	public String getSourceFileDb() {
		return this.sourceFileDb;
	}

	public void setSourceFileDb(String sourceFileDb) {
		this.sourceFileDb = sourceFileDb;
	}

	public String getInfoUpdateServer() {
		return this.infoUpdateServer;
	}

	public void setInfoUpdateServer(String infoUpdateServer) {
		this.infoUpdateServer = infoUpdateServer;
	}

	public String getTaskBeginTime() {
		return this.taskBeginTime;
	}

	public void setTaskBeginTime(String taskBeginTime) {
		this.taskBeginTime = taskBeginTime;
	}

	public String getTaskRemark() {
		return this.taskRemark;
	}

	public void setTaskRemark(String taskRemark) {
		this.taskRemark = taskRemark;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStopTime() {
		return this.stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

	public String getOperateType() {
		return this.operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getTotalDataSize() {
		return this.totalDataSize;
	}

	public void setTotalDataSize(String totalDataSize) {
		this.totalDataSize = totalDataSize;
	}

	public String getLastGathDateSize() {
		return this.lastGathDateSize;
	}

	public void setLastGathDateSize(String lastGathDateSize) {
		this.lastGathDateSize = lastGathDateSize;
	}

	public String getTaskState() {
		return this.taskState;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}

}