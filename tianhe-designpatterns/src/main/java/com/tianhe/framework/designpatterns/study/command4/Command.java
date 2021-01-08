package com.tianhe.framework.designpatterns.study.command4;

public abstract class Command {

	private Peddler peddler;//商贩引用

	public Peddler getPeddler() {
		return peddler;
	}

	public void setPeddler(Peddler peddler) {
		this.peddler = peddler;
	}

	public Command(Peddler peddler) {
		this.peddler = peddler;
	}

	public abstract void sail();
}
