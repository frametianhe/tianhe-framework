package com.tianhe.framework.guava.online.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @author: shasha.hu01
 * @date: 2019/3/14 下午3:30
 * @description: 异步刷新的GuavaCache模板
 */
public abstract class GuavaAbstractRefreshLoadingCache<K, V> {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    //用于初始化cache的参数及其缺省值
    private int maximumSize = 1000;					//最大缓存条数，子类在构造方法中调用setMaximumSize(int size)来更改
    private int refreshAfterWriteDuration = 60;		//数据存在时长，子类在构造方法中调用setExpireAfterWriteDuration(int duration)来更改
    private TimeUnit timeUnit = TimeUnit.MINUTES;	//时间单位（分钟）

    private Date resetTime;		//Cache初始化或被重置的时间
    private long highestSize=0;	//历史最高记录数
    private Date highestTime;	//创造历史记录的时间

    private LoadingCache<K, V> cache;
    ListeningExecutorService backgroundRefreshPools = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(20,new ThreadFactory() {
		@Override
		public Thread newThread(Runnable r) {
			return new Thread(r,"finance-common-guava-"+ System.identityHashCode(r));
		}
	}));

    /**
     * 通过调用getCache().get(key)来获取数据
     * @return cache
     */
    public LoadingCache<K, V> getCache() {
        if(cache == null){	//使用双重校验锁保证只有一个cache实例
            synchronized (this) {
                if(cache == null){
                    cache = CacheBuilder.newBuilder().maximumSize(maximumSize)		//缓存数据的最大条目
                            .refreshAfterWrite(refreshAfterWriteDuration, timeUnit)	//数据被创建多久后被移除
                            .recordStats()											//启用统计
                            .build(new CacheLoader<K, V>() {
                                @Override
                                public V load(K key) throws Exception {
                                    return fetchData(key);
                                }
                                //重载刷新方法 异步刷新
                                public ListenableFuture<V> reload(final K key, V oldValue) {
                                   return backgroundRefreshPools.submit(new Callable<V>() {
                                        @Override
                                        public V call() throws Exception {
                                            return fetchData(key);
                                        }
                                    });
                                }
                            });
                    this.resetTime = new Date();
                    this.highestTime = new Date();
                    logger.debug("本地缓存{}初始化成功", this.getClass().getSimpleName());
                }
            }
        }

        return cache;
    }

    /**
     * 根据key从数据库或其他数据源中获取一个value，并被自动保存到缓存中。
     * @param key
     * @return value,连同key一起被加载到缓存中的。
     */
    protected abstract V fetchData(K key);

    /**
     * 从缓存中获取数据（第一次自动调用fetchData从外部获取数据），并处理异常
     * @param key
     * @return Value
     * @throws ExecutionException
     */
    protected V getValue(K key) throws ExecutionException {
        V result = getCache().get(key);
        if(getCache().size() > highestSize){
            highestSize = getCache().size();
            highestTime = new Date();
        }

        return result;
    }

    public long getHighestSize() {
        return highestSize;
    }

    public Date getHighestTime() {
        return highestTime;
    }

    public Date getResetTime() {
        return resetTime;
    }

    public void setResetTime(Date resetTime) {
        this.resetTime = resetTime;
    }

    public int getMaximumSize() {
        return maximumSize;
    }


    /**
     * 设置最大缓存条数
     * @param maximumSize
     */
    public void setMaximumSize(int maximumSize) {
        this.maximumSize = maximumSize;
    }

    /**
     * 设置数据存在时长（分钟）
     * @param refreshAfterWriteDuration
     */

    public void setRefreshAfterWriteDuration(int refreshAfterWriteDuration) {
        this.refreshAfterWriteDuration = refreshAfterWriteDuration;
    }
}
