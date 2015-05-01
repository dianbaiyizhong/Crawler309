package org.zjicm.crawler.controller;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.zjicm.crawler.controller.dao.ControllerDao;
import org.zjicm.crawler.gather.Gather;
import org.zjicm.crawler.gather.singleton.UnVisitedUrlListDao;
import org.zjicm.crawler.initialzation.Initialization;
import org.zjicm.crawler.url.pojo.UnVisitedUrl;
import org.zjicm.crawler.util.XmlReadUtil;
import org.zjicm.crawler.util.pojo.ControlConfig;
import org.zjicm.crawler.visitedurl.rpc.VisitedurlClient;

public class Controller {
	public static boolean flag = true; // 循环信号量，用于判断调度线程是否需要继续
	private static int threadMax; // 最大线程数
	private static int gatherMax;// 单位线程最大抓取数
	static VisitedurlClient vClient = new VisitedurlClient();

	static XmlReadUtil xmlutil = new XmlReadUtil();

	private static ControlConfig getControlMsg() {
		ControlConfig controlConfig = null;
		controlConfig = xmlutil.readControlConfigXml();
		return controlConfig;
	}

	static {
		ControlConfig cc = getControlMsg();
		threadMax = cc.getThreads();
		gatherMax = cc.getThreadGatherMax();
	}

	private ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors
			.newFixedThreadPool(threadMax);

	public void action(String status) {

		if ("start".equals(status)) {

			// 1启动状态
			// 导入数据
			start();
		} else if ("pause".equals(status)) {
			// 2暂停状态
			pause();
		} else if ("stop".equals(status)) {
			// 3停止状态
			stop();
		} else if ("restart".equals(status)) {
			// 4重启状态
			restart();
		}
	}

	/**
	 * 重启首先运行初始化模块，然后调用运行模块
	 * 
	 */
	private void restart() {

		Initialization initialization = new Initialization();
		initialization.action();
		start();

	}

	private void stop() {
		flag = false;
		Gather.flag = false;
		UnVisitedUrlListDao.getInstance().clear();
		vClient.getVisitedurlClient().clearVisitedUrl();
	}

	private void pause() {
		// 1修改调度线程的标识位
		flag = false;
		Gather.flag = false;
		// 2修改爬虫的标识位

		// 把待抓放进数据库存储
		ControllerDao controllerDao = new ControllerDao();

		controllerDao.saveUnVisitedUrl(UnVisitedUrlListDao.getInstance()
				.getUnVisitedUrlList());

		vClient.getVisitedurlClient().saveVisitedUrl();
		// 不要清除已抓和待抓

	}

	private void start() {
		/**
		 * 调度线程的作用为:根据线程池的大小以及信号量，判断是否需要将爬虫线程加入线程池
		 * */

		// 创建待抓实例
		final UnVisitedUrlListDao uDao = UnVisitedUrlListDao.getInstance();

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (flag) {
					// 判断，如果线程池中的线程数量小于最大线程数，则创建一个线程加入线程池
					if (threadPool.getActiveCount() < threadMax) {
						// 当活跃线程为0的时候才输出到控制台，否则输出太多
						if (threadPool.getActiveCount() == 0) {
							// System.out
							// .println("now the actived thread count:  "
							// + threadPool.getActiveCount());
						}

						// 创建队列，用于接收待抓
						Queue<UnVisitedUrl> queue = new LinkedList<UnVisitedUrl>();

						// 从待抓取出相应个数的待抓队列，传递给爬虫线程
						for (int i = 0; i < gatherMax; i++) {

							// 从代抓队列中获取url给新的队列
							UnVisitedUrl u = uDao.get_A_UnVisitedUrl();

							queue.add(u);
						}

						// 创建新的爬虫任务

						Gather gather = new Gather(queue);

						threadPool.execute(gather);

					} else {
						// 如果线程池中的线程已满，则等待一段时间
						try {
							Thread.sleep(8000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});

		t.start();

	}
}