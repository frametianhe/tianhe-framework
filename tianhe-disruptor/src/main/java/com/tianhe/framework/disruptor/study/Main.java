package com.tianhe.framework.disruptor.study;

import com.tianhe.framework.disruptor.study.procon.SimpleEvent;

public class Main {

	/**
	 * 等待策略
	 * 
	 * Disruptor 定义了 com.lmax.disruptor.WaitStrategy 接口用于抽象Consumer如何等待新事件，这是策略模式的应用。
       Disruptor 提供了多个WaitStrategy 的实现，每种策略都具有不同性能和优缺点，根据实际运行环境的 CPU 的硬件特点选择恰当的策略，并配合特定的 JVM 的配置参数，
 	      能够实现不同的性能提升。
 	      
       BlockingWaitStrategy、SleepingWaitStrategy、YieldingWaitStrategy 等，其中，
       BlockingWaitStrategy 是最低效的策略，但其对CPU的消耗最小并且在各种不同部署环境中能提供更加一致的性能表现；
       SleepingWaitStrategy的性能表现跟BlockingWaitStrategy差不多，对CPU的消耗也类似，但其对生产者线程的影响最小，适合用于异步日志类似的场景；
       YieldingWaitStrategy 的性能是最好的，适合用于低延迟的系统。在要求极高性能且事件处理线数小于CPU逻辑核心数的场景中。
       BlockingWaitStrategy: 对低延迟和吞吐率不像CPU占用那么重要
       BusySpinWaitStrategy： CPU使用率高，低延迟
       LiteBlockingWaitStrategy: BlockingWaitStrategy变种，实验性的
       PhasedBackoffWaitStrategy： Spins, then yields, then waits，不过还是适合对低延迟和吞吐率不像CPU占用那么重要的情况
       SleepingWaitStrategy: spin， then yield，然后sleep(LockSupport.parkNanos(1L))在性能和CPU占用率之间做了平衡。
       TimeoutBlockingWaitStrategy： sleep一段时间。 低延迟。
       YieldingWaitStrategy： 使用spin, Thread.yield()方式
       
       	发布事件
        Disruptor 的事件发布过程是一个两阶段提交的过程：
       	第一步：先从 RingBuffer 获取下一个可以写入的事件的序号；
       	第二步：获取对应的事件对象，将数据写入事件对象；
       	第三部：将事件提交到 RingBuffer;
       	事件只有在提交之后才会通知 EventProcessor 进行处理；ringBuffer.publish 方法最好包含在 finally中以确保必须得到调用；
       	因为如果某个请求的sequence未被提交，将会堵塞后续的发布操作或者其它的producer
       	
       	disruptor.shutdown();//关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；  
		executor.shutdown();//关闭 disruptor 使用的线程池；如果需要的话，必须手动关闭， disruptor 在 shutdown 时不会自动关闭；  
		
		disruptor
		生产者消费者模型，一对一，一对多，多个handler，每个handler都会把消息消费一遍
		EventHandler是消费者，订阅者模式
		
		
		线程池 threadPoolExecutor
		请求进来，请求爽小于等于核心线程数，线程池就创建线程处理请求，请求大于核心线程处数量，就放入请求队列，请求数量大于队列，队列占满以后，请求数量小于线程池
		最大线程数，线程池会创建线程去执行请求，这一部分线程在超时时间以后会自动销毁，请求数量大于线程池最大线程数量，任务会被线程池拒绝，可以自定义线程池策略
		dubbo中使用建议线程池核心线程数和线程池最大线程数一样，减少任务延迟，建议追加服务器增加客户体验
		
		jdk自带的阻塞队列
		线程池默认用jdk阻塞队列，有界ArrayBlockingQueue，无界LinkedBlockingQueue
		用disruptor无锁队列
		
		这两种队列的区别，为什么disruptor无锁队列效率高
		disruptor采用无锁，阻塞队列使用lock锁
		disruptor避免伪共享
		disruptor采用CAS无锁实现，这个是cpu级别的命令，阻塞队列采用锁是系统级别的命令，会有上下文切换等系统层面的开销
		
	 */
	
	public static void main(String[] args) {
		SimpleEvent simpleEvent = new SimpleEvent();
		simpleEvent.setValue("asdasd");
		System.out.println(1<<6);
	}
}
