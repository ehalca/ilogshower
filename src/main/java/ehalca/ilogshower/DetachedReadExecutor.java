/**
 * 
 */
package ehalca.ilogshower;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.SchedulingTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author Hulk
 *
 */
public class DetachedReadExecutor implements InitializingBean,ReadExecutor {
	
	@Autowired(required=false)
	private SchedulingTaskExecutor taskExecutor;
	
	
	public void afterPropertiesSet() throws Exception {
		if (this.taskExecutor == null){
			this.taskExecutor = new ThreadPoolTaskExecutor();
		}
	}


	public void readLogFile(AbstractLogFileContext context) {
		
	}

}
