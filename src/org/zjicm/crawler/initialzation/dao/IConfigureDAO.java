package org.zjicm.crawler.initialzation.dao;

import java.util.List;

import org.zjicm.crawler.url.vo.Gatherwebsite;

public interface IConfigureDAO {

	public int getTaskID();

	public String getTaskState(int taskId);

	public boolean ModifyTaskState(int taskId, String TaskState);

	public List<Gatherwebsite> GetTaskSeed(int taskID);

	public String getVisitedServer(int taskID);

	public String getLuceneConfig(int taskID);
}
