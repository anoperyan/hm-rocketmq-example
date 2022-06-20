package net.hm.rocketmq.exam.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.hm.rocketmq.exam.dto.OrderDTO;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Yan Jiahong
 * Created on 2022/6/17
 */
@RestController
@RequestMapping("/api/produce")
public class ProduceController {
    @Autowired
    private DefaultMQProducer producer;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/addOrder")
    public String addOrder(@RequestBody OrderDTO dto) throws Exception {
        dto.setGmtCreate(new Date());
        Message msg = new Message();
        msg.setBody(objectMapper.writeValueAsBytes(dto));
        msg.setTopic("yjh_test");
        producer.send(msg);
        return "OK";
    }
}
