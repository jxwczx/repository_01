//package jc.com.webSocket.v2;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.WebSocketMessage;
//import org.springframework.web.socket.WebSocketSession;
//
//public class SystemWebSocketHandler implements WebSocketHandler {
//	 
//    private static final List<WebSocketSession> users = new ArrayList<WebSocketSession>();
//
//    //连接已建立
//    public void afterConnectionEstablished(WebSocketSession session)
//            throws Exception {
//        users.add(session);
//    }
//    
//    //消息接收处理
//    public void handleMessage(WebSocketSession session, WebSocketMessage<?> ws_msg)
//            throws Exception {
//        //消息处理
//    }
//    
//    //连接已关闭
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status)
//            throws Exception {
//        users.remove(session);
//    }
//
//    
//    //异常处理
//    public void handleTransportError(WebSocketSession session, Throwable e)
//            throws Exception {
//        if(session.isOpen()) session.close();
//        users.remove(session);
//    }
//    
//    public boolean supportsPartialMessages() {
//        return false;
//    }
//    
//    /**
//     * 自定义接口
//     * 给所有在线用户发送消息
//     * @param message
//     * @throws IOException 
//     */
//    public void sendMessageToUsers(TextMessage message) throws IOException {
//        for (WebSocketSession user : users) {
//            if (user.isOpen()) user.sendMessage(message);
//        }
//    }
//    
//    /**
//     * 自定义接口
//     * 给某个用户发送消息
//     * @param userName
//     * @param message
//     * @throws IOException 
//     */
//    public void sendMessageToUser(String userName, TextMessage message) throws IOException {
//        for (WebSocketSession user : users) {
//            if (user.getAttributes().get(Constants.WEBSOCKET_USERNAME).equals(userName)) {
//               if (user.isOpen()) {
//                   user.sendMessage(message);
//                   break;
//               }
//            }
//        }
//    }
//}
