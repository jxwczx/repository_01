//package jc.com.webSocket.v2;
//
//import java.util.Map;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.http.server.ServletServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.server.HandshakeInterceptor;
//
//public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {
//
//	//握手前
//    public boolean beforeHandshake(ServerHttpRequest request,
//            ServerHttpResponse response, WebSocketHandler handler,
//            Map<String, Object> attr) throws Exception {
//        if (request instanceof ServletServerHttpRequest) {
//        	if(request.getHeaders().containsKey("Sec-WebSocket-Extensions")) {
//        		request.getHeaders().set("Sec-WebSocket-Extensions", "permessage-deflate");
//        	}
//            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
//            HttpSession session = servletRequest.getServletRequest().getSession(false);
//            if (session != null) {
//                String userName = (String) session.getAttribute(Constants.SESSION_USERNAME);
//                attr.put(Constants.WEBSOCKET_USERNAME,userName);
//            }
//        }
//        return true;
//    }
//    
//    //握手后
//    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
//            WebSocketHandler handler, Exception e) {
//    	System.out.println("握手后");
//    }
//
//}
