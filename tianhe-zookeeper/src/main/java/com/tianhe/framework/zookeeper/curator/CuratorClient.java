package com.tianhe.framework.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;

import java.util.List;

/**
 * Created by tianhe on 2019/11/18.
 */
public class CuratorClient {

    public static void checkPath(CuratorFramework curatorFramework, String path) {
        try {
            curatorFramework.checkExists().forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getChildren(CuratorFramework curatorFramework,String path) {
        try {
            List<String> childPathList = curatorFramework.getChildren().forPath(path);
            for (String childPath : childPathList) {
                System.out.println(childPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createNode(CuratorFramework curatorFramework,String path) {
        try {
            curatorFramework.create().forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createEphemeralSeqNode(CuratorFramework curatorFramework,String path,String data) {
        try {
            if(data == null){
                curatorFramework.create().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path);
            }else{
                curatorFramework.create().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path,data.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setData(CuratorFramework curatorFramework,String path,String data) {
        try {
            curatorFramework.setData().forPath(path,data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void asyncSetDataListener(CuratorFramework curatorFramework,String path,String data) {
        curatorFramework.getCuratorListenable().addListener(new CuratorListener() {
            @Override
            public void eventReceived(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                System.out.println("异步设置节点数据成功  "+curatorEvent.getPath()+" "+curatorEvent.getData());
            }
        });
        try {
            curatorFramework.setData().forPath(path,data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void asyncSetDataCallback(CuratorFramework curatorFramework,String path,String data) {
        try {
            curatorFramework.setData().inBackground(new BackgroundCallback() {
                @Override
                public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                    System.out.println("异步设置节点数据");
                }
            }).forPath(path,data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void asyncCreateNode(CuratorFramework curatorFramework,String path) {
        try {
//            这里的回调函数是可选
            curatorFramework.create().inBackground(new BackgroundCallback() {
                @Override
                public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                    System.out.println("节点创建完成  节点路径    "+curatorEvent.getPath());
                }
            }).forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteNode(CuratorFramework curatorFramework,String path) {
        try {
            curatorFramework.delete().forPath(path);
//            确认节点删除
//            curatorFramework.delete().guaranteed().forPath("/zk_test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void watcherChildren(CuratorFramework curatorFramework,String path) {
        try {
            curatorFramework.getChildren().usingWatcher(new CuratorWatcher() {
                @Override
                public void process(WatchedEvent watchedEvent) throws Exception {
                    //                这里一般根据节点的变动类型是新增还是删除做响应的操作
                    System.out.println("获取子节点变动监听   操作类型，"+ watchedEvent.getType()+"    节点路径,"+watchedEvent.getPath());
                }
            }).forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
