package org.zjicm.crawler.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.zjicm.crawler.util.pojo.DBConfig;

public class XmlWriteUtil {

	public void WriteTaskConfig(String isLucene, String isApply,
			String isStore, String time, String id) throws DocumentException,
			FileNotFoundException {
		SAXReader read = new SAXReader();

		Document document = read.read(new File("xml/Task_Config.xml"));
		read.read(new FileInputStream("xml/Task_Config.xml"));

		Element root = document.getRootElement();

		// root.element("IsLucene").setText(isLucene);
		// root.element("IsApplyTemplate").setText(isApply);
		// root.element("IsStoreVisitedUrl").setText(isStore);
		// root.element("MonitorTime").setText(time);
		root.element("TaskID").setText(id);
		XMLWriter output;
		try {
			output = new XMLWriter(new FileWriter("xml/Task_Config.xml"));
			output.write(document);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void WriteControlConfig(String threads, String threadsMax)
			throws DocumentException, FileNotFoundException {
		SAXReader read = new SAXReader();

		Document document = read.read(new File("xml/Control_Config.xml"));
		read.read(new FileInputStream("xml/Control_Config.xml"));

		Element root = document.getRootElement();

		root.element("Threads").setText(threads);
		root.element("ThreadGatherMax").setText(threadsMax);

		XMLWriter output;
		try {
			output = new XMLWriter(new FileWriter("xml/Control_Config.xml"));
			output.write(document);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void WriteLuceneIp(String ServerIP) throws DocumentException,
			FileNotFoundException {
		SAXReader read = new SAXReader();

		Document document = read.read(new File("xml/Task_Config.xml"));
		read.read(new FileInputStream("xml/Task_Config.xml"));

		Element root = document.getRootElement();

		root.element("LuceneIp").setText(ServerIP);

		XMLWriter output;
		try {
			output = new XMLWriter(new FileWriter("xml/Task_Config.xml"));
			output.write(document);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void WriteLucenePort(String port) throws DocumentException,
			FileNotFoundException {
		SAXReader read = new SAXReader();

		Document document = read.read(new File("xml/Task_Config.xml"));
		read.read(new FileInputStream("xml/Task_Config.xml"));

		Element root = document.getRootElement();

		root.element("LucenePort").setText(port);

		XMLWriter output;
		try {
			output = new XMLWriter(new FileWriter("xml/Task_Config.xml"));
			output.write(document);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void WriteDBConfig(String ServerIP, String port, String username,
			String password, String dbname) throws DocumentException,
			FileNotFoundException {
		DBConfig c = new DBConfig();
		SAXReader read = new SAXReader();

		Document document = read.read(new File("xml/DataBase_Config.xml"));
		read.read(new FileInputStream("xml/DataBase_Config.xml"));

		Element root = document.getRootElement();
		List<Element> elements = root.elements();

		root.element("ServerUserName").setText(username);
		root.element("ServerIP").setText(ServerIP + ":" + port);

		root.element("ServerPassword").setText(password);

		XMLWriter output;
		try {
			output = new XMLWriter(new FileWriter("xml/DataBase_Config.xml"));
			output.write(document);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
