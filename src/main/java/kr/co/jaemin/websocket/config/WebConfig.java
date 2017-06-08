
package kr.co.jaemin.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.PerConnectionWebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import kr.co.jaemin.websocket.handler.ChatWebSocketHandler;
import kr.co.jaemin.websocket.interceptor.ChatWebSocketHandlerInterceptor;

@Configuration
@EnableWebMvc
@EnableWebSocket
@ComponentScan(basePackages={"kr.co.jaemin.websocket"})
public class WebConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

	
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(chatWebSocketHandler(), "/chat-sock")
    .addInterceptors(new ChatWebSocketHandlerInterceptor()).withSockJS();
  }
  
  @Bean
  public WebSocketHandler chatWebSocketHandler() {
    return new PerConnectionWebSocketHandler(ChatWebSocketHandler.class);
  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
          configurer.enable();
  }
}
