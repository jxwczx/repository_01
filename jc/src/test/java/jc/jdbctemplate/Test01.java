//package jc.jdbctemplate;
//
//import java.io.IOException;
//import java.net.URI;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ExecutionException;
//
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.util.concurrent.ListenableFuture;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.WebSocketHttpHeaders;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.client.standard.StandardWebSocketClient;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import jc.com.webSocket.v2.SystemWebSocketHandler;
//
//public class Test01 {
//	public static void test() {
//		System.out.println("start");
//		ApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");
//                
//        JdbcTemplate jdbcTemplate  =  (JdbcTemplate) context.getBean("jdbcTemplate");
//        String sql = "select * from chat_user ";
//        List<Map<String,Object>> users = jdbcTemplate.queryForList(sql);
//        System.out.println(users.size());
//    }
//	
//	public static void main(String[] args) {
//		Test01.connectTest2();
//	}
//	
//	
//    
//    public static void connectTest2(){
//        StandardWebSocketClient client = new StandardWebSocketClient();
//        WebSocketHandler webSocketHandler = new SystemWebSocketHandler();
//        String uriTemplate = "ws://127.0.0.1:8080/websocket";
//        UriComponentsBuilder fromUriString = UriComponentsBuilder.fromUriString(uriTemplate);
//        fromUriString.queryParam("account","111111");
//        URI uri = fromUriString.buildAndExpand().encode().toUri();
//        WebSocketHttpHeaders headers = null;
//        ListenableFuture<WebSocketSession> doHandshake = client.doHandshake(webSocketHandler, headers  , uri);
//        try {
//            WebSocketSession session = doHandshake.get();
//            session.sendMessage(new TextMessage("hello world"));
//        } catch (InterruptedException | ExecutionException | IOException e) {
//            e.printStackTrace();
//        }
//        
//    }
//}
