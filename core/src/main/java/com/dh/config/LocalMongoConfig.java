package com.dh.config;


import com.mongodb.ConnectionString;
import com.mongodb.client.MongoDatabase;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;
import org.springframework.context.annotation.Configuration; // ★ 추가

//@Profile("!test")


//mongoDB 연결을 설정하는 곳
@Slf4j
@Configuration
public class LocalMongoConfig {

    private static final String MONGODB_IMAGE_NAME = "mongo";
    private static final int MONGODB_INNER_PORT = 27017;
    private static final String DATABASE_NAME = "notification";
    private static final GenericContainer mongo = createMongoInstance();

    private static GenericContainer createMongoInstance(){
        return new GenericContainer(DockerImageName.parse(MONGODB_IMAGE_NAME))
                .withExposedPorts(MONGODB_INNER_PORT)
                .withReuse(true);
    }

    @PostConstruct
    public void startMongo(){
        try{
            System.out.println("!!!!!!!!!!!!! START !!!!!!!!!!!!!");
            mongo.start();

        } catch (Exception e){
            ///log.error(e.getMessage());
            log.error("!!!!★★★ FAIL ", e);  // ★

        }
    }

    @PreDestroy
    public void stopMongo(){
        try{
            mongo.stop();
        } catch (Exception e){
            log.error(e.getMessage(), e);

        }
    }

    @Bean(name = "notificationMongoFactory")
    public MongoDatabaseFactory notificationMongoFactory(){
            return new SimpleMongoClientDatabaseFactory(connectionString());
    }

    private ConnectionString connectionString(){
        String host = mongo.getHost();
        Integer port = mongo.getMappedPort(MONGODB_INNER_PORT);
        return new ConnectionString("mongodb://" + host + ":" + port + "/" + DATABASE_NAME);
    }




}
