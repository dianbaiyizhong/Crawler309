package org.zjicm.crawler.initialzation.service.impl;

import java.util.List;

import org.zjicm.crawler.initialzation.dao.IConfigureDAO;
import org.zjicm.crawler.initialzation.dao.impl.ConfigureDAO;
import org.zjicm.crawler.initialzation.service.IConfigureService;
import org.zjicm.crawler.url.vo.Gatherwebsite;

public class ConfigureService implements IConfigureService {

	@Override
	public List<Gatherwebsite> GetTaskSeed(int taskID) {
		IConfigureDAO configureDAO = new ConfigureDAO();
		return configureDAO.GetTaskSeed(taskID);
	}

	@Override
	public boolean ModifyTaskState(int taskId, String TaskState) {
		IConfigureDAO configureDAO = new ConfigureDAO();
		return configureDAO.ModifyTaskState(taskId, TaskState);
	}

	@Override
	public int getTaskID() {
		IConfigureDAO configureDAO = new ConfigureDAO();
		return configureDAO.getTaskID();
	}

	@Override
	public String getTaskState(int taskId) {
		IConfigureDAO configureDAO = new ConfigureDAO();

		return configureDAO.getTaskState(taskId);
	}

	@Override
	public String getLuceneConfig(int taskID) {
		IConfigureDAO configureDAO = new ConfigureDAO();
		return configureDAO.getLuceneConfig(taskID);

	}

	@Override
	public String getVisitedServer(int taskID) {
		IConfigureDAO configureDAO = new ConfigureDAO();
		return configureDAO.getVisitedServer(taskID);
	}
}
