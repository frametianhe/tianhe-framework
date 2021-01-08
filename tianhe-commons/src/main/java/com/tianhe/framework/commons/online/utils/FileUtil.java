package com.tianhe.framework.commons.online.utils;

import java.io.File;

/**
 * 级联删除文件夹
 * @author:weifeng.jiang
 * @DATE:2016年12月9日 @TIME: 下午5:52:06
 */
public class FileUtil {
	
	public void deleteFile(File file){
		if(file.exists()){
			if(file.isFile()){
				file.delete();
			}else if(file.isDirectory()){
				File files[] = file.listFiles();
				for(int i=0;i<files.length;i++){
					this.deleteFile(files[i]);
				}
			}
			file.delete();
		}else{
			System.out.println("所删除的文件不存在!");
		}
		System.out.println("删除成功!");
	}
	
	public static void main(String[] args) {
		new FileUtil().deleteFile(new File("D:\\target"));
	}
	
	
}
