package ru.dionisius.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Dionisius on 26.10.2017.
 */
@Configuration
@ComponentScan("ru.dionisius.data.dao")
@ComponentScan("ru.dionisius.service")
public class SpringRootConfig {
    public SpringRootConfig() {
        System.out.println("SpringRootConfig instantiated.");
    }
}
