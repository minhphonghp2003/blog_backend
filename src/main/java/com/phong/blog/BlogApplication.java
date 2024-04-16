package com.phong.blog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@SpringBootApplication
public class BlogApplication {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(BlogApplication.class);
        application.run(args);

//			SpringApplication.run(BlogApplication.class, args);

    }

}
