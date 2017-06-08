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
	 * WebSocketSession ������ ȸ�� ����Ʈ
	 */

	private List<WebSocketSession> connectedUsers;
	
	public ChatWebSocketHandler() {
		this.connectedUsers = new LinkedList<WebSocketSession>();
	}
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("������ IP : " + session.getRemoteAddress().getHostName());
		connectedUsers.add(session);
		//ȸ���� �����ϸ� �����������Ǹ���Ʈ�� ���� �߰�
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		connectedUsers.remove(session);
		//ȸ���� ������ �����ϸ� �����������Ǹ���Ʈ���� ���� ����
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		Map<String, Object> map = session.getAttributes();
		String user_id = (String) map.get("user_id");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("user_id", user_id);
		data.put("message", message.getPayload());
		System.out.println(user_id + "/" + message.getPayload());	//ID�� �޼���
		
		ObjectMapper om = new ObjectMapper();
		String jsonStr = om.writeValueAsString(data);
		System.out.println(connectedUsers.size());	//connectedUsers �迭�� ����� ��(����� ����Ǿ��ִ���)
		for (WebSocketSession webSocketSession : connectedUsers) {
			if (!session.getId().equals(webSocketSession)) {
				System.out.println(session.getId()+"//"+ webSocketSession);	//���ǿ� �ִ� ���̵�鿡 FOR�� ���鼭..
				webSocketSession.sendMessage(new TextMessage(jsonStr));
			}
		}
		//���� �޼����� ������ ó�� sendMessage : jsonType�� �������� + �޼���
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		//����ó��
		super.handleTransportError(session, exception);
	}
	

}
