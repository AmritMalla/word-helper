package com.amrit.wordhelper;

import com.amrit.wordhelper.service.FileStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.Resource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableMongoRepositories(basePackages = "com.amrit.wordhelper.repository")
public class WordHelperApplication implements CommandLineRunner {

    @Resource
    private FileStorageService fileStorageService;

    public static void main(String[] args) {
        SpringApplication.run(WordHelperApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        fileStorageService.deleteAll();
        fileStorageService.init();
    }
}
