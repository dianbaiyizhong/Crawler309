package org.zjicm.crawler.util.pojo;

public class ControlConfig {
	public int getThreads() {
		return threads;
	}

	public void setThreads(int threads) {
		this.threads = threads;
	}

	public int getThreadGatherMax() {
		return threadGatherMax;
	}

	public void setThreadGatherMax(int threadGatherMax) {
		this.threadGatherMax = threadGatherMax;
	}

	private int threads;

	private int threadGatherMax;

}
