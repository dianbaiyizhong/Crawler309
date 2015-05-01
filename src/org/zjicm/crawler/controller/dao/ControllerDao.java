package org.zjicm.crawler.controller.dao;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import org.zjicm.crawler.database.conn.DBConnByMySql;
import org.zjicm.crawler.url.pojo.UnVisitedUrl;
import org.zjicm.crawler.url.pojo.VisitedUrl;
import org.zjicm.crawler.visitedurl.dao.IVisitedUrlConvert;
import org.zjicm.crawler.visitedurl.dao.impl.VisitedUrlConvert;
import org.zjicm.crawler.visitedurl.rpc.VisitedurlClient;

public class ControllerDao {
	static VisitedurlClient vClient = new VisitedurlClient();
	IVisitedUrlConvert vConvert = new VisitedUrlConvert();

	/**
	 * 把待抓链接保存起来，方便下次继续跑
	 * 
	 * @param list
	 * @return
	 */
	public void saveUnVisitedUrl(Set<UnVisitedUrl> list) {

		// synchronized (list) {

		String sql = "insert into unvisitedurl(md5,url) values(?,?)";
		Connection conn = DBConnByMySql.getConnection();

		PreparedStatement pstmt = null;
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			for (UnVisitedUrl unVisitedUrl : list) {
				pstmt.setString(1, unVisitedUrl.getMd5());

				// 为了避免出现乱码插入错误，这里要转
				String url = unVisitedUrl.getUrl();
				url = URLEncoder.encode(url, "UTF-8");
				pstmt.setString(2, url);

				System.out.println(unVisitedUrl.getUrl());

				pstmt.addBatch();
			}
			pstmt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			DBConnByMySql.close(conn, pstmt);
		}
		// }
	}

}
