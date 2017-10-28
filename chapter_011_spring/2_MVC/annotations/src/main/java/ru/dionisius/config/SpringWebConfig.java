package ru.dionisius.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Dionisius on 26.10.2017.
 */
@EnableWebMvc
@Configuration
@ComponentScan("ru.dionisius.controllers")
public class SpringWebConfig extends WebMvcConfigurerAdapter{
}
