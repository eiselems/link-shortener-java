package com.marcuseisele.linkshortener.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@Configuration
@EnableJpaRepositories(basePackages = "com.marcuseisele.linkshortener.repository")
@Slf4j
public class LinkShortenerConfiguration {

    @PostConstruct
    public void loaded(){
        log.info("Loaded LinkShortener Configuration");
    }
}
