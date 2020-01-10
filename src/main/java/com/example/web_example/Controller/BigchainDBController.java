package com.example.web_example.Controller;

import com.example.web_example.Domain.WebResult;
import com.example.web_example.Service.BigchainDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @BelongsProject: web_example
 * @BelongsPackage: com.example.web_example.Controller
 * @Author: keer
 * @CreateTime: 2020-01-10 09:46
 * @Description: BigchainDB的接口
 */
@RestController()
@RequestMapping("/bigchaindb")
public class BigchainDBController {
    @Autowired
    BigchainDBService bigchainDBService;

    /**
     * BDQL使用接口
     *
     * @param sql
     * @return
     */
    @RequestMapping(value = "/work/{sql}",method = RequestMethod.GET)
    public WebResult work(@PathVariable String sql) {
        return bigchainDBService.work(sql);
    }

    /**
     * 创建资产
     *{
     *  "asset":{
     *      "car":"baoma",
     *      "color":"red"
     *  }
     *  "metadata":{
     *      "length":"12",
     *      "time":"2015.12.3"
     *  }
     *}
     * @param map
     * @return
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public WebResult create(@RequestBody Map map) {
        Map asset = (Map) map.get("asset");
        Map metadata = (Map) map.get("metadata");
        return bigchainDBService.creaateAsset(asset, metadata);
    }


    /**
     * 给指定资产添加metadata
     * {
     *     "assetID":"0x1345484566445646",
     *     "metadata":{
     *         "length":"1200",
     *         "time":"2017.1.3"
     *     }
     * }
     * @param map
     * @return
     */
    @RequestMapping(value = "/metadata",method = RequestMethod.POST)
    public WebResult metadata(@RequestBody Map map) {
        Map metadata = (Map) map.get("metadata");
        String assetID = (String) map.get("assetID");
        return bigchainDBService.addMetaDataforAsset(metadata, assetID);
    }

    /**
     * 交易资产
     * {
     *     "assetID":"0x1345484566445646",
     *     "key":"sadfewjrjsdsdafsafasdf",
     *     "metadata":{
     *          "length":"2200",
     *          "time":"2020.1.10"
     * }
     * @param map
     * @return
     */
    @RequestMapping(value = "/transfer",method = RequestMethod.POST)
    public WebResult transfer(@RequestBody Map map) {
        Map metadata = (Map) map.get("metadata");
        String assetID = (String) map.get("assetID");
        String key = (String) map.get("key");
        return bigchainDBService.transferAssert(assetID,metadata,key);
    }

}
