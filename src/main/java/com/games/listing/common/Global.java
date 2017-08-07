package com.games.listing.common;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;


public class Global {

	private static final Logger log = LoggerFactory.getLogger(Global.class);

	private static Properties prop_config;
	private static boolean isExternalConfig = true;
	
	public static String JDBC_URL;
	public static String JDBC_USERNAME;
	public static String JDBC_PASSWORD;
	public static int DB_FETCH_SIZE;
	
	public static String filePath;
	
	public static void loadClass() {
		log.info("**********Loading all Properties ***********");
	}

	public static final class HeaderType {
		public static final String CONTENT_TYPE = "Content-Type";
		public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
		public static final String AUTHORIZATION = "Authorization";
	}

	static {
		try {
			if (isExternalConfig) {
				log.info("*****External config**********");
				prop_config = new Properties();
				prop_config.load(new FileInputStream("./config.properties"));
			} else {
				log.info("*******Internal config**********");
				prop_config = PropertiesLoaderUtils.loadAllProperties("config.properties");
			}
			
			JDBC_URL = prop_config.getProperty("JDBC_URL");
			JDBC_USERNAME = prop_config.getProperty("JDBC_USERNAME");
			JDBC_PASSWORD = prop_config.getProperty("JDBC_PASSWORD");
			DB_FETCH_SIZE = Integer.parseInt(prop_config.getProperty("DB_FETCH_SIZE"));
			filePath = prop_config.getProperty("filePath");
			
		} catch (Exception e) {
			log.error("*****Properties exception****", e);
			e.printStackTrace();
		}

	}

}
