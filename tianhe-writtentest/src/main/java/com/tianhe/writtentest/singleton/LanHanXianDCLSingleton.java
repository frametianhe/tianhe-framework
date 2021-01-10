package com.tianhe.writtentest.singleton;

/**
 * 这种写法在getSingleton方法中对singleton进行了两次判空，第一次是为了不必要的同步，第二次是在singleton等于null的情况下才创建实例
 * DCL优点是资源利用率高，第一次执行getInstance时单例对象才被实例化，效率高。缺点是第一次加载时反应稍慢一些
 * 在高并发环境下也有一定的缺陷，虽然发生的概率很小。DCL虽然在一定程度解决了资源的消耗和多余的同步，线程安全等问题，但是他还是在某些情况会出现失效的问题，也就是DCL失效
 * Created by tianhe on 2020/3/29.
 *
 * javap -c LanHanXianDCLSingleton 查看字节码文件的汇编代码
 * javap -v LanHanXianDCLSingleton 查看方法flag ACC_SYNCHRONIZED同步标志
 */
public class LanHanXianDCLSingleton {

//    线程可见性、禁止指令重排序,解决dcl失效问题
    private volatile static LanHanXianDCLSingleton instance;

    private LanHanXianDCLSingleton(){

    }

    public static LanHanXianDCLSingleton getInstance(){
//        为什么要双重判断，只是第一次创建对象才需要加锁，初始化完成后就不需要
        if(instance == null){//线程1 2 到达这里
            synchronized (LanHanXianDCLSingleton.class){//锁住new代码 //线程1到这里继续向下执行，线程2等待
//                不加volatile编译器会把这个判断去掉，读取会再次从主存读取，写完会同步主存
                if(instance == null){//线程1到这里发现instance为空，继续执行if代码块，执行完退出同步代码块，然后线程2进入同步代码
//                    如果这里不加一次判断就会造成instance再次实例化
//                    对引用类型赋值，new对象分为几步，防止这几步在执行过程中被其他其他线程获取这个变量，取到一个不完整的实例
//                    编译器做了优化，允许对象在构造函数未调用完前，将共享变量的引用指向部分构造的对象，虽然对象未被完全实例化，但是已
//                    不会null了
                    instance = new LanHanXianDCLSingleton();//二次判断，为了避免线程一在创建实例之后，线程二进来也创建了新实例
                }
            }
        }
        return instance;
    }
}
