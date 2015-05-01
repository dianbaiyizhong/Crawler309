package org.zjicm.lucene.rpc;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.security.UserGroupInformation;
import org.zjicm.lucene.pojo.Item;

public class Client {

	/**
	 * @param args
	 */
	private static Configuration conf = new Configuration();
	private static Method webData;
	// private static Method getItem;
	static {
		try {
			webData = ServerTransport.class.getMethod("webData",
					new Class[] { Item.class });
			// getItem = ServerTransport.class.getMethod("getItem", new Class[]
			// {ItemList.class});
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public void toClient(List<Item> list) {
	public String toClient(Item item, int port, String ip) {
		// TODO Auto-generated method stub
		// System.out.print(list.size());
		// ItemList il = new ItemList(list);

		InetSocketAddress[] defaultAddresses = new InetSocketAddress[] { new InetSocketAddress(
				ip, port) };
		Object[][] params = new Object[1][1];
		// params[0][0] = il;
		params[0][0] = item;

		Object[] x = null;
		Item[][] pitems = new Item[][] { { item } };
		// System.out.print(((List) params[0][0]).size());
		UserGroupInformation user = UserGroupInformation
				.createRemoteUser("user");
		try {
			// RPC.call(getItem, params, defaultAddresses, user, conf);
			x = RPC.call(webData, pitems, defaultAddresses, user, conf);
			// System.out.print(x[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (String) x[0];

	}

}
