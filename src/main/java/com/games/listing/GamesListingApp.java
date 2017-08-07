package com.games.listing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.games.listing.common.Global;

@SpringBootApplication
public class GamesListingApp {
	
	private static final Logger log = LoggerFactory.getLogger(GamesListingApp.class);

	public static void main(String[] args) {
		SpringApplication.run(GamesListingApp.class, args);
		
		/*loading all properties*/
		Global.loadClass();
		
		log.info("************Service Started Successfully************");
	}
}
