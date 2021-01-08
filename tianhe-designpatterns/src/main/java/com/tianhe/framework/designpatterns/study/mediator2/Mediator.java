package com.tianhe.framework.designpatterns.study.mediator2;

/**
 * 具体的中介者类
 *
 * @author:姜伟锋
 * @DATE:2016年3月19日 @TIME: 下午7:25:57
 */
public class Mediator {

	// 搭档的引用
	private Man man;
	private Women women;

	public void setMan(Man man) {
		this.man = man;
	}

	public void setWomen(Women women) {
		this.women = women;
	}

	public void getParter(Person person) {
		// 将搭档设置上
		if (person instanceof Man) {
			this.setMan((Man) person);
		} else {
			this.setWomen((Women) person);
		}

		// 判断条件
		if (man == null || women == null) {
			System.out.println("我不是同性恋");
		} else {
			if (man.getCondition() == women.getCondition()) {
				System.out.println(man.getName() + "和" + women.getName() + "绝配");
			} else {
				System.out.println(man.getName() + "和" + women.getName() + "不配");
			}
		}

	}
}
