package com.tianhe.framework.springboot.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by tianhe on 2019/10/9.
 */
@Service
public class AsyncDemoServiceImpl implements AsyncDemoService {

    @Async
    @Override
    public void doService() {

    }
}
