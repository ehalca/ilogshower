/**
 * 
 */
package ehalca.ilogshower;

import java.util.List;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

/**
 * @author ehalc
 *
 */
@Configuration
@EnableWebSocketMessageBroker
@EnableScheduling
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/random").withSockJS().setStreamBytesLimit(2048 * 1024)
            .setHttpMessageCacheSize(10000)
            .setDisconnectDelay(30 * 1000);
	}

	public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
	}
        
        
	public void configureClientInboundChannel(ChannelRegistration registration) {
	}

	public void configureClientOutboundChannel(ChannelRegistration registration) {
	}

	public void addArgumentResolvers(
			List<org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver> argumentResolvers) {
	}

	public void addReturnValueHandlers(
			List<org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler> returnValueHandlers) {
	}

	public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
		return true;
	}

	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/read");
	}



}
