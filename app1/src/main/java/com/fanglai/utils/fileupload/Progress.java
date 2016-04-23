package com.fanglai.utils.fileupload;

import com.alibaba.fastjson.JSONObject;

/**
 * 上传进度
 * @author 方来
 * @date 2015-11-26
 */
public class Progress {
	private long bytesRead;	//目前为止读取文件的比特数
	private long contentLength;	//文件总大小
	private int items;	//目前正在读取第几个文件
	
	public long getBytesRead() {
		return bytesRead;
	}
	public void setBytesRead(long bytesRead) {
		this.bytesRead = bytesRead;
	}
	public long getContentLength() {
		return contentLength;
	}
	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}
	public int getItems() {
		return items;
	}
	public void setItems(int items) {
		this.items = items;
	}
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
