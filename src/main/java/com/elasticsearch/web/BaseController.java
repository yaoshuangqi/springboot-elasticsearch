package com.elasticsearch.web;

import com.alibaba.fastjson.JSON;
import com.elasticsearch.util.HttpUtil;
import com.elasticsearch.util.JsonMapper;
import com.elasticsearch.util.ResponseMessage;
import com.elasticsearch.util.ReturnCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Objects;

/**
 * @author quanroon.ysq
 * @version 1.0.0
 * @date 2020/2/29 17:40
 * @content
 */
public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public BaseController() {
    }

    protected ResponseMessage getResponseMsg_failed(ReturnCodeEnum codeEnum) {
        ResponseMessage resMsg = new ResponseMessage();
        resMsg.setRetCode(codeEnum.getRetCode());
        resMsg.setMessage(codeEnum.getMessage());
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("\r\n响应body:" + JsonMapper.getInstance().toJson(resMsg));
        }

        return resMsg;
    }

    protected ResponseMessage getResponseMsg_failed(String code, String message) {
        ResponseMessage resMsg = new ResponseMessage();
        resMsg.setRetCode(code);
        resMsg.setMessage(message);
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("\r\n响应body:" + JsonMapper.getInstance().toJson(resMsg));
        }

        return resMsg;
    }

    protected ResponseMessage getResponseMsg_success(Object object) {
        ResponseMessage resMsg = new ResponseMessage();
        resMsg.setResult(object);
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("\r\n响应body:" + JsonMapper.getInstance().toJson(resMsg));
        }

        return resMsg;
    }

    protected ResponseMessage httpRequestToTrans(String configRequestUrlKey, String reqBody) {

        String coreUrl = "";//Global.getConfig(configRequestUrlKey);//配置文件设置的请求路径
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("requestUrl = " + configRequestUrlKey);
        }

        ResponseMessage resMsg;
        if (Objects.isNull(coreUrl)) {
            resMsg = new ResponseMessage();
            resMsg.setRetCode(ReturnCodeEnum.REQUEST_PARAM_ISNULL.getRetCode());
            resMsg.setMessage("req url is null");
            return resMsg;
        } else {
            try {
                String resBody = HttpUtil.doPost(coreUrl, reqBody);
                return (ResponseMessage)JSON.parseObject(resBody, ResponseMessage.class);
            } catch (MalformedURLException var6) {
                var6.printStackTrace();
                this.logger.error("", var6);
                resMsg = new ResponseMessage();
                resMsg.setRetCode("2000011");
                resMsg.setMessage("请求Url格式错误");
                return resMsg;
            } catch (IOException var7) {
                var7.printStackTrace();
                this.logger.error("", var7);
                resMsg = new ResponseMessage();
                resMsg.setRetCode(ReturnCodeEnum.REQUEST_PARAM_ISNULL.getRetCode());
                resMsg.setMessage(var7.getMessage());
                return resMsg;
            }
        }
    }
}
