package com.example.web_example.Service.implement;

import com.bigchaindb.util.KeyPairUtils;
import com.example.web_example.BDQLParser.BDQLUtil;
import com.example.web_example.Bigchaindb.BigchainDBUtil;
import com.example.web_example.Bigchaindb.KeyPairHolder;
import com.example.web_example.Domain.WebResult;
import com.example.web_example.Service.BigchainDBService;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @BelongsProject: web_example
 * @BelongsPackage: com.example.web_example.Service
 * @Author: keer
 * @CreateTime: 2020-01-10 09:51
 * @Description:
 */
@Service
public class BigchainDBServiceImpl implements BigchainDBService {

    private Logger logger = LoggerFactory.getLogger(BigchainDBServiceImpl.class);
    @Autowired
    BDQLUtil bdqlUtil;

    @Autowired
    BigchainDBUtil bigchainDBUtil;

    @Autowired
    KeyPairHolder keyPairHolder;

    /**
     * 使用BDQL语句在BigchainDB上进行操作
     * @param sql
     * @return
     */
    @Override
    public WebResult work(String sql) {
        return bdqlUtil.work(sql);
    }

    /**
     * 创建资产
     * @param asset 资产数据
     * @param metadata 附加数据
     * @return
     */
    @Override
    public WebResult creaateAsset(Map asset,Map metadata){
        WebResult webResult=new WebResult();
        try {
            String assetId=bigchainDBUtil.createAsset(asset,metadata);
            webResult.setData(assetId);
            webResult.setStatus(WebResult.SUCCESS);
            webResult.setMessage("创建资产成功！！");
            logger.info("创建资产成功！！");
        } catch (Exception e) {
            logger.error("创建资产出错！！！");
            e.printStackTrace();
        }
        return webResult;
    }

    /**
     * 给指定资产增加metadata
     * @param metadata
     * @param assetID
     * @return
     */
    public WebResult addMetaDataforAsset(Map metadata,String assetID){
        String txid=bigchainDBUtil.transferToSelf(metadata,assetID);
        return checkTransactionExit(txid);
    }

    /**
     * 交易资产
     * @param assetID
     * @param metadata
     * @param publicKey
     * @return
     */
    public WebResult transferAssert(String assetID, Map metadata, String publicKey){

        String txID=bigchainDBUtil.transfer(assetID,metadata, (EdDSAPublicKey) KeyPairUtils.decodeKeyPair(publicKey).getPublic());
        return checkTransactionExit(txID);
    }

    /**
     * 检查交易ID是否存在
     * @param txID
     * @return
     */
    private WebResult checkTransactionExit(String txID){
        WebResult webResult=new WebResult();
        if(bigchainDBUtil.checkTransactionExit(txID)){
            webResult.setMessage("交易成功！！");
            webResult.setStatus(WebResult.SUCCESS);
            webResult.setData(txID);
            logger.info("交易成功，交易ID："+txID);
        }else {
            webResult.setData(null);
            webResult.setMessage("交易失败！！！请检查数据格式和资产ID是否正确");
        }
        return webResult;
    }

}
