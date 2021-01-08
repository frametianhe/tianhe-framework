package com.tianhe.framework.designpatterns.study.command4;

public class AppleCommand extends Command {
	
	public AppleCommand(Peddler peddler) {
		super(peddler);
	}

	@Override
	public void sail() {
		this.getPeddler().saleApple();
	}

}
