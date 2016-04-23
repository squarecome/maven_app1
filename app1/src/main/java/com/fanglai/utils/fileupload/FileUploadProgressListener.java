package com.fanglai.utils.fileupload;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;
 
@Component
public class FileUploadProgressListener implements ProgressListener {
    private static ThreadLocal<HttpSession> session = new ThreadLocal<HttpSession>();
 
    public void setSession(HttpSession session){
    	FileUploadProgressListener.session.set(session);
        Progress status = new Progress();
        session.setAttribute("status", status);
    }
 
    /*
     * pBytesRead 到目前为止读取文件的比特数 pContentLength 文件总大小 pItems 目前正在读取第几个文件
     */
    public void update(long pBytesRead, long pContentLength, int pItems) {
        Progress status = (Progress) FileUploadProgressListener.session.get().getAttribute("status");
        status.setBytesRead(pBytesRead);
        status.setContentLength(pContentLength);
        status.setItems(pItems);
    }
 
}