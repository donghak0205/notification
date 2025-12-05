package com.dh.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//mongoDB 연결을 설정을 기반으로 템플릿을 만드는 곳
@Configuration
@EnableMongoRepositories(
    basePackages = "com.dh",
    mongoTemplateRef = MongoTemplateConfig.MONGO_TEMPLATE
)
public class  MongoTemplateConfig {

    public static final String MONGO_TEMPLATE = "notificationMongoTemplate";

    @Bean(name = MONGO_TEMPLATE)
    public MongoTemplate notificationMongoTemplate( MongoDatabaseFactory notificationMongoFactory,
                                                    MongoConverter mongoConverter)
    {
        return new MongoTemplate(notificationMongoFactory, mongoConverter);
    }
}
