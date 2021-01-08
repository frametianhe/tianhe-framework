package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.EventFactory;

/**
 * @author:weifeng.jiang
 * @DATE:2016年12月30日 @TIME: 上午10:46:12
 */
public class LogEventFactory implements EventFactory<LogEvent>{

	@Override
	public LogEvent newInstance() {
		return new LogEvent();
	}
}
