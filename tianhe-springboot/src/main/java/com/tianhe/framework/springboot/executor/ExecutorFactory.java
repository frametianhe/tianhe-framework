package com.tianhe.framework.springboot.executor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 注册线程池配置bean
 *
 * @author humin@didichuxing.com
 * @since 2017/7/25
 */
@Component(value = "executorFactory")
@EnableConfigurationProperties(ExecutorsConfig.class)
@Order(0)
@Slf4j
public class ExecutorFactory implements BeanFactoryAware {
    private static final Map<String, ExecutorService> SERVICE_MAP = new HashMap<>(5);

    private static final ExecutorService FORK_JOIN_POOL = new ForkJoinPool();

    @Autowired
    private ExecutorsConfig executorsConfig;

    private BeanFactory beanFactory;

    public static ExecutorService getExecutorService(String name) {
        if (StringUtils.isBlank(name)) {
            return FORK_JOIN_POOL;
        }
        ExecutorService executorService = SERVICE_MAP.get(name);
        return executorService == null ? create(name, new ExecutorsConfig.ExecutorConfig(name)) : executorService;
    }

    /**
     * 创建线程池
     *
     * @param name           线程池的名字
     * @param executorConfig 线程池的配置
     * @return 线程池
     */
    public static ExecutorService create(String name, ExecutorsConfig.ExecutorConfig executorConfig) {
        RejectedExecutionHandler rejectedExecutionHandler;
        switch (executorConfig.getPolicy()) {
            case "A":
                rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
                break;
            case "C":
                rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();
                break;
            case "D":
                rejectedExecutionHandler = new ThreadPoolExecutor.DiscardPolicy();
                break;
            default:
                rejectedExecutionHandler = new ThreadPoolExecutor.DiscardPolicy();
        }
        BlockingQueue<Runnable> blockingQueue = executorConfig.getQueueSize() <= 0
                ? new LinkedBlockingQueue<>() :
                new ArrayBlockingQueue<>(executorConfig.getQueueSize());

        return new ThreadPoolExecutor(executorConfig.getCorePoolSize(),
                executorConfig.getMaximumPoolSize(), executorConfig.getKeepAlive(),
                TimeUnit.MILLISECONDS, blockingQueue, new NamedThreadFactory(name),
                rejectedExecutionHandler);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        configure();
    }

    private void configure() {
        ConfigurableBeanFactory configurableBeanFactory = (ConfigurableBeanFactory) beanFactory;
        List<ExecutorsConfig.ExecutorConfig> config = executorsConfig.getConfigs();
        String names = executorsConfig.getNames();
        if (StringUtils.isNotBlank(names)) {
            String[] nameArray = StringUtils.split(names, ",");
            if (nameArray != null && nameArray.length > 0) {
                Arrays.stream(nameArray).forEach(s -> config.add(new ExecutorsConfig.ExecutorConfig(s)));
            }
        }
        config.forEach(executorConfig -> {
            ExecutorService executorService = create(executorConfig.getName(), executorConfig);
            SERVICE_MAP.put(executorConfig.getName(), executorService);
            configurableBeanFactory.registerSingleton(executorConfig.getName(), executorService);
        });
    }
}
