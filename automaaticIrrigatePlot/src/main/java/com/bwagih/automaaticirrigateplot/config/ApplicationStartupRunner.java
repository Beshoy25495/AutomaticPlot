package com.bwagih.automaaticirrigateplot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupRunner implements CommandLineRunner
        , WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    @Autowired
    ApplicationContext context;

    Logger logger = LoggerFactory.getLogger(ApplicationStartupRunner.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info(
                "ApplicationName: " + context.getApplicationName()
                        + "StartupDate: " + context.getStartupDate());

    }

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        factory.setPort(9088);
    }
}
