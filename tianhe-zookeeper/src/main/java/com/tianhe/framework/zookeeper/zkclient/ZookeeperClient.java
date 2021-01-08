package com.tianhe.framework.zookeeper.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

import java.util.List;

/**
 * Created by tianhe on 2019/11/18.
 */
public class ZookeeperClient {

    public static void unsubscribeDataChanges(ZkClient zkClient,String path) {
        IZkDataListener listener = new IZkDataListener(){
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("节点数据改变  "+dataPath+"    "+data);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("节点删除    "+dataPath);
            }
        };
        zkClient.unsubscribeDataChanges(path,listener);
    }

    public static void subscribeDataChange(ZkClient zkClient,String path) {
        IZkDataListener listener = new IZkDataListener(){
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("节点数据改变  "+dataPath+"    "+data);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("节点删除    "+dataPath);
            }
        };
        zkClient.subscribeDataChanges(path,listener);
    }

    public static void getChildren(ZkClient zkClient,String path) {
        List<String> children = zkClient.getChildren(path);
        for (String child : children) {
            System.out.println(child);
        }
    }

    public static void deleteNode(ZkClient zkClient,String path) {
        zkClient.delete(path);
    }

    public static void createEphemeralSeqNode(ZkClient zkClient,String path,String data) {
        zkClient.create(path,data, CreateMode.EPHEMERAL_SEQUENTIAL);
    }

    public static void setPersistentNode(ZkClient zkClient,String path,String data) {
        zkClient.create(path,data, CreateMode.PERSISTENT);
    }

    public static void setData(ZkClient zkClient,String path,String data) {
        zkClient.writeData(path,data);
    }

    public static void getData(ZkClient zkClient,String path) {
        Object data = zkClient.readData(path);
        System.out.println(data);
    }

    public static void checkPath(ZkClient zkClient,String path) {
        zkClient.exists(path);
    }
}
