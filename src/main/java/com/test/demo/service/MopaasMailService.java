/**
 * www.mopaas.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.test.demo.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;

/**
 *                       
 * @Filename: MopaasMailService.java
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
public interface MopaasMailService extends JavaMailSender{

    public void send(MimeMessage msg);
    
    public MimeMessage createMimeMessage();
}
