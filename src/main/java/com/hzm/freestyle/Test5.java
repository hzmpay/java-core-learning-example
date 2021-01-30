package com.hzm.freestyle;

import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年12月09日
 */
public class Test5 {

    static final int NCPU = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        String a = "{\"http://172.21.23.82:8768/am/api/v1/callback/hikvision/alarm\":[200453,200455,199708,199428]}";
//        String jsonString = JSONObject.toJSONString(a);

        System.out.println(JSONObject.parseObject(a));
    }

    public static String getBase64ByUrl(String imageUrl) throws Exception {
        return null;
    }

    private static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        // 创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        // 每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        // 使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        // 关闭输入流
        inStream.close();
        // 把outStream里的数据写入内存
        return outStream.toByteArray();
    }
}
