package org.zjicm.crawler.visitedurl.dao;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.ipc.VersionedProtocol;

public interface IVisitedUrlDAO extends VersionedProtocol {

	public Text filterVisitedUrl(Text text);

	public int getVisitedUrlSize();

	public void clearVisitedUrl();

	// public String getVisitedUrlMap();

	public boolean saveVisitedUrl();

	// 传一个链接的数量比较少，所以用String作为参数即可
	public boolean SetVisitedUrlListToDB(String str);

	// 传一个链接的数量比较少，所以用String作为参数即可
	public boolean FilterVisitedUrlByMd5FromDB(String md5);

	// 传一个链接的数量比较少，所以用String作为参数即可
	public void setVisitedUrl(String str);
}
