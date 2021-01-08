package com.tianhe.framework.designpatterns.study.command4;

public class BananaCommand extends Command {

	public BananaCommand(Peddler peddler) {
		super(peddler);
	}

	@Override
	public void sail() {
		this.getPeddler().saleBanana();
	}

}
