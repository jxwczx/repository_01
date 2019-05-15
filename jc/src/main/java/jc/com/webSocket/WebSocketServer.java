package jc.com.webSocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;

@ServerEndpoint("/webServer/{userId}/{loginName}")
public class WebSocketServer {
	private static int onlineCount = 0;
	private static Map<String, Set<WebSocketServer>> userSocket = new HashMap<>();
	private Session session;
	private String userId;
	private String loginName;

	@OnOpen
	public void onOpen(@PathParam("userId") String userId,@PathParam("loginName") String loginName, Session session) throws IOException {
		this.session = session;
		this.userId = userId;
		this.loginName = loginName;
		onlineCount++;
		if (userSocket.containsKey(this.userId)) {
			userSocket.get(this.userId).add(this);
		} else {
			Set<WebSocketServer> addUserSet = new HashSet<>();
			addUserSet.add(this);
			userSocket.put(this.userId, addUserSet);
		}
	}

	@OnClose
	public void onClose() {
		onlineCount--;
		if (userSocket.get(this.userId).size() == 0) {
			userSocket.remove(this.userId);
		} else {
			userSocket.get(this.userId).remove(this);
		}
		
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		String msg = JSON.toJSON(new Message(null,null,userId,loginName,message)).toString();
		sendMessageToAllUser(userId,msg);
	}

	@OnError
	public void onError(Session session, Throwable error) {

	}

	/**
	 * 向某人发送信息
	 * 
	 * */
	public Boolean sendMessageToUser(Long userId, String message) {
		if (userSocket.containsKey(userId)) {
			for (WebSocketServer WS : userSocket.get(userId)) {
				try {
					WS.session.getBasicRemote().sendText(message);
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
			return true;
		}
		return false;
	}
	/**
	 * 向所有人发送信息
	 * 
	 * */
	public void sendMessageToAllUser(String userId, String message) {
		for(String key:userSocket.keySet()) {
			if(!key.equals(userId)) {
				if (userSocket.containsKey(key)) {
					for (WebSocketServer WS : userSocket.get(key)) {
						try {
							WS.session.getBasicRemote().sendText(message);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
	}
}
