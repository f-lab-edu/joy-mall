package com.mini.joymall.commons.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic newStockDecreaseTopic() {
        return TopicBuilder.name("new-stock-decrease")
                .partitions(1)
                .replicas(3)
                .build();
    }
}
