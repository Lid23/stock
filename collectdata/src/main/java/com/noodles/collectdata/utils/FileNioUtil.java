package com.noodles.collectdata.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 文件名：FileNioUtil.java
 * 描述：Java Nio相关
 * 作者：KJ00019
 * 日期：2017年11月27日下午1:34:27
 */
public class FileNioUtil {
	
	public static void main(String args[]){
		/*Path path = Paths.get("d:/2.txt");
		try{
			Files.delete(path);
		}catch(IOException e){
			e.printStackTrace();
		}*/
		
		String dirPath = "D:/NioTest/test/";
		
		//listDir(dirPath);
		
		//listDirAndSub(dirPath);
		
		//createDirs(dirPath);
		
		//createFile(dirPath + File.separator + "test.txt");
		
		//String srcPath = "D:/NioTest/1.txt";
		//String targetPath = "D:/NioTest/2.txt";
		
		//REPLACE_EXISTING:文件存在，就替换
		//copyFile(srcPath, targetPath, StandardCopyOption.REPLACE_EXISTING);
		
		watchFileState(dirPath);
	}
	
	/**
	 * Path操作
	 * Path类是Jdk7新增加的特性之一，用来代替java.io.File类
	 * 之所以增加这个类，是由于java.io.File类有很多缺陷，java.io.File
	 * 类里面很多方法失败时没有异常处理，或抛出异常，Path速度快，方便
	 * */
	
	
	/**
	 * 遍历目录，不包括子目录的文件
	 * @param dirPath
	 * 作者：KJ00019
	 * 日期：2017年11月27日下午1:49:03
	 */
	public static void listDir(String dirPath){
		try {
			Path dir = Paths.get(dirPath);
			DirectoryStream<Path> stream = Files.newDirectoryStream(dir);
			for(Path path : stream){
				System.out.println(path.getFileName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 遍历目录，及其子目录下的文件
	 * @param dirPath
	 * 作者：KJ00019
	 * 日期：2017年11月27日下午1:54:05
	 */
	public static void listDirAndSub(String dirPath){
		try {
			Path dir = Paths.get(dirPath);
			Files.walkFileTree(dir, new SimpleFileVisitor<Path>(){
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException{
					System.out.println(file.getFileName());
					return super.visitFile(file, attrs);
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建多级目录
	 * @param filepath
	 * 作者：KJ00019
	 * 日期：2017年11月27日下午2:27:54
	 */
	public static void createDirs(String dirPath){
		try {
			Path dir = Paths.get(dirPath);
			Files.createDirectories(dir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建文件，不存在则抛出异常
	 * @param filePath
	 * 作者：KJ00019
	 * 日期：2017年11月27日下午2:33:41
	 */
	public static void createFile(String filePath){
	    try {
	    	Path dir = Paths.get(filePath);
			Files.createFile(dir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 文件复制
	 * @param srcPath
	 * @param targetPath
	 * @param copyOption 复制模式
	 * 作者：KJ00019
	 * 日期：2017年11月27日下午2:59:37
	 */
	public static void copyFile(String srcPath, String targetPath, CopyOption copyOption){
		try {
			Path src = Paths.get(srcPath);
			Path target = Paths.get(targetPath);
			Files.copy(src, target, copyOption);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 写入字符串
	 * @param filePath
	 * @param line
	 * @throws IOException
	 * 作者：KJ00019
	 * 日期：2017年11月27日下午4:18:17
	 */
	public static void writeFile(String filePath, String line) throws IOException{
		Path src = Paths.get(filePath);
	    BufferedWriter writer = null;
		try {
			writer = Files.newBufferedWriter(src, StandardCharsets.UTF_8
			        ,StandardOpenOption.APPEND);
			writer.write(line);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(writer != null){
				writer.close();
			}
		}
	}
	
	/**
	 * 监测目录下的 文件,目录 是否被修改，创建，或删除
	 * @param filePath
	 * 作者：KJ00019
	 * 日期：2017年11月27日下午5:16:47
	 */
	public static void watchFileState(String filePath){
		try {
			Path dir = Paths.get(filePath);
			WatchService service = FileSystems.getDefault().newWatchService();
			WatchKey key = dir.register(service, StandardWatchEventKinds.ENTRY_MODIFY, 
					StandardWatchEventKinds.ENTRY_DELETE,
					StandardWatchEventKinds.ENTRY_CREATE);
			while(true){
				key = service.take();
				for(WatchEvent<?> event : key.pollEvents()){
					if(event.kind() == StandardWatchEventKinds.ENTRY_MODIFY){
						System.out.println("这个目录下的文件被修改了");
					}
					if(event.kind() == StandardWatchEventKinds.ENTRY_DELETE){
						System.out.println("这个目录下的文件被删除了");
					}
					if(event.kind() == StandardWatchEventKinds.ENTRY_CREATE){
						System.out.println("这个目录下的文件被创建了");
					}
				}
				key.reset();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}