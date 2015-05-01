package org.zjicm.lucene.rpc;

import org.zjicm.lucene.pojo.Item;
import org.zjicm.lucene.pojo.ItemList;

//import java.util.List;

public interface ServerTransport {

	/**
	 * @param args
	 * @return
	 */
	public String webData(Item entity);

	public void getItem(ItemList list);

}
