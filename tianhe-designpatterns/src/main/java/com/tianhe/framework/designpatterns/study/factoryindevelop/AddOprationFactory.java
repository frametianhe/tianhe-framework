package com.tianhe.framework.designpatterns.study.factoryindevelop;

public class AddOprationFactory implements OprationFactory{

	@Override
	public Opration getOpration() {
		return new AddOpration();
	}
}
