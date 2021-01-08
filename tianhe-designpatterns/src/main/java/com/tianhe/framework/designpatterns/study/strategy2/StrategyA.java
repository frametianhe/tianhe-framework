package com.tianhe.framework.designpatterns.study.strategy2;

public class StrategyA implements Strategy{

	@Override
	public double cost(double num) {
		return num * 0.8;
	}

}
