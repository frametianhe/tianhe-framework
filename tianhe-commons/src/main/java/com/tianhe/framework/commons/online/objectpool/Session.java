package com.tianhe.framework.commons.online.objectpool;

import java.util.Date;

/**
 * weifeng.jiang 2018-07-20 20:00
 */
public class Session {

    private Date createTime = new Date();
    private Date updateTime = new Date();

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
