package org.zjicm.crawler.url.pojo;

import java.io.Serializable;

import org.zjicm.crawler.url.util.MD5;
import org.zjicm.crawler.url.vo.Gatherwebsite;

public class UnVisitedUrl implements Serializable {

	private String url;
	private String md5;
	private Gatherwebsite gatherwebsite;
	private int nowDepth;

	public Gatherwebsite getGatherwebsite() {
		return gatherwebsite;
	}

	public void setGatherwebsite(Gatherwebsite gatherwebsite) {
		this.gatherwebsite = gatherwebsite;
	}

	public int getNowDepth() {
		return nowDepth;
	}

	public void setNowDepth(int nowDepth) {
		this.nowDepth = nowDepth;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		MD5 md5 = new MD5();
		setMd5(md5.MD5Encode(url));
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((md5 == null) ? 0 : md5.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnVisitedUrl other = (UnVisitedUrl) obj;
		if (md5 == null) {
			if (other.md5 != null)
				return false;
		} else if (!md5.equals(other.md5))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UnVisitedUrlBean [url=" + url + ", md5=" + md5 + "]";
	}

}
