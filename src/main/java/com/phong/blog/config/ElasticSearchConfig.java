package com.phong.blog.config;

//import org.elasticsearch.client.RestHighLevelClient;

import org.apache.http.HttpResponseInterceptor;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.RestClients;
//import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
//import org.springframework.data.elasticsearch.client.elc.ElasticsearchClients;
//import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//import org.springframework.data.elasticsearch.support.HttpHeaders;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.Map;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.phong.blog.Searching.Repository")
@ComponentScan(basePackages = { "com.phong.blog" })
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {
    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
               .connectedTo("blog-6234584370.us-east-1.bonsaisearch.net:443")
                .usingSsl()
                .withBasicAuth("g85ydjzeir","e7h431s4kf")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

}
