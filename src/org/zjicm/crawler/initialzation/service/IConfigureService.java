package org.zjicm.crawler.initialzation.service;

import java.util.List;

import org.zjicm.crawler.url.vo.Gatherwebsite;

public interface IConfigureService {

	public int getTaskID();

	public String getTaskState(int taskId);

	public boolean ModifyTaskState(int taskId, String TaskState);

	public String getLuceneConfig(int taskID);

	public String getVisitedServer(int taskID);

	public List<Gatherwebsite> GetTaskSeed(int taskID);
}
