package com.tianhe.framework.guava.online.cache;

import com.google.common.base.Strings;
import org.springframework.stereotype.Component;


/**
 * 科目表缓存类
 * @author: dengxu.guo
 * @date: 2017/11/10 17:02
 */
@Component
public class UserCache extends GuavaAbstractLoadingCache<String, User> implements LocalCache<String, User> {

    private UserCache(){
        setMaximumSize(600); //最大缓存条数
    }

    /**
     * 从数据库读取数据，并被自动保存到缓存中
     * @param key
     * @return
     */
    @Override
    protected User fetchData(String key) {
        if (Strings.isNullOrEmpty(key)) {
            return null;
        }else{
            User user = new User();
            System.out.println("从数据库中查找");
            return user;
        }
    }

    /**
     * 从缓存中获取数据（第一次自动调用fetchData从外部获取数据），并处理异常
     * @param key
     * @return
     */
    @Override
    public User get(String key) {
        try {
            return getValue(key);
        } catch (Exception e) {
            logger.error("未查到科目数据", key ,e);
            throw new RuntimeException("未查到科目数据");
        }
    }
}
