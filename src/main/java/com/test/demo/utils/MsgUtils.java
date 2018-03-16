/**
 * www.mopaas.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.test.demo.utils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.test.demo.vo.MsgOutVo;

/**
 *                       
 * @Filename: MsgUtils.java
 *
 * @Description: 
 *
 * @Version: 1.0
 *
 * @Author: yshen
 *
 * @Email: yang.shen@anchora.me
 *
 *       
 * @History:<br>
 *<li>Author: yshen</li>
 *<li>Date: Jul 19, 2017</li>
 *<li>Version: 1.0</li>
 *<li>Content: create</li>
 *
 */
public class MsgUtils {

    private static Logger logger = Logger.getLogger(MsgUtils.class);

    /**
     * @param phoneNo
     * @param content
     * @return 100 success other error
     */
    public static MsgOutVo sendMsg(String phoneNo, String content) {
        MsgOutVo out = new MsgOutVo();
        out.setReturnCode("-1");
        out.setReturnMessage("Unknown error");
        
        String sendMsg =  "1";
        String sendMsgUrl = "url";
        String sendMsgUser = "User";
        String sendMsgPwd = "Pwd";
        
        if("1".equals(sendMsg)) {
            String realPwd = SecurityUtil.decrypt(sendMsgPwd);
            StringBuffer sb = new StringBuffer();
            sb
            .append("uid=")
            .append(sendMsgUser)
            .append("&pwd=")
            .append(Md5.MD5(realPwd + sendMsgUser))
            .append("&mobile=")
            .append(phoneNo)
            .append("&encode=utf8")
            .append("&content=")
            .append(content);
            
            String str = HttpClientUtils.getByHttp(sendMsgUrl, sb.toString());
            Map<String, String> map = new HashMap<String, String>();
            try {
                if(str != null && str.length() > 0) {
                    String str2 = new String(str.getBytes("iso-8859-1"),"gbk");
                    String[] str2s = str2.split("&");
                    for(String tmp : str2s) {
                        if(tmp.contains("=")) {
                            map.put(tmp.split("=")[0], tmp.split("=")[1]);
                        } else {
                            map.put(tmp, null);
                        }
                    }
                    out.setReturnCode(map.get("stat"));
                    out.setReturnMessage(map.get("message"));
                }
            } catch (UnsupportedEncodingException e) {
            }
        } else {
            logger.info("send_msg has not been setted or not setted to 1, does not send msg.");
        }
        logger.debug("Send msg return code:" + out.getReturnCode());
        logger.debug("Send msg return msg:" + out.getReturnMessage());
        return out;
    }
    
    public static MsgOutVo sendMsg2(String phoneNo, String content) {
        MsgOutVo out = new MsgOutVo();
        out.setReturnCode("-1");
        out.setReturnMessage("Unknown error");
        content = content.replaceAll(" ","%20");
        String sendMsg =  "1";
        String sendMsgUrl = "url";
        String sendMsgUser =  "User";
        String sendMsgPwd =  "Pwd";
        
        if("1".equals(sendMsg)) {
            String realPwd = SecurityUtil.decrypt(sendMsgPwd);
            StringBuffer sb = new StringBuffer();
            sb
            .append("ac=send&uid=")
            .append(sendMsgUser)
            .append("&pwd=")
            .append(Md5.MD5(realPwd + sendMsgUser))
            .append("&mobile=")
            .append(phoneNo)
//          .append("&encode=utf8")
            .append("&content=")
            .append(content);
            
            String str = HttpClientUtils.getByHttp(sendMsgUrl, sb.toString());
            Map<String, String> map = new HashMap<String, String>();
            try {
                if(str != null && str.length() > 0) {
                    String str2 = new String(str.getBytes("iso-8859-1"),"gbk");
                    String[] str2s = str2.split("&");
                    for(String tmp : str2s) {
                        if(tmp.contains("=")) {
                            map.put(tmp.split("=")[0], tmp.split("=")[1]);
                        } else {
                            map.put(tmp, null);
                        }
                    }
                    out.setReturnCode(map.get("stat"));
                    out.setReturnMessage(map.get("message"));
                }
            } catch (UnsupportedEncodingException e) {
            }
        } else {
            logger.info("send_msg has not been setted or not setted to 1, does not send msg.");
        }
        logger.debug("Send msg return code:" + out.getReturnCode());
        logger.debug("Send msg return msg:" + out.getReturnMessage());
        return out;
    }
}
