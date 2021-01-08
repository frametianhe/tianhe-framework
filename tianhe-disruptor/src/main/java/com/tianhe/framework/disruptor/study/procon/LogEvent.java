package com.tianhe.framework.disruptor.study.procon;

import java.io.Serializable;

/**
 * 消息组件
 * @author:weifeng.jiang
 * @DATE:2016年12月31日 @TIME: 下午9:09:10
 */
public class LogEvent implements Serializable {

    /**
	 *  
	 */
	private static final long serialVersionUID = 9103243705490658641L;
	
	private long logId;
    private String content;
    
    public LogEvent(){
        
    }
    
    public LogEvent(long logId, String content){
        this.logId = logId;
        this.content = content;
    }

    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	@Override
	public String toString() {
		return "LogEvent [logId=" + logId + ", content=" + content + "]";
	}
}