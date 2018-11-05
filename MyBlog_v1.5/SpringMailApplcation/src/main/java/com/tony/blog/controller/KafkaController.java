//package com.tony.blog.controller;
//
//import com.tony.blog.mq.KafkaConsumer;
//import com.tony.blog.mq.KafkaProducer;
//import com.tony.blog.utils.model.Message;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//@RequestMapping("/kafka")
//public class KafkaController {
//
//    @Autowired
//    private KafkaConsumer kafkaConsumer;
//
//    @Autowired
//    private KafkaProducer kafkaProducer;
//
//    @RequestMapping("")
//    public String msg(){
//        return "sendMsg";
//    }
//
//
//    @PostMapping("/send")
//    @ResponseBody
//    public String sendMsg(String msg){
//        kafkaProducer.sendMsg(msg);
//        return "success";
//    }
//
//    @RequestMapping("/receive")
//    @ResponseBody
//    public Message receiveMsg(){
//        return kafkaConsumer.getMessages();
//    }
//}
