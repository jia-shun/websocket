package cn.js.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
    public void addViewControllers(ViewControllerRegistry viewControllerRegistry){
        viewControllerRegistry.addViewController("/login").setViewName("/login");
        viewControllerRegistry.addViewController("/chat").setViewName("/chat");
    }
}
