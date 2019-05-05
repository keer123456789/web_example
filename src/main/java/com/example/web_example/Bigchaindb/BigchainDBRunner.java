package com.example.web_example.Bigchaindb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bigchaindb.builders.BigchainDbConfigBuilder;

import com.example.web_example.Util.HttpUtil;
import com.example.web_example.Util.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 连接BigchainDB
 */
@Component
public class BigchainDBRunner {
    //日志输出
    private static Logger logger = LoggerFactory.getLogger(BigchainDBRunner.class);
    //获取配置文件的BigchainDB的url


    private static String url= PropertyUtil.getProperties("url");

    /**
     * 连接BigchainDB
     */
    public  boolean StartConn() {
        return StartConn(url);
    }

    public  boolean StartConn(String urls) {

        BigchainDbConfigBuilder
                .baseUrl(urls)
                .setup();
        String body = HttpUtil.httpGet(urls);
        logger.info(body);
        JSONObject jsonObject = JSON.parseObject(body, JSONObject.class);
        logger.info(jsonObject.getString("version"));
        if (jsonObject.getString("version").equals("2.0.0b9")) {
            logger.info("与节点：" + urls + ",连接成功");
            return true;
        } else {
            logger.info("与节点：" + urls + ",连接失败");
            return false;
        }
    }

    public static void main(String[] args) {
//        StartConn();
    }

}