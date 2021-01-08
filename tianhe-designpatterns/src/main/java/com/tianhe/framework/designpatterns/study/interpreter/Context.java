package com.tianhe.framework.designpatterns.study.interpreter;

/**
 * 上下文环境类，用来保存文法
 * @author:姜伟锋
 * @DATE:2016年3月19日 @TIME: 下午6:35:01
 */
public class Context {

	private String input;
	private int output;

	public Context(String input){
		this.input = input;
	}

	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public int getOutput() {
		return output;
	}
	public void setOutput(int output) {
		this.output = output;
	}


}
