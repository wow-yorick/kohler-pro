/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.util;

import java.io.File;

/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2014年10月24日
 */
public class DelFiles {
    /**
     * @Title: main
     * @Description:
     * @param args
     * @return void
     * @author lisr
     * @date Mar 7, 2012 5:36:04 PM
     * @throws
     */
    //用以模糊删除头部为str的文件
    public static boolean delFilesByPath(String path,String str){
     //参数说明---------path:要删除的文件的文件夹的路径---------str:要匹配的字符串的头
     boolean b=false;
     File file = new File(path);
     File[] tempFile = file.listFiles();
     for(int i = 0; i < tempFile.length; i++){
      if(tempFile[i].getName().startsWith(str)||tempFile[i].getName().endsWith(str)){
       System.out.println("将被删除的文件名:"+tempFile[i].getName());
       boolean del=deleteFile(path+tempFile[i].getName());
       if(del){
        System.out.println("文件"+tempFile[i].getName()+"删除成功");
        b=true;
       }else{
        System.out.println("文件"+tempFile[i].getName()+"删除失败");
       }
      }
     }
     return b;
    }
    
    private static boolean deleteFile(String path){
     boolean del=false;
     File file=new File(path);
     if(file.isFile()){
      file.delete();
      del=true;
     }
     return del;
    }
    
    public static void main(String[] args) {
     // TODO Auto-generated method stub
     String path="C:/workspace/publish/";
     String str=".vm";
     if(delFilesByPath(path,str)){
      System.out.println(path+"中包含"+str+"的文件已经全部删除成功!");
     }else{
         System.out.println(path+"中包含"+str+"的文件已经删除失败或该文件夹下不存在这类文件!");
     }
    }
}
