package com.module.common.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * IP工具类
 * @author 三多
 * @Time 2020/3/3
 */
@UtilityClass
@Slf4j
public class IpUtil {
    public String getCityInfo(String ip){

        try {
            //db
            ClassPathResource resource = new ClassPathResource("/ip2region/ip2region.db");
            String tmpDir = System.getProperties().getProperty("java.io.tmpdir");
            String dbPath = tmpDir + "ip.db";
            File file = new File(dbPath);
            InputStream inputStream = resource.getInputStream();
            FileUtils.copyInputStreamToFile(inputStream,file);
            /**
             * DbSearcher.BINARY_ALGORITHM //Binary
             * DbSearcher.MEMORY_ALGORITYM //Memory
             * 查询算法 //B-tree
             */
            int algorithm = DbSearcher.BINARY_ALGORITHM;
            DbConfig config = new DbConfig();
            DbSearcher searcher = new DbSearcher(config,file.getPath());
            //方法定义
            Method method =null;
            switch (algorithm){
                case DbSearcher.BTREE_ALGORITHM:
                    method = searcher.getClass().getMethod("btreeSearch",String.class);
                    break;
                case DbSearcher.BINARY_ALGORITHM:
                    method = searcher.getClass().getMethod("binarySearch", String.class);
                    break;
                case DbSearcher.MEMORY_ALGORITYM:
                    method = searcher.getClass().getMethod("memorySearch", String.class);
                    break;
            }
            DataBlock dataBlock = null;
            if(! Util.isIpAddress(ip)){
                log.warn("Error: Invalid ip address");
            }
            dataBlock = (DataBlock)method.invoke(searcher,ip);
            return dataBlock.getRegion();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
