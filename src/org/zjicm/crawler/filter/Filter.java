package org.zjicm.crawler.filter;

import java.util.List;

import org.zjicm.crawler.analyzer.pojo.Page;
import org.zjicm.crawler.filter.dao.FilterDao;
import org.zjicm.crawler.gather.singleton.UnVisitedUrlListDao;
import org.zjicm.crawler.initialzation.singleton.TaskProperty;
import org.zjicm.crawler.url.pojo.UnVisitedUrl;
import org.zjicm.crawler.visitedurl.dao.IVisitedUrlConvert;
import org.zjicm.crawler.visitedurl.dao.impl.VisitedUrlConvert;

public class Filter {
	static FilterDao queueTool = new FilterDao();
	static TaskProperty taskPropertyDao = new TaskProperty();
	IVisitedUrlConvert vConvert = new VisitedUrlConvert();

	public void action(Page page, List unFilterlist) {

		synchronized (this) {
			// 获取代抓实例
			UnVisitedUrlListDao uDao = UnVisitedUrlListDao.getInstance();
			try {
				// 待抓去重
				unFilterlist = checkUnVisitedUrlList(unFilterlist);
				System.out.println("UnVisited Url size is:"
						+ uDao.getUnVisitedUrlBeanListSize());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("error:CheckUnVisited failure...");
				return;
			}

			try {
				// 已抓去重
				System.out.println("Visited Url Size is:"
						+ vConvert.getVisitedUrlSize());

				List<UnVisitedUrl> vlist = unFilterlist;

				unFilterlist = vConvert.filterVisitedUrl(vlist);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("error:CheckVisitedUrl failure...");

			}

			/**
			 * 在新的URL还没放进代抓队列之前,应该判断代抓的数量 在这里判断待抓队列的数量，如果大于某个数值，那就把剩余的url存入数据库
			 * 否则就继续存进代抓队列
			 **/

			try {
				if (queueTool.SetRedundantListToDB(unFilterlist, 2147483647)) {

					System.out.println("待抓数量已经满，往数据库存储");
					// 往数据库存储之后，为程序打上标识1，以便在待抓数量少到一定的程度的时候，从数据库调出来继续跑
					taskPropertyDao.setIsReturnUnVisitedUrlFormDB("1");
				} else {
					System.out.println("new url have save in Unvisitrd Url...");

					
					uDao.setUnVisitedUrl(unFilterlist);

				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("_______________" + "数据库出错");
				return;
			}

			// 获取是否从数据库中获取待抓链接 的标识
			String IsReturnUnVisitedUrlFormDB = TaskProperty
					.getIsReturnUnVisitedUrlFormDB();

			// 如果 数据库中获取待抓链接 的标识为1并且待抓的数据量小于一定的数量,那就从数据库调入
			try {
				//
				if (IsReturnUnVisitedUrlFormDB.equals("1")
						&& uDao.getUnVisitedUrlBeanListSize() <= 3000) {
					System.out
							.println("_______________" + "调入数据库待抓队列到本地任务执行成功");
					queueTool.GetUnVisitedUrlFormDB(20);
					taskPropertyDao.setIsReturnUnVisitedUrlFormDB("0");
				}
			} catch (Exception e) {
				System.out.println("_______________" + "调入数据库待抓队列到本地任务执行失败");
				return;
			}

		}
	}

	/**
	 * 待抓队列去重
	 * 
	 * @param list
	 * @return
	 */
	private List checkUnVisitedUrlList(List list) {
		UnVisitedUrlListDao uDao = UnVisitedUrlListDao.getInstance();
		list = uDao.filterUnVisitedUrlList(list);
		return list;
	}

}