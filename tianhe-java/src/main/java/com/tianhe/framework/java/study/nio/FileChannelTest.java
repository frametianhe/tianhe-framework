package com.tianhe.framework.java.study.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by tianhe on 2019/10/23.
 */
public class FileChannelTest {

    public static void main(String[] args) throws Exception {
//        readFile();

        writeFile();
    }

    private static void writeFile() throws IOException {
        File file = new File("/Users/jiangweifeng/soft/mm.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String str = "java nio";
        buffer.put(str.getBytes());
        buffer.flip();
        fileChannel.write(buffer);
        fileChannel.close();
        fileOutputStream.close();
    }

    private static void readFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("/Users/jiangweifeng/soft/mm.txt");
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        fileChannel.read(buffer);
        buffer.flip();

        StringBuffer stringBuffer = new StringBuffer();
        for (;buffer.remaining() > 0;){
            byte b = buffer.get();
            stringBuffer.append((char) b);
        }
        System.out.println(stringBuffer);
        fileInputStream.close();
    }
}
