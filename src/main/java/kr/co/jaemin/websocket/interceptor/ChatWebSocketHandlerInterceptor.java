package kr.co.jaemin.websocket.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import kr.co.jaemin.dto.ChatmemberDTO;

@Configuration
@EnableWebSocket
public class ChatWebSocketHandlerInterceptor extends HttpSessionHandshakeInterceptor {

	//������ ����Ǳ��� interceptor, ������ ���� �� �� ������ ���ǿ� �ִ� ���� attributes�� �־���
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		
		System.out.println("Before handshake");
		System.out.println("URI:" + request.getURI());
		ServletServerHttpRequest sshr = (ServletServerHttpRequest) request;
		HttpServletRequest req = sshr.getServletRequest();	//��������ť��Ƽ������ �Ⱦ�
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	//��������ť��Ƽ���� ���̵� �޾ƿ� 
		attributes.put("user_id", user.getUsername());
		return super.beforeHandshake(request, response, wsHandler, attributes);
	}
	
	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {

		super.afterHandshake(request, response, wsHandler, ex);
	}
	

}
