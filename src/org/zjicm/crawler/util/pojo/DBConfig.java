package org.zjicm.crawler.util.pojo;

public class DBConfig {
	private String serverIP;
	private String serverUserName;
	private String serverPassword;
	private String dataBaseName;

	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public String getServerUserName() {
		return serverUserName;
	}

	public void setServerUserName(String serverUserName) {
		this.serverUserName = serverUserName;
	}

	public String getServerPassword() {
		return serverPassword;
	}

	public void setServerPassword(String serverPassword) {
		this.serverPassword = serverPassword;
	}

	public String getDataBaseName() {
		return dataBaseName;
	}

	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}

	@Override
	public String toString() {
		return "CrawlerConfig [ serverIP=" + serverIP + ", serverUserName="
				+ serverUserName + ", serverPassword=" + serverPassword
				+ ", dataBaseName=" + dataBaseName + "]";
	}

}
