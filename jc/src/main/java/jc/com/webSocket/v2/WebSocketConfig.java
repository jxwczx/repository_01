//package jc.com.webSocket.v2;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.SockJsServiceRegistration;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistration;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//@Configuration
//@EnableWebMvc
//@EnableWebSocket
//public class WebSocketConfig implements WebSocketConfigurer{
//
//	public void registerWebSocketHandlers(WebSocketHandlerRegistry reg) {
////		// 添加拦截地址以及相应的websocket消息处理器
////        WebSocketHandlerRegistration registration = reg.addHandler(systemWebSocketHandler(), "/websocket","sockjs/websocket");
////        SockJsServiceRegistration sockJS = registration.withSockJS();
////        // 添加拦截器
////        registration.addInterceptors(new WebSocketHandshakeInterceptor());
//        
//        String websocket_url = "/websocket";                     //设置websocket的地址
//        reg.addHandler(systemWebSocketHandler(), websocket_url)        //注册到Handler
//           .addInterceptors(new WebSocketHandshakeInterceptor())    //注册到Interceptor
//           ;
//        
//        String sockjs_url    = "test/sockjs/websocket";           //设置sockjs的地址
//        reg.addHandler(systemWebSocketHandler(),sockjs_url )        //注册到Handler
//           .addInterceptors(new WebSocketHandshakeInterceptor())    //注册到Interceptor
//           .withSockJS(); 
//        
//	}
//    
//    @Bean
//    public WebSocketHandler systemWebSocketHandler(){
//        return new SystemWebSocketHandler();
//    }
//}
