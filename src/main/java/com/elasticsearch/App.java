package com.elasticsearch;

import com.elasticsearch.config.ElasticSearchConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by yaoshuangqi on 2020/01/24.
 */
@SpringBootApplication
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        logger.info(" application running...");
    }


}
