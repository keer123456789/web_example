package com.example.web_example.Service;

import com.example.web_example.Domain.WebResult;

import java.util.Map;

/**
 * @BelongsProject: web_example
 * @BelongsPackage: com.example.web_example.Service
 * @Author: keer
 * @CreateTime: 2020-01-10 09:53
 * @Description:
 */
public interface BigchainDBService {
    WebResult work(String sql);

    WebResult creaateAsset(Map asset, Map metadata);

    WebResult addMetaDataforAsset(Map metadata, String assetID);

    WebResult transferAssert(String assetID, Map metadata, String publicKey);
}
