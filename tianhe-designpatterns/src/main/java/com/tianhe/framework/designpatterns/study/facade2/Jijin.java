package com.tianhe.framework.designpatterns.study.facade2;

public class Jijin {

	private Gupiao gupiao;
	private Guozai guozai;
	private Qihuo qihuo;
	
	public Jijin(){
		guozai = new Guozai();
		gupiao = new Gupiao();
		qihuo = new Qihuo();
	}
	
	public void maiJijinA(){
		guozai.mai();
		qihuo.mai();
	}
	
	public void maiJijinB(){
		guozai.mai();
		qihuo.mai();
		gupiao.chao();
	}
}
