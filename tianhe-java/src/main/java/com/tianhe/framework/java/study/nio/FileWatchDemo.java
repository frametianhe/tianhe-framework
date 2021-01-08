package com.tianhe.framework.java.study.nio;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tianhe on 2019/10/24.
 */
public class FileWatchDemo {

    private static String path = "/Users/jiangweifeng/soft";

    public static void main(String[] args) throws IOException {
        WatchService watcher = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(FileWatchDemo.path);
        path.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY,StandardWatchEventKinds.ENTRY_DELETE,StandardWatchEventKinds.ENTRY_CREATE);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Listener(watcher));
    }

   static class Listener implements Runnable{

        private WatchService watcher;

        public Listener(WatchService watcher){
            this.watcher = watcher;
        }

        @Override
        public void run() {
            try {
                for (;;){
                    WatchKey watchKey = watcher.take();
                    List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
                    for (WatchEvent<?> watchEvent : watchEvents) {
                        if(StandardWatchEventKinds.ENTRY_DELETE.name().equals(watchEvent.kind().name())){
                            System.out.println(watchEvent.context()+"文件发生了  "+ watchEvent.kind()    +"事件" + watchEvent.count());
                        }
                    }
                    watchKey.reset();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                try {
                    watcher.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
