package net.hm.rocketmq.exam.properties;

import com.alibaba.fastjson.JSON;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.PostConstruct;

/**
 * @author Yan Jiahong
 * Created on 2022/6/17
 */
@Data
@Slf4j
public class MQProducerProperties {
    public static final String PREFIX = "rocketmq-client.producer";
    private String groupName;
    private String nameServAddr;
    private String topic;

    @PostConstruct
    public void init() {
        log.info("MQProducerProperties初始化成功！\r\n{}\r\n", JSON.toJSONString(this));
    }
}
