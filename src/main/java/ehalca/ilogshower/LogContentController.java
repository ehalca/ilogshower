/**
 * 
 */
package ehalca.ilogshower;

import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.scheduling.SchedulingTaskExecutor;

import ehalca.ilogshower.logfile.LogFile;
import ehalca.ilogshower.logfile.MessageLogFileCriteria;
import ehalca.ilogshower.logfile.RequestSearchLogFileCriteria;
import ehalca.ilogshower.service.FileLogService;
import ehalca.ilogshower.transport.InitLogFileResponse;
import ehalca.ilogshower.utils.LogFileUtilities;
import org.springframework.context.ApplicationEvent;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * @author Hulk
 *
 */
public abstract class LogContentController implements InitializingBean, ApplicationListener<ApplicationEvent>{
	
	
	@Autowired
	private MessageSendingOperations<String> messagingTemplate;
	
	private ReadingTaskExecutor taskExecutor;
        
	
	public void onConnect(MessageLogFileCriteria criteria, SimpMessageHeaderAccessor headerAccessor){
            this.taskExecutor.startReading(headerAccessor.getSessionId(), criteria.toLogFileContext(), this.getFileLogService().getLogFile(criteria));
	}
        
        public void onUpdate(MessageLogFileCriteria criteria, SimpMessageHeaderAccessor headerAccessor){
            this.taskExecutor.updateReading(headerAccessor.getSessionId(), criteria.toLogFileContext());
        }
        
        public void onStop(MessageLogFileCriteria criteria, SimpMessageHeaderAccessor headerAccessor){
            this.taskExecutor.stopReading(headerAccessor.getSessionId());
        }
	
	public InitLogFileResponse initLogFile(Map<String, Object> params){
		RequestSearchLogFileCriteria criteria = new RequestSearchLogFileCriteria(params);
		LogFile file = getFileLogService().getLogFile(criteria);
		String id = LogFileUtilities.getUserIdentifier();
		return new InitLogFileResponse(file.getFile().exists(), LogFileUtilities.getNumberOfLines(file), id);
	}
	
	public abstract FileLogService getFileLogService();
	public abstract SchedulingTaskExecutor getReadExecutor();

	public void afterPropertiesSet() throws Exception {
		if (taskExecutor == null){
			this.taskExecutor = new ReadingTaskExecutor(this.messagingTemplate);
			((ReadingTaskExecutor)this.taskExecutor).setCorePoolSize(5);
			((ReadingTaskExecutor)this.taskExecutor).setMaxPoolSize(10);
			((ReadingTaskExecutor)this.taskExecutor).setQueueCapacity(10);
			((ReadingTaskExecutor)this.taskExecutor).initialize();
		}
	}

        public void onApplicationEvent(ApplicationEvent event) {
            if (event instanceof SessionConnectedEvent){
            }else if (event instanceof SessionDisconnectEvent){
                StompHeaderAccessor headers = StompHeaderAccessor.wrap(((SessionDisconnectEvent)event).getMessage());
               this.onStop(null, headers);
            }
        } 
	
}
