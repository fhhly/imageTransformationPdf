package com.fhh.utils;

import com.fhh.bo.FolderBO;

import java.io.File;

/**
 * 封装输入文件夹 下属的子文件和子文件夹
 * @author 小浩
 * @Date 2019/7/3 16:03
 */
public class FileUtils {
	
	/**
	 * 由文件夹返回子文件夹和文件路径
	 * @author 小浩
	 * @Date 2019/7/3 16:03
	 * @param folderBO 文件夹
	 */
	public static void getPackageFolder(FolderBO folderBO){
		File f = new File(folderBO.getFolderPath());
		
		if(!f.exists()){
			return;
		}
		
		File[] listFiles = f.listFiles();
		if(listFiles==null||listFiles.length==0){
			return;
		}
		
		for (File file : listFiles) {
			if(file.isDirectory()){
				folderBO.getFolderPathList().add(file.getAbsolutePath());
			}else if(file.isFile()){
				folderBO.getFilePathList().add(file.getAbsolutePath());
			}
		}
	}
}
