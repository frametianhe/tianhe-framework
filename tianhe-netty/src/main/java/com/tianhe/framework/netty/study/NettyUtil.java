package com.tianhe.framework.netty.study;

/**
 * @author: he.tian
 * @time: 2018-09-10 19:21
 */
public abstract class NettyUtil {

    public static final String OS_NAME = System.getProperty("os.name");
    private static boolean IS_LINUX_PLATFORM = false;

    static {
        if(OS_NAME != null && OS_NAME.toLowerCase().contains("linux")){
            IS_LINUX_PLATFORM = true;
        }
    }

    public static boolean isLinuxPlatform(){
        return IS_LINUX_PLATFORM;
    }
}
