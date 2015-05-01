package org.zjicm.crawler.initialzation.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.zjicm.crawler.database.conn.DBConnByMySql;
import org.zjicm.crawler.initialzation.dao.IConfigureDAO;
import org.zjicm.crawler.url.vo.Gatherwebsite;

public class ConfigureDAO implements IConfigureDAO {

	@Override
	public int getTaskID() {

		PreparedStatement stmt = null;

		int id = 0;
		Connection conn = DBConnByMySql.getConnection();

		String sql = "select * from taskinfo  where OperateType = 'uncomplete'";
		try {
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				id = rs.getInt("taskid");
				return id;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		DBConnByMySql.close(conn);
		return id;

	}

	@Override
	public String getTaskState(int taskId) {

		PreparedStatement stmt = null;

		String str = "";
		Connection conn = DBConnByMySql.getConnection();

		String sql = "select * from taskinfo  where  TaskID  = " + taskId;
		try {
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				str = rs.getString("TaskState");
				return str;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		DBConnByMySql.close(conn);
		return str;

	}

	@Override
	public boolean ModifyTaskState(int taskId, String TaskState) {

		boolean ret = false;
		Connection conn = DBConnByMySql.getConnection();
		String sql = "update taskinfo" + " set TaskState =" + "'" + TaskState
				+ "'" + " where TaskId=" + taskId;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			int r = pstmt.executeUpdate();
			ret = r == 1;
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBConnByMySql.close(conn);
		return ret;
	}

	@Override
	public List<Gatherwebsite> GetTaskSeed(int taskID) {

		List<Gatherwebsite> list = new ArrayList<Gatherwebsite>();
		PreparedStatement stmt = null;
		Connection conn = DBConnByMySql.getConnection();
		String sql = "select * from taskinfo,gatherwebsite where taskinfo.TaskID = gatherwebsite.TaskID and gatherwebsite.TaskID  = "
				+ taskID;

		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				Gatherwebsite gatherwebsite = new Gatherwebsite();
				gatherwebsite.setSiteEnterUrl(rs.getString("SiteEnterURL"));
				gatherwebsite.setSiteName(rs.getString("SiteName"));
				gatherwebsite.setTaskId(rs.getInt("TaskID"));
				gatherwebsite.setSiteId(rs.getInt("SiteID"));
				gatherwebsite.setTemplate(rs.getString("Template"));
				gatherwebsite.setCrawlerDepth(rs.getString("CrawlerDepth"));
				gatherwebsite.setFilterDomain(rs.getString("FilterDomain"));
				gatherwebsite.setDirectUrl(rs.getString("DirectUrl"));
				gatherwebsite.setDirectDomain(rs.getString("DirectDomain"));
				gatherwebsite.setCrawlerDepth(rs.getString("CrawlerDepth"));
				list.add(gatherwebsite);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getLuceneConfig(int id) {
		PreparedStatement stmt = null;
		Connection conn = DBConnByMySql.getConnection();
		String sql = "select * from lucene_info where id = (select SourceFileDB from taskinfo where taskid ="
				+ id + ")";
		String str = "";
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				str += rs.getString("lucene_ip") + ":";
				str += rs.getString("lucene_port");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;

	}

	@Override
	public String getVisitedServer(int taskID) {

		PreparedStatement stmt = null;

		String str = "";
		Connection conn = DBConnByMySql.getConnection();

		String sql = "select * from taskinfo  where  TaskID  = " + taskID;
		try {
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				str = rs.getString("InfoUpdateServer");
				return str;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		DBConnByMySql.close(conn);
		return str;

	}

}
