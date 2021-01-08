package com.tianhe.framework.springboot.executor;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import javax.security.auth.message.AuthException;

/**
 * 上下文工具类
 *
 * @author humin
 * @since 2017年7月05日
 */
public class ContextUtil {

    public static final ThreadLocal<Map<String, Object>> CONTEXT = ThreadLocal.withInitial(LinkedHashMap::new);

    public static final Set<String> STANDARD_KEYS = ContextEnum.NAME_HEADER.keySet();

    /**
     * 根据key获取上下文的值
     *
     * @param key key
     * @return 上下文的值
     */
    public static Object get(String key) {
        return CONTEXT.get().get(key);
    }

    /**
     * 设置上下文的值
     *
     * @param key   key
     * @param value 值
     */
    public static void put(String key, Object value) {
        CONTEXT.get().put(key, value);
    }

    /**
     * @param map 目标 map
     */
    public static void putAll(Map<String, Object> map) {
        if (map == null) {
            return;
        }
        CONTEXT.get().putAll(map);
    }

    /**
     * 获取标准上下文,诸如traceid,spanid,mid等等
     *
     * @return 标准上下文
     */
    public static Map<String, String> getStandard() {
        Map<String, String> map = new LinkedHashMap<>(20);
        for (String standardKey : STANDARD_KEYS) {
            Object value = get(standardKey);
            if (value != null && StringUtils.isNotBlank(value.toString())) {
                map.put(standardKey, value.toString());
            }
        }
        return map;
    }

    /**
     * 获取标准上下文的http头
     *
     * @return 标准上下文
     */
    public static Map<String, String> getStandardHeader() {
        return ContextEnum.NAME_HEADER.keySet().stream().filter(key -> get(key) != null)
                .collect(Collectors.toMap(ContextEnum.NAME_HEADER::get, key -> get(key).toString()));
    }

    public static void remove() {
        CONTEXT.remove();
    }

    /**
     * 函数包装类
     *
     * @author humin@didichuxing.com
     * @since 2017/12/11
     */
    public static abstract class AbstractFunctionWrapper<T, R> implements Function<T, R> {

        Map<String, String> context = ContextUtil.getStandard();

        @Override
        public R apply(T t) {
            context.forEach(ContextUtil::put);
            return convert(t);
        }

        /**
         * 把T转为R
         *
         * @param t 参数t
         * @return t转换后的结果
         */
        public abstract R convert(T t);
    }


    /**
     * 函数包装类
     *
     * @author humin@didichuxing.com
     * @since 2017/12/11
     */
    public static abstract class AbstractConsumerWrapper<T> implements Consumer<T> {

        Map<String, String> context = ContextUtil.getStandard();

        @Override
        public void accept(T t) {
            context.forEach(ContextUtil::put);
            consume(t);
        }

        /**
         * 消费
         *
         * @param t 参数
         */
        public abstract void consume(T t);
    }


    /**
     * 函数包装类
     *
     * @author humin@didichuxing.com
     * @since 2017/12/11
     */
    public static abstract class AbstractBiFunctionWrapper<T, U, R> implements BiFunction<T, U, R> {

        Map<String, String> context = ContextUtil.getStandard();

        @Override
        public R apply(T t, U u) {
            context.forEach(ContextUtil::put);
            return convert(t, u);
        }

        /**
         * 把t和u转换为R的结果
         *
         * @param t 参数t
         * @param u 参数u
         * @return r
         */
        public abstract R convert(T t, U u);
    }
}
