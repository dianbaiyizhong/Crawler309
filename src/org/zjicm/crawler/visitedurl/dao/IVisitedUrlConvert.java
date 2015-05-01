package org.zjicm.crawler.visitedurl.dao;

import java.util.List;

import org.zjicm.crawler.url.pojo.UnVisitedUrl;
import org.zjicm.crawler.url.pojo.VisitedUrl;

public interface IVisitedUrlConvert {

	public List filterVisitedUrl(List<UnVisitedUrl> list);

	public int getVisitedUrlSize();

	public void setVisitedUrl(VisitedUrl v);

	public void clearVisitedUrl();

	// public Map<String, VisitedUrl> getVisitedUrlMap();

	public boolean saveVisitedUrl();

	public boolean SetVisitedUrlListToDB(VisitedUrl v);

	public boolean FilterVisitedUrlByMd5FromDB(String md5);

}
