package org.zjicm.crawler.fetcher;

import java.util.Timer;
import java.util.TimerTask;

import org.zjicm.crawler.controller.Controller;
import org.zjicm.crawler.initialzation.service.IConfigureService;
import org.zjicm.crawler.initialzation.service.impl.ConfigureService;
import org.zjicm.crawler.initialzation.singleton.TaskProperty;
import org.zjicm.crawler.util.XmlReadUtil;
import org.zjicm.crawler.util.pojo.TaskConfig;

public class Fetcher {

	// 任务配置信息的单例
	static TaskProperty tp = new TaskProperty();
	// 控制数据库配置类
	static IConfigureService configureDAO = new ConfigureService();
	static String TaskState;
	static int TaskID;
	static int MonitorTime;
	static XmlReadUtil xmlUtil = new XmlReadUtil();

	public void startFetcher() {

		Controller controller = new Controller();
		controller.action("restart");

		// 定时器，每隔一定的时间去监测状态
	}

	public void start() {
		// 程序一开始获取任务TaskID
		TaskConfig taskConfig = xmlUtil.readTaskConfigXml();
		TaskID = taskConfig.getTaskId();
		// 把TaskID存进内存，随时调用
		tp.setTaskID(TaskID);
		tp.setIsLucene(taskConfig.getIsLucene());
		tp.setIsApplyTemplate(taskConfig.getIsApplyTemplate());
		tp.setIsStoreVisitedUrl(taskConfig.getIsStoreVisitedUrl());
		// 让程序开始运行
		MonitorTime = taskConfig.getMonitorTime();

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			public void run() {

				// 每隔一定时间获取任务状态
				TaskState = configureDAO.getTaskState(tp.getTaskID());

				// 如果控制台修改了状态，就会执行下面的if
				if (!TaskState.equals(tp.getTaskState())) {

					// 重新记录新的状态
					tp.setTaskState(TaskState);

					// 把新的状态放进控制类
					if (tp.getTaskState() != "") {
						System.out.println("The Task State is :" + TaskState);
						Controller controller = new Controller();
						controller.action(TaskState);

					} else {
						// 状态等于空的话，就证明，程序刚好是第一次开始的
						System.out.println("任务处于停止状态......正在接受控制台信号......");
					}

					if (tp.getTaskState().equals("start")) {
						startFetcher();

					}

				}

			}

		}, 0, MonitorTime);

	}
}