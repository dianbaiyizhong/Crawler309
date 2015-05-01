package org.zjicm.crawler.util;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.zjicm.crawler.util.pojo.ControlConfig;
import org.zjicm.crawler.util.pojo.DBConfig;
import org.zjicm.crawler.util.pojo.TaskConfig;

public class XmlReadUtil {

	public TaskConfig readTaskConfigXml() {
		File file = new File("xml/Task_Config.xml");
		SAXReader reader = new SAXReader();
		Element root = null;
		try {
			Document doc = reader.read(file);
			root = doc.getRootElement();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		TaskConfig config = new TaskConfig();

		config.setTaskId(Integer.parseInt(root.elementText("TaskID")));
		config.setMonitorTime(Integer.parseInt(root.elementText("MonitorTime")));
		config.setIsLucene(Integer.parseInt(root.elementText("IsLucene")));
		config.setIsApplyTemplate(Integer.parseInt(root
				.elementText("IsApplyTemplate")));
		config.setIsStoreVisitedUrl(Integer.parseInt(root
				.elementText("IsStoreVisitedUrl")));

		return config;

	}

	public DBConfig readDBConfigXml() {
		File file = new File("xml/DataBase_Config.xml");
		SAXReader reader = new SAXReader();
		Element root = null;
		try {
			Document doc = reader.read(file);
			root = doc.getRootElement();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		DBConfig dBConfig = new DBConfig();

		dBConfig.setDataBaseName(root.elementText("DataBaseName"));
		dBConfig.setServerIP(root.elementText("ServerIP"));
		dBConfig.setServerPassword(root.elementText("ServerPassword"));
		dBConfig.setServerUserName(root.elementText("ServerUserName"));

		return dBConfig;

	}

	public ControlConfig readControlConfigXml() {
		File file = new File("xml/Control_Config.xml");
		SAXReader reader = new SAXReader();
		Element root = null;
		try {
			Document doc = reader.read(file);
			root = doc.getRootElement();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		ControlConfig controlConfig = new ControlConfig();

		controlConfig.setThreadGatherMax(Integer.parseInt(root
				.elementText("ThreadGatherMax")));
		controlConfig.setThreads(Integer.parseInt(root.elementText("Threads")));

		return controlConfig;

	}

}
