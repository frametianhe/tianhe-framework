package com.tianhe.framework.designpatterns.study.facade3;

/**
 * 功能说明：代码结构清晰，只看主方法就知道这个业务逻辑实现什么功能，可读性更好
 *
 * @author:weifeng.jiang
 * @DATE:2017/5/25 @TIME:13:55
 */
public class Demo {

    /**
     * 功能说明：主方法就是门面
     * @author:weifeng.jiang
     */
    public static void main(String[] args){

        DemoService demoService = new DemoServiceImpl();

        //第一步
        demoService.stepOne();

        //第二步
        demoService.stepTwo();

        //第三步
        demoService.stepThree();
    }
}
