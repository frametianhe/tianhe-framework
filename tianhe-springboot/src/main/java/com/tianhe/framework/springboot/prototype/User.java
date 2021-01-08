package com.tianhe.framework.springboot.prototype;

/**
 * Created by tianhe on 2019/11/28.
 */
//@Component
public class User {

//    @Autowired
    private Address address;

    public void getAddress(){
//         从运行结果看，虽然address是prototype类型但是在初始化user的时候对address进行依赖注入的对象已经确定了
        System.out.println(address);
    }
}
