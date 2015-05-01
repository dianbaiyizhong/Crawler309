package org.zjicm.crawler.analyzer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.zjicm.crawler.analyzer.dao.IDataDAO;
import org.zjicm.crawler.analyzer.vo.Webcontinfo;
import org.zjicm.crawler.database.conn.DBConnByMySql;

public class DataDAO implements IDataDAO {

	@Override
	public void saveData(Webcontinfo webcontinfo) {
		PreparedStatement stmt = null;
		Connection conn = DBConnByMySql.getConnection();

		try {

			stmt = conn
					.prepareStatement("INSERT INTO webcontinfo (`TextTitle`,`Text`,`PageTime`,`PageURL`,`reproter`,`ColectTime`,`TaskID`,`SiteID`) VALUES (?,?,?,?,?,?,?,?)");
			stmt.setString(1, webcontinfo.getTextTitle());
			stmt.setString(2, webcontinfo.getText());
			stmt.setString(3, webcontinfo.getPageTime());
			stmt.setString(4, webcontinfo.getPageUrl());
			stmt.setString(5, webcontinfo.getReproter());
			stmt.setString(6, webcontinfo.getColectTime());
			stmt.setString(7, webcontinfo.getTaskId());
			stmt.setString(8, webcontinfo.getSiteId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
