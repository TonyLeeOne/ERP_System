//package com.tony.blog.mq;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.tony.blog.utils.model.Message;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//@Slf4j
//public class KafkaConsumer {
//
//
//    private String message=null;
//
//    @KafkaListener(topics = {"sysMsg"})
//    public void receiveMsg(ConsumerRecord<?,?> record){
//        Optional<?> kafkaMessage=Optional.ofNullable(record.value());
//        if(kafkaMessage.isPresent()){
//            Object msg=kafkaMessage.get();
//            log.info("接收到的消息为:"+msg.toString());
//            message=msg.toString();
//        }
//    }
//
//
//    public Message getMessages(){
//        Gson gson=new GsonBuilder().create();
//        return gson.fromJson(message,Message.class);
//    }
//
//
//}
