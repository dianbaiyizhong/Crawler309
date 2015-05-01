package org.zjicm.lucene.pojo;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
//import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.hadoop.io.Writable;

public class ItemList implements Writable {

	private ConcurrentLinkedQueue<Item> list;

	public ItemList() {
		list = new java.util.concurrent.ConcurrentLinkedQueue<Item>();
	}

	public ItemList(List<Item> list) {
		this.list = new java.util.concurrent.ConcurrentLinkedQueue<Item>();
		this.list.addAll(list);
	}

	public ConcurrentLinkedQueue<Item> getList() {
		return list;
	}

	public void setList(ConcurrentLinkedQueue<Item> list) {
		this.list = list;
	}

	@Override
	public void readFields(DataInput arg0) throws IOException {

		int len = arg0.readInt();
		for (int i = 0; i < len; i++) {
			Item item = new Item();
			item.readFields(arg0);
			this.list.add(item);
		}

	}

	@Override
	public void write(DataOutput arg0) throws IOException {
		// TODO Auto-generated method stub
		int len = list.size();
		arg0.writeInt(len);
		for (Item item : list) {
			item.write(arg0);
		}
	}

}
