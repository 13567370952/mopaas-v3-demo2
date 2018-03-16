/**
 * www.mopaas.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.test.demo.utils;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 *                       
 * @Filename: MailUtil.java
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
public class MailUtil {

    private static Logger logger = Logger.getLogger(MailUtil.class);

    public static void sendMail(JavaMailSender mailSender, String from, String to, String title, String content) {
        try {

            MimeMessage msg = mailSender.createMimeMessage();
            msg.addHeader("Content-type", "text/html");
            MimeMessageHelper helper = new MimeMessageHelper(msg, true, "GB2312");
            helper.setTo(to);
            helper.setFrom("MoPaaS" + "<" + from + ">");
            helper.setSubject(title);
            helper.setText(content.toString(), true);
//            ClassPathResource file = new ClassPathResource("/home/yshen/11.txt");
//            FileSystemResource file = new FileSystemResource("/home/yshen/11.txt");
            //加入附件
//            helper.addAttachment("11.txt", file);
            mailSender.send(msg);
            
        } catch (Exception e) {
            LogUtil.exception(logger, e);
            logger.error("邮件发送失败！");
        }
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void sendMail(String url, String api_user, String api_key, String from, String to, String title, String content) {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpost = new HttpPost(url);

            List nvps = new ArrayList();
            // 不同于登录SendCloud站点的帐号，您需要登录后台创建发信子帐号，使用子帐号和密码才可以进行邮件的发送。
            nvps.add(new BasicNameValuePair("api_user", api_user));
            nvps.add(new BasicNameValuePair("api_key", SecurityUtil.decrypt(api_key)));
            nvps.add(new BasicNameValuePair("from", "MoPaaS" + "<" + from + ">"));
            nvps.add(new BasicNameValuePair("to", to));
            nvps.add(new BasicNameValuePair("subject", title));
            nvps.add(new BasicNameValuePair("html", content));

            httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            // 请求
            HttpResponse response = httpclient.execute(httpost);
            // 处理响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
                // 读取xml文档
                String result = EntityUtils.toString(response.getEntity());
                logger.info(" sendcloud result=" + result);
            } else {
                logger.error(" sendcloud error!");
            }
        } catch (Exception e) {
            LogUtil.exception(logger, e);
            logger.error("Sending email error!");
        }
    }
    
    @SuppressWarnings({ "deprecation", "resource" })
    public static void sendMailWithAttachment(String url, String api_user, String api_key, String from, String to, String title, String content,File file) {
        try {
//          HttpClient httpclient = new DefaultHttpClient();
//          HttpPost httpost = new HttpPost(url);
            HttpPost httpPost = new HttpPost(url);
            HttpClient httpClient = new DefaultHttpClient();
            MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE,null, Charset.forName("UTF-8"));
            entity.addPart("api_user", new StringBody(api_user, Charset.forName("UTF-8")));
            entity.addPart("api_key", new StringBody(SecurityUtil.decrypt(api_key), Charset.forName("UTF-8")));
            entity.addPart("from", new StringBody("MoPaaS" + "<" + from + ">", Charset.forName("UTF-8")));
            entity.addPart("to", new StringBody(to, Charset.forName("UTF-8")));
            entity.addPart("fromName", new StringBody("MoPaaS" + "<" + from + ">", Charset.forName("UTF-8")));
            entity.addPart("subject", new StringBody(title, Charset.forName("UTF-8")));
            entity.addPart("html", new StringBody(content, Charset.forName("UTF-8")));

            // 添加附件
//          File file = new File("/home/yshen/Desktop/205016.csv");
            FileBody attachment = new FileBody(file, "application/octet-stream", "UTF-8");
            entity.addPart("attachments", attachment);
            //多附件
//          File file1 = new File("D:\\测试1.txt");
//          FileBody attachment1 = new FileBody(file1, "application/octet-stream","UTF-8");
//          entity.addPart("attachments", attachment1);

            //添加附件, 文件流形式
            //File file = new File("D:\\1.txt");
            //String attachName = "attach.txt";
            //InputStreamBody is = new InputStreamBody(new FileInputStream(file),
            //attachName);
            //entity.addPart("attachments", is);
            //多附件
            //File file1 = new File("D:\\1.txt");
            //String attachName1 = "attach.txt";
            //InputStreamBody is1 = new InputStreamBody(new FileInputStream(file1),
            //attachName1);
            //entity.addPart("attachments", is1);

            httpPost.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPost);
            // 处理响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 正常返回, 解析返回数据
                String result = EntityUtils.toString(response.getEntity());
                logger.info(" sendcloud result=" + result);
            } else {
                logger.error(" sendcloud error!");
            }
            httpPost.releaseConnection();
        } catch (Exception e) {
            LogUtil.exception(logger, e);
            logger.error("Sending email error!");
        }
    }

}
