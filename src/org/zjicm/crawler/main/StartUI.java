package org.zjicm.crawler.main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import org.dom4j.DocumentException;
import org.zjicm.crawler.database.impl.LoadData;
import org.zjicm.crawler.fetcher.Fetcher;
import org.zjicm.crawler.util.XmlWriteUtil;

public class StartUI extends JFrame {
	static int width = 413;
	static int height = 525;
	static XmlWriteUtil xmlWriteUtil = new XmlWriteUtil();
	static LoadData loadData = new LoadData();
	private JTextField tf_db_ip;
	private JTextField tf_db_port;
	private JTextField tf_db_username;
	private JTextField tf_db_password;
	private JLabel lbl_db_ip;
	private JLabel lbl_db_port;
	private JLabel lbl_db_username;
	private JLabel lbl_db_password;
	private JButton btn_linkserver = new JButton("连接服务器");
	private JLabel label_sysinfo = new JLabel("系统信息");
	private JComboBox cb_taskid = new JComboBox();
	private JLabel label_taskid = new JLabel("任务编号");
	private JLabel label_thread = new JLabel("采集线程数");
	private JLabel label_threadmax = new JLabel("单位进程采集量");
	private JTextField tf_threadmax;
	private JTextField tf_thread;
	private JButton button_start;
	private JButton button_cancel;
	private JLabel lblNewLabel;

	public StartUI() {
		LoadInfo();

		getContentPane().setLayout(null);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(22, 10, 345, 105);
		getContentPane().add(lblNewLabel);
		ImageIcon icon = new ImageIcon("res/logo.png");

		lblNewLabel.setIcon(icon);

		button_cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});

		button_start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					xmlWriteUtil.WriteControlConfig(tf_thread.getText(),
							tf_threadmax.getText());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (DocumentException e1) {
					e1.printStackTrace();
				}

				button_start.setEnabled(false);
				Fetcher fetcher = new Fetcher();
				fetcher.start();
			}
		});

		// 获取TaskID
		LoadTaskId();

		// 连接数据库
		btn_linkserver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// 开始修改数据库xml文件
				try {
					xmlWriteUtil.WriteDBConfig(tf_db_ip.getText(),
							tf_db_port.getText(), tf_db_username.getText(),
							tf_db_password.getText(), "Crawler309");

					xmlWriteUtil.WriteTaskConfig(null, null, null, null,
							tf_db_ip.getText());
					LoadTaskId();

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (DocumentException e1) {
					e1.printStackTrace();
				}

			}

		});

	}

	private void LoadTaskId() {

		try {

			ArrayList<String> list_taskid = loadData.LoadTaskId();
			label_sysinfo.setText("数据库连接成功");
			for (int i = 0; i < list_taskid.size(); i++) {
				cb_taskid.addItem(list_taskid.get(i));

			}
		} catch (Exception e) {
			// 在用户第二次输入错误的数据库信息的时候，要清空combox的内容

			cb_taskid.removeAllItems();
			cb_taskid.repaint();

			label_sysinfo.setText("数据库连接失败");

		}

		cb_taskid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		
		setInitLocation();

	}

	private void LoadInfo() {

		tf_db_ip = new JTextField();
		tf_db_ip.setText("192.168.1.102");
		tf_db_ip.setBounds(71, 148, 96, 21);
		getContentPane().add(tf_db_ip);
		tf_db_ip.setColumns(10);

		tf_db_port = new JTextField();
		tf_db_port.setText("3306");
		tf_db_port.setBounds(241, 148, 102, 21);
		getContentPane().add(tf_db_port);
		tf_db_port.setColumns(10);

		tf_db_username = new JTextField();
		tf_db_username.setText("root");
		tf_db_username.setBounds(71, 179, 96, 21);
		getContentPane().add(tf_db_username);
		tf_db_username.setColumns(10);

		tf_db_password = new JTextField();
		tf_db_password.setText("");
		tf_db_password.setBounds(241, 179, 102, 21);
		getContentPane().add(tf_db_password);
		tf_db_password.setColumns(10);

		lbl_db_ip = new JLabel("IP地址");
		lbl_db_ip.setBounds(22, 151, 51, 15);
		getContentPane().add(lbl_db_ip);

		lbl_db_port = new JLabel("端口号");
		lbl_db_port.setBounds(190, 151, 54, 15);
		getContentPane().add(lbl_db_port);

		lbl_db_username = new JLabel("用户名");
		lbl_db_username.setBounds(22, 182, 51, 15);
		getContentPane().add(lbl_db_username);

		lbl_db_password = new JLabel("密码");
		lbl_db_password.setBounds(190, 182, 54, 15);
		getContentPane().add(lbl_db_password);

		btn_linkserver.setBounds(38, 238, 126, 23);
		getContentPane().add(btn_linkserver);

		label_sysinfo.setBounds(241, 242, 96, 15);
		getContentPane().add(label_sysinfo);

		cb_taskid.setBounds(124, 301, 70, 21);
		getContentPane().add(cb_taskid);
		label_taskid.setBounds(38, 304, 54, 15);
		getContentPane().add(label_taskid);

		label_thread.setBounds(22, 383, 70, 15);
		getContentPane().add(label_thread);

		label_threadmax.setBounds(190, 383, 102, 15);
		getContentPane().add(label_threadmax);

		tf_thread = new JTextField();
		tf_thread.setText("10");
		tf_thread.setBounds(88, 380, 66, 21);
		getContentPane().add(tf_thread);
		tf_thread.setColumns(10);

		tf_threadmax = new JTextField();
		tf_threadmax.setText("200");
		tf_threadmax.setBounds(283, 380, 66, 21);
		getContentPane().add(tf_threadmax);
		tf_threadmax.setColumns(10);

		button_start = new JButton("采集");
		button_start.setBounds(38, 454, 93, 23);
		getContentPane().add(button_start);

		button_cancel = new JButton("退出");
		button_cancel.setBounds(250, 454, 93, 23);
		getContentPane().add(button_cancel);

		JSeparator separator = new JSeparator();
		separator.setBounds(22, 289, 345, 2);
		getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(22, 349, 345, 2);
		getContentPane().add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(22, 426, 345, 2);
		getContentPane().add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(22, 125, 345, 2);
		getContentPane().add(separator_3);
	}

	public static void main(String[] args) {
		StartUI startUI = new StartUI();
		startUI.setSize(413, 525);
		startUI.setVisible(true);

		startUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void setInitLocation() {
		int windowWidth = width; // 获得窗口宽

		int windowHeight = height; // 获得窗口高

		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包

		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸

		int screenWidth = screenSize.width; // 获取屏幕的宽

		int screenHeight = screenSize.height; // 获取屏幕的高

		setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2
				- windowHeight / 2);// 设置窗口居中显示
	}
}
