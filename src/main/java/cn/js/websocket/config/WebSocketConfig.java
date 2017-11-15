package cn.js.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
@Configuration
//此注解表示开启WebSocket支持。通过此注解开启使用STOMP协议来传输基于代理（message broker）的消息。
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        //注册一个名为/endpointChat的节点，并指定使用SockJS协议。
        stompEndpointRegistry.addEndpoint("/endpointChat").withSockJS();
    }
    //配置消息代理（Message Broker），可以理解为信息传输的通道
    public void configureMessageBroker(MessageBrokerRegistry messageBrokerRegistry){
        //点对点式应增加一个/queue的消息代理。相应的如果是广播室模式可以设置为"/topic"
        messageBrokerRegistry.enableSimpleBroker("/queue");
    }
}
