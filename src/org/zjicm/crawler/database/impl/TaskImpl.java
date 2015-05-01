package org.zjicm.crawler.database.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.zjicm.crawler.database.conn.DBConnByMySql;
import org.zjicm.crawler.initialzation.pojo.SeedElement;

public class TaskImpl {

	public boolean ModifyTaskState(String NeedModifyValue, String TableName,
			int TaskID) {

		boolean ret = false;
		Connection conn = DBConnByMySql.getConnection();
		String sql = "update " + TableName + " set TaskState =" + "'"
				+ NeedModifyValue + "'" + " where TaskId=" + TaskID;

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

	public String GetValue(String ColumnName, String TableName, String IDname,
			int id) {
		PreparedStatement stmt = null;

		String ret = "";
		Connection conn = DBConnByMySql.getConnection();

		String sql = "select " + ColumnName + " from " + TableName + " where "
				+ IDname + "=" + id;
		try {
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ret = rs.getString(ColumnName);
				return ret;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		DBConnByMySql.close(conn);
		return ret;

	}

	public final List<SeedElement> GetTaskSeed(int TaskID) {
		List<SeedElement> list = new ArrayList<SeedElement>();
		PreparedStatement stmt = null;
		Connection conn = DBConnByMySql.getConnection();
		String sql = "select SiteEnterURL,SiteID,gatherwebsite.TaskID,Template from taskinfo,gatherwebsite where taskinfo.TaskID = gatherwebsite.TaskID and gatherwebsite.TaskID  = "
				+ TaskID;

		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				SeedElement seedElement = new SeedElement();
				seedElement.setSeedUrl(rs.getString("SiteEnterURL"));
				seedElement.setTaskID(rs.getInt("TaskID"));
				seedElement.setSiteID(rs.getInt("SiteID"));
				seedElement.setTemplate(rs.getString("Template"));
				list.add(seedElement);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public final int GetTaskID() {
		PreparedStatement stmt = null;
		Connection conn = DBConnByMySql.getConnection();
		String sql = "select * from taskinfo where OperateType = 'uncomplete'";

		int TaskID = 0;
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				TaskID = rs.getInt("TaskID");
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return TaskID;

	}

	public String GetCheckDirect(int TaskID, int SiteID, String TableName) {
		PreparedStatement stmt = null;
		Connection conn = DBConnByMySql.getConnection();
		String sql = "select DirectDomain from taskinfo,gatherwebsite where taskinfo.TaskID = gatherwebsite.TaskID and gatherwebsite.TaskID  = "
				+ TaskID + " and  SiteID=" + SiteID;
		String CheckDirectDomain = "";

		try {
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				CheckDirectDomain = rs.getString("DirectDomain");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return CheckDirectDomain;

	}

	public final List GetFilterDomain(int TaskID, int SiteID, String TableName) {
		List list = new ArrayList<String>();
		PreparedStatement stmt = null;
		Connection conn = DBConnByMySql.getConnection();
		String sql = "select FilterDomain from taskinfo,gatherwebsite where taskinfo.TaskID = gatherwebsite.TaskID and gatherwebsite.TaskID  = "
				+ TaskID + " and  SiteID=" + SiteID;

		String FilterDomain = "";
		try {
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				FilterDomain = rs.getString("FilterDomain");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			String FilterDomainArr[] = FilterDomain.split(",");
			for (int i = 0; i < FilterDomainArr.length; i++) {
				list.add(FilterDomainArr[i]);
			}
		} catch (Exception e) {
			System.out.println("FilterDomain未设置");

		}
		return list;

	}

	public final List GetDirectUrl(int TaskID, int SiteID, String TableName) {
		List list = new ArrayList();
		boolean ret = false;
		Connection conn = DBConnByMySql.getConnection();
		String sql = "select DirectUrl from taskinfo,gatherwebsite where taskinfo.TaskID = gatherwebsite.TaskID and gatherwebsite.TaskID  = "

				+ TaskID + " and  SiteID=" + SiteID;
		String DirectUrl = "";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				DirectUrl = rs.getString("DirectUrl");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			String DirectUrlArr[] = DirectUrl.split(",");
			for (int i = 0; i < DirectUrlArr.length; i++) {
				list.add(DirectUrlArr[i]);
			}
		} catch (Exception e) {
			System.out.println("DirectUrl值未设置");

		}
		return list;

	}

}
