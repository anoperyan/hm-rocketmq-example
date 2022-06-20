package net.hm.rocketmq.exam.config;

import lombok.extern.slf4j.Slf4j;
import net.hm.rocketmq.exam.properties.MQProducerProperties;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yan Jiahong
 * Created on 2022/6/17
 */
@Configuration
@Slf4j
public class MQProducerConfiguration {
    @Autowired
    private MQProducerProperties mqProducerProperties;

    @Bean
    @ConfigurationProperties(prefix = MQProducerProperties.PREFIX)
    public MQProducerProperties mqProducerProperties() {
        return new MQProducerProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultMQProducer defaultMQProducer() {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setNamesrvAddr(mqProducerProperties.getNameServAddr());
        producer.setProducerGroup(mqProducerProperties.getGroupName());
        try {
            producer.start();
        } catch (MQClientException e) {
            log.error("RocketMQ生产者启动失败！原因：" + e.getErrorMessage());
            throw new RuntimeException("RocketMQ生产者启动失败！", e);
        }
        log.info("RocketMQ生产者启动成功！");
        return producer;
    }
}
