package com.zhu.sellbackend.base;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;

/**
 * @ClassName BaseController
 * @Description
 * @Author qgh
 * @Date 2020-02-27 10:21
 **/
@Slf4j
public class BaseController {

    protected HttpSession session;

    public BaseController() {
    }

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.session = request.getSession();
    }

    protected JSONObject convertRequestBody() {
        String param = null;
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

        try {
            request.setCharacterEncoding("UTF-8");
            BufferedReader br = request.getReader();
            String buffer = null;
            StringBuffer buff = new StringBuffer();

            while((buffer = br.readLine()) != null) {
                buff.append(buffer + "\n");
            }

            br.close();
            param = buff.toString();
//            System.out.print("请求参数：" + param);
            log.info(param);
            return JSONObject.parseObject(param);
        } catch (Exception var6) {
            log.error(var6.getMessage());
            return null;
        }
    }

    protected String getParam() {
        String param = null;
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

        try {
            request.setCharacterEncoding("UTF-8");
            BufferedReader br = request.getReader();
            String buffer = null;
            StringBuffer buff = new StringBuffer();

            while((buffer = br.readLine()) != null) {
                buff.append(buffer + "\n");
            }

            br.close();
            param = buff.toString();
//            System.out.print("请求参数：" + param);
            log.info(param);
            return param;
        } catch (Exception var6) {
            log.error(var6.getMessage());
            return null;
        }
    }

    protected void setApplicationInfo(String key, Object value) {
        this.session.setAttribute(key, value);
    }

    protected Object getApplicationInfo(String key) {
        return this.session.getAttribute(key);
    }
}

