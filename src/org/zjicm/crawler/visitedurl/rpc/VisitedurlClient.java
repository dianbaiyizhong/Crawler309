package org.zjicm.crawler.visitedurl.rpc;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.zjicm.crawler.initialzation.singleton.TaskProperty;
import org.zjicm.crawler.visitedurl.dao.IVisitedUrlDAO;

public class VisitedurlClient {
	static TaskProperty tp = new TaskProperty();

	public IVisitedUrlDAO getVisitedurlClient() {
		IVisitedUrlDAO iVisitedUrlDAO = null;

		InetSocketAddress addr = new InetSocketAddress(tp.getVisitedurlIp(),
				Integer.parseInt(tp.getVisitedurlPort()));
		try {
			iVisitedUrlDAO = (IVisitedUrlDAO) RPC.waitForProxy(
					IVisitedUrlDAO.class, 1, addr, new Configuration());

			return iVisitedUrlDAO;
		} catch (IOException e) {
			e.printStackTrace();
			return null;

		} finally {
			// close(iVisitedUrlDAO);

		}

	}

	public void close(IVisitedUrlDAO iVisitedUrlDAO) {
		RPC.stopProxy(iVisitedUrlDAO);
	}
}
