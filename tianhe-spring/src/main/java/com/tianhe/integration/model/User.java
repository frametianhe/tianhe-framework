package com.tianhe.integration.model;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by tianhe on 2019/12/11.
 */
@Component
@Data
public class User {

    private String userName;

    private String pwd;
}
