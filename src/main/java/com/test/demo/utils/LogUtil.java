/**
 * www.mopaas.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.test.demo.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

/**
 *                       
 * @Filename: LogUtil.java
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
 *<li>Date: Jul 7, 2017</li>
 *<li>Version: 1.0</li>
 *<li>Content: create</li>
 *
 */
public class LogUtil {

    private static final Logger logger = Logger.getLogger(LogUtil.class);
    
    public static void exception(Logger logger, Throwable e) {
        StringWriter sw = new StringWriter();
        try {
            e.printStackTrace(new PrintWriter(sw));
            logger.error(logger.getName() + " occured " + e.getClass().getSimpleName() + " " + e.getMessage());
            logger.error(sw.toString());
        } finally {
            try {
                sw.close();
            } catch (IOException e1) {
                logger.error(e1);
            }
        }
    }
    
    public static void exception(Class<?> clazz, Throwable e) {
        StringWriter sw = new StringWriter();
        try {
            e.printStackTrace(new PrintWriter(sw));
//            if (e instanceof AppException) {
//                logger.warn(clazz.getName() + " occured " + e.getClass().getSimpleName() + " " + MsgEnum.getByCode(((AppException) e).getErrorCode()));
//            } else {
                logger.warn(clazz.getName() + " occured " + e.getClass().getSimpleName() + " " + e.getMessage());
//            }
            logger.warn(sw.toString());
        } finally {
            try {
                sw.close();
            } catch (IOException e1) {
                logger.error(e1);
            }
        }
    }

}
