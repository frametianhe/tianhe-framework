package com.tianhe.framework.designpatterns.study.strategy2;


public class StrategyB implements Strategy{

	@Override
	public double cost(double num) {
		if(num >= 200){
			return num - 50;
		}
		return num;
	}

}
