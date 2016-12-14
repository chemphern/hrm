package com.ymz.hrm.common.utils;

import java.io.*;

import com.ymz.hrm.common.exception.SysException;

/**
 * 权限工具类
 * Created by lixiaoxin on 2016/11/2.
 */
public class XmlParseObjectUtil {

    /**
     * 读取权限.xml文件，返回java实体
     * @param filePath 路径
     * @param  encoding 文件解析编码
     */
    public static <T> T getXmlObjectByFile(String filePath,String encoding,Class<T> t) throws Exception{
        InputStream is = new FileInputStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(is, encoding));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = br.readLine();
            if (line == null){
                break;
            }
            sb.append(line).append("\r\n");
        }
        if (is != null) {
            is.close();
        }
        if (br != null) {
            br.close();
        }
       return JaxbMapper.fromXml(sb.toString(), t);
    }

    /**
     * 读取权限.xml文件，返回java实体
     * @param  is 文件流
     * @param  encoding 文件解析编码
     */
    public static <T> T getXmlObjectByStream(InputStream is,String encoding,Class<T> t) throws Exception{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, encoding));
        try {
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line).append("\r\n");
            }
            if (is != null) {
                is.close();
            }
            if (br != null) {
                br.close();
            }
        }catch (Exception e){
            is.close();
            br.close();
            throw new SysException("文件解析错误：" + e.getMessage() );
        }
        return JaxbMapper.fromXml(sb.toString(),t);
    }
}
