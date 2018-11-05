//package com.tony.blog.mq;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.tony.blog.utils.model.Message;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//@Slf4j
//public class KafkaProducer {
//
//    @Autowired
//    private KafkaTemplate<String,String> kafkaTemplate;
//
//    private Gson gson=new GsonBuilder().create();
//
//    public void sendMsg(String msg){
//        Message message=new Message();
//        message.setId(System.currentTimeMillis());
//        message.setMsg(msg);
//        message.setSendTime(new Date());
//
//        log.info("你发送的内容为:"+message.toString());
//
//        kafkaTemplate.send("sysMsg",gson.toJson(message));
//
//        log.info("消息发送成功");
//    }
//
//
//}
