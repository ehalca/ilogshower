/**
 * 
 */
package ehalca.ilogshower;

import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.scheduling.SchedulingTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import ehalca.ilogshower.logfile.LogFile;
import ehalca.ilogshower.logfile.MessageLogFileCriteria;
import ehalca.ilogshower.logfile.RequestSearchLogFileCriteria;
import ehalca.ilogshower.logfile.SearchLogFileCriteria;
import ehalca.ilogshower.service.FileLogService;
import ehalca.ilogshower.transport.InitLogFileResponse;
import ehalca.ilogshower.utils.LogFileUtilities;

/**
 * @author Hulk
 *
 */
public abstract class LogContentController implements InitializingBean, ApplicationListener<BrokerAvailabilityEvent> {
	
	
	@Autowired
	private MessageSendingOperations<String> messagingTemplate;
	
	private SchedulingTaskExecutor taskExecutor;
	
	public void onConnect(MessageLogFileCriteria criteria){
		this.taskExecutor.execute(new DetachedReadExecutor(criteria.toLogFileContext(), this.messagingTemplate, getFileLogService().getLogFile(criteria)));
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
			this.taskExecutor = new ThreadPoolTaskExecutor();
			((ThreadPoolTaskExecutor)this.taskExecutor).setCorePoolSize(5);
			((ThreadPoolTaskExecutor)this.taskExecutor).setMaxPoolSize(10);
			((ThreadPoolTaskExecutor)this.taskExecutor).setQueueCapacity(10);
			((ThreadPoolTaskExecutor)this.taskExecutor).initialize();
		}
	}

	public void onApplicationEvent(BrokerAvailabilityEvent event) {
		System.out.println("Event:" + event.toString());
	}
	
	
}
