package ru.dionisius.data.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.dionisius.data.dao.AdDao;
import ru.dionisius.data.dao.FakeDao;
import ru.dionisius.data.dao.IAdsDao;
import ru.dionisius.service.AdService;
import ru.dionisius.service.IAdService;

/**
 * Created by Dionisius on 09.12.2017.
 */
@Configuration
@ComponentScan("ru.dionisius")
public class sosConfig {
//    @Bean
//    public IAdsDao dao(){
//        return new FakeDao();
//    }
//
//
//    @Bean
//    public IAdService service(){
//        return new AdService();
//    }
}
