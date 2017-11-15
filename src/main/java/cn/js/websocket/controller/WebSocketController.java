package cn.js.websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import java.security.Principal;

@Controller
public class WebSocketController {
    @Autowired
    //通过SimpMessagingTemplate模板向浏览器发送消息。如果是广播模式，可以直接使用注解@SendTo
    private SimpMessagingTemplate simpMessagingTemplate;

    //开启STOMP协议来传输基于代理的消息，这时控制器支持使用@MessageController，就像使用@RequestMapping是一样的
    //当浏览器向服务端发送请求时，通过@MessageController映射/chat这个路径
    @MessageMapping("/chat")
    //在SpringMVC中，可以直接在参数中获得principal,其中包含当前用户的信息
    public void handleChat(Principal principal,String msg){
        //下面的代码就是如果发送人是Michael，接收人就是Janet，发送的信息是message，反之亦然。
        if(principal.getName().equals("Michael")){
            //通过SimpMessagingTemplate的convertAndSendToUser向用户发送消息。
            //第一参数表示接收信息的用户，第二个是浏览器订阅的地址，第三个是消息本身
            simpMessagingTemplate.convertAndSendToUser("Janet","/queue/notifications",
                    principal.getName() + "-发送:" + msg);
        } else {
            simpMessagingTemplate.convertAndSendToUser("Michael","/queue/notifications",
                    principal.getName() + "-发送:" + msg);
        }
    }
}
