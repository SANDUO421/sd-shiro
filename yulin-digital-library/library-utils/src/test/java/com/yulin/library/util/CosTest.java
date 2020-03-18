package com.yulin.library.util;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class CosTest {

    private COSClient cosClient;

    @Before
    public void init() {
        // 1 初始化用户身份信息（secretId, secretKey）。
        String secretId = "AKIDOaXcT63dYUBcnjss4XmmW1JbeT2MnIsh";
        String secretKey = "lGWWHvKUO3TdXlmY4KL7dtzsXyA6nDNN";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-chengdu");
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        cosClient = new COSClient(cred, clientConfig);
    }

    @Test
    public void listBuckets(){
        List<Bucket> buckets = cosClient.listBuckets();
        for (Bucket bucketElement : buckets) {
            String bucketName = bucketElement.getName();
            String bucketLocation = bucketElement.getLocation();
            System.out.println("bucketName="+bucketName+",bucketLocation="+bucketLocation);
        }
    }

    @Test
    public void listObjects(){
        // Bucket的命名格式为 BucketName-APPID ，此处填写的存储桶名称必须为此格式
        String bucketName = "lyyu1988-1251826854";
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        // 设置bucket名称
        listObjectsRequest.setBucketName(bucketName);
        // prefix表示列出的object的key以prefix开始
        //listObjectsRequest.setPrefix("images/");
        // deliter表示分隔符, 设置为/表示列出当前目录下的object, 设置为空表示列出所有的object
        //listObjectsRequest.setDelimiter("/");
        // 设置最大遍历出多少个对象, 一次listobject最大支持1000
        listObjectsRequest.setMaxKeys(1000);
        ObjectListing objectListing = null;
        do {
            try {
                objectListing = cosClient.listObjects(listObjectsRequest);
            } catch (CosServiceException e) {
                e.printStackTrace();
                return;
            } catch (CosClientException e) {
                e.printStackTrace();
                return;
            }
            // common prefix表示表示被delimiter截断的路径, 如delimter设置为/, common prefix则表示所有子目录的路径
            List<String> commonPrefixs = objectListing.getCommonPrefixes();

            // object summary表示所有列出的object列表
            List<COSObjectSummary> cosObjectSummaries = objectListing.getObjectSummaries();
            for (COSObjectSummary cosObjectSummary : cosObjectSummaries) {
                // 文件的路径key
                String key = cosObjectSummary.getKey();
                // 文件的etag
                String etag = cosObjectSummary.getETag();
                // 文件的长度
                long fileSize = cosObjectSummary.getSize();
                // 文件的存储类型
                String storageClasses = cosObjectSummary.getStorageClass();

                System.out.println("key="+key+",etag="+etag+",fileSize="+fileSize+",storageClasses="+storageClasses);
            }

            String nextMarker = objectListing.getNextMarker();
            listObjectsRequest.setMarker(nextMarker);
        } while (objectListing.isTruncated());
    }

    @Test
    public void putObject1(){
        File file=new File("E:\\手机视频照片\\令煜\\2b3fee19a651f0e964a47dfab5c164ef.jpg");
        cosClient.putObject("lyyu1988-1251826854","123.jpg",file);
    }

    @Test
    public void putObject2() throws FileNotFoundException {
        File file=new File("E:\\手机视频照片\\令煜\\2b3fee19a651f0e964a47dfab5c164ef.jpg");
//        ObjectMetadata objectMetadata=new ObjectMetadata();
//        cosClient.putObject("lyyu1988-1251826854","123.jpg",new FileInputStream(file));
    }

}
