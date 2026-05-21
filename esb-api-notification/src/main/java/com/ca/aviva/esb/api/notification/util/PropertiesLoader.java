package com.ca.aviva.esb.api.notification.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aviva.ca.esb.core.conf.client.api.PropertiesLoaderService;

public class PropertiesLoader {
	
	public static final Logger log = LoggerFactory.getLogger(PropertiesLoader.class);
	
	PropertiesLoaderService propertiesLoaderService = null;

	public PropertiesLoaderService getPropertiesLoaderService() {
		return propertiesLoaderService;
	}

	public void setPropertiesLoaderService(
			PropertiesLoaderService propertiesLoaderService) {
		this.propertiesLoaderService = propertiesLoaderService;
	}
}
