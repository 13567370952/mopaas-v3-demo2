/**
 * www.mopaas.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.test.demo.service.impl;


import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.test.demo.service.MopaasMailService;
import com.test.demo.utils.SecurityUtil;

/**
 *                       
 * @Filename: MopaasMailServiceImpl.java
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
@Component(value="mopaasMailService")
public class MopaasMailServiceImpl extends JavaMailSenderImpl implements MopaasMailService{
    private static Logger logger = Logger.getLogger(MopaasMailServiceImpl.class);
    
    public void init(){
        String isSsl = "0";
        String sslPort = "465";
        String host = "smtp.exmail.qq.com";
        String username = "no-reply@mopaas.com";
        String password = "DxpcbxOq//Wkq/YMIUdMzw==";
        if(host == null || "".equals(host)) {
            logger.error("inpaas_mail_sender_stmp_server has not been setted.");
            return;
        }
        
        if(username == null || "".equals(username)) {
            logger.error("inpaas_mail_sender_username has not been setted.");
            return;
        }
        
        if(password == null || "".equals(password)) {
            logger.error("inpaas_mail_sender_password has not been setted.");
            return;
        }
        
        password = SecurityUtil.decrypt(password);
        
        super.setHost(host);
        super.setUsername(username);
        super.setPassword(password);
        
        Properties javaMailProperties = new Properties();
        javaMailProperties.setProperty("mail.smtp.auth", "true");
        
        if("0".equals(isSsl)) {
            if(sslPort == null || "".equals(sslPort)) {
                logger.error("inpaas_mail_sender_stmp_server_ssl_port has not been setted.");
                return;
            }
            super.setPort(Integer.valueOf(sslPort));
            javaMailProperties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }
        super.setJavaMailProperties(javaMailProperties);
    }
    
    @Override
    public void send(MimeMessage msg) {
        super.send(msg);
    }

    @Override
    public MimeMessage createMimeMessage() {
        init();
        return super.createMimeMessage();
    }
    
}
