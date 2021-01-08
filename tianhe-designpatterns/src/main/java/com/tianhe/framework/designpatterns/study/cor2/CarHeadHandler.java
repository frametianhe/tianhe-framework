package com.tianhe.framework.designpatterns.study.cor2;

public class CarHeadHandler extends CarHandler{

	@Override
	public void handlerCar() {
		System.out.println("组装车头");
		if(this.carHandler != null){
			this.carHandler.handlerCar();
		}
	}

}
