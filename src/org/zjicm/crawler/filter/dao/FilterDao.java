package org.zjicm.crawler.filter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.zjicm.crawler.database.conn.DBConnByMySql;
import org.zjicm.crawler.gather.singleton.UnVisitedUrlListDao;
import org.zjicm.crawler.url.pojo.UnVisitedUrl;
import org.zjicm.crawler.url.pojo.VisitedUrl;

public class FilterDao {

	public boolean FilterVisitedUrlByMd5FromDB(String md5) {

		PreparedStatement stmt = null;

		Connection conn = DBConnByMySql.getConnection();

		String sql = "select md5 from visitedurl where md5 = '" + md5 + "'";
		try {
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		DBConnByMySql.close(conn);
		return false;

	}

	public boolean FilterUnVisitedUrlByMd5FromDB(String md5) {

		PreparedStatement stmt = null;

		Connection conn = DBConnByMySql.getConnection();

		String sql = "select md5 from unvisitedurl where md5 = '" + md5 + "'";
		try {
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		DBConnByMySql.close(conn);
		return false;

	}

	public boolean SetRedundantListToDB(List<UnVisitedUrl> list, int count) {

		// 先判断出是否多余,先获取代抓实例
		UnVisitedUrlListDao unVisitedUrlListDao = UnVisitedUrlListDao
				.getInstance();
		if (unVisitedUrlListDao.getUnVisitedUrlBeanListSize() <= count) {
			// 如果代抓队列并没有大于指定的数量，那就什么都没发生，返回false
			return false;
		}
		// 在新的url的队列进来之前，先来个Set来去一下重
		Set<UnVisitedUrl> newUrlList = new HashSet<UnVisitedUrl>();
		Iterator it = list.iterator();

		while (it.hasNext()) {
			newUrlList.add((UnVisitedUrl) it.next());
		}

		boolean ret = false;
		Connection conn = DBConnByMySql.getConnection();
		String sql = "insert into unvisitedurl(url,md5) values(?,?)";

		try {
			for (UnVisitedUrl unVisitedUrl : newUrlList) {
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, unVisitedUrl.getUrl());
				pstmt.setString(2, unVisitedUrl.getMd5());

				int r = pstmt.executeUpdate();
				ret = r == 1;
				pstmt.close();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		DBConnByMySql.close(conn);
		return ret;

	}

	public boolean SetVisitedUrlListToDB(Map<String, VisitedUrl> map) {
		boolean ret = false;
		Connection conn = DBConnByMySql.getConnection();
		String sql = "insert into visitedurl(url,md5) values(?,?)";

		try {

			for (String key : map.keySet()) {

				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, map.get(key).getUrl());
				pstmt.setString(2, map.get(key).getMd5());

				int r = pstmt.executeUpdate();
				ret = r == 1;
				pstmt.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return ret;

	}

	public void GetUnVisitedUrlFormDB(int count) {
		UnVisitedUrlListDao uDao = UnVisitedUrlListDao.getInstance();
		PreparedStatement stmt = null;

		Connection conn = DBConnByMySql.getConnection();

		String sql = "select * from unvisitedurl limit " + "0," + count;
		try {
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				UnVisitedUrl unVisitedUrl = new UnVisitedUrl();
				unVisitedUrl.setUrl(rs.getString("url"));
				uDao.setUnVisitedUrl(unVisitedUrl);
				DeleteUrlByID(rs.getString("md5"), "unvisitedurl");

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		DBConnByMySql.close(conn);

	}

	public boolean DeleteUrlByID(String md5, String tableName) {
		boolean ret = false;
		Connection conn = DBConnByMySql.getConnection();
		String sql = "delete from " + tableName + " where md5=" + "'" + md5
				+ "'";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			int r = pstmt.executeUpdate();
			ret = r == 1;
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;

	}

}