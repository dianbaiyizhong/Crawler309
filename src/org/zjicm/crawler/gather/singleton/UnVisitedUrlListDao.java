package org.zjicm.crawler.gather.singleton;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.zjicm.crawler.controller.Controller;
import org.zjicm.crawler.filter.dao.FilterDao;
import org.zjicm.crawler.url.pojo.UnVisitedUrl;
import org.zjicm.crawler.url.pojo.VisitedUrl;
import org.zjicm.crawler.visitedurl.dao.IVisitedUrlConvert;
import org.zjicm.crawler.visitedurl.dao.impl.VisitedUrlConvert;
import org.zjicm.crawler.visitedurl.rpc.VisitedurlClient;

public class UnVisitedUrlListDao {

	private Set<UnVisitedUrl> UnVisitedUrlList = new HashSet<UnVisitedUrl>();
	static FilterDao queueTool = new FilterDao();
	IVisitedUrlConvert vConvert = new VisitedUrlConvert();

	private static final UnVisitedUrlListDao INSTANCE = new UnVisitedUrlListDao();

	public synchronized static final UnVisitedUrlListDao getInstance() {
		return UnVisitedUrlListDao.INSTANCE;
	}

	public synchronized UnVisitedUrl get_A_UnVisitedUrl() {
		Iterator it = UnVisitedUrlList.iterator();
		UnVisitedUrl u = null;
		try {
			if (it.hasNext()) {
				u = (UnVisitedUrl) it.next();
				if (u != null) {
					it.remove();
					// 在代抓中删除的同时，要马上放入已抓
					// 已抓入队
					VisitedUrl vUrl = new VisitedUrl();

					vUrl.setUrl(u.getUrl());
					vUrl.setMd5(u.getMd5());
					vUrl.setGatherwebsite(u.getGatherwebsite());

					if (Controller.flag) {
						vConvert.setVisitedUrl(vUrl);
					}

				}

			}
		} catch (Exception e) {

		} finally {
			return u;
		}
	}

	public synchronized void setUnVisitedUrl(UnVisitedUrl url) {
		Controller controller = new Controller();
		UnVisitedUrlList.add(url);

	}

	public synchronized void setUnVisitedUrl(List list) {

		Iterator<UnVisitedUrl> it = list.iterator();
		while (it.hasNext()) {
			
			
			UnVisitedUrl unVisitedUrl = (UnVisitedUrl) it.next();

			UnVisitedUrlList.add(unVisitedUrl);
		}

		System.out.println("new url stored size:" + list.size());

	}

	/** 将过滤方法写到操纵类里，可以方便的解决一端读取，一端插入的造成的重复问题 */
	public synchronized List filterUnVisitedUrlList(List list) {
		Iterator it = list.iterator();

		while (it.hasNext()) {
			UnVisitedUrl unVisitedUrl = (UnVisitedUrl) it.next();
			/**
			 * 同时将md5发送到数据库unvisitedurl做一次过滤
			 */
			// 在if里加上这个，可从数据库中过滤,||queueTool.FilterUnVisitedUrlByMd5FromDB(unVisitedUrl.getMd5())
			if (UnVisitedUrlList.contains(unVisitedUrl)) {

				it.remove();
			}
		}

		return list;

	}

	public int getUnVisitedUrlBeanListSize() {
		return UnVisitedUrlList.size();
	}

	public Iterator<UnVisitedUrl> getUnVisitedUrlIterator() {
		return UnVisitedUrlList.iterator();
	}

	public Set<UnVisitedUrl> getUnVisitedUrlList() {
		return UnVisitedUrlList;
	}

	/** 清除待抓队列 */
	public void clear() {
		UnVisitedUrlList.clear();
	}

}