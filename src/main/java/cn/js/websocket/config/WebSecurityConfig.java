package cn.js.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author JiaShun
 * @date 2017-08-22 11:44
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 设置Spring Security对/和/"login"路径不拦截
     * 设置SpringSecurity的登录页面为/login
     * 登录成功后转向/chat路径
     * @param httpSecurity HttpSecurity
     * @throws Exception Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/","/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/chat")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    /**
     * 在内存中分配两个用户Michael和Janet，密码都为freedom，角色都是USER
     * @param authenticationManagerBuilder AuthenticationManagerBuilder
     * @throws Exception Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .inMemoryAuthentication()
                .withUser("Michael").password("freedom").roles("USER")
                .and()
                .withUser("Janet").password("freedom").roles("USER");
    }

    /**
     * /resources/static/目录下的静态资源，Spring Security不拦截
     * @param webSecurity webSecurity
     */
    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers("/resources/static/**");
    }

}
