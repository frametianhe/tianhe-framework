package com.tianhe.framework.designpatterns.study.command3;

//商户服务员
public class Waiter {

	private Command command;

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public Waiter(Command command) {
		super();
		this.command = command;
	}

	public Waiter() {

	}

	public void sail(){
		command.sail();
	}
}
