package com.info7255.ebl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//import javax.servlet.Filter;

@EnableAsync
@Configuration
@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
@EnableWebMvc
@EnableRedisRepositories(basePackages = "com.info7255.ebl.*")
public class EBlApplication {

//    @Bean
//    public Filter shallowEtagFilter() {
//        return (Filter) new ShallowEtagHeaderFilter();
//    }

    public static void main(String[] args) {
        SpringApplication.run(EBlApplication.class, args);
    }

}
