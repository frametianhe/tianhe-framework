package com.tianhe.framework.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;

/**
 * Created by tianhe on 2019/9/29.
 */
public class Main {

    //    网路通讯相关防止网络抖动可以用异步的方式
    public static void main(String[] args) {

        CuratorFramework curatorFramework = CuratorFactory.getInstance();

//        判断节点存在不存在
        CuratorClient.checkPath(curatorFramework,"zk_test");

//        获取子节点变动情况，新增子节点、删除子节点都会收到监听，一般用于zookeeper分布式事件驱动，修改子节点的数据不会收到监听
        CuratorClient.watcherChildren(curatorFramework,"zk_test");

//        创建临时有序节点，创建失败会重试，系统关闭会自动删除临时节点
        CuratorClient.createEphemeralSeqNode(curatorFramework,"zk_mm",null);

//        创建持久节点，系统关闭不会删除节点
        CuratorClient.createNode(curatorFramework,"zk_demo");

//        删除节点
        CuratorClient.deleteNode(curatorFramework,"zk_mm");

//        获取所有子节点
        CuratorClient.getChildren(curatorFramework,"zk_test");

//        设置节点数据
        CuratorClient.setData(curatorFramework,"zk_mm","zk_mm");




//        异步创建节点
        CuratorClient.asyncCreateNode(curatorFramework,"zk_test");

//        异步设置节点数据
        CuratorClient.asyncSetDataCallback(curatorFramework,"zk_test","zk_test");

//        异步设置节点数据
        CuratorClient.asyncSetDataListener(curatorFramework,"zk_test","zk_test");

    }
}
