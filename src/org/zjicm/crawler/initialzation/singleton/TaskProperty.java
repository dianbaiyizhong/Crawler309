package org.zjicm.crawler.initialzation.singleton;

public class TaskProperty {
	// 任务状态
	private static String TaskState = null;
	// TaskID
	private static int TaskID = 0;
	// 是否 是时候 从数据库调入待抓数据
	private static String isReturnUnVisitedUrlFormDB = null;
	// 是否索引
	private static int isLucene = 0;

	// 是否使用网页模板

	private static int isApplyTemplate = 0;

	// 是否把已抓队列存储到数据库
	private int isStoreVisitedUrl = 0;

	// 索引端口
	private static String lucenePort;
	// 索引Ip
	private static String luceneIp;

	// 已抓服务器ip
	private static String visitedurlIp;

	// 已抓服务器port
	private static String visitedurlPort;

	public synchronized String getVisitedurlPort() {

		return visitedurlPort;
	}

	public synchronized void setVisitedurlPort(String Parameter) {
		visitedurlPort = Parameter;
	}

	public synchronized String getVisitedurlIp() {

		return visitedurlIp;
	}

	public synchronized void setVisitedurlIp(String Parameter) {
		visitedurlIp = Parameter;
	}

	public synchronized String getLuceneIp() {

		return luceneIp;
	}

	public synchronized void setLuceneIp(String Parameter) {
		luceneIp = Parameter;
	}

	public synchronized String getLucenePort() {

		return lucenePort;
	}

	public synchronized void setLucenePort(String Parameter) {
		lucenePort = Parameter;
	}

	public synchronized int getIsStoreVisitedUrl() {

		return isStoreVisitedUrl;
	}

	public synchronized void setIsStoreVisitedUrl(int Parameter) {
		isStoreVisitedUrl = Parameter;
	}

	public synchronized int getIsApplyTemplate() {

		return isApplyTemplate;
	}

	public synchronized void setIsApplyTemplate(int Parameter) {
		isApplyTemplate = Parameter;
	}

	public synchronized int getIsLucene() {

		return isLucene;
	}

	public synchronized void setIsLucene(int Parameter) {
		isLucene = Parameter;
	}

	public synchronized String getTaskState() {

		return TaskState;
	}

	public synchronized void setTaskState(String Parameter) {
		TaskState = Parameter;
	}

	public static synchronized String getIsReturnUnVisitedUrlFormDB() {

		return isReturnUnVisitedUrlFormDB;
	}

	public synchronized void setIsReturnUnVisitedUrlFormDB(String Parameter) {
		isReturnUnVisitedUrlFormDB = Parameter;
	}

	public synchronized int getTaskID() {
		return TaskID;
	}

	public synchronized void setTaskID(int Parameter) {

		TaskID = Parameter;
	}

}
