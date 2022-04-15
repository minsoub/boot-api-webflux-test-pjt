package com.bithumbsystems.bootstarterproject.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveRepositoriesAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.data.mongodb.repository.config.ReactiveMongoRepositoryConfigurationExtension;
import org.springframework.data.mongodb.repository.support.ReactiveMongoRepositoryFactoryBean;

//@EnableReactiveMongoRepositories
//public class MongoConfig extends AbstractReactiveMongoConfiguration {
//    @Override
//    protected String getDatabaseName() {
//        return "sampleDB";
//    }
//
//    @Override
//    public MongoClient reactiveMongoClient() {
//        return MongoClients.create();
//    }
//}


//@Configuration
//@ConditionalOnClass({MongoClient.class, ReactiveMongoRepository.class})
//@ConditionalOnMissingBean({ReactiveMongoRepositoryFactoryBean.class, ReactiveMongoRepositoryConfigurationExtension.class})
//@ConditionalOnProperty(prefix = "spring.data.mongodb.reactive-repositories", name = "enabled", havingValue = "true", matchIfMissing = true)
//@Import(MongoReactiveRepositoriesAutoConfiguration.class)
//@AutoConfigureAfter(MongoReactiveDataAutoConfiguration.class)
public class MongoConfig {

//    @EnableReactiveMongoRepositories
//    private static class EnableReactiveMongoRepositoriesConfiguration {
//
//    }
}



//@EnableReactiveMongoRepositories
//public class MongoConfig extends AbstractReactiveMongoConfiguration {
//    @Bean
//    public MongoClient mongoClient() {
//        return MongoClients.create();
//    }
//
//    @Override
//    protected String getDatabaseName() {
//        return "sampleDB";
//    }
//}
