package com.games.listing.config;

import javax.servlet.Filter;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
	@Bean
	public Filter corsFilter() {
		return new CorsFilter();
	}

}
