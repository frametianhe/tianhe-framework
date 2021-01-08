package com.tianhe.framework.zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;

/**
 * Created by tianhe on 2019/9/29.
 */
public class Main {

    public static void main(String[] args) {

        ZkClient zkClient = ZookeeperFactory.getInstance();

//        判断节点是否存在
        ZookeeperClient.checkPath(zkClient,"zk_test");

//        创建持久节点，服务关闭节点不会自动删除
        ZookeeperClient.setPersistentNode(zkClient,"zk_test","zk_test");

//        创建临时有序节点
        ZookeeperClient.createEphemeralSeqNode(zkClient,"zk_test","zk_test");

//        删除节点
        ZookeeperClient.deleteNode(zkClient,"zk_test");

//        获取所有子节点
        ZookeeperClient.getChildren(zkClient,"zk_test");


//        设置节点数据
        ZookeeperClient.setData(zkClient,"zk_test","zk_test");
//        读取节点数据
        ZookeeperClient.getData(zkClient,"zk_test");

//        订阅节点数据变化
        ZookeeperClient.subscribeDataChange(zkClient,"zk_test");

//        取消节点数据变化订阅
        ZookeeperClient.unsubscribeDataChanges(zkClient,"zk_test");

    }

}
