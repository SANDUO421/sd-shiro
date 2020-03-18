package com.yulin.library.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Date;

public class FileUtils {

    public static String getPath(){
        return getPath(new Date());
    }

    public static String getPath(Date date){
        int year = DateUtil.year(date);
        int month=DateUtil.month(date)+1;
        int day=DateUtil.dayOfMonth(date);
        return File.separator+year+File.separator+month+File.separator+day+File.separator;
    }

    public static String getRandomFileName(){
        return getRandomFileName(null);
    }

    public static String getRandomFileName(String extension){
        if(StringUtils.isBlank(extension)){
            extension="";
        }else{
            extension="."+extension;
        }
        return IdUtil.objectId()+extension;
    }

}
