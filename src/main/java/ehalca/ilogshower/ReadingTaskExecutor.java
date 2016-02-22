/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ehalca.ilogshower;

import com.google.common.collect.Maps;
import ehalca.ilogshower.logfile.LogFile;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 *
 * @author Hulk
 */
public class ReadingTaskExecutor extends ThreadPoolTaskExecutor {
    
    private Map<String, DetachedReadExecutor> executors = Maps.newConcurrentMap();
    
    private MessageSendingOperations<String> messagingTemplate;
    

    public ReadingTaskExecutor(MessageSendingOperations<String> messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    
    public void startReading(String sessionId, AbstractLogFileContext context, LogFile logFile){
         if (executors.get(sessionId) == null){
            DetachedReadExecutor reader = new DetachedReadExecutor(context, this.messagingTemplate, logFile);
            this.submit(reader);
            this.executors.put(sessionId, reader);
         }
    }
    
    public void updateReading(String sessionId, AbstractLogFileContext context) {
        DetachedReadExecutor executor = executors.get(sessionId);
        if (executor != null && executor.isReading()) {
            executor.getFileContext().update(context);
        }
    }
    
    public void stopReading(String sessionId){
        DetachedReadExecutor executor = executors.get(sessionId);
        if (executor != null) {
            if (executor.isAlive()){
                executor.interrupt();
            }
            executors.remove(sessionId);
        }
    }

}
