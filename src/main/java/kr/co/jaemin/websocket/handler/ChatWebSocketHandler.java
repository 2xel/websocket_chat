package kr.co.jaemin.websocket.handler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ChatWebSocketHandler extends TextWebSocketHandler{
	
	/*
	 * WebSocketSession 접속한 회원 리스트
	 */

	private List<WebSocketSession> connectedUsers;
	
	public ChatWebSocketHandler() {
		this.connectedUsers = new LinkedList<WebSocketSession>();
	}
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("접속한 IP : " + session.getRemoteAddress().getHostName());
		connectedUsers.add(session);
		//회원이 접속하면 접속유저세션리스트에 세션 추가
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		connectedUsers.remove(session);
		//회원이 접속을 종료하면 접속유저세션리스트에서 세션 제거
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		Map<String, Object> map = session.getAttributes();
		String user_id = (String) map.get("user_id");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("user_id", user_id);
		data.put("message", message.getPayload());
		System.out.println(user_id + "/" + message.getPayload());	//ID와 메세지
		
		ObjectMapper om = new ObjectMapper();
		String jsonStr = om.writeValueAsString(data);
		System.out.println(connectedUsers.size());	//connectedUsers 배열의 사이즈를 봄(몇명이 연결되어있는지)
		for (WebSocketSession webSocketSession : connectedUsers) {
			if (!session.getId().equals(webSocketSession)) {
				System.out.println(session.getId()+"//"+ webSocketSession);	//세션에 있는 아이디들에 FOR문 돌면서..
				webSocketSession.sendMessage(new TextMessage(jsonStr));
			}
		}
		//소켓 메세지가 왔을시 처리 sendMessage : jsonType의 유저정보 + 메세지
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		//에러처리
		super.handleTransportError(session, exception);
	}
	

}
