package com.zhu.sellbackend.config;

import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.rs.PutPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName QiniuConfig
 * @Description
 * @Author qgh
 * @Date 2020-03-04 12:11
 **/
@Configuration
public class QiniuConfig {

    @Value("${qiniu.accessKey}")
    private String ACCESS_KEY;
    @Value("${qiniu.secretKey}")
    private String SECRET_KEY;
    @Value("${qiniu.bucketName}")
    private String BUCKET_NAME;

    @Bean
    public Mac mac() {
        return new Mac(ACCESS_KEY, SECRET_KEY);
    }

    @Bean
    public PutPolicy putPolicy() {
        return new PutPolicy(BUCKET_NAME);
    }

    @Bean
    public PutExtra putExtra() {
        return new PutExtra();
    }

}
