package org.zjicm.crawler.database.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.zjicm.crawler.util.XmlReadUtil;
import org.zjicm.crawler.util.pojo.DBConfig;

public class DBConnByMySql {
	static String dbdriver = "com.mysql.jdbc.Driver";
	static XmlReadUtil xmlUtil = new XmlReadUtil();

	private static DBConfig getConnMsg() {
		DBConfig dBConfig = null;

		dBConfig = xmlUtil.readDBConfigXml();

		return dBConfig;
	}

	public static Connection getConnection() {
		Connection ret = null;
		try {
			Class.forName(dbdriver);
			DBConfig cc = getConnMsg();
			String url = "jdbc:mysql://" + cc.getServerIP() + "/"
					+ cc.getDataBaseName()
					+ "?useUnicode=true&characterEncoding=UTF-8";
			ret = DriverManager.getConnection(url, cc.getServerUserName(),
					cc.getServerPassword());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
