package com.tianhe.framework.springboot.executor;

import java.util.LinkedList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 注册线程池配置bean
 *
 * @author humin@didichuxing.com
 * @since 2017/7/25
 */
@ConfigurationProperties(prefix = "executors")
@Slf4j
@Data
public class ExecutorsConfig {

    private List<ExecutorConfig> configs = new LinkedList<>();

    private String names;


    /**
     * 线程池配置
     *
     * @author humin@didichuxing.com
     * @since 2017/7/25
     */
    @Data
    public static class ExecutorConfig {
        private String name;

        /**
         * 线程池固定线程数,即使他们空闲也不会被回收,默认cpu个数
         */
        private int corePoolSize = Runtime.getRuntime().availableProcessors();

        /**
         * 线程池最大线程数,默认corePoolSize * 2
         */
        private int maximumPoolSize = corePoolSize * 2;

        /**
         * 允许最大空闲时间,单位毫秒
         */
        private int keepAlive = 10000;

        /**
         * 拒绝策略,默认直接丢弃
         */
        private String policy = "D";

        /**
         * 队列长度,默认0,会实现为linkedblockingqueue
         */
        private int queueSize = 0;

        public ExecutorConfig() {
        }

        public ExecutorConfig(String name) {
            this.name = name;
        }
    }

}
