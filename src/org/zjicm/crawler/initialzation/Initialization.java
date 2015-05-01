package org.zjicm.crawler.initialzation;

import java.util.List;

import org.zjicm.crawler.gather.singleton.UnVisitedUrlListDao;
import org.zjicm.crawler.initialzation.service.IConfigureService;
import org.zjicm.crawler.initialzation.service.impl.ConfigureService;
import org.zjicm.crawler.initialzation.singleton.TaskProperty;
import org.zjicm.crawler.url.pojo.UnVisitedUrl;
import org.zjicm.crawler.url.vo.Gatherwebsite;
import org.zjicm.crawler.visitedurl.rpc.VisitedurlClient;

public class Initialization {
	static TaskProperty tp = new TaskProperty();
	static IConfigureService iConfigureService = new ConfigureService();
	static VisitedurlClient vClient = new VisitedurlClient();

	static/**
			 * 初始化的时候修改任务状态值未restart
			 */
	private void InitializatinState(int TaskID) {
		// 修改程序状态值为restart并且设置单例的值
		if (!iConfigureService.ModifyTaskState(TaskID, "restart")) {
			System.out.println("error:ModifyTaskState failure...");
		} else {
			tp.setTaskState("restart");
		}
	}

	/**
	 * 根据任务配置信息获取种子
	 * 
	 * @return
	 */
	private List getSeedElement(int TaskID) {
		List<Gatherwebsite> SeedElementList = iConfigureService
				.GetTaskSeed(TaskID);
		return SeedElementList;
	}

	/**
	 * 清除待抓、已抓
	 */
	private void clearList() {
		UnVisitedUrlListDao uDao = UnVisitedUrlListDao.getInstance();
		uDao.clear();
		vClient.getVisitedurlClient().clearVisitedUrl();

	}

	public void action() {

		try {

			// 获取TaskID
			int TaskID = tp.getTaskID();

			// 获取索引服务器
			if (tp.getIsLucene() == 1) {
				getLuceneConfig(TaskID);
			}
			// 获取已抓服务器
			getVisitedServerConfig(TaskID);

			// 获取种子配置
			List<Gatherwebsite> GatherwebsiteList = getSeedElement(TaskID);

			// 清除待抓、已抓
			clearList();
			// 获取代抓队列
			UnVisitedUrlListDao uDao = UnVisitedUrlListDao.getInstance();
			for (int i = 0; i < GatherwebsiteList.size(); i++) {

				UnVisitedUrl u = new UnVisitedUrl();
				// 设置爬虫深度
				u.setNowDepth(0);

				u.setGatherwebsite(GatherwebsiteList.get(i));

				System.out.println("seed url is:"
						+ GatherwebsiteList.get(i).getSiteEnterUrl());

				u.setUrl(GatherwebsiteList.get(i).getSiteEnterUrl());

				// 待抓入队
				uDao.setUnVisitedUrl(u);

			}

			// 把状态值修改为restart
			InitializatinState(TaskID);

			// 把 从数据库调入待抓队列的标识 设为 0
			tp.setIsReturnUnVisitedUrlFormDB("0");

			System.out.println("The Task Initialization successful...");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void getVisitedServerConfig(int taskID) {
		String str = iConfigureService.getVisitedServer(taskID);
		String[] arr = str.split(":");
		tp.setVisitedurlIp(arr[0]);
		tp.setVisitedurlPort(arr[1]);

	}

	private void getLuceneConfig(int taskID) {
		String str = iConfigureService.getLuceneConfig(taskID);
		String[] arr = str.split(":");
		tp.setLuceneIp(arr[0]);
		tp.setLucenePort(arr[1]);

	}
}