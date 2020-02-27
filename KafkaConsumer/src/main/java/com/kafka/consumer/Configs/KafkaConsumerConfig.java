package com.kafka.consumer.Configs;


import com.kafka.consumer.Models.Email;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${kafka.bootstrap.server}")
    private String kafkaBootstrapServer;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServer);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config);
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }


    @Bean
    public ConsumerFactory<String, Email> emailConsumerFactory() {
        Map<String, Object> config = new HashMap<>();
        JsonDeserializer<Email> deserializer = new JsonDeserializer<>(Email.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("com.kafka.consumer.Models");
        deserializer.setUseTypeMapperForKey(true);
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServer);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Email> emailKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Email> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(emailConsumerFactory());
        return factory;
    }

}