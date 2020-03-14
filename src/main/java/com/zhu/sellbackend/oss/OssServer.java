package com.zhu.sellbackend.oss;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @ClassName OssServer
 * @Description
 * @Author qgh
 * @Date 2020-03-04 12:11
 **/
@Component
public class OssServer {

    @Autowired
    private Mac mac;
    @Autowired
    private PutPolicy putPolicy;
    @Autowired
    private PutExtra putExtra;

    public String uploadFile(InputStream inputStream, String fileName) throws AuthException, JSONException {
        String upToken = this.getUpToken();
        PutRet ret = IoApi.Put(upToken, fileName, inputStream, putExtra);
        String response = ret.getResponse();
        return response;
    }

    private String getUpToken() throws AuthException, JSONException {
        String token = putPolicy.token(mac);
        return token;
    }

}
