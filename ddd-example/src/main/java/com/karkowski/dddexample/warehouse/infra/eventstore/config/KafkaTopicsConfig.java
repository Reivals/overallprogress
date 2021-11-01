package com.karkowski.dddexample.warehouse.infra.eventstore.config;

import com.karkowski.dddexample.warehouse.domain.event.ProductCreatedEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicsConfig {

    public static final String PRODUCT_CREATED_TOPIC_NAME = "product-created";
    public static final String PRODUCT_REMOVED_TOPIC_NAME = "product-removed";

    @Bean
    public NewTopic productCreatedTopic() {
        return TopicBuilder.name(ProductCreatedEvent.EVENT_NAME).build();
    }

    @Bean
    public NewTopic productRemovedTopic() {
        return TopicBuilder.name(PRODUCT_REMOVED_TOPIC_NAME).build();
    }
}
