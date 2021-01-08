package com.tianhe.framework.designpatterns.study.facade3;

/**
 * 功能说明：门面模式接口实现类
 *
 * @author:weifeng.jiang
 * @DATE:2017/5/25 @TIME:13:52
 */
public class DemoServiceImpl implements DemoService{

    @Override
    public void stepOne() {
        System.out.println("第一步");
    }

    @Override
    public void stepTwo() {
        System.out.println("第二步");
    }

    @Override
    public void stepThree() {
        System.out.println("第三步");
    }
}
