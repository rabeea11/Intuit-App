package com.example.IntuitApp.kafka;

import com.example.IntuitApp.model.Payment;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@Configuration
    public class Config {

        private final KafkaProperties kafkaProperties;

        @Autowired
        public Config(KafkaProperties kafkaProperties) {
            this.kafkaProperties = kafkaProperties;
        }

        @Bean
        public ProducerFactory<String, Payment> producerFactory() {
            Map<String, Object> properties = kafkaProperties.buildProducerProperties();
            properties.put(
                    ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                    StringSerializer.class);
            properties.put(
                    ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                    JsonSerializer.class);
            return new DefaultKafkaProducerFactory<>(properties);
        }

        @Bean
        public KafkaTemplate<String , Payment> kafkaTemplate() {
            return new KafkaTemplate<>(producerFactory());
        }


}
