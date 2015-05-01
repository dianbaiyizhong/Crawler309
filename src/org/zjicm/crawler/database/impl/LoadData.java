package org.zjicm.crawler.database.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.zjicm.crawler.database.conn.DBConnByMySql;

public class LoadData {

	public ArrayList<String> LoadTaskId() {
		ArrayList<String> list = new ArrayList<String>();
		PreparedStatement stmt = null;
		Connection conn = DBConnByMySql.getConnection();
		String sql = "select * from taskinfo";

		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				list.add(rs.getString("TaskID"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public ArrayList<String> LoadLuceneConfig(String id) {
		ArrayList<String> list = new ArrayList<String>();
		PreparedStatement stmt = null;
		Connection conn = DBConnByMySql.getConnection();
		String sql = "select lucene_ip,lucene_port from lucene_info where id = "
				+ id;

		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String str = "";

				str += rs.getString("lucene_ip") + ":";
				str += rs.getString("lucene_port");
				list.add(str);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public String getTask_luceneIp(String taskid) {
		String result = "";
		PreparedStatement stmt = null;
		Connection conn = DBConnByMySql.getConnection();
		String sql = "select * from taskinfo where taskid = " + taskid;

		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				result = rs.getString("SourceFileDB");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

}
