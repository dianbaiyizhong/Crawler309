package org.zjicm.crawler.gather;

import java.util.Iterator;
import java.util.Queue;

import org.zjicm.crawler.analyzer.Analyze;
import org.zjicm.crawler.analyzer.pojo.Page;
import org.zjicm.crawler.gather.dao.HttpClientDao;
import org.zjicm.crawler.initialzation.singleton.TaskProperty;
import org.zjicm.crawler.url.pojo.UnVisitedUrl;

public class Gather implements Runnable {
	static TaskProperty taskPropertyDao = new TaskProperty();

	/** 信号量，用于判断爬虫模块是否终止 */
	public static boolean flag;

	// 待抓队列
	private Queue<UnVisitedUrl> unVisitedUrlQueue;

	// 构造方法传入一个url队列
	public Gather(Queue<UnVisitedUrl> unVisitedUrlQueue) {
		this.unVisitedUrlQueue = unVisitedUrlQueue;

		// 初始化时设为true，停止时手工设为false
		flag = true;
	}

	// 爬虫的运行模块
	public void run() {

		HttpClientDao hc = new HttpClientDao();

		// 判断队列代抓队列是否还有Url
		Iterator it = unVisitedUrlQueue.iterator();

		// 解析实体类，将获取的源文件信息赋值到该类
		Page page = new Page();

		while (flag && it.hasNext()) {

			UnVisitedUrl uUrl = (UnVisitedUrl) it.next();

			if (uUrl == null)
				continue;

			// 调用获取源文件模块
			StringBuilder source = null;

			// 如果控制模块的flag为false，则不再解析

			source = hc.HttpClient(uUrl);

			// 将源文件赋值给解析实体类

			page.setPageText(source);
			page.setPageUrl(uUrl.getUrl());
			page.setNowDepth(uUrl.getNowDepth());
			page.setGatherwebsite(uUrl.getGatherwebsite());
			// 在这里开始调用解析模板，获取网页内容以及新链接
			Analyze analyze = new Analyze();

			try {
				// 如果控制模块的flag为false，则不再解析

				if (page.getPageText() != null) {
					analyze.SelectAnalyzeMethodAndGetNewUrl(page);
				}

			} catch (Exception e) {
				System.out.println("error:thread number: "
						+ Thread.currentThread().getId() + "  【"
						+ uUrl.getUrl() + "】 because the template is not suit");
				e.printStackTrace();

			}

		}
	}
}