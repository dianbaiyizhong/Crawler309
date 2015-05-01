package org.zjicm.crawler.analyzer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.zjicm.crawler.analyzer.dao.IDataDAO;
import org.zjicm.crawler.analyzer.dao.impl.DataDAO;
import org.zjicm.crawler.analyzer.pojo.Page;
import org.zjicm.crawler.analyzer.util.PageUrlTool;
import org.zjicm.crawler.analyzer.vo.Webcontinfo;
import org.zjicm.crawler.filter.Filter;
import org.zjicm.crawler.initialzation.singleton.TaskProperty;
import org.zjicm.crawler.url.pojo.UnVisitedUrl;
import org.zjicm.lucene.pojo.Item;
import org.zjicm.lucene.rpc.Client;

public class Analyze {
	static Pattern sinaurl = Pattern
			.compile("sina.com.cn/news/\\w{1}/\\d{4}-\\d{1,2}-\\d{1,2}.*");
	static GetPageContent getPageContent = new GetPageContent();
	static Filter filter = new Filter();
	static PageUrlTool pageUrlTool = new PageUrlTool();
	static IDataDAO dataDAO = new DataDAO();
	static TaskProperty tp = new TaskProperty();
	static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");// 可以

	public void SelectAnalyzeMethodAndGetNewUrl(Page page) {

		// 开始获取新的Url，先定义好链表
		List<UnVisitedUrl> NewUrlList = null;

		// 获取目标深度
		int TargetDepth = Integer.parseInt(page.getGatherwebsite()
				.getCrawlerDepth());

		// 通过比较深度来确定是否通过该链接获取新的链接
		if (page.getNowDepth() <= TargetDepth - 1) {
			try {
				// 调用获取新链接的方法,请注意，此时NewUrlList里还没有包含Gatherwebsite信息
				NewUrlList = pageUrlTool.extracLinksToList(page);

			} catch (Exception e) {
				System.out.println("error:get new url failure");
			}

			// 如果判断获取的新url的数量大于等于0，那就进行过滤
			if (NewUrlList.size() >= 0) {
				try {
					// 在这里调用过滤的方法
					filter.action(page, NewUrlList);
				} catch (Exception e) {
					System.out.println("error:filter part is failure...");

				}
			}
		}

		// 在这里通过模板判断调用哪个解析方法类
		Webcontinfo webcontinfo = new Webcontinfo();

		if (tp.getIsApplyTemplate() != 0) {

			if (page.getPageUrl().contains("tianya.cn/post")) {

				webcontinfo = getPageContent.getBBSTianyaContent(page);

			} else if (page.getPageUrl().contains("news.sina.com.cn")) {
				// 正则表达式，匹配http://jiangsu.sina.com.cn/news/m/2014-12-21/d这类字符串
				webcontinfo = getPageContent.getNewsSinaContent(page);

				// Matcher m = sinaurl.matcher(page.getPageUrl());
				// if (m.find()) {
				// }
			} else if (page.getPageUrl().contains("tieba.baidu.com/p/")) {

				webcontinfo = getPageContent.getTiebaContent(page);

			} else {
				// 在有模板的情况下，没有写上对应的类，默认用这个方法
				webcontinfo = getPageContent.getBBSTianyaContent(page);

			}

		} else {
			webcontinfo = getPageContent.getOtherContent(page);
		}

		// 有正文的就存入数据库
		if (!webcontinfo.getText().equals("没有正文")) {
			// 在控制台输出标题，监视内容的获取
			System.out.println("title:【" + webcontinfo.getTextTitle() + "】");

			if (tp.getIsLucene() == 1) {
				synchronized (Item.class) {
					try {
						Boolean b;
						Item item = new Item(webcontinfo.getTextTitle(),
								webcontinfo.getText(),
								webcontinfo.getPageUrl(),
								webcontinfo.getReproter(), "",
								webcontinfo.getPageTime(), page
										.getGatherwebsite().getSiteName(), "");

						String i = new Client().toClient(item,
								Integer.parseInt(tp.getLucenePort()),
								tp.getLuceneIp());
						if (i.equals("1")) {
							System.out.println("Lucene have build...");
						} else {
							System.out.println("error:Lucene build error...");
						}

					} catch (Exception e) {
						System.out.println("error:Lucene error");
					}

				}
			}
			webcontinfo.setSiteId(page.getGatherwebsite().getSiteId()
					.toString());
			webcontinfo.setTaskId(page.getGatherwebsite().getTaskId()
					.toString());

			Webcontinfo webcontinfo1 = new Webcontinfo();
			webcontinfo1.setText(webcontinfo.getText());
			webcontinfo1.setTextTitle(webcontinfo.getTextTitle());
			webcontinfo1.setPageTime(webcontinfo.getPageTime());
			webcontinfo1.setPageUrl(webcontinfo.getPageUrl());
			webcontinfo1.setReproter(webcontinfo.getReproter());
			webcontinfo1.setTaskId(webcontinfo.getTaskId());
			webcontinfo1.setSiteId(webcontinfo.getSiteId());
			Date now = new Date();
			webcontinfo1.setColectTime(dateFormat.format(now));
			dataDAO.saveData(webcontinfo1);

		}

	}
}