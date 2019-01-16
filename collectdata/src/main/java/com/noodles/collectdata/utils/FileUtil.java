package com.noodles.collectdata.utils;

import java.io.*;

public class FileUtil {

    /**
     * 读取文件
     * @param f
     * @return
     * @throws Exception
     * 作者：KJ00019
     * 日期：2017年11月27日上午10:00:01
     */
    public static byte[] getBytesFromFile(File f) throws Exception {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(f);
            byte[] b = new byte[fileInputStream.available()];
            fileInputStream.read(b);
            return b;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
            	
            }
        }
    }
    
    /**
     * 读取文件行
     * @param filePath
     * @return
     * @throws Exception
     * 作者：KJ00019
     * 日期：2017年11月27日上午10:01:26
     */
    public static String getStringFromFile(String filePath) throws Exception{
    	File file = new File(filePath);
    	BufferedReader br = new BufferedReader(new FileReader(file));
    	
    	StringBuffer sb = new StringBuffer();
    	String line = null;
    	while((line = br.readLine()) != null){
    		sb.append(line + "\r\n");
    	}
    	br.close();
    	return sb.toString();
    }
    
    /**
     * 将字节流写入文件
     * @param savePath
     * @param data
     * 作者：KJ00019
     * 日期：2017年11月27日上午10:00:30
     */
    public static void wirteDataToFile(String savePath, byte[] data) {
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream = new FileOutputStream(savePath);
            fileOutputStream.write(data);
            fileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
            }
        }
    }
    
    /**
     * 删除文件
     * @param file
     * 作者：KJ00019
     * 日期：2017年11月27日上午10:00:42
     */
    public static void deleteFiles(File file){
		if(!file.exists()){
			return;
		}
		if(file.isFile()){
			System.out.println("删除文件" + file.getAbsolutePath() + "\\" + file.getName());
			file.delete();
		}else if(file.isDirectory()){
			File[] fileArr = file.listFiles();
			for(File fileTerm : fileArr){
				deleteFiles(fileTerm);
			}
		}
	}
    
    /**
     * 统计当前目录下的文件数量
     * @param file
     * @return
     * 作者：KJ00019
     * 日期：2017年11月27日上午10:03:34
     */
    public static int getSubFilesNum(File file){
    	int count = 0;
    	if(!file.exists()){
			return count;
		}
    	
		if(file.isDirectory()){
			File[] fileArr = file.listFiles();
			for(File fileTerm : fileArr){
				if(fileTerm.isFile()){
					count++;
				}
			}
		}
		return count;
	}
    
    /**
     * 递归统计文件数量
     * @param file
     * @return
     * 作者：KJ00019
     * 日期：2017年11月27日上午10:03:17
     */
    public static int getAllFilesNum(File file){
    	int count = 0;
    	if(!file.exists()){
			return count;
		}
    	
		if(file.isDirectory()){
			count += getSubFilesNum(file);
			File[] fileArr = file.listFiles();
			for(File fileTerm : fileArr){
				if(fileTerm.isDirectory()){
					count += getAllFilesNum(fileTerm);
				}
			}
		}
		return count;
	}
    
    
    public static String getStringFromFileByText(String filePath, String filterStr) throws Exception{
    	File file = new File(filePath);
    	BufferedReader br = new BufferedReader(new FileReader(file));
    	
    	String line = null;
    	int count = 0;
		int fileName = 0;
		boolean flag = false;
		int lineCount = 0;
    	while((line = br.readLine()) != null){
    		lineCount++;
    		if(count%1000 != 0){
    			FileNioUtil.writeFile("D:/cafiles/revoke/正式场/" + fileName + ".txt", line + "\r");
    			flag = true;
    		}
    		
    		if(count%1000 == 0){
    			fileName = fileName + 1;
    			FileNioUtil.createFile("D:/cafiles/revoke/正式场/" + fileName + ".txt");
    			
    			if(count == 0){
    				FileNioUtil.writeFile("D:/cafiles/revoke/正式场/" + fileName + ".txt", line + "\r");
    			}
    			
    			if(flag){
    				FileNioUtil.writeFile("D:/cafiles/revoke/正式场/" + (fileName-1) + ".txt", line);
    			}
    			
    			flag = false;
    		}
    		
    		count = count + 1;
    			
    	}
    	System.out.println(lineCount);
    	br.close();
    	return "done";
    }
    
    public static void main(String args[]){
    	try {
			String str = getStringFromFileByText("D:/cafiles/revoke/20180930-注销ID.txt", "供应商推送结果");
			System.out.println(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
