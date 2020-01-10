package com.example.web_example.Bigchaindb;


import com.bigchaindb.util.KeyPairUtils;

import com.example.web_example.Util.PropertyUtil;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import net.i2p.crypto.eddsa.KeyPairGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyPair;

@Component
public class KeyPairHolder {
    private static Logger logger = LoggerFactory.getLogger(KeyPairHolder.class);

    private static String keyPath = PropertyUtil.getProperties("keyPath");

    /**
     * 通过./keypair.txt获得密钥对
     *
     * @return 获得秘钥对
     */
    public KeyPair getKeyPairFromTXT(String path) {
        try {
            return KeyPairUtils.decodeKeyPair(getKeyPairFormTXT(path));
        } catch (Exception e) {
            logger.error("对应路径下没有密钥文件");
            return null;
        }
    }

    /**
     * 通过./keypair.txt获得密钥对
     *
     * @return 获得固定路径下的秘钥
     */
    public String getKeyPairFormTXT(String path) {
        try {
            FileInputStream in = new FileInputStream(path);
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
            String key = new String(buffer);
            logger.info("成功获得" + path + "路径下的密钥");
            return key;
        } catch (Exception e) {
            logger.error("对应路径下没有密钥文件");
            return null;
        }
    }




    /**
     * 从配置文件中keypath指定路径获得发送交易使用的公钥
     *
     * @return
     */
    public EdDSAPublicKey getPublic() {
       return getPublic(keyPath);
    }

    /**
     * 从指定路径获得公钥
     * @param path
     * @return
     */
    public EdDSAPublicKey getPublic(String path) {
        logger.info("获得" + path + "中的公钥");
        return (EdDSAPublicKey) getKeyPairFromTXT(path).getPublic();
    }


    /**
     * 从配置文件中keypath指定路径获得发送交易使用的私钥
     *
     * @return
     */
    public EdDSAPrivateKey getPrivate() {
        return getPrivate(keyPath);
    }

    /**
     * 从指定路径获得私钥
     * @param path
     * @return
     */
    public EdDSAPrivateKey getPrivate(String path) {
        logger.info("获得" + path + "中的私钥");
        return (EdDSAPrivateKey) getKeyPairFromTXT(path).getPrivate();
    }

    /**
     * 将密钥对存贮在配置文件中keypath指定路径下的文件中
     *
     * @param keyPair
     */
    public void SaveKeyPairToTXT(KeyPair keyPair) {
        SaveKeyPairToTXT(keyPair,keyPath);
    }

    /**
     * 将密钥对存储在指定路径下的文件中
     * @param keyPair
     * @param path
     */

    public void SaveKeyPairToTXT(KeyPair keyPair ,String path) {
        try {
            logger.info("开始写密钥到" + path);
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(KeyPairUtils.encodePrivateKeyBase64(keyPair).getBytes());
            fos.close();
            logger.info("写密钥成功");
        } catch (Exception e) {
            logger.error("写密钥失败");
            e.printStackTrace();
        }

    }


    /**
     * 获得密钥对
     *
     * @return
     */
    public KeyPair getKeyPair() {
        KeyPairGenerator edDsaKpg = new KeyPairGenerator();
        logger.info("成功获取新的密钥对");
        return edDsaKpg.generateKeyPair();
    }

//    /**
//     * 公钥转换成字符串
//     * @param key
//     * @return
//     */
//    public  String pubKeyToString(EdDSAPublicKey key){
//        return KeyPairUtils.encodePublicKeyInBase58(key);
//    }


    public static void main(String[] args) {

        KeyPairHolder keyPairHolder=new KeyPairHolder();
        keyPairHolder.SaveKeyPairToTXT(keyPairHolder.getKeyPair(),"./keypair2.txt");
    }

}