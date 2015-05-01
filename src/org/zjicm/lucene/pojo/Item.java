package org.zjicm.lucene.pojo;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
//import java.sql.Timestamp;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

public class Item implements Writable {

	@Override
	public String toString() {
		return "Item [title=" + title + ", content=" + content + ", url=" + url
				+ ", writer=" + writer + ", keywords=" + keywords + ", time="
				+ time + ", place=" + place + ", website=" + website + "]";
	}

	private String title;
	private String content;

	private String url;
	private String writer;
	private String keywords;
	private String time;

	private String place;
	private String website;

	public Item() {
	}

	public Item(String title, String content, String url, String writer,
			String keywords, String time, String place, String website) {
		// this.id = id;
		this.title = title;
		this.content = content;
		this.url = url;
		this.writer = writer;
		this.keywords = keywords;
		this.time = time;
		this.place = place;
		this.website = website;

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Override
	public void readFields(DataInput arg0) throws IOException {

		this.setTitle(Text.readString(arg0));
		this.setContent(Text.readString(arg0));
		this.setUrl(Text.readString(arg0));
		this.setWriter(Text.readString(arg0));
		this.setKeywords(Text.readString(arg0));
		this.setTime(Text.readString(arg0));
		this.setPlace(Text.readString(arg0));
		this.setWebsite(Text.readString(arg0));

	}

	@Override
	public void write(DataOutput arg0) throws IOException {

		Text.writeString(arg0, this.getTitle());
		Text.writeString(arg0, this.getContent());
		Text.writeString(arg0, this.getUrl());
		Text.writeString(arg0, this.getWriter());
		Text.writeString(arg0, this.getKeywords());
		Text.writeString(arg0, this.getTime());
		Text.writeString(arg0, this.getPlace());
		Text.writeString(arg0, this.getWebsite());

	}

}
