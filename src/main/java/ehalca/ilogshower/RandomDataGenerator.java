/**
 * 
 */
package ehalca.ilogshower;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author ehalc
 *
 */
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = WebApplicationContext.SCOPE_SESSION)
public class RandomDataGenerator implements
    ApplicationListener<BrokerAvailabilityEvent> {

    private final MessageSendingOperations<String> messagingTemplate;

    @Autowired
    public RandomDataGenerator(
        final MessageSendingOperations<String> messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void onApplicationEvent(BrokerAvailabilityEvent event) {
    }

    @Scheduled(fixedDelay = 10000)
    public void sendDataUpdates() {
        this.messagingTemplate.convertAndSend(
            "/data/", new Random().nextInt(100));

    }
}