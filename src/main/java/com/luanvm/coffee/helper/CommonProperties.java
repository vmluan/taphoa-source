/**
 * Copyright (c) 2013 THKAssociates,Inc. All rights reserved.
 * Filename   : CommonProperties.java
 * Description: 
 * @author    : Khanh.Pham
 * Created    : Sep 3, 2013
 */

package com.luanvm.coffee.helper;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;


public class CommonProperties {

	private static final Logger logger = LoggerFactory.getLogger(Properties.class);

	public static Properties myProperties() {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource(
				"/common.properties"));
		Properties properties = null;
		try {
			propertiesFactoryBean.afterPropertiesSet();
			properties = propertiesFactoryBean.getObject();

		} catch (IOException e) {
			logger.error("Cannot load properties file.", e);
		}
		
		
		return properties;
	}
	
	public static String ServerUploadPath()
	{
		return myProperties().getProperty(Constants.SERVER_UPLOAD_PATH);
	}
}