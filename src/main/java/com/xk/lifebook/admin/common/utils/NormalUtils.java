package com.xk.lifebook.admin.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NormalUtils {
    /**
     * 获取现在时间
     *
     * @return返回长时间格式 yyyy-MM-dd HH:mm:ss
     */
    public Date getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     *
     * @return返回短时间格式 yyyy-MM-dd
     */
    public Date getNowDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    //去除最后一个特殊符号
    public String subLastSymbol(String str, String Symbol){
        int indx = str.lastIndexOf(Symbol);
        if(indx!=-1){
            str = str.substring(0,indx)+str.substring(indx+1,str.length());
        }
        return str;
    }
    //文件上传
    public String saveFile(MultipartFile multipartFile, String path, String fileName) throws IOException {

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
        //无指定文件名则以时间戳命名
        if(fileName == null){
            Date date = new Date();
            //获取文件后缀名
            String prefix=fileName.substring(file.getName().lastIndexOf(".")+1);
            fileName =  String.valueOf(date.getTime()) + prefix;
        }

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
        byte[] bs = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        bos.flush();
        bos.close();
        return fileName;
    }
    //清除网站无用数据
    //清除Ueditor无用图片等数据
}
