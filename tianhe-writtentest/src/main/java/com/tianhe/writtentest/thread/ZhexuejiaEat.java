package com.tianhe.writtentest.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tianhe on 2020/2/20.
 */
public class ZhexuejiaEat {

    public static void main(String[] args) {

        /**
         * 5 个沉默寡言的哲学家围坐在圆桌前，每人面前一盘意面。叉子放在哲学家之间的桌面上。（5 个哲学家，5 根叉子）

         所有的哲学家都只会在思考和进餐两种行为间交替。哲学家只有同时拿到左边和右边的叉子才能吃到面，而同一根叉子在同一时间只能被一个哲学家使用。每个哲学家吃完面后都需要把叉子放回桌面以供其他哲学家吃面。只要条件允许，哲学家可以拿起左边或者右边的叉子，但在没有同时拿到左右叉子时不能进食。

         假设面的数量没有限制，哲学家也能随便吃，不需要考虑吃不吃得下。

         设计一个进餐规则（并行算法）使得每个哲学家都不会挨饿；也就是说，在没有人知道别人什么时候想吃东西或思考的情况下，每个哲学家都可以在吃饭和思考之间一直交替下去。

         来源：力扣（LeetCode）
         链接：https://leetcode-cn.com/problems/the-dining-philosophers
         著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         */

        /**
         * 这道题本质上其实是想考察如何避免死锁。
         易知：当 55 个哲学家都拿着其左边(或右边)的叉子时，会进入死锁。

         故最多只允许 44 个哲学家去持有叉子，可保证至少有 11 个哲学家能吃上意大利面（即获得到 22 个叉子）。
         因为最差情况下是：44 个哲学家都各自持有1个叉子，此时还 剩余 11 个叉子 可供使用，这 44 个哲学家中必然有1人能获取到这个 剩余的 11 个叉子，从而手持 22 个叉子，可以吃意大利面。
         即：44 个人中，11 个人有 22 个叉子，33 个人各持 11 个叉子，共计 55 个叉子。

         既然最多只允许4个哲学家去持有叉子，那么如果只允许3个哲学家去持有叉子是否可行呢？
         当然可行，33个哲学家可以先都各自持有11把叉子，此时还剩余22把叉子；接下来，这33个哲学家中必有22人能获取到这剩余的22把叉子，从而手持22把叉子，可以吃意大利面。而必然剩余11人只能持有11把叉子。
         即：33 个人中，22 个人各自持有 22 个叉子，11 个人持有 11 个叉子，共计 55 个叉子。

         因此，如果只允许44个哲学家去持有叉子，在头1轮中只能有11人能吃上意大利面；如果只允许33个哲学家去持有叉子，在头一轮中能有22个人能吃上意大利面。直觉上来讲，一轮能有22个人完成吃面 在时间上 显然优于 一轮只有11个人吃面。

         用Semaphore去实现上述的限制：Semaphore eatLimit = new Semaphore(4);
         一共有5个叉子，视为5个ReentrantLock，并将它们全放入1个数组中。
         */
    }
}


class DiningPhilosophers {
    //1个Fork视为1个ReentrantLock，5个叉子即5个ReentrantLock，将其都放入数组中
    private ReentrantLock[] lockList = {new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock()};

    //限制 最多只有4个哲学家去持有叉子
    private Semaphore eatLimit = new Semaphore(4);

    public DiningPhilosophers() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {

        int leftFork = (philosopher + 1) % 5;    //左边的叉子 的编号
        int rightFork = philosopher;    //右边的叉子 的编号

        eatLimit.acquire();    //限制的人数 -1

        lockList[leftFork].lock();    //拿起左边的叉子
        lockList[rightFork].lock();    //拿起右边的叉子

        pickLeftFork.run();    //拿起左边的叉子 的具体执行
        pickRightFork.run();    //拿起右边的叉子 的具体执行

        eat.run();    //吃意大利面 的具体执行

        putLeftFork.run();    //放下左边的叉子 的具体执行
        putRightFork.run();    //放下右边的叉子 的具体执行

        lockList[leftFork].unlock();    //放下左边的叉子
        lockList[rightFork].unlock();    //放下右边的叉子

        eatLimit.release();//限制的人数 +1
    }
}
